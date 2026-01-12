package com.example.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "groceries_g")
public class Grocery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "item", nullable = false)
    private String item;
    private String category;
    private Integer quantity;
    private Double price;
    private Double discount;
    private Double last;

    
    public Grocery() {}

    public Grocery(String item, String category, Integer quantity, Double price, Double discount, Double last) {
        this.item = item;
        this.category = category;
        this.quantity = quantity;
        this.price = price;
        this.discount = discount;
        this.last = last;
    }


    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getItem() { return item; }
    public void setItem(String item) { this.item = item; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }

    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }

    public Double getDiscount() { return discount; }
    public void setDiscount(Double discount) { this.discount = discount; }

    public Double getLast() { return last; }
    public void setLast(Double last) { this.last = last; }
}
