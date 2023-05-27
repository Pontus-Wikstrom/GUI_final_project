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

    public void cardInput() {

        creditCard.setCardNumber(text_input_kortnr.getText());
        creditCard.setValidYear(Integer.parseInt(text_input_year.getText()));
        creditCard.setValidMonth(Integer.parseInt(text_input_month.getText()));
        creditCard.setVerificationCode(Integer.parseInt(text_input_cvc.getText()));
    }
}
