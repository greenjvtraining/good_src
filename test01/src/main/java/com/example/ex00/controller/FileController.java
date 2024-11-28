package com.example.ex00.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.ex00.service.FileService;

@Controller
public class FileController {

	@Value("${file.upload-dir}")
	private String uploadDir;
	
	@Autowired
	private FileService fileService;
	
	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("files", fileService.getUploadFiles());
		return "index";
	}
	
	@PostMapping("/upload")
	public String uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
		fileService.saveFile(file, uploadDir);
		return "redirect:/";
	}
}
