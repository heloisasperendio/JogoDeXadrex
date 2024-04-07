package org.example;

public class OrderItem {
    private Integer quantity;
    private double price;
    private Product product;

    public OrderItem() {

}
    public OrderItem(Integer quantity, double price,Product product) {
        this.quantity = quantity;
        this.price = price;
        this.product = product;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    public double subTotal() {
        return price * quantity;
    }

    @Override
    public String toString() {
        return getProduct().getName() + ", $" + price + ", Quantity: " + quantity + ", Subtotal: $" + subTotal();
    }
}
