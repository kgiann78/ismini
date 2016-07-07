package gr.uoa.ec.ismini.shoppingList;

import gr.uoa.ec.ismini.models.Product;
import gr.uoa.ec.ismini.models.ShoppingListItem;

import java.util.*;

public class DummyShoppingList {
    private static Map<Integer, ShoppingListItem> shoppingCart = new HashMap<>();

    public static void add(Product product) {
        if (!shoppingCart.containsKey(product.getKey())) {
            shoppingCart.put(product.getKey(), new ShoppingListItem(product, 1));
        }
        else {
            ShoppingListItem shoppingListItem = shoppingCart.get(product.getKey());
            shoppingListItem.setAmount(shoppingListItem.getAmount() + 1);
        }
    }

    public static ShoppingListItem[] getShoppingCart() {
        return shoppingCart.values().toArray(new ShoppingListItem[shoppingCart.size()]);
    }
}
