package com.example.project4;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
    void placeUserOrder(ActionEvent event) {
        //add it to store orders array
        //clear List View?
    }

    @FXML
    void removeSelectedItem(ActionEvent event) {
        MenuItem item = userOrders.getSelectionModel().getSelectedItem();
        userOrders.getItems().remove(item);
        Double total = Double.parseDouble(orderTotal.getText()) - item.itemPrice();
        orderTotal.setText(total.toString());
    }

    //not sure when or where to call this method so that sales tax is being displayed when user navigates to order
    //basket screen. same with subtotal and total
    void displayAmounts() {
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
