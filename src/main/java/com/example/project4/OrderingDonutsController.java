package com.example.project4;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.text.DecimalFormat;

public class OrderingDonutsController {
    protected MainController mainController;
    protected static final DecimalFormat df = new DecimalFormat("###,##0.00");
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

    public void setMainController(MainController main){
        mainController = main;
    }

    @FXML
    void initialize() {
        donutTypes.setItems(types);
        donutQuantity.setItems(quantity);
        donutFlavors.setItems(flavors);
        subtotal.setText("0.00");
    }

    @FXML
    void removeSelected() {
        MenuItem item = orderList.getSelectionModel().getSelectedItem();
        if(item == null){
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("No item has been selected.");
            a.show();
            return;
        }
        orderList.getItems().remove(item);
        double total = Double.parseDouble(subtotal.getText()) - item.itemPrice();
        subtotal.setText(String.valueOf(df.format(total)));
    }

    @FXML
    void addToListView(ActionEvent event) {
        String donutType = donutTypes.getSelectionModel().getSelectedItem();
        String donutFlavor = donutFlavors.getSelectionModel().getSelectedItem();
        Integer donutQ = donutQuantity.getSelectionModel().getSelectedItem();
        if(donutType == null || donutFlavor == null || donutQ  == null){
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Missing information for order.");
            a.show();
            return;
        }
        Donut donut = new Donut(donutType, donutFlavor, donutQ);

        orderList.getItems().add(donut);
        double total = donut.itemPrice() + Double.parseDouble(subtotal.getText());
        subtotal.setText(String.valueOf(df.format(total)));

        donutFlavors.getSelectionModel().clearSelection();
        donutQuantity.getSelectionModel().clearSelection();
        donutTypes.getSelectionModel().clearSelection();
    }

    @FXML
    void donutAddToOrder(ActionEvent event) {
        ObservableList<MenuItem> order = orderList.getItems();
        if(order.isEmpty()){
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Please add an order.");
            a.show();
            return;
        }
        for(int i = 0; i < order.size(); i++) {
            mainController.addToOrder(order.get(i));
        }
        orderList.getItems().clear();
        donutFlavors.getSelectionModel().clearSelection();
        donutQuantity.getSelectionModel().clearSelection();
        donutTypes.getSelectionModel().clearSelection();
        subtotal.setText("0.00");

        Alert a = new Alert(Alert.AlertType.CONFIRMATION);
        a.setContentText("Donut Order Placed");
        a.show();
    }
}
