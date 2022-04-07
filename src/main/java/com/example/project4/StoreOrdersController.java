package com.example.project4;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class StoreOrdersController {
    protected MainController mainController;
    protected ObservableList<Integer> orderNumbers = FXCollections.observableArrayList();
    protected ObservableList <MenuItem> selectedOrder = FXCollections.observableArrayList();
    protected static final DecimalFormat df = new DecimalFormat("###,##0.00");

    @FXML
    private ComboBox<Integer> storeOrderNumber;

    @FXML
    private TextField storeOrderTotal;

    @FXML
    private ListView<MenuItem> storeOrdersList;

    public void setMainController(MainController main){
        mainController = main;
        for(int i = 0; i < mainController.getStoreOrders().getStoreOrdersArray().size(); i++) {
            orderNumbers.add(mainController.getStoreOrders().getStoreOrdersArray().get(i).getOrderNumber());
        }
        if (orderNumbers.isEmpty()) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setHeaderText("Orders");
            a.setContentText("No Store Orders have been placed");
            a.show();
            Stage stage = (Stage) a.getDialogPane().getScene().getWindow();
            stage.setAlwaysOnTop(true);
            return;

        }
        storeOrderNumber.setItems(orderNumbers);
        //show first one by default
        storeOrderNumber.setValue(orderNumbers.get(0));
    }

    @FXML
    void initialize() {
        //set numbers drop-down
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
        for(int i = 0; i < mainController.getStoreOrders().getStoreOrdersArray().size(); i++){
            if(mainController.getStoreOrders().getStoreOrdersArray().get(i).getOrderNumber() == orderNum){
                mainController.getStoreOrders().remove(mainController.getStoreOrders().getStoreOrdersArray().get(i));
                break;
            }
        }

        //remove number from OrderNums list and update
        orderNumbers.remove(orderNum);
        storeOrderNumber.setItems(orderNumbers);
    }

    @FXML
    void exportStoreOrders(ActionEvent event) throws FileNotFoundException {
        //put try-catch for exception
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Open Target File for the Export");
        chooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text Files", "*.txt"),
                new FileChooser.ExtensionFilter("All Files", "*.*"));
        Stage stage = new Stage();
        File targetFile = chooser.showSaveDialog(stage); //get the reference of the target file

        //write code to write to the file.
        PrintWriter pw;
        if(targetFile.canWrite()) {
            pw = new PrintWriter(targetFile);
            for(int i = 0; i < mainController.getStoreOrders().getStoreOrdersArray().size(); i++) {
                int orderNum = mainController.getStoreOrders().getStoreOrdersArray().get(i).getOrderNumber();
                Order order = mainController.getStoreOrders().getStoreOrdersArray().get(i);
                pw.println("Order" + orderNum);
                pw.print(order.toString());
                pw.println();
            }
            pw.close();
        } else {
            //alert can't write to file
        }
    }

    @FXML
    void selectOrderNumber() {
        storeOrdersList.getItems().clear();

        for(int i = 0 ; i < mainController.getStoreOrders().getStoreOrdersArray().size(); i++){
            if(storeOrderNumber.getValue().equals(mainController.getStoreOrders().getStoreOrdersArray().get(i).getOrderNumber())){
                selectedOrder.addAll(mainController.getStoreOrders().getStoreOrdersArray().get(i).getOrders());
                storeOrdersList.setItems(selectedOrder);
                storeOrderTotal.setText(df.format(mainController.getStoreOrders().getStoreOrdersArray().get(i).getTotal()));
                break;
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
