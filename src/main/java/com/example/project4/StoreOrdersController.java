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

    public void setMainController(MainController main){
        mainController = main;
        for(int i = 0; i < mainController.getStoreOrders().getStoreOrdersArray().size(); i++) {
            orderNumbers.add(mainController.getStoreOrders().getStoreOrdersArray().get(i).getOrderNumber());
        }
    }

    @FXML
    void initialize() {
        //set numbers drop-down
        storeOrderNumber.setItems(orderNumbers);
        if(!orderNumbers.isEmpty()) {
            storeOrderNumber.setValue(1);
        }
    }

    @FXML
    void cancelStoreOrder(ActionEvent event) {
        Integer orderNum = storeOrderNumber.getSelectionModel().getSelectedItem();
        if(orderNum == null){
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Please select an order.");
            a.show();
            return;
        }
        //remove from ListView and clear total
        storeOrdersList.getItems().clear();
        storeOrderTotal.clear();
        //show next order by default??

        //remove order from storeOrder arraylist
        Order order = mainController.getStoreOrders().getStoreOrdersArray().get(orderNum);
        mainController.getStoreOrders().remove(order);

        //remove number from OrderNums list and update
        orderNumbers.remove(orderNum);
        storeOrderNumber.setItems(orderNumbers);
    }

    @FXML
    void exportStoreOrders(ActionEvent event) {

    }

    @FXML
    void selectOrderNumber() {
        DecimalFormat df = new DecimalFormat("0.00"); //look at format
        storeOrdersList.getItems().clear();

        for(int i =0; i < mainController.getStoreOrders().getStoreOrdersArray().size(); i++){
            if(storeOrderNumber.getValue().equals(mainController.getStoreOrders().getStoreOrdersArray().get(i).getOrderNumber())){
                selectedOrder.add(mainController.getStoreOrders().getStoreOrdersArray().get(i));
                storeOrdersList.setItems(selectedOrder);
                storeOrderTotal.setText(df.format(mainController.getStoreOrders().getStoreOrdersArray().get(i).getTotal()));
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
