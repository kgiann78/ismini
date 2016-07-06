package gr.uoa.ec.ismini.helpers;

import gr.uoa.ec.ismini.models.Category;
import gr.uoa.ec.ismini.models.Product;


public class ProductsDummy {
    private static Category[] categories = new Category[] {
            new Category("Σαλάτες", 1, "Salads"),
            new Category("Ζυμαρικά", 2, "Pasta"),
            new Category("Ζεστά πιάτα", 3, "Hot Dishes"),
            new Category("Σαντουιτς", 4, "Sandwitch"),
            new Category("Γλυκά & σοκολάτες", 5, "Sweets"),
            new Category("Ποτά & αναψυκτικά", 6, "Beverages")
    };

    private static Product[] products = new Product[] {
            new Product("Caesars Salad", "Σαλάτα Καίσαρα", 1, categories[0] , 5.0, 5.0),
            new Product("Greek Salad", "Χωριάτικη", 2, categories[0], 5.0, 5.0),
            new Product("Chef's Salad", "Σαλάτα του Σέφ", 3, categories[0], 5.0, 5.0),
            new Product("Spaghetti Bolonese", "Μακαρόνια με κιμά", 4, categories[1], 5.0, 5.0),
            new Product("Carbonara", "Καρμπονάρα", 5, categories[1], 5.0, 5.0),
            new Product("Tagliatelle al tono", "Ταλιατέλες με τόνο", 6, categories[1], 5.0, 5.0),
            new Product("Bisteca", "Μπιφτέκι", 7, categories[2], 5.0, 5.0),
            new Product("Steak", "Μπριζόλα", 8, categories[2], 5.0, 5.0),
            new Product("Ham-Cheese sandwitch", "Σάντουιτς ζαμπον-τυρί", 9, categories[3], 5.0, 5.0),
            new Product("Turkey-cheese sandwitch", "Σάντουιτς Γαλοπούλα-τυρί", 10, categories[3], 5.0, 5.0),
            new Product("Mars", "Mars", 11, categories[4], 5.0, 5.0),
            new Product("ION with almonds", "ΙΟΝ αμυγδάλου", 12, categories[4], 5.0, 5.0),
            new Product("Coca Cola Zero", "Coca Cola Zero", 13, categories[5], 5.0, 5.0),
            new Product("Coca Cola", "Coca Cola", 14, categories[5], 5.0, 5.0),
            new Product("Water", "Νερό", 15, categories[5], 5.0, 5.0),
            new Product("Orange Juice", "Χυμός Πορτοκάλι", 16, categories[5], 5.0, 5.0)
    };

    public static Category[] getCategories() {
        return categories;
    }

    public static Category getCategory(int id) {
        return categories[id];
    }

    public static Product[] getProducts() {
        return products;
    }

    public static Product getProduct(int id) {
        return products[id];
    }
}
