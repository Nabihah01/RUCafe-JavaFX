package com.example.project4;

/**
 * Three different types of donuts: yeast donut, cake donut and donut holes.
 * Each type of donuts includes a variety of flavors.
 * Regardless of the flavors, a yeast donut is $1.59, a cake donut is $1.79, and a donut hole is $0.39.
 */
public class Donut extends MenuItem{
    private String donutType;
    public Donut(String donut){
        this.donutType = donut;
    }
    @Override
    public double itemPrice(){
        if(donutType.equals("yeast")){
            return 1.59;
        } else if (donutType.equals("cake")) {
            return 1.79;
        }
        else{
            return 0.39;
        }
    }
}
