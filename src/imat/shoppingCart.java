package imat;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import se.chalmers.cse.dat216.project.CartEvent;
import se.chalmers.cse.dat216.project.Product;
import se.chalmers.cse.dat216.project.ShoppingCartListener;
import se.chalmers.cse.dat216.project.ShoppingItem;

import java.io.IOException;
import java.util.HashMap;

public class shoppingCart extends AnchorPane implements ShoppingCartListener {
    MainViewController controller;

    @FXML
    private FlowPane productsFlowPane;
    @FXML
    private Label Totalpris;

    private final Model model = Model.getInstance();
    private HashMap<String, HistoryCardController> historyCardHashMap;

    public shoppingCart(MainViewController controller, HashMap<String, HistoryCardController> historyCardHashMap){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Varukorg.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        System.out.println("hello");

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        this.controller = controller;
        this.historyCardHashMap = historyCardHashMap;

        model.getShoppingCart().addShoppingCartListener(this);

        initFlowPane();
    }

    private void initFlowPane() {
        productsFlowPane.getChildren().clear();
        productsFlowPane.setVgap(20);
    }

    public void toPayment(){
        controller.toPayment();
    }

    private void updateProductFlowPane() {
        productsFlowPane.getChildren().clear();

        for (ShoppingItem item : model.getShoppingCart().getItems()) {
            Product product = item.getProduct();
            productsFlowPane.getChildren().add(this.historyCardHashMap.get(product.getName()));
        }
    }

    @Override
    public void shoppingCartChanged(CartEvent arg0) {
        updateProductFlowPane();    
        
    }
}
