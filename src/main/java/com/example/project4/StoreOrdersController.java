package com.example.project4;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class StoreOrdersController {
    protected MainController mainController;
    protected ObservableList<Integer> orderNumbers = FXCollections.observableArrayList();
    protected ObservableList <Order> selectedOrder = FXCollections.observableArrayList();

    @FXML
    private ComboBox<Integer> storeOrderNumber;

    @FXML
    private TextField storeOrderTotal;

    @FXML
    private ListView<Order> storeOrdersList;

    public ComboBox<Integer> getStoreOrderNumber() {
        return storeOrderNumber;
    }

    public void setMainController(MainController main){
        mainController = main;
        for(int i = 0; i < mainController.getStoreOrders().getStoreOrders().size(); i++) {
            orderNumbers.add(mainController.getStoreOrders().getStoreOrders().get(i).getOrderNumber());
        }
        selectOrderNumber();
    }

    @FXML
    void initialize() {
        //set numbers drop-down
        storeOrderNumber.setItems(orderNumbers);
        storeOrderNumber.setValue(1);
    }

    @FXML
    void cancelStoreOrder(ActionEvent event) {
        Order order = storeOrdersList.getSelectionModel().getSelectedItem();
        if(order == null){
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Please select an order.");
            a.show();
            return;
        }
        storeOrdersList.getItems().remove(order);
        double total = Double.parseDouble(storeOrderTotal.getText()) - order.getTotal();
        storeOrderTotal.setText(String.valueOf(total));
    }

    @FXML
    void exportStoreOrders(ActionEvent event) {

    }

    @FXML
    void selectOrderNumber() {
        DecimalFormat df = new DecimalFormat("0.00"); //look at format

        for(int i =0; i < mainController.getStoreOrders().getStoreOrders().size(); i++){
            if(storeOrderNumber.getValue().equals(mainController.getStoreOrders().getStoreOrders().get(i).getOrderNumber())){
                selectedOrder.add(mainController.getStoreOrders().getStoreOrders().get(i));
                storeOrdersList.setItems(selectedOrder);
                storeOrderTotal.setText(df.format(mainController.getStoreOrders().getStoreOrders().get(i).getTotal()));
            }
        }
        //calculate total
//        double total = 0.0;
//        ObservableList<MenuItem> order = storeOrdersList.getItems();
//        for(MenuItem m: order) {
//            total += m.itemPrice();
//        }
//        storeOrderTotal.setText(String.valueOf(total));
    }

}
