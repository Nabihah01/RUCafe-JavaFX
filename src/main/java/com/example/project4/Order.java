package com.example.project4;

import java.util.ArrayList;

/**
 *  An instance of this class has a unique order number
 *  and keeps the list of menu items added by the user.
 *  This class must implement the Customizable interface above,
 *  to provide the behavior of adding and removing menu items
 */
public class Order implements Customizable{
    private ArrayList<MenuItem> orders;
    private int orderNumber;
    public Order(ArrayList<MenuItem> orders, int orderNumber){
        this.orders = orders;
        this.orderNumber = orderNumber;
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

    @Override
    public String toString(){
        return orders.toString();
    }

    public static void main(String [] args){
        ArrayList <String> addIns = new ArrayList<>();
        Coffee coffee = new Coffee("Tall",addIns, 1);
        Donut donut = new Donut("yeast", "chocolate", 2);
        ArrayList<MenuItem> orders = new ArrayList<>();
        orders.add(coffee);
        System.out.println(orders);
        orders.add(donut);
        System.out.println(orders);


    }
}
