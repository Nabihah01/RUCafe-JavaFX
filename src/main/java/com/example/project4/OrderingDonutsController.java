package com.example.project4;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.util.ArrayList;

public class OrderingDonutsController {
    int orderNumber = 1;

    @FXML
    private ChoiceBox<String> donutFlavors;

    @FXML
    private ChoiceBox<Integer> donutQuantity;

    @FXML
    private ChoiceBox<String> donutTypes;

    @FXML
    private ListView<MenuItem> orderList;

    @FXML
    private TextField subtotal;


    @FXML
    void removeSelected() {
        MenuItem item = orderList.getSelectionModel().getSelectedItem();
        orderList.getItems().remove(item);
        Double total = Double.parseDouble(subtotal.getText()) - item.itemPrice();
        subtotal.setText(total.toString());
    }

    @FXML
    void addToListView(ActionEvent event) {
        String donutType = donutTypes.getSelectionModel().getSelectedItem();
        String donutFlavor = donutFlavors.getSelectionModel().getSelectedItem();
        Integer donutQ = donutQuantity.getSelectionModel().getSelectedItem();
        Donut donut = new Donut(donutType, donutFlavor, donutQ);

        orderList.getItems().add(donut);
        Double total = donut.itemPrice() + Double.parseDouble(subtotal.getText());
        subtotal.setText(total.toString());
    }

    @FXML
    void donutAddToOrder(ActionEvent event) {
        ArrayList<MenuItem> orderArray = (ArrayList<MenuItem>) orderList.getItems();
        Order orders = new Order(orderArray, orderNumber);
        orderNumber++;
        //create an orderArray by getting items from list view
        //have a global variable for order number?
        //does order number start from 1 again in coffee window?
        //using orderArray and orderNumber, create Order variable
        //add it to ArrayList for Ordering Basket and for Store Orders?
    }

    @FXML
    void setFlavorChoiceBox() {
        donutFlavors.setItems(FXCollections.observableArrayList(
                "Chocolate", "Glazed", "Jelly", "Old-Fashioned"));
    }

    @FXML
    void setTypeChoiceBox() {
        donutTypes.setItems(FXCollections.observableArrayList(
                "Yeast", "Donut Holes", "Cake"));
    }

    @FXML
    void setQuantityChoiceBox() {
        donutQuantity.setItems(FXCollections.observableArrayList(
                1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12));
    }
}
