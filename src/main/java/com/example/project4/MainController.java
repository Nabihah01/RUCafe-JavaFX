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
    private Order yourOrder;
    private double price = 0;
    private StoreOrders storeOrders;

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
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setContentText("Order Coffee cannot be loaded. Please try again.");
            errorAlert.show();
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
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setContentText("Order Donuts can't be loaded. Please try again.");
            errorAlert.show();
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
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("OrderingBasket-view.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();
            //giving errors!!! whyyy
            OrderingBasketController basketController = fxmlLoader.getController();
            basketController.setMainController(this);
        } catch(Exception e) {
            e.printStackTrace();
//            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
//            errorAlert.setContentText("Your order cannot be loaded. Please try again.");
//            errorAlert.show();
        }

    }

}