package com.example.project4;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class StoreOrdersController {

    @FXML
    private ChoiceBox<Integer> storeOrderNumber;

    @FXML
    private TextField storeOrderTotal;

    @FXML
    private ListView<MenuItem> storeOrdersList;

    void initialize() {
        //show first order by default
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

        //display it to listView
        

        //calculate total
        Double total = 0.0;
        ObservableList<MenuItem> order = storeOrdersList.getItems();
        for(MenuItem m: order) {
            total += m.itemPrice();
        }
        storeOrderTotal.setText(total.toString());
    }

}
