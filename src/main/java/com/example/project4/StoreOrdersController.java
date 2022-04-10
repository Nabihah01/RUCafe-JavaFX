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

/**
 A GUI class which allows user to view all orders that have been placed, cancel any orders,
 and export order to text file.
 @author Nabihah, Maryam
 */
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

    /**
     * Stores main controller for future use
     * gets all the order numbers of orders that have been placed
     * and sets combo box to display first order number by default
     * @param main the main application layout controller
     */
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
        storeOrderNumber.setValue(orderNumbers.get(0));
    }

    /**
     * Event Handler for when user selects an order number for which order to display
     * @param event an Event representing some type of action
     */
    @FXML
    void selectOrderNum(ActionEvent event) {
        storeOrdersList.getItems().clear();
        if(storeOrderNumber.getValue() == null){
            return;
        }
        for(int i = 0 ; i < mainController.getStoreOrders().getStoreOrdersArray().size(); i++) {
            if(storeOrderNumber.getValue().equals(mainController.getStoreOrders().getStoreOrdersArray().get(i).getOrderNumber())){
                selectedOrder.addAll(mainController.getStoreOrders().getStoreOrdersArray().get(i).getOrders());
                storeOrdersList.setItems(selectedOrder);
                storeOrderTotal.setText(df.format(mainController.getStoreOrders().getStoreOrdersArray().get(i).getTotal()));
                break;
            }
        }
    }

    /**
     * Event Handler for when user clicks "Cancel Order" button
     * @param event an Event representing some type of action
     */
    @FXML
    void cancelStoreOrder(ActionEvent event) {
        Integer orderNum = storeOrderNumber.getSelectionModel().getSelectedItem();
        if(orderNum == null){
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Please select an order.");
            a.show();
            return;
        }
        storeOrdersList.getItems().clear();
        storeOrderTotal.clear();

        for(int i = 0; i < mainController.getStoreOrders().getStoreOrdersArray().size(); i++){
            if(mainController.getStoreOrders().getStoreOrdersArray().get(i).getOrderNumber() == orderNum){
                mainController.getStoreOrders().remove(mainController.getStoreOrders().getStoreOrdersArray().get(i));
                break;
            }
        }

        storeOrderNumber.getSelectionModel().clearSelection();
        orderNumbers.remove(orderNum);
        storeOrderNumber.setItems(orderNumbers);
        if(!orderNumbers.isEmpty()) {
            storeOrderNumber.setValue(orderNumbers.get(0));
        }
        Alert a = new Alert(Alert.AlertType.CONFIRMATION);
        a.setContentText("Order has been cancelled.");
        a.show();
    }

    /**
     * Event Handler for when user clicks "Export Orders" button
     * @param event an Event representing some type of action
     */
    @FXML
    void exportStoreOrders(ActionEvent event) {
        if(orderNumbers.isEmpty()){
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("No orders to export");
            a.show();
            return;
        }
        try {
            FileChooser chooser = new FileChooser();
            chooser.setTitle("Open Target File for the Export");
            chooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text Files", "*.txt"),
                    new FileChooser.ExtensionFilter("All Files", "*.*"));
            Stage stage = new Stage();
            File targetFile = chooser.showSaveDialog(stage);
            PrintWriter pw;
            if(!targetFile.exists()){
                targetFile.createNewFile();
            }
            if(targetFile.canWrite()) {
                pw = new PrintWriter(targetFile);
                for(int i = 0; i < mainController.getStoreOrders().getStoreOrdersArray().size(); i++) {
                    int orderNum = mainController.getStoreOrders().getStoreOrdersArray().get(i).getOrderNumber();
                    double total = mainController.getStoreOrders().getStoreOrdersArray().get(i).getTotal();
                    Order order = mainController.getStoreOrders().getStoreOrdersArray().get(i);
                    pw.println("Order " + orderNum);
                    pw.print(order.toString());
                    pw.println("Total: " + df.format(total));
                    pw.println();
                }
                pw.close();
            } else {
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setHeaderText("Export Orders");
                a.setContentText("Cannot write to file. Please try again.");
                a.show();
                return;
            }
        }
        catch (Exception e){
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setHeaderText("Export Orders");
            a.setContentText("Cannot export orders. Please try again.");
            a.show();
            return;
        }
        Alert a = new Alert(Alert.AlertType.CONFIRMATION);
        a.setContentText("All orders have been exported to file");
        a.show();
    }
}
