package com.example.project4;

import java.util.ArrayList;

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
    private String cupSize;
    private ArrayList<String> addIns;
    private int quantity;
    public Coffee(String cupSize, ArrayList<String> addIns, int quantity){
        this.cupSize = cupSize;
        this.addIns = addIns;
        this.quantity = quantity;
    }

    public String getCupSize() {
        return cupSize;
    }

    public ArrayList<String> getAddIns() {
        return addIns;
    }

    @Override
    public double itemPrice() {
        double price = 0;
        int addIn = addIns.size();
        price += addIn * 0.30;

        switch (cupSize) {
            case "Short":
                price += 1.69;
                break;
            case "Tall":
                price += 2.09;
                break;
            case "Grande":
                price += 2.49;
                break;
            case "Venti":
                price += 2.89;
                break;
        }
        return price * quantity;
    }

    @Override
    public boolean add(Object obj) {
        if(obj instanceof String){
            String addIn = (String) obj;
            addIns.add(addIn);
            return true;
        }
        return false;
    }

    @Override
    public boolean remove(Object obj) {
        if(obj instanceof String){
            String addIn = (String) obj;
            addIns.remove(addIn);
            return true;
        }
        return false;
    }

    @Override
    public String toString(){
        if(!addIns.isEmpty()){
            return this.cupSize + " coffee with " + String.join(", ", this.addIns) +
                    "(" + this.quantity + ")";
        }
        else {
            return this.cupSize + " black coffee " + "(" + this.quantity + ")";
        }

    }

    public static void main(String []args){
        ArrayList <String> addIns = new ArrayList<>();
        Coffee coffee = new Coffee("Tall",addIns, 1);
        System.out.println(coffee.toString());
    }
}
