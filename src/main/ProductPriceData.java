package main;
import java.util.*;

// Map to hold static product's prices data
public class ProductPriceData {
	public static TreeMap<String, Double> itemPriceMap = new TreeMap<String, Double>();
	
	static{
		itemPriceMap.put("ProductA", 70.0);
		itemPriceMap.put("ProductB", 30.0);
		itemPriceMap.put("ProductC", 20.0);
		itemPriceMap.put("ProductD", 15.0);
	}
}
