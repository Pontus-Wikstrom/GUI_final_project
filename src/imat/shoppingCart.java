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

    private final Model model = Model.getInstance();
    @FXML
    private Label delivery;
    @FXML
    private Label payment;
    @FXML
    private Label shoppingCart;
    @FXML
    private FlowPane varukorgWizardLista;

    @FXML
    private Label totalpris;


    public shoppingCart(MainViewController controller){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("wizardVarukorg.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        System.out.println("hello");

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        this.controller = controller;

        addVara();
        setPrice();

    }

    public void addVara(){

    }

    public void back() {
        //back to main
    }

    public void nextStep() {
        //go to leverans
    }

    public void setPrice() {

    }
}
