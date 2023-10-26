package sample.shopping;

public class OrderDTO {

    private int orderID;
    private String orderDate;
    private int userID;
    private double total;

    public OrderDTO() {
    }

    public OrderDTO(int orderID, String orderDate, int userID, double total) {
        this.orderID = orderID;
        this.orderDate = orderDate;
        this.userID = userID;
        this.total = total;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

}
