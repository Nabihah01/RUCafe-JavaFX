package com.example.project4;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

public class OrderingCoffeeController {

    @FXML
    private CheckBox caramel;

    @FXML
    private ChoiceBox<Integer> coffeeQuantity;

    @FXML
    private ChoiceBox<String> coffeeSize;

    @FXML
    private TextField coffeeTotal;

    @FXML
    private CheckBox cream;

    @FXML
    private CheckBox milk;

    @FXML
    private CheckBox syrup;

    @FXML
    private CheckBox whipped;

    @FXML
    void coffeeAddtoOrder(ActionEvent event) {

    }

    @FXML
    void setSizeChoiceBox() {
        ObservableList<String> sizes = FXCollections.observableArrayList(
                "Short", "Tall", "Grande", "Venti");
        coffeeSize.setItems(sizes);
        coffeeSize.setValue("Short");
    }

    @FXML
    void setQuantityChoiceBox() {
        ObservableList<Integer> quantity = FXCollections.observableArrayList(
                1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12);
        coffeeQuantity.setItems(quantity);
        coffeeSize.setValue("1");
    }

}
