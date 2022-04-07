package com.example.project4;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class OrderingBasketController {
    protected MainController mainController;
    private ObservableList<MenuItem> order = FXCollections.observableArrayList();

    @FXML
    private TextField orderSalesTax;

    @FXML
    private TextField orderSubTotal;

    @FXML
    private TextField orderTotal;

    @FXML
    private ListView<MenuItem> userOrders;

    public void setMainController(MainController main){
        mainController = main;

        for(int i = 0; i < mainController.getYourOrder().getOrders().size(); i++) {
            order.add(mainController.getYourOrder().getOrders().get(i));
        }
        displayYourOrderTotal();
    }

    @FXML
    void initialize() {
        //display your order
        userOrders.setItems(order);
    }

    @FXML
    void placeUserOrder(ActionEvent event) {
        ObservableList<MenuItem> order = userOrders.getItems();
        if(order.isEmpty()){
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("There is no order to place");
            a.show();
            return;
        }

        mainController.placeOrder();
        userOrders.getItems().clear();
        orderSalesTax.clear();
        orderTotal.clear();
        orderSubTotal.clear();
    }

    @FXML
    void removeSelectedItem(ActionEvent event) {
        ObservableList<MenuItem> order = userOrders.getItems();
        if(order.isEmpty()){
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("There is no item to remove");
            a.show();
            return;
        }

        //remove from ListView
        MenuItem item = userOrders.getSelectionModel().getSelectedItem();
        userOrders.getItems().remove(item);
        //remove from order arraylist
        mainController.removeFromOrder(item);
        //update price
        displayYourOrderTotal();
    }

    void displayYourOrderTotal() {
        double subtotal = mainController.getYourOrder().getTotal();
        orderSubTotal.setText(String.valueOf(subtotal));

        double salesTax = (6.625 / 100) * subtotal;
        orderSalesTax.setText(String.valueOf(salesTax));

        double total = subtotal + salesTax;
        orderTotal.setText(String.valueOf(total));

    }

}
