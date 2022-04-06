package com.example.project4;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.util.ArrayList;

public class OrderingCoffeeController {
    private MainController mainController;

    @FXML
    private CheckBox caramel;

    @FXML
    private ComboBox<Integer> coffeeQuantity;
    protected ObservableList<Integer> quantity = FXCollections.observableArrayList(
            1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12);

    @FXML
    private ComboBox<String> coffeeSize;
    protected ObservableList<String> sizes = FXCollections.observableArrayList(
            "Short", "Tall", "Grande", "Venti");

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

    ArrayList<String> addIns = new ArrayList<>();

    public void setMainController(MainController main){
        mainController = main;
    }
    @FXML
    void initialize() {
        coffeeQuantity.setItems(quantity);
        coffeeSize.setItems(sizes);
        coffeeSize.setValue("Short");
        coffeeQuantity.setValue(1);
        coffeeTotal.setText("1.69");
    }

    @FXML
    void creamSelected(ActionEvent event) {
        double price = Double.parseDouble(coffeeTotal.getText());
        if(!cream.isSelected()) {
            if(addIns.contains("cream"))
                addIns.remove("cream");
            price -= 0.30;
        }
        else {
            price += 0.30;
            addIns.add("cream");
        }
        coffeeTotal.setText(String.valueOf(price));
    }

    @FXML
    void milkSelected(ActionEvent event) {
        double price = Double.parseDouble(coffeeTotal.getText());
        if(!milk.isSelected()) {
            if(addIns.contains("milk"))
                addIns.remove("milk");
            price -= 0.30;
        }
        else {
            price += 0.30;
            addIns.add("milk");
        }
        coffeeTotal.setText(String.valueOf(price));
    }

    @FXML
    void caramelSelected(ActionEvent event) {
        double price = Double.parseDouble(coffeeTotal.getText());
        if(!caramel.isSelected()) {
            if(addIns.contains("caramel"))
                addIns.remove("caramel");
            price -= 0.30;
        }
        else {
            price += 0.30;
            addIns.add("caramel");
        }
        coffeeTotal.setText(String.valueOf(price));
    }

    @FXML
    void syrupSelected(ActionEvent event) {
        double price = Double.parseDouble(coffeeTotal.getText());
        if(!syrup.isSelected()) {
            price -= 0.30;
            if(addIns.contains("syrup"))
                addIns.remove("syrup");
        }
        else {
            price += 0.30;
            addIns.add("syrup");
        }
        coffeeTotal.setText(String.valueOf(price));
    }
    @FXML
    void whippedSelected(ActionEvent event) {
        double price = Double.parseDouble(coffeeTotal.getText());
        if(!whipped.isSelected()) {
            price -= 0.30;
            if(addIns.contains("whipped cream"))
                addIns.remove("whipped cream");
        }
        else {
            price += 0.30;
            addIns.add("whipped cream");
        }
        coffeeTotal.setText(String.valueOf(price));
    }

    @FXML
    void quantitySelected(ActionEvent event) {
        Coffee coffeeOrder = new Coffee(coffeeSize.getSelectionModel().getSelectedItem(), addIns, coffeeQuantity.getSelectionModel().getSelectedItem());
        double price = coffeeOrder.itemPrice();
        coffeeTotal.setText(String.valueOf(price));
    }

    @FXML
    void sizeSelected(ActionEvent event) {
        Coffee coffeeOrder = new Coffee(coffeeSize.getSelectionModel().getSelectedItem(), addIns, coffeeQuantity.getSelectionModel().getSelectedItem());
        double price = coffeeOrder.itemPrice();
        coffeeTotal.setText(String.valueOf(price));
    }

    @FXML
    void coffeeAddtoOrder(ActionEvent event) {
        ArrayList<String> addIns = new ArrayList<>();
        if(cream.isSelected())
            addIns.add("cream");
        if(caramel.isSelected())
            addIns.add("caramel");
        if(syrup.isSelected())
            addIns.add("syrup");
        if(milk.isSelected())
            addIns.add("milk");
        if(whipped.isSelected())
            addIns.add("whipped");

        Coffee coffeeOrder = new Coffee(coffeeSize.getSelectionModel().getSelectedItem(), addIns, coffeeQuantity.getSelectionModel().getSelectedItem());
        mainController.addToOrder(coffeeOrder);

        whipped.setSelected(false);
        cream.setSelected(false);
        caramel.setSelected(false);
        syrup.setSelected(false);
        milk.setSelected(false);
        coffeeSize.setValue("Short");
        coffeeQuantity.setValue(1);
        coffeeTotal.setText("1.69");

        Alert a = new Alert(Alert.AlertType.CONFIRMATION);
        a.setContentText("Coffee Order Placed");
        a.show();

    }

}
