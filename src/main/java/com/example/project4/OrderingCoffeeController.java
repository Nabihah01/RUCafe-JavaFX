package com.example.project4;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

public class OrderingCoffeeController {

    @FXML
    private CheckBox caramel;

    @FXML
    private ChoiceBox<?> coffeeQuantity;

    @FXML
    private ChoiceBox<?> coffeeSize;

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

}
