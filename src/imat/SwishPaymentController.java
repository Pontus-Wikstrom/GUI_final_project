package imat;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import se.chalmers.cse.dat216.project.Customer;
import se.chalmers.cse.dat216.project.IMatDataHandler;

import java.io.IOException;

public class SwishPaymentController extends AnchorPane {

    @FXML
    private TextField phoneNumber;
    Customer customer;

    public SwishPaymentController() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("betalning_telefonnr.fxml"));
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

    public boolean controlPhoneNumber() {
        if (phoneNumber.getText().isBlank()) {
            return false;
        }
        return true;
    }

    public void preFilled() {
        if (customer.getPhoneNumber() != null && customer.getPhoneNumber() != "") {
            phoneNumber.setText(customer.getPhoneNumber());
        }
    }
}
