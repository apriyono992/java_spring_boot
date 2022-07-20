package com.example.item_management.controller;

import com.example.item_management.model.Item;
import com.example.item_management.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/item")
public class ItemController {
    @Autowired private ItemRepository itemRepository;

    @GetMapping("/test-api")
    public String allAccess() {
        return "Test API.";
    }

    @PostMapping("/create")
    public ResponseEntity<Item> create(
            @RequestBody Item item) {
        try {
            Item member = new Item();
            member.setId(UUID.randomUUID().toString());
            member.setNama(item.getNama());
            member.setPrice(item.getPrice());
            member.setDeskripsi(item.getDeskripsi());
            member.setImg(item.getImg());
            return new ResponseEntity<>(itemRepository.save(member), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Item> update(
            @PathVariable("id") String id,
            @RequestBody Item item) {
        try {
            Item member = itemRepository.findById(id).get();
            member.setNama(item.getNama());
            member.setPrice(item.getPrice());
            member.setDeskripsi(item.getDeskripsi());
            member.setImg(item.getImg());
            return new ResponseEntity<>(itemRepository.save(member), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<Item>> findAll() {
        return new ResponseEntity<>(itemRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Item> findById(
            @PathVariable("id") String id) {
        Optional<Item> item = itemRepository.findById(id);
        return item.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/delete/{id}")
    public String delete(
            @PathVariable("id") String id) {
        try {
            itemRepository.deleteById(id);
            return "Successfully deleted";
        } catch (Exception e) {
            return "Failed to delete";
        }
    }



}
