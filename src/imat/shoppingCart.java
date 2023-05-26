package imat;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import se.chalmers.cse.dat216.project.CartEvent;
import se.chalmers.cse.dat216.project.Product;
import se.chalmers.cse.dat216.project.ShoppingCartListener;
import se.chalmers.cse.dat216.project.ShoppingItem;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class shoppingCart extends AnchorPane implements ShoppingCartListener {
    MainViewController controller;

    @FXML
    private FlowPane productsFlowPane;
    @FXML
    private Label Totalpris;

    private final Model model = Model.getInstance();
    private HashMap<String, ShoppingCartCardController> shoppingCartCardHashMap;

    public shoppingCart(MainViewController controller, HashMap<String, ShoppingCartCardController> shoppingCartCardHashMap){
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
        this.shoppingCartCardHashMap = shoppingCartCardHashMap;

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
        ObservableList<Node> productCardList = productsFlowPane.getChildren();

        List<String>  lst = new ArrayList<String>(0);
        for (Node item : productCardList) {
            lst.add(((ShoppingCartCardController) item).getName());
        }

        lst.sort(null);

        productsFlowPane.getChildren().clear();

        for (String productName : lst) {
            productsFlowPane.getChildren().add(this.shoppingCartCardHashMap.get(productName));
        }

        // for (ShoppingItem item : model.getShoppingCart().getItems()) {
        //     if (item.getAmount() == 0) {
        //         //productsFlowPane.getChildren().clear
        //     }
        // }
        /*for (ShoppingItem item : model.getShoppingCart().getItems()) {
            Product product = item.getProduct();
        }*/
    }

    public void fillShoppingCartFlowPane() {
        productsFlowPane.getChildren().clear();

        
        List<String>  lst = new ArrayList<String>(0);

        for (ShoppingItem item : model.getShoppingCart().getItems()) {
            String productName = item.getProduct().getName();
            lst.add(productName);
        }

        lst.sort(null);

        for (String productName : lst) {
            productsFlowPane.getChildren().add(this.shoppingCartCardHashMap.get(productName));
        } 
    }

    @Override
    public void shoppingCartChanged(CartEvent arg0) {
        //fillShoppingCartFlowPane();    
        
    }
}
