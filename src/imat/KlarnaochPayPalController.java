package imat;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class KlarnaochPayPalController extends AnchorPane {

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

    }

    public boolean controlEmail() {
        if (email.getText().isEmpty()) {
            System.out.println("False");
            return false;
        }
        System.out.println("True");
        return true;

    }
}

