package com.kad.supermarket.model;
//TODO use of lombok for a better code visibility

/**
 * ProductOffer model class
 */
public class ProductOffer {
	public Product product;
	public Integer count;
	public Double price;
	
	public ProductOffer(Product product, Integer count, Double price){
		this.product = product;
		this.count = count;
		this.price = price;
	}
}
