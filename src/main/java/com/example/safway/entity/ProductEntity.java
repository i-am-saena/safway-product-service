package com.example.safway.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "ITEM_DETAILS")
@AllArgsConstructor
@NoArgsConstructor
public class ProductEntity {
    @Id
    @GeneratedValue()
    @Column(name = "item_id")
    private Integer id;
    private String name;
    private Double price;
    private String category;
}
