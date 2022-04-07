package com.example.project4;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

import java.util.ArrayList;


/**
 * TO DO:
 * menu closes everything closes
 * alert for when can't write to file
 * add total to when we export orders
 * fix null ptr exception for storeOrders
 * javadoc comments
 * system testing
 * remove all print statements
 */

public class MainController {
    //stores a user's orders
    private int orderNum = 1;
    private Order yourOrder;
    private double price = 0;
    private StoreOrders storeOrders;
    private Stage coffeeStage = new Stage();
    private Stage donutStage = new Stage();
    private Stage orderBaskStage = new Stage();
    private Stage storeOrderStage = new Stage();

    public MainController() {
        this.yourOrder = new Order(new ArrayList<>(), orderNum);
        this.storeOrders = new StoreOrders();
    }

    public Order getYourOrder() {
        return yourOrder;
    }

    public StoreOrders getStoreOrders() {
        return storeOrders;
    }

    public void addToOrder(MenuItem item){
        yourOrder.add(item);
        if (item instanceof Coffee) {
            price += item.itemPrice();
        }
        if (item instanceof Donut) {
            price += item.itemPrice();
        }
        yourOrder.setTotal(price);
        System.out.println(yourOrder.toString() + " price : " + price + " orderNum: "+ orderNum);
    }

    public void removeFromOrder(MenuItem item) {
        yourOrder.remove(item);
        if (item instanceof Coffee) {
            price -= item.itemPrice();
        }
        if (item instanceof Donut) {
            price -= item.itemPrice();
        }
        yourOrder.setTotal(price);
    }

    public void placeOrder(){
        storeOrders.add(yourOrder);
        orderNum++;
        price = 0;
        yourOrder = new Order(new ArrayList<MenuItem>(), orderNum);
    }

    @FXML
     void orderCoffee(ActionEvent event) {
        try {
            if(coffeeStage.isShowing() || donutStage.isShowing() || orderBaskStage.isShowing()
            || storeOrderStage.isShowing()){
                coffeeStage.toFront();
                return;
            }
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("OrderingCoffee-view.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            coffeeStage = new Stage();
            coffeeStage.setScene(new Scene(root1));
            coffeeStage.show();
            OrderingCoffeeController coffeeController = fxmlLoader.getController();
            coffeeController.setMainController(this);

        } catch(Exception e) {
            e.printStackTrace();
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setContentText("Order Coffee cannot be loaded. Please try again.");
            errorAlert.show();
        }
    }

    @FXML
    void orderDonuts(ActionEvent event) {
        try {
            if(coffeeStage.isShowing() || donutStage.isShowing() || orderBaskStage.isShowing()
                    || storeOrderStage.isShowing()){
                donutStage.toFront();
                return;
            }
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("OrderingDonuts-view.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            donutStage = new Stage();
            donutStage.setScene(new Scene(root1));
            donutStage.show();
            OrderingDonutsController donutsController = fxmlLoader.getController();
            donutsController.setMainController(this);
        } catch(Exception e) {
            e.printStackTrace();
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setContentText("Order Donuts can't be loaded. Please try again.");
            errorAlert.show();
        }

    }

    @FXML
    void viewStoreOrders(ActionEvent event) {
        try {
            if(coffeeStage.isShowing() || donutStage.isShowing() || orderBaskStage.isShowing()
                    || storeOrderStage.isShowing()){
                storeOrderStage.toFront();
                return;
            }
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("StoreOrders-view.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            storeOrderStage = new Stage();
            storeOrderStage.setScene(new Scene(root1));
            storeOrderStage.show();
            StoreOrdersController storeOrdersController = fxmlLoader.getController();
            storeOrdersController.setMainController(this);
        } catch(Exception e) {
            e.printStackTrace();
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setContentText("Your store orders cannot be loaded. Please try again.");
            errorAlert.show();
        }

    }

    @FXML
    void viewYourOrder(ActionEvent event) {
        try {
            if(coffeeStage.isShowing() || donutStage.isShowing() || orderBaskStage.isShowing()
                    || storeOrderStage.isShowing()){
                orderBaskStage.toFront();
                return;
            }
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("OrderingBasket-view.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            orderBaskStage = new Stage();
            orderBaskStage.setScene(new Scene(root1));
            orderBaskStage.show();
            OrderingBasketController basketController = fxmlLoader.getController();
            basketController.setMainController(this);
        } catch(Exception e) {
            e.printStackTrace();
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setContentText("Your order cannot be loaded. Please try again.");
            errorAlert.show();
        }

    }

}