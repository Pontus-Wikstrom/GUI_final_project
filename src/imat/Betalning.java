package imat;

import com.sun.tools.javac.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class Betalning extends AnchorPane {

    MainViewController controller;
    @FXML
    private Pane paypal;
    @FXML
    private Pane klarna;
    @FXML
    private Pane card;
    @FXML
    private Pane swish;
    @FXML
    private Label tillbaka;
    @FXML
    private Label payment;
    @FXML
    private Label delivery;
    @FXML
    private Label shoppingCart;
    @FXML
    private Label slutPris;
    @FXML
    private Label doneButton;
    @FXML
    private Label adress;
    @FXML
    private AnchorPane paymentOptionContent;

    public Betalning(MainViewController controller) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Betalning.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        System.out.println("hello");

        try {
            fxmlLoader.load();
        } catch (
                IOException exception) {
            throw new RuntimeException(exception);
        }
        this.controller = controller;

    }

    public void payPalOption(){
        paypal.setStyle("-fx-background-color: #ffffff");
    }
}
