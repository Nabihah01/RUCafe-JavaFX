package com.example.project4;

/**
 *  An instance of this class has a unique order number
 *  and keeps the list of menu items added by the user.
 *  This class must implement the Customizable interface above,
 *  to provide the behavior of adding and removing menu items
 */
public class Order implements Customizable{
    @Override
    public boolean add(Object obj) {
        return false;
    }

    @Override
    public boolean remove(Object obj) {
        return false;
    }
}
