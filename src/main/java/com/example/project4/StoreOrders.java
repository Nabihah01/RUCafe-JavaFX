package com.example.project4;

import java.util.ArrayList;

/**
 * An instance of this class keeps a list of orders placed by the user.
 * This class must implement the Customizable interface above to provide the behavior of adding and removing orders
 */
public class StoreOrders implements Customizable {
    private ArrayList<Order> storeOrders;

    public StoreOrders(){
        this.storeOrders = new ArrayList<Order>();
    }

    public ArrayList<Order> getStoreOrdersArray() {
        return storeOrders;
    }

    @Override
    public boolean add(Object obj) {
        if(obj instanceof Order){
            Order order = (Order) obj;
            storeOrders.add(order);
            return true;
        }
        return false;
    }

    @Override
    public boolean remove(Object obj) {
        if(obj instanceof Order){
            Order order = (Order) obj;
            storeOrders.remove(order);
            return true;
        }
        return false;
    }

}
