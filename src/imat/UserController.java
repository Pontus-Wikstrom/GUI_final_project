package imat;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import se.chalmers.cse.dat216.project.Order;

public class UserController extends AnchorPane{
    @FXML
    private TextField text_input_kortnr;
    @FXML
    private TextField text_input_month;
    @FXML
    private TextField text_input_year;
    @FXML
    private TextField text_input_cvc;
    
    @FXML
    private FlowPane historyFlowPane;

    private HashMap<String, HistoryCardController> historyCardHashMap;
    private List<Order> orderHistoryList;
    private final Model model = Model.getInstance();

    public UserController(HashMap<String, HistoryCardController> historyCardHashMap) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("user.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        System.out.println("hello");

        try {
            fxmlLoader.load();
        } catch (
                IOException exception) {
            throw new RuntimeException(exception);
        }

        this.historyCardHashMap = historyCardHashMap;
        orderHistoryList = model.getOrderHistory();
        initFlowPane();

        //String login_name = text_input_loginname.getText();

        // String kortnummer = text_input_kortnr.getText();
        // String month = text_input_month.getText();
        // String year = text_input_year.getText();
        // String cvc = text_input_cvc.getText();

    }

    private void initFlowPane() {
        historyFlowPane.getChildren().clear();
        historyFlowPane.setHgap(20);
        historyFlowPane.setVgap(20);
    }
}
