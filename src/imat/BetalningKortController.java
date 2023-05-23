package imat;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class BetalningKortController extends AnchorPane{
    @FXML
    private TextField text_input_kortnr;
    @FXML
    private TextField text_input_month;
    @FXML
    private TextField text_input_year;
    @FXML
    private TextField text_input_cvc;

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

        String kortnummer = text_input_kortnr.getText();
        String month = text_input_month.getText();
        String year = text_input_year.getText();
        String cvc = text_input_cvc.getText();

    }
}
