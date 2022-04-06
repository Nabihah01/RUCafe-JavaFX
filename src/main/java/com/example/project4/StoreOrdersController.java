package com.example.project4;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

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
        if(item == null){
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Please select an order.");
            a.show();
            return;
        }
        storeOrdersList.getItems().remove(item);
        double total = Double.parseDouble(storeOrderTotal.getText()) - item.itemPrice();
        storeOrderTotal.setText(String.valueOf(total));
    }

    @FXML
    void exportStoreOrders(ActionEvent event) {

    }

    @FXML
    void selectOrderNumber() {
        //from storeOrders array in storeOrder class, find correct order.
        Integer orderNum = storeOrderNumber.getSelectionModel().getSelectedItem();
        Order orderToDisplay = MainController.storeOrders.getStoreOrders().get(orderNum - 1);

        //display it to listView
        storeOrdersList.setItems((ObservableList<MenuItem>) orderToDisplay);

        //calculate total
        double total = 0.0;
        ObservableList<MenuItem> order = storeOrdersList.getItems();
        for(MenuItem m: order) {
            total += m.itemPrice();
        }
        storeOrderTotal.setText(String.valueOf(total));
    }

}
