package com.example.project4;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class OrderingBasketController {
    private MainController mainController;

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
    }
    @FXML
    void initialize() {
        //display your order
        userOrders.setItems((ObservableList<MenuItem>) mainController.yourOrder.getOrders());
        //display your order total
        displayYourOrderTotal();
    }

    @FXML
    void placeUserOrder(ActionEvent event) {
        //add it to store orders array
        //add order number to combo box in store order
        // get combo box from store orders and update it
        mainController.placeOrder();
        userOrders.getItems().clear();
    }

    @FXML
    void removeSelectedItem(ActionEvent event) {
        MenuItem item = userOrders.getSelectionModel().getSelectedItem();
        userOrders.getItems().remove(item);
        double total = Double.parseDouble(orderTotal.getText()) - item.itemPrice();
        orderTotal.setText(String.valueOf(total));
    }

    void displayYourOrderTotal() {
        double subtotal = 0.0;
        ObservableList<MenuItem> order = userOrders.getItems();
        for(MenuItem m: order) {
            subtotal += m.itemPrice();
        }
        orderSubTotal.setText(String.valueOf(subtotal));

        double salesTax = (6.625 / 100) * Double.parseDouble(orderSubTotal.getText());
        orderSalesTax.setText(String.valueOf(salesTax));

        double total = Double.parseDouble(orderSubTotal.getText()) - salesTax;
        orderTotal.setText(String.valueOf(total));

    }

}
