package gr.uoa.ec.ismini.models;

public class ShoppingListItem {
    private Product product;
    private int amount;

    public ShoppingListItem(Product product, int amount) {
        this.product = product;
        this.amount = amount;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "{" +
                "product=" + product +
                ", amount=" + amount +
                '}';
    }
}
