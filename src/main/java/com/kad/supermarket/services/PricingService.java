package com.kad.supermarket.services;

import com.kad.supermarket.data.ProductOfferData;
import com.kad.supermarket.model.Cart;
import com.kad.supermarket.model.Product;
import com.kad.supermarket.model.ProductOffer;

import java.util.*;

/**
 * Cart Manager class
 */
public class PricingService {

	/**
	 * Method to calculate the price of the products in the given cart
	 * @param cart
	 * @return
	 */
	public double calculateTotalPrice(Cart cart){
		Double priceToPay = 0.0;

		TreeMap<Product, Integer> productsListMap = cart.getProductList();

		// Iterate through the list
		Set<Product> keys = productsListMap.keySet();
		   for (Iterator<Product> i = keys.iterator(); i.hasNext();) {
			   Product item = i.next();
		     Integer count = productsListMap.get(item);

			// In case there is an offer for this product
		     if(ProductOfferData.itemOfferMap.containsKey(item.name)){
		    	 ProductOffer thisOffer = ProductOfferData.itemOfferMap.get(item.name);
		    	 
		    	 // case if the count of the products exceeds offer count
		    	 if(thisOffer.count < count)
		    	 {
		    		 priceToPay += (count % thisOffer.count)* item.originalPrice
							 + (count / thisOffer.count) * thisOffer.price;
		    	 }
		    	 else if(thisOffer.count == count)
		    	 {
		    		 priceToPay += thisOffer.price;
		    	 }
		    	 else
		    	 {
		    		 priceToPay += (count)* item.originalPrice;
		    	 }
		     }
			 //no offer,
		     else
		     {
		    	 priceToPay += (count)* item.originalPrice;
		     }
		   }

		return priceToPay;
	}
}
