package com.example.project4;

import java.util.ArrayList;

/**
 * This class extends MenuItem and represents coffee object.
 * Has three attributes - cupsize, addins, and quantity
 *  @author Nabihah, Maryam
 */
public class Coffee extends MenuItem implements Customizable{
    private String cupSize;
    private ArrayList<String> addIns;
    private int quantity;

    /**
     * Constructor for coffee object
     * @param cupSize size of coffee
     * @param addIns addins
     * @param quantity quantity
     */
    public Coffee(String cupSize, ArrayList<String> addIns, int quantity){
        this.cupSize = cupSize;
        this.addIns = addIns;
        this.quantity = quantity;
    }

    /**
     * overrides itemPrice method, and calculates price for
     * coffee
     * @return price of coffee object
     */
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

    /**
     * overrides method, adds new coffee object to list
     * @param obj to be added
     * @return boolean
     */
    @Override
    public boolean add(Object obj) {
        if(obj instanceof String){
            String addIn = (String) obj;
            addIns.add(addIn);
            return true;
        }
        return false;
    }

    /**
     * overrides method, removes coffee object from list
     * @param obj to be removed
     * @return boolean
     */
    @Override
    public boolean remove(Object obj) {
        if(obj instanceof String){
            String addIn = (String) obj;
            addIns.remove(addIn);
            return true;
        }
        return false;
    }

    /**
     * overrides toString method and returns string form of
     * coffee object
     * @return string
     */
    @Override
    public String toString(){
        if(!addIns.isEmpty()){
            return this.cupSize + " coffee with " + String.join(", ", this.addIns) +
                    " (" + this.quantity + ")";
        }
        else {
            return this.cupSize + " black coffee " + "(" + this.quantity + ")";
        }

    }
}
