package com.example.project4;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.text.DecimalFormat;

/**
 A GUI class which allows user to view their current order (basket),
 remove any items from the basket, and place their order
 @author Nabihah, Maryam
 */
public class OrderingBasketController {
    protected MainController mainController;
    private ObservableList<MenuItem> order = FXCollections.observableArrayList();
    protected static final DecimalFormat df = new DecimalFormat("###,##0.00");

    @FXML
    private TextField orderSalesTax;

    @FXML
    private TextField orderSubTotal;

    @FXML
    private TextField orderTotal;

    @FXML
    private ListView<MenuItem> userOrders;

    /**
     * sets main controller and gets all the items currently in the user's order
     * and displays those items along with its subtotal, sales tax, and total
     * @param main the main application layout controller
     */
    public void setMainController(MainController main){
        mainController = main;

        for(int i = 0; i < mainController.getYourOrder().getOrders().size(); i++) {
            order.add(mainController.getYourOrder().getOrders().get(i));
        }
        displayYourOrderTotal();
        if(order.isEmpty()){
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setHeaderText("Orders");
            a.setContentText("No orders have been placed");
            a.show();
            Stage stage = (Stage) a.getDialogPane().getScene().getWindow();
            stage.setAlwaysOnTop(true);
            return;
        }
        userOrders.setItems(order);
    }

    /**
     * Event Handler for when user clicks place order button
     * @param event an Event representing some type of action
     */
    @FXML
    void placeUserOrder(ActionEvent event) {
        ObservableList<MenuItem> order = userOrders.getItems();
        if(order.isEmpty()){
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("There is no order to place");
            a.show();
            return;
        }

        mainController.placeOrder();
        Alert a = new Alert(Alert.AlertType.CONFIRMATION);
        a.setContentText("Order has been placed");
        a.show();
        userOrders.getItems().clear();
        orderSalesTax.clear();
        orderTotal.clear();
        orderSubTotal.clear();
    }

    /**
     * Event Handler for when user clicks remove selected item button
     * @param event an Event representing some type of action
     */
    @FXML
    void removeSelectedItem(ActionEvent event) {
        ObservableList<MenuItem> order = userOrders.getItems();

        //remove from ListView
        MenuItem item = userOrders.getSelectionModel().getSelectedItem();
        if(item == null){
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("No item selected.");
            a.show();
            return;
        }
        userOrders.getItems().remove(item);
        //remove from order arraylist
        mainController.removeFromOrder(item);
        //update price
        displayYourOrderTotal();
    }

    /**
     * calculates subtotal, total, and sales tax of order and displays it
     */
    void displayYourOrderTotal() {
        double subtotal = mainController.getYourOrder().getPrice();
        orderSubTotal.setText(String.valueOf(df.format(subtotal)));

        double salesTax = (6.625 / 100) * subtotal;
        orderSalesTax.setText(String.valueOf(df.format(salesTax)));

        double total = subtotal + salesTax;
        orderTotal.setText(String.valueOf(df.format(total)));
        mainController.getYourOrder().setTotal(total);
    }

}
