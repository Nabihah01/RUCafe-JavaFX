package com.example.project4;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

import java.util.ArrayList;

public class MainController {
    //stores a user's orders
    private int orderNum = 1;
    protected Order yourOrder = new Order(new ArrayList<MenuItem>(), orderNum);
    private double price = 0;
    static StoreOrders storeOrders = new StoreOrders(new ArrayList<>());

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

    public void placeOrder(){
        storeOrders.add(yourOrder);
        orderNum++;
        price = 0;
        yourOrder = new Order(new ArrayList<MenuItem>(), orderNum);
        System.out.println(storeOrders.getStoreOrders().get(0));
    }

    @FXML
     void orderCoffee(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("OrderingCoffee-view.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();
            OrderingCoffeeController coffeeController = fxmlLoader.getController();
            coffeeController.setMainController(this);

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void orderDonuts(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("OrderingDonuts-view.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();
            OrderingDonutsController donutsController = fxmlLoader.getController();
            donutsController.setMainController(this);
        } catch(Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    void viewStoreOrders(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("StoreOrders-view.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();
//            StoreOrdersController storeOrdersController = fxmlLoader.getController();
//            storeOrdersController.setMainController(this);
        } catch(Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    void viewYourOrder(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("OrderingBasket-view.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();
            //giving errors!!! whyyy
            OrderingBasketController orderingBasketController = fxmlLoader.getController();
            orderingBasketController.setMainController(this);
        } catch(Exception e) {
            e.printStackTrace();
//            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
//            errorAlert.setContentText("Your store orders cannot be loaded. Please try again.");
//            errorAlert.show();
        }

    }

}