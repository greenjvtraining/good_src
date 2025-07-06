package com.example.ai_ollama.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ai_ollama.dto.BbsDto;
import com.example.ai_ollama.dto.RequestBbsDto;
import com.example.ai_ollama.entity.BbsEntity;
import com.example.ai_ollama.repository.BbsRepository;

@Service
public class BbsService {

	@Autowired
	private BbsRepository bbsRepository;
	
	public BbsEntity regist(RequestBbsDto dto) {
		BbsEntity entity = new BbsEntity();
		entity.requestDtoToEntity(dto);
		
		return bbsRepository.save(entity);
	}

	public List<BbsDto> getList() {
		List<BbsEntity> entities = bbsRepository.findAll();
		
		List<BbsDto> list = new ArrayList<>();
		for(BbsEntity entity : entities) {
			BbsDto dto = entity.entityToDto();
			list.add(dto);
		}
		
		return list;
	}
	
	public BbsDto getBbs(int bno) {
		Optional<BbsEntity> entity = bbsRepository.findById(bno);
		BbsDto bbs = new BbsDto();
		if(entity.isPresent()) {
			bbs = entity.get().entityToDto();
		}
		
		return bbs;
	}
	
}
