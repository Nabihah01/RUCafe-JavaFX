package com.example.project4;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.util.ArrayList;

public class StoreOrdersController {

    @FXML
    private ComboBox<Integer> storeOrderNumber;

    @FXML
    private TextField storeOrderTotal;

    @FXML
    private ListView<MenuItem> storeOrdersList;

    public ComboBox<Integer> getStoreOrderNumber() {
        return storeOrderNumber;
    }

    @FXML
    void initialize() {
        //show first order by default
        if(MainController.storeOrders.getStoreOrders().get(0) != null) {
            Order firstOrder = MainController.storeOrders.getStoreOrders().get(0);
            storeOrdersList.setItems((ObservableList<MenuItem>) firstOrder);
            storeOrderNumber.setValue(1);
        }
    }

    @FXML
    void cancelStoreOrder(ActionEvent event) {
        MenuItem item = storeOrdersList.getSelectionModel().getSelectedItem();
        storeOrdersList.getItems().remove(item);
        Double total = Double.parseDouble(storeOrderTotal.getText()) - item.itemPrice();
        storeOrderTotal.setText(total.toString());
    }

    @FXML
    void exportStoreOrders(ActionEvent event) {

    }

    void selectOrderNumber() {
        //from storeOrders array in storeOrder class, find correct order.
        Integer orderNum = storeOrderNumber.getSelectionModel().getSelectedItem();
        Order orderToDisplay = MainController.storeOrders.getStoreOrders().get(orderNum - 1);

        //display it to listView
        storeOrdersList.setItems((ObservableList<MenuItem>) orderToDisplay);

        //calculate total
        Double total = 0.0;
        ObservableList<MenuItem> order = storeOrdersList.getItems();
        for(MenuItem m: order) {
            total += m.itemPrice();
        }
        storeOrderTotal.setText(total.toString());
    }

}
