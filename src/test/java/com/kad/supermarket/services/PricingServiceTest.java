package com.kad.supermarket.services;

import com.kad.supermarket.data.ProductOfferData;
import com.kad.supermarket.model.Cart;
import com.kad.supermarket.model.Product;
import com.kad.supermarket.model.ProductOffer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.TreeMap;

class PricingServiceTest {

    private final Double EXPECTED_TOTAL_PRICE_FOR_EMPTY_CART = 0.0;
    private final Double EXPECTED_TOTAL_PRICE_FOR_CART1 = 340.0;
    private final Double EXPECTED_TOTAL_PRICE_FOR_CART2 = 110.0;
    private final Double EXPECTED_PRICE_FOR_CART_WITHOUT_DISCOUNT = 95.0;

    private PricingService pricingService = new PricingService();

    private TreeMap<String, ProductOffer> itemOfferMap = new TreeMap<String, ProductOffer>();
    private ProductOfferData productOfferData = new ProductOfferData();

    private TreeMap<String, Double> priceData = new TreeMap<>();

    private Cart emptyCart = new Cart();
    private Cart cartWithoutDiscount = new Cart();
    private Cart cartWithDiscount1 = new Cart();
    private Cart cartWithDiscount2 = new Cart();

    @BeforeEach
    void setUp() {
        //setup products with their initial price
        Product productA = new Product("ProductA", 70.0);
        Product productB = new Product("ProductB", 30.0);
        Product productC = new Product("ProductC", 20.0);
        Product productD = new Product("ProductDDDD", 15.0);

        //setup offer lists for the products
        productOfferData.itemOfferMap.put("ProductA", new ProductOffer(productA,3, 160.0));
        productOfferData.itemOfferMap.put("ProductB", new ProductOffer(productB,2, 45.0));

        //Setup multiple carts to test later
        // For 4 * A, 1 * B and 4 * C
        // Expected price 340
        cartWithDiscount1.getProductList().put(productA,4 );
        cartWithDiscount1.getProductList().put(productB,1 );
        cartWithDiscount1.getProductList().put(productC,4 );

        // For 1 * B and 4 * C
        // Expected price 110
        cartWithDiscount2.getProductList().put(productB, 1);
        cartWithDiscount2.getProductList().put(productC, 4);

        //for 4 * C and 1 * D
        //Expected price 95
        cartWithoutDiscount.getProductList().put(productC, 4);
        cartWithoutDiscount.getProductList().put(productD, 1);
    }

    @AfterEach
    void tearDown() {

    }

    @Test
    void calculateTotalPriceForEmptyCart() {
        Assertions.assertEquals(EXPECTED_TOTAL_PRICE_FOR_EMPTY_CART,
                pricingService.calculateTotalPrice(emptyCart),
                "the cart is empty, the price = 0");
    }

    @Test
    void calculateTotalPriceWithoutDiscount() {
        Assertions.assertEquals(EXPECTED_PRICE_FOR_CART_WITHOUT_DISCOUNT,
                pricingService.calculateTotalPrice(cartWithoutDiscount),
                "expected total price : " + EXPECTED_PRICE_FOR_CART_WITHOUT_DISCOUNT);
    }

    @Test
    void calculateTotalPriceWithDiscount1() {
        Assertions.assertEquals(EXPECTED_TOTAL_PRICE_FOR_CART1,
                pricingService.calculateTotalPrice(cartWithDiscount1),
                "expected total price : " + EXPECTED_TOTAL_PRICE_FOR_CART1);
    }

    @Test
    void calculateTotalPriceWithDiscount2() {
        Assertions.assertEquals(EXPECTED_TOTAL_PRICE_FOR_CART2,
                pricingService.calculateTotalPrice(cartWithDiscount2),
                "expected total price : " + EXPECTED_TOTAL_PRICE_FOR_CART2);
    }
}