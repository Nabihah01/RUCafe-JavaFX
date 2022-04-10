package com.example.project4;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 A GUI class which allows user to order coffee
 @author Nabihah, Maryam
 */
public class OrderingCoffeeController {
    protected MainController mainController;
    protected static final DecimalFormat df = new DecimalFormat("###,##0.00");

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
    private CheckBox caramel;

    @FXML
    private CheckBox cream;

    @FXML
    private CheckBox milk;

    @FXML
    private CheckBox syrup;

    @FXML
    private CheckBox whipped;

    ArrayList<String> addIns = new ArrayList<>();

    /**
     * Stores main controller for future use
     * @param main the main application layout controller
     */
    public void setMainController(MainController main){
        mainController = main;
    }

    /**
     * This method is called to initialize controller after root element has been
     * processed. The choices for quantity and size are set. Default value of coffee size is
     * set to short, default quantity is set to 1, and total to 1.69.
     */
    @FXML
    void initialize() {
        coffeeQuantity.setItems(quantity);
        coffeeSize.setItems(sizes);
        coffeeSize.setValue("Short");
        coffeeQuantity.setValue(1);
        coffeeTotal.setText("1.69");
    }

    /**
     * Event Handler for when cream add-In is selected
     * @param event an Event representing some type of action
     */
    @FXML
    void creamSelected(ActionEvent event) {
        String cupSize = coffeeSize.getSelectionModel().getSelectedItem();
        Integer quantity = coffeeQuantity.getSelectionModel().getSelectedItem();
        if(!milk.isSelected()) {
            if(addIns.contains("cream"))
                addIns.remove("cream");
        }
        else {
            addIns.add("cream");
        }
        Coffee coffee = new Coffee(cupSize, addIns, quantity);
        double price = coffee.itemPrice();
        coffeeTotal.setText(String.valueOf(df.format(price)));
    }

    /**
     * Event Handler for when milk is selected
     * @param event an Event representing some type of action
     */
    @FXML
    void milkSelected(ActionEvent event) {
        String cupSize = coffeeSize.getSelectionModel().getSelectedItem();
        Integer quantity = coffeeQuantity.getSelectionModel().getSelectedItem();
        if(!milk.isSelected()) {
            if(addIns.contains("milk"))
                addIns.remove("milk");
        }
        else {
            addIns.add("milk");
        }
        Coffee coffee = new Coffee(cupSize, addIns, quantity);
        double price = coffee.itemPrice();
        coffeeTotal.setText(String.valueOf(df.format(price)));
    }

    /**
     * Event Handler for when caramel is selected
     * @param event an Event representing some type of action
     */
    @FXML
    void caramelSelected(ActionEvent event) {
        String cupSize = coffeeSize.getSelectionModel().getSelectedItem();
        Integer quantity = coffeeQuantity.getSelectionModel().getSelectedItem();
        if(!caramel.isSelected()) {
            if(addIns.contains("caramel"))
                addIns.remove("caramel");
        }
        else {
            addIns.add("caramel");
        }
        Coffee coffee = new Coffee(cupSize, addIns, quantity);
        double price = coffee.itemPrice();
        coffeeTotal.setText(String.valueOf(df.format(price)));
    }

    /**
     * Event Handler for when syrup is selected
     * @param event an Event representing some type of action
     */
    @FXML
    void syrupSelected(ActionEvent event) {
        String cupSize = coffeeSize.getSelectionModel().getSelectedItem();
        Integer quantity = coffeeQuantity.getSelectionModel().getSelectedItem();
        if(!syrup.isSelected()) {
            if(addIns.contains("syrup"))
                addIns.remove("syrup");
        }
        else {
            addIns.add("syrup");
        }
        Coffee coffee = new Coffee(cupSize, addIns, quantity);
        double price = coffee.itemPrice();
        coffeeTotal.setText(String.valueOf(df.format(price)));
    }

    /**
     * Event Handler for when whipped cream is selected
     * @param event an Event representing some type of action
     */
    @FXML
    void whippedSelected(ActionEvent event) {
        String cupSize = coffeeSize.getSelectionModel().getSelectedItem();
        Integer quantity = coffeeQuantity.getSelectionModel().getSelectedItem();
        if(!whipped.isSelected()) {
            if(addIns.contains("whipped cream"))
                addIns.remove("whipped cream");
        }
        else {
            addIns.add("whipped cream");
        }
        Coffee coffee = new Coffee(cupSize, addIns, quantity);
        double price = coffee.itemPrice();
        coffeeTotal.setText(String.valueOf(df.format(price)));
    }

    /**
     * Event Handler for when user selects quantity
     * @param event an Event representing some type of action
     */
    @FXML
    void quantitySelected(ActionEvent event) {
        Coffee coffeeOrder = new Coffee(coffeeSize.getSelectionModel().getSelectedItem(), addIns, coffeeQuantity.getSelectionModel().getSelectedItem());
        double price = coffeeOrder.itemPrice();
        coffeeTotal.setText(String.valueOf(df.format(price)));
    }

    /**
     * Event Handler for when user selects size
     * @param event an Event representing some type of action
     */
    @FXML
    void sizeSelected(ActionEvent event) {
        Coffee coffeeOrder = new Coffee(coffeeSize.getSelectionModel().getSelectedItem(), addIns, coffeeQuantity.getSelectionModel().getSelectedItem());
        double price = coffeeOrder.itemPrice();
        coffeeTotal.setText(String.valueOf(df.format(price)));
    }

    /**
     * Event Handler for when user clicks Add to Order button
     * @param event an Event representing some type of action
     */
    @FXML
    void coffeeAddtoOrder(ActionEvent event) {
        Coffee coffeeOrder = new Coffee(coffeeSize.getSelectionModel().getSelectedItem(), (ArrayList<String>) addIns.clone(), coffeeQuantity.getSelectionModel().getSelectedItem());
        mainController.addToOrder(coffeeOrder);

        whipped.setSelected(false);
        cream.setSelected(false);
        caramel.setSelected(false);
        syrup.setSelected(false);
        milk.setSelected(false);
        coffeeSize.setValue("Short");
        coffeeQuantity.setValue(1);
        coffeeTotal.setText("1.69");
        addIns.clear();

        Alert a = new Alert(Alert.AlertType.CONFIRMATION);
        a.setContentText("Coffee Order Placed");
        a.show();
    }
}
