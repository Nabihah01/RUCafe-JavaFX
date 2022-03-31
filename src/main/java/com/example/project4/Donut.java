package com.example.project4;

/**
 * Three different types of donuts: yeast donut, cake donut and donut holes.
 * Each type of donuts includes a variety of flavors.
 * Regardless of the flavors, a yeast donut is $1.59, a cake donut is $1.79, and a donut hole is $0.39.
 */
public class Donut extends MenuItem{
    private String donutType;
    private int donutQuantity;
    private String donutFlavor;
    private static final double YEAST = 1.59;
    private static final double CAKE = 1.79;
    private static final double HOLE = 0.39;
    public Donut(String donutType, String donutFlavor, int donutQuantity){
        this.donutType = donutType;
        this.donutQuantity = donutQuantity;
        this.donutFlavor = donutFlavor;
    }
    @Override
    public double itemPrice(){
        if(donutType.equals("yeast")){
            return YEAST;
        } else if (donutType.equals("cake")) {
            return CAKE;
        }
        else{
            return HOLE;
        }
    }

    public String getDonutType() {
        return donutType;
    }

    public String getDonutFlavor() {
        return donutFlavor;
    }

    public int getDonutQuantity() {
        return donutQuantity;
    }

    @Override
    public String toString(){
        return this.donutFlavor + " " + this.donutType + " donut " + "(" + this.donutQuantity + ")";
    }
    public static void main(String []args){
        Donut donut = new Donut("cake","strawberry", 1);
        System.out.println(donut.toString());
    }
}
