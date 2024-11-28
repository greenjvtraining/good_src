package com.example.ex00.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.stream.Stream;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class BackupService {
	
	private final String uploadDir = "C:/uploads";
	private final String backupDir = "C:/backup";
	
	@Scheduled(cron = "0 48 11 * * ?")
	public void backupFiles() {
		try(Stream<Path> files = Files.list(Paths.get(uploadDir))){
			files.forEach(file -> {
				try {
					Path backupPath = Paths.get(backupDir);
					if(!Files.exists(backupPath)) {
						Files.createDirectories(backupPath);
					}
					Files.move(file, backupPath.resolve(file.getFileName()), StandardCopyOption.REPLACE_EXISTING);
					System.out.println("백업 완료 : " + file.getFileName());
					
				}catch(IOException e) {
					System.err.println("파일 백업 실패 : " + file.getFileName());
				}
			});
		}catch(IOException e) {
			System.err.println("백업 디렉토리 탐색 실패...");
		}
	}
}
