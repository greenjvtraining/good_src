package com.example.demo.service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.demo.dto.MemoDto;
import com.example.demo.entity.Member;
import com.example.demo.entity.Memo;
import com.example.demo.repository.MemoRepository;

import net.coobird.thumbnailator.Thumbnails;

@Service
public class MemoService {
	
	@Value("${spring.servlet.multipart.location}")
	private String uploadPath;
	
	@Autowired
	private MemoRepository memoRepository;
	
	public void regMemo(MemoDto memo) {
		Memo entity = new Memo();
		Member member = new Member();
		member.setUsername(memo.getWriter());
		entity.setMember(member);
		entity.setTitle(memo.getTitle());
		entity.setContent(memo.getContent());
		entity.setOriginalFilename(memo.getPhoto().getOriginalFilename());
		entity.setNewFilename(UUID.randomUUID().toString() + "_" + entity.getOriginalFilename() );
		entity.setThumbnailFilename("s_" + entity.getNewFilename());
		
		memoRepository.save(entity);
		
		//파일저장
		File file = new File(entity.getNewFilename());
		
		try {
			memo.getPhoto().transferTo(file);
			
			File thumbnailFile = new File(uploadPath + entity.getThumbnailFilename());
			File ufile = new File(uploadPath + entity.getNewFilename());
			
			Thumbnails.of(ufile).size(100, 100).toFile(thumbnailFile);
			
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public List<Memo> getList(){
		List<Memo> list = memoRepository.findAll();
		
		return list;
	}
}
