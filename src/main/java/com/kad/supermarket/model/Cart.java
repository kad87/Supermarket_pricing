package com.kad.supermarket.model;

import com.kad.supermarket.exceptions.EmptyCartException;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeMap;

//TODO use of lombok for a better code visibility
/**
 * Cart model class
 */
public class Cart {
    /**
     * Treemap structure for product list
     */
    private TreeMap<Product, Integer> productList = new TreeMap<Product, Integer>();


    public TreeMap<Product, Integer> getProductList() {
        return productList;
    }

    public void setProductList(TreeMap<Product, Integer> productList) {
        this.productList = productList;
    }

    /**
     * add item to cart
     * @param product
     * @param count
     */
    public void addProduct(Product product, Integer count){
        //First product of its kind
        if(!productList.containsKey(product))
            productList.put(product, count);
        else
        {
            //update count if not the first product
            productList.put(product, count + productList.get(product));
        }
    }

    /**
     *
     * @param item
     * @param count
     * @throws Exception
     */
    public void removeProduct(Product item, Integer count) throws Exception{

        if(!productList.containsKey(item))
            throw new Exception("Product does not exist in your cart");

        // remove the given count, and update the list,
        // if count == number of products in cart then remove the product from list
        // if count > number of products in cart then thrw exception
        if(productList.get(item) == count)
            productList.remove(item);
        else if(productList.get(item) > count)
            productList.put(item, productList.get(item) - count);
        else
            throw new Exception("count for  product exceeds the count in the cart");
    }

    /**
     * Prints all the cart contents
     * @throws Exception
     */
    public void reviewCart() throws EmptyCartException
    {
        if(productList.isEmpty())
            throw new EmptyCartException("Your cart is empty");

        // Iterate through the cart and display the items with their quantities
        Set<Product> productsInCart = productList.keySet();
        for(Iterator<Product> i = productsInCart.iterator(); i.hasNext();){
            Product item = i.next();
            Integer count = productList.get(item);
            System.out.println("Item: "+ item + " of Count: " + count);
        }
    }

    /**
     * Method to empty the cart
      */

    public void emptyCart(){
        productList.clear();
    }

}
