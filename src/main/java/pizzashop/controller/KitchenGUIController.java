package pizzashop.controller;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import java.util.Calendar;

public class KitchenGUIController {
    //TODO: Missing generic
    @FXML
    private ListView<String> kitchenOrdersList;
    @FXML
    public Button cook;
    @FXML
    public Button ready;

    public static  ObservableList<String> order = FXCollections.observableArrayList();
    private Object selectedOrder;
    private Calendar now = Calendar.getInstance();
    //TODO: No need to call new String(). "" is enough
    private String extractedTableNumberString = "";
    private int extractedTableNumberInteger;

    public void initialize() {
        //TODO: Updates to the list where made using a thread with busy waiting. Changed to use the observer pattern
        kitchenOrdersList.setItems(order);
        order.addListener((ListChangeListener<? super String>) change -> {
            kitchenOrdersList.setItems(order);
        });
        //Controller for Cook Button
        cook.setOnAction(event -> {
            selectedOrder = kitchenOrdersList.getSelectionModel().getSelectedItem();
            if(selectedOrder == null) {
                //TODO: Eroare daca se apasa butonul fara sa fie selectat un item
                return;
            }
            kitchenOrdersList.getItems().remove(selectedOrder);
            kitchenOrdersList.getItems().add(selectedOrder.toString()
                     .concat(" Cooking started at: ").toUpperCase()
                     .concat(now.get(Calendar.HOUR)+":"+now.get(Calendar.MINUTE)));
        });
        //Controller for Ready Button
        ready.setOnAction(event -> {
            selectedOrder = kitchenOrdersList.getSelectionModel().getSelectedItem();
            if(selectedOrder == null) {
                //TODO: Eroare daca se apasa butonul fara sa fie selectat un item
                return;
            }
            kitchenOrdersList.getItems().remove(selectedOrder);
            extractedTableNumberString = selectedOrder.toString().subSequence(5, 6).toString();
            extractedTableNumberInteger = Integer.valueOf(extractedTableNumberString);
            System.out.println("--------------------------");
            System.out.println("Table " + extractedTableNumberInteger +" ready at: " + now.get(Calendar.HOUR)+":"+now.get(Calendar.MINUTE));
            System.out.println("--------------------------");
        });
    }
}
