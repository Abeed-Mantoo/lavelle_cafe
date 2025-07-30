package com.cafe.cafe.controller;

import com.cafe.cafe.model.Item;
import com.cafe.cafe.repository.ItemRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")  // allow frontend calls
@RestController
@RequestMapping("/api/items")
public class ItemController {

    private final ItemRepository itemRepository;

    public ItemController(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @GetMapping
    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }
    
    @GetMapping
    public List<Item> getfewItems() {
        return itemRepository.findAll();
    }

}
