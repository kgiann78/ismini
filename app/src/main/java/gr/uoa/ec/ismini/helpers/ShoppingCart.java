package gr.uoa.ec.ismini.helpers;

import gr.uoa.ec.ismini.models.Product;
import gr.uoa.ec.ismini.models.ShoppingCartItem;

import java.util.*;

public class ShoppingCart {
    private static Map<Integer, ShoppingCartItem> shoppingCart = new HashMap<>();

    public static void add(Product product) {
        if (!shoppingCart.containsKey(product.getKey())) {
            shoppingCart.put(product.getKey(), new ShoppingCartItem(product, 1));
        }
        else {
            ShoppingCartItem shoppingCartItem = shoppingCart.get(product.getKey());
            shoppingCartItem.setAmount(shoppingCartItem.getAmount() + 1);
        }
    }

    public static ShoppingCartItem[] getShoppingCart() {
        return shoppingCart.values().toArray(new ShoppingCartItem[shoppingCart.size()]);
    }
}
