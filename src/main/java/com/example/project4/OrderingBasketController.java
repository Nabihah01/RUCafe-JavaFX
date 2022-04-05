package com.example.project4;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class OrderingBasketController {

    @FXML
    private TextField orderSalesTax;

    @FXML
    private TextField orderSubTotal;

    @FXML
    private TextField orderTotal;

    @FXML
    private ListView<MenuItem> userOrders;

    @FXML
    void initialize() {
        //display your order
        userOrders.setItems((ObservableList<MenuItem>) MainController.yourOrder.getOrders());
        //display your order total
        displayYourOrderTotal();
    }

    @FXML
    void placeUserOrder(ActionEvent event) {
        //add it to store orders array
        MainController.orderNum++;
        //add order number to combo box in store order
        // get combo box from store orders and update it

        MainController.storeOrders.add(MainController.yourOrder);
        userOrders.getItems().clear();
    }

    @FXML
    void removeSelectedItem(ActionEvent event) {
        MenuItem item = userOrders.getSelectionModel().getSelectedItem();
        userOrders.getItems().remove(item);
        Double total = Double.parseDouble(orderTotal.getText()) - item.itemPrice();
        orderTotal.setText(total.toString());
    }

    void displayYourOrderTotal() {
        Double subtotal = 0.0;
        ObservableList<MenuItem> order = userOrders.getItems();
        for(MenuItem m: order) {
            subtotal += m.itemPrice();
        }
        orderSubTotal.setText(subtotal.toString());

        Double salesTax = (6.625 / 100) * Double.parseDouble(orderSubTotal.getText());
        orderSalesTax.setText(salesTax.toString());

        Double total = Double.parseDouble(orderSubTotal.getText()) - salesTax;
        orderTotal.setText(total.toString());

    }

}
