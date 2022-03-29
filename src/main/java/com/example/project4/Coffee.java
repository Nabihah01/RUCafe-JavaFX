package com.example.project4;

/**
 * An instance of this class is a menu item in an order.
 * This class must extend MenuItem class and implement the Customizable interface above to
 * provide the behavior of adding and removing the add-ins.
 *
 * Brewed coffee, with the choices of different add-ins: cream, syrup, milk, caramel, and whipped cream;
 * and, with the choices of different cup sizes: Short, Tall, Grande and Venti.
 * The price for a Short black coffee is $1.69. Each add-in costs $0.30. The price increase for the next size is $0.40.
 * For example, the price increase for a Grande is $0.80,
 * therefore a Grande black coffee is $2.49, and a Grande coffee with cream and syrup would be $3.09.
 */
public class Coffee extends MenuItem implements Customizable{

    @Override
    public double itemPrice(){
        return 0;
    }

    @Override
    public boolean add(Object obj) {
        return false;
    }

    @Override
    public boolean remove(Object obj) {
        return false;
    }
}
