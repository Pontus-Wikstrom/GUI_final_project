package imat;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import se.chalmers.cse.dat216.project.ShoppingItem;

import java.io.IOException;

public class shoppingCart extends AnchorPane {
    MainViewController controller;

    @FXML
    private FlowPane dinaVaror;
    @FXML
    private Label KassaKnapp;
    @FXML
    private Label Totalpris;

    public shoppingCart(MainViewController controller){
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

    }

    public void toPayment(){
        controller.toPayment();
    }
}
