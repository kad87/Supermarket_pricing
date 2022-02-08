package tests;
import main.*;

//TODO Use a more dynamic input for tests (text files, h2 database ...)
//TODO Use Unit tests framework for this
public class programTest {
	/**
	 *
	 * @param args
	 */
	public static void main(String[] args){
		// For 4 * A, 1 * B and 4 * C
		// Expected price 340
		CartManager ck = new CartManager();
		ck.addProduct("ProductA", 4);
		ck.addProduct("ProductB", 1);
		ck.addProduct("ProductC", 4);
		if(ck.calculateTotalPrice(ck.groceriesList) == 340.0) System.out.println("Passed");
		else System.out.println("Failed | " + ck.calculateTotalPrice(ck.groceriesList) + "!= 340.0");
		
		// For 1 * B and 4 * C
		// Expected price 110
		try {
			//removing previously added productsA
			ck.removeProduct("ProductA", 4);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(ck.calculateTotalPrice(ck.groceriesList) == 110.0) System.out.println("Passed");
		else System.out.println("Failed | " + ck.calculateTotalPrice(ck.groceriesList) + "!= 110.0");
	}
}
