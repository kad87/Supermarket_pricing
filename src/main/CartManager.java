package main;
import java.util.*;

/**
 * Cart Manager class
 */
public class CartManager {
	public static TreeMap<String, Integer> groceriesList = new TreeMap<String, Integer>();

	/**
	 * add item to cart
	 * @param product
	 * @param count
	 */
	public static void addProduct(String product, Integer count){
		//First product of its kind
		if(!groceriesList.containsKey(product))
			groceriesList.put(product, count);
		else
		{
			//update count if not the first product
			groceriesList.put(product, count + groceriesList.get(product));
		}
	}

	/**
	 *
	 * @param item
	 * @param count
	 * @throws Exception
	 */
	public static void removeProduct(String item, Integer count) throws Exception{

		if(!groceriesList.containsKey(item))
			throw new Exception("Product does not exist in your cart");

		// remove the given count, and update the list, 
		// if count == number of products in cart then remove the product from list 
		// if count > number of products in cart then thrw exception
		if(groceriesList.get(item) == count)
			groceriesList.remove(item);
		else if(groceriesList.get(item) > count)
			groceriesList.put(item, groceriesList.get(item) - count);
		else
			throw new Exception("count for  product exceeds the count in the cart");
	}
	
	/**
	 * Prints all the cart contents
	 * @throws Exception
	 */
	public static void reviewCart() throws Exception
	{
		if(groceriesList.isEmpty())
			throw new Exception("Your cart is empty");
		
		// Iterate through the cart and display the items with their quantities
		Set<String> productsInCart = groceriesList.keySet();
		for(Iterator<String> i = productsInCart.iterator(); i.hasNext();){
			String item = i.next();
			Integer count = groceriesList.get(item);
			System.out.println("Item: "+ item + " of Count: " + count);
		}
	}
	
	
	// Method to empty the cart
	public static void emptyCart(){
		groceriesList.clear();
	}

	/**
	 * Method to calculate the price of the cart
	 * @param productsListMap
	 * @return
	 */
	public static double calculateTotalPrice(TreeMap<String, Integer> productsListMap){
		Double priceToPay = 0.0;
		
		// Iterate through the list
		Set<String> keys = productsListMap.keySet();
		   for (Iterator<String> i = keys.iterator(); i.hasNext();) {
		     String item = i.next();
		     Integer count = productsListMap.get(item);
		     
			// In case there is an offer for this product
		     if(ProductOfferData.itemOfferMap.containsKey(item)){
		    	 ProductOffer thisOffer = ProductOfferData.itemOfferMap.get(item);
		    	 
		    	 // case if the count of the products exceeds offer count
		    	 if(thisOffer.count < count)
		    	 {
		    		 priceToPay += (count % thisOffer.count)* ProductPriceData.itemPriceMap.get(item)
							 + (count / thisOffer.count) * thisOffer.price;
		    	 }
		    	 else if(thisOffer.count == count)
		    	 {
		    		 priceToPay += thisOffer.price;
		    	 }
		    	 else
		    	 {
		    		 priceToPay += (count)* ProductPriceData.itemPriceMap.get(item);
		    	 }
		     }
			 //no offer,
		     else
		     {
		    	 priceToPay += (count)* ProductPriceData.itemPriceMap.get(item);
		     }
		   }
		
		return priceToPay;
	}
}
