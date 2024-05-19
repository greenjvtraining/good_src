package com.example.prj.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.prj.dao.BoardMapper;
import com.example.prj.dto.Board;
import com.example.prj.dto.BoardDto;

import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;

@Slf4j
@Controller
@RequestMapping("/board")
public class BoardController {

	@Autowired
	private BoardMapper boardMapper;

	@Value("${spring.servlet.multipart.location}")
	private String uploadPath;

	@RequestMapping("/regist")
	public String registProc(Board board) {
		BoardDto boardDto = new BoardDto();
		boardDto.setComment(board.getComment());
		String originName = board.getFileName();
		boardDto.setOriginName(originName);

		// BoardDto boardDto = BoardDto.of(board);
		log.info(originName);

		String newName = UUID.randomUUID().toString() + "_" + originName;
		log.info(newName);
		boardDto.setNewName(newName);

		// 파일 저장
		File file = new File(newName);

		try {
			board.getFile().transferTo(file);
			log.info("파일업로드 성공");

			// 썸네일 생성
			String thumbnailSaveName = "s_" + newName;
			boardDto.setThumbnailName(thumbnailSaveName);

			File thumbnailFile = new File(uploadPath + thumbnailSaveName);
			File ufile = new File(uploadPath + newName);
			// Thumbnailator.createThumbnail(file, thumbnailFile, 100, 100);
			Thumbnails.of(ufile).size(100, 100).toFile(thumbnailFile);
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		boardMapper.insert(boardDto);
		return "redirect:/board/result";
	}

	@RequestMapping("/regist1")
	public @ResponseBody String regist1(Board board) {
		BoardDto boardDto = new BoardDto();
		boardDto.setComment(board.getComment());
		String originName = board.getFileName();
		boardDto.setOriginName(originName);

		// BoardDto boardDto = BoardDto.of(board);
		log.info(originName);

		String newName = UUID.randomUUID().toString() + "_" + originName;
		log.info(newName);
		boardDto.setNewName(newName);

		// 파일 저장
		File file = new File(newName);

		try {
			board.getFile().transferTo(file);
			log.info("파일업로드 성공");

			// 썸네일 생성
			String thumbnailSaveName = "s_" + newName;
			boardDto.setThumbnailName(thumbnailSaveName);

			File thumbnailFile = new File(uploadPath + thumbnailSaveName);
			File ufile = new File(uploadPath + newName);
			// Thumbnailator.createThumbnail(file, thumbnailFile, 100, 100);
			Thumbnails.of(ufile).size(100, 100).toFile(thumbnailFile);
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		boardMapper.insert(boardDto);

		return "ok";
	}

	@RequestMapping("/result")
	public String result(Model model) {
		log.info("result.....");
		List<BoardDto> list = boardMapper.getList();
		log.info("list : " + list);
		model.addAttribute("list", list);
		return "result";
	}

	@RequestMapping("/detail")
	public String view(@RequestParam("bno") int bno, Model model) {
		BoardDto boardDto = boardMapper.getBoard(bno);
		model.addAttribute("board", boardDto);

		return "view";
	}

	@RequestMapping("/download1")
	public void download1(@RequestParam("bno") int bno, HttpServletResponse response) {
		BoardDto boardDto = boardMapper.getBoard(bno);
		File file = new File(uploadPath, boardDto.getNewName());
		try {
			InputStream is = new FileInputStream(file);
			response.setContentType("application/octec-stream");
			response.setHeader("Content-Disposition", "attachment; filename=\"" + boardDto.getOriginName() + "\"");
			response.setHeader("Content-Length", "" + file.length());

			OutputStream os = response.getOutputStream();

			byte[] b = new byte[(int) file.length()];
			int readBuffer = 0;

			while ((readBuffer = is.read(b)) > 0) {
				os.write(b, 0, readBuffer);
			}

			is.close();
			os.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@RequestMapping("/download")
	public ResponseEntity<InputStreamResource> download(@RequestParam("bno") int bno) {
		BoardDto boardDto = boardMapper.getBoard(bno);
		File file = new File(uploadPath, boardDto.getNewName());
		try {
			InputStream is = new FileInputStream(file);
			// InputStreamResource는 InputStream을 래핑하는 클래스로, Spring이 이를 사용하여 파일 데이터를 클라이언트로
			// 스트리밍합니다.
			InputStreamResource resource = new InputStreamResource(is);

			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
			headers.setContentDispositionFormData("attachment", boardDto.getOriginName());
			headers.setContentLength(file.length());

			return new ResponseEntity<>(resource, headers, HttpStatus.OK);
			// response.setContentType("application/octec-stream");
			// response.setHeader("Content-Disposition", "attachment; filename=\"" +
			// boardDto.getOriginName() + "\"");
			// response.setHeader("Content-Length", "" + file.length());

			/*
			 * OutputStream os = response.getOutputStream();
			 * 
			 * byte[] b = new byte[(int)file.length()]; int readBuffer = 0;
			 * 
			 * while((readBuffer = is.read(b)) > 0) { os.write(b, 0, readBuffer); }
			 * 
			 * is.close(); os.close();
			 */
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (IOException e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
}
