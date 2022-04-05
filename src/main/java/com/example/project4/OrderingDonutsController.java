package com.example.project4;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.util.ArrayList;

public class OrderingDonutsController {
    @FXML
    private ComboBox<String> donutFlavors;
    protected ObservableList<String> flavors = FXCollections.observableArrayList(
            "Chocolate", "Glazed", "Jelly", "Old-Fashioned");

    @FXML
    private ComboBox<Integer> donutQuantity;
    protected ObservableList<Integer> quantity = FXCollections.observableArrayList(
            1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12);

    @FXML
    private ComboBox<String> donutTypes;
    protected ObservableList<String> types = FXCollections.observableArrayList(
            "Yeast", "Donut Holes", "Cake");

    @FXML
    private ListView<MenuItem> orderList;

    @FXML
    private TextField subtotal;

    @FXML
    void initialize() {
        donutTypes.setItems(types);
        donutQuantity.setItems(quantity);
        donutFlavors.setItems(flavors);
    }

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

        donutFlavors.getSelectionModel().clearSelection();
        donutQuantity.getSelectionModel().clearSelection();
        donutTypes.getSelectionModel().clearSelection();
    }

    @FXML
    void donutAddToOrder(ActionEvent event) {
        ObservableList<MenuItem> order = orderList.getItems();
        for(int i = 0; i < order.size(); i++) {
            MainController.yourOrder.getOrders().add(order.get(i));
        }
        orderList.getItems().clear();
        donutFlavors.getSelectionModel().clearSelection();
        donutQuantity.getSelectionModel().clearSelection();
        donutTypes.getSelectionModel().clearSelection();
        subtotal.clear();
    }
}
