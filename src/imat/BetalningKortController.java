package imat;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import se.chalmers.cse.dat216.project.CreditCard;
import se.chalmers.cse.dat216.project.IMatDataHandler;

public class BetalningKortController extends AnchorPane{
    @FXML
    private TextField text_input_kortnr;
    @FXML
    private TextField text_input_month;
    @FXML
    private TextField text_input_year;
    @FXML
    private TextField text_input_cvc;
    CreditCard creditCard;

    public BetalningKortController() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("betalning_kort.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        System.out.println("hello");

        try {
            fxmlLoader.load();
        } catch (
                IOException exception) {
            throw new RuntimeException(exception);
        }
        creditCard = IMatDataHandler.getInstance().getCreditCard();
    }

    public boolean controlCard() {
        System.out.println(text_input_kortnr.getText());
        if (text_input_kortnr.getText().isBlank() || text_input_year.getText().isBlank()
                || text_input_month.getText().isBlank() || text_input_cvc.getText().isBlank()) {
            System.out.println("False");
            return false;
        }
        System.out.println("True");
        return true;

    }

    public void preFilled() {
        if (creditCard.getCardNumber() != null && creditCard.getCardNumber() != "") {
            text_input_kortnr.setText(creditCard.getCardNumber());
        }
        if (creditCard.getValidYear() != 0) {
            text_input_year.setText(String.valueOf(creditCard.getValidYear()));
        }
        if (creditCard.getValidMonth() != 0) {
            text_input_month.setText(String.valueOf(creditCard.getValidMonth()));
        }
        if (creditCard.getVerificationCode() != 0) {
            text_input_cvc.setText(String.valueOf(creditCard.getVerificationCode()));
        }
    }
}
