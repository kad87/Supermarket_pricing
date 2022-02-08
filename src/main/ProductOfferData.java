package main;
import java.util.*;

// Class containing static data for the offers on products
public class ProductOfferData {
	public static TreeMap<String, ProductOffer> itemOfferMap = new TreeMap<String, ProductOffer>();
	
	static{
		itemOfferMap.put("ProductA", new ProductOffer("ProductA", 3, 160.0));
		itemOfferMap.put("ProductB", new ProductOffer("ProductB", 2, 45.0));
	}
	
	
}
