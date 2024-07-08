package com.example.test.controller;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.test.dto.BoardDto;

import net.coobird.thumbnailator.Thumbnails;

@Controller
public class MyController {

	@Value("${spring.servlet.multipart.location}")
	private String uploadPath;
	
	@RequestMapping({"/", "/main"})
	public String root() {
		return "main";
	}
	
	@RequestMapping("/regist")
	public String regist() {
		return "regist";
	}
	
	@PostMapping("/registProc")
	public @ResponseBody String registProc(BoardDto board) {
		System.out.println("registProc.............");
		
		String originName = board.getFileName();

		System.out.println(originName);

		String newName = UUID.randomUUID().toString() + "_" + originName;
		System.out.println(newName);

		// 파일 저장
		File file = new File(newName);

		try {
			board.getImgfile().transferTo(file);
			System.out.println("파일업로드 성공");

			// 썸네일 생성
			String thumbnailSaveName = "s_" + newName;

			File thumbnailFile = new File(uploadPath + thumbnailSaveName);
			File ufile = new File(uploadPath + newName);
			// Thumbnailator.createThumbnail(file, thumbnailFile, 100, 100);
			Thumbnails.of(ufile).size(100, 100).toFile(thumbnailFile);
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return newName;
	}
}
