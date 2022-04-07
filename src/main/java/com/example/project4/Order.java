package com.example.project4;

import java.util.ArrayList;

// this is for one individual order. list of all items user ordered and the order number for the user.
/**
 *  An instance of this class has a unique order number
 *  and keeps the list of menu items added by the user.
 *  This class must implement the Customizable interface above,
 *  to provide the behavior of adding and removing menu items
 */
public class Order implements Customizable {
    //should we add orderTotal?
    private ArrayList<MenuItem> orders;
    private int orderNumber;
    private double total;
    private double salesTax;
    private double subTotal;

    public Order(ArrayList<MenuItem> orders, int orderNumber){
        this.orders = orders;
        this.orderNumber = orderNumber;
        this.total = 0;
    }

    @Override
    public boolean add(Object obj) {
        if(obj instanceof MenuItem){
            MenuItem item = (MenuItem) obj;
            orders.add(item);
            return true;
        }
        return false;
    }

    @Override
    public boolean remove(Object obj) {
        if(obj instanceof MenuItem){
            MenuItem item = (MenuItem) obj;
            orders.remove(item);
            return true;
        }
        return false;
    }

    public ArrayList<MenuItem> getOrders() {
        return orders;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getTotal() {
        double total = 0;
        for(int i = 0; i < orders.size(); i++) {
            total += orders.get(i).itemPrice();
        }
        return total;
    }

    @Override
    public String toString(){
        StringBuilder userOrders = new StringBuilder();
        for(int i = 0; i < orders.size(); i++){
            userOrders.append(orders.get(i).toString()).append(System.lineSeparator());
        }
        return userOrders.toString();
    }

    public static void main(String [] args){
        ArrayList <String> addIns = new ArrayList<>();
        Coffee coffee = new Coffee("Tall",addIns, 1);
        Donut donut = new Donut("yeast", "chocolate", 2);
        ArrayList<MenuItem> orders = new ArrayList<>();
        Order order = new Order(orders, 1);
        orders.add(coffee);
        System.out.println(orders);
        orders.add(donut);
        System.out.println(order.toString());


    }
}
