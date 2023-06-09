package imat;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import se.chalmers.cse.dat216.project.Customer;
import se.chalmers.cse.dat216.project.IMatDataHandler;

import java.io.IOException;

public class KlarnaochPayPalController extends AnchorPane {

    Customer customer;

    @FXML
    private TextField email;

    public KlarnaochPayPalController() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("paypalochklarna.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        System.out.println("hello");

        try {
            fxmlLoader.load();
        } catch (
                IOException exception) {
            throw new RuntimeException(exception);
        }
        customer = IMatDataHandler.getInstance().getCustomer();

    }

    public boolean controlEmail() {
        if (email.getText().isBlank()) {
            return false;
        }
        return true;
    }

    public void preFilled() {
        if (customer.getEmail() != null && customer.getEmail() != "") {
            email.setText(customer.getEmail());
        }
    }
}


