package com.example.ai_pgv.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ai_pgv.entity.Item;
import com.example.ai_pgv.repository.ItemRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ItemController {

    private final ItemRepository itemRepository;

    @PostMapping("/add")
    public String add() {
    	System.out.println("add........");
        Item item = new Item();
        item.setEmbedding(new float[]{1.0f, 2.0f, 3.0f});
        itemRepository.save(item);
        return "저장 완료";
    }

    @GetMapping("/list")
    public List<Item> list() {
    	System.out.println("list........");
        return itemRepository.findAll();
    }
}
