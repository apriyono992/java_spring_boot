package com.example.item_management.repository;

import com.example.item_management.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item,String> {
    List<Item> findByNamaContaining(String nama);
}
