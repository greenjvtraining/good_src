package com.example.ex00.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileService {

	@Value("${file.upload-dir}")
	private String uploadDir;
	
	public void saveFile(MultipartFile file, String uploadDir) throws IOException {
		Path uploadPath = Paths.get(uploadDir);
		
		if(!Files.exists(uploadPath)) {
			Files.createDirectories(uploadPath);
		}
		
		file.transferTo(new File(uploadDir + "/" + file.getOriginalFilename()));
	}
	
	public List<String> getUploadFiles(){
		Path uploadPath = Paths.get(uploadDir);
		if(!Files.exists(uploadPath)) {
			return List.of();
		}
		
		try {
			return Files.list(uploadPath)
					    .map(Path::getFileName)
					    .map(Path::toString)
					    .toList();
		} catch (IOException e) {
			throw new RuntimeException("파일목록을 불러올 수 없습니다.", e);
		}
	}
}
