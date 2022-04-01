package com.example.project4;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class OrderingDonutsController {

    @FXML
    private ChoiceBox<String> donutFlavors;

    @FXML
    private ChoiceBox<Integer> donutQuantity;

    @FXML
    private ChoiceBox<String> donutTypes;

    @FXML
    private ListView<?> orderList;

    @FXML
    private TextField subtotal;

    @FXML
    void addToListView(ActionEvent event) {

    }

    @FXML
    void donutAddToOrder(ActionEvent event) {

    }

    void setChoiceBox() {
        donutFlavors.setItems(FXCollections.observableArrayList(
                "Chocolate", "Glazed", "Jelly", "Old-Fashioned"));
        donutTypes.setItems(FXCollections.observableArrayList(
                "Yeast", "Donut Holes", "Cake"));
        donutQuantity.setItems(FXCollections.observableArrayList(
                1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12));
    }

}
