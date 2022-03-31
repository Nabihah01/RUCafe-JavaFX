package com.example.project4;

/**
 * This is the superclass of all menu items, such as donuts and coffee.
 * Any class defined for a menu item must extend this class
 */
public abstract class MenuItem {
    public MenuItem(){
    }
    public abstract double itemPrice();
}
