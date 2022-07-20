package com.example.item_management.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "t_item")
public class Item {
    @Id
    private String id;

    private String nama;

    private String deskripsi;

    private int price;

    private String img;
}
