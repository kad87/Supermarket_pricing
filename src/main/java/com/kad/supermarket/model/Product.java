package com.kad.supermarket.model;

//TODO use of lombok for a better code visibility
/**
 * Product model class
 */
public class Product implements Comparable<Product>{
    public String name;
    public Double originalPrice;

    public Product(String name, Double price) {
        this.name = name;
        this.originalPrice = price;
    }

    @Override
    public int compareTo(Product o) {
        return o.name.compareTo(this.name);
    }
}
