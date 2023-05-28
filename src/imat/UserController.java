package imat;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import se.chalmers.cse.dat216.project.*;

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
    private TextField text_input_email;
    @FXML
    private TextField text_input_phone;
    @FXML
    private TextField text_input_address;
    @FXML
    private TextField text_input_city;
    @FXML
    private TextField text_input_postCode;
    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;
    @FXML
    private Label userName;
    
    @FXML
    private FlowPane historyFlowPane;

    private HashMap<String, HistoryCardController> historyCardHashMap;
    private List<Order> orderHistoryList;
    private final Model model = Model.getInstance();
    Customer customer;
    CreditCard creditCard;
    User user;



<<<<<<< HEAD
    private double vBarPosition = 0;

=======
>>>>>>> parent of eb538de (Merge branch 'not_broken_2_pls')
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
        customer = IMatDataHandler.getInstance().getCustomer();
        creditCard = IMatDataHandler.getInstance().getCreditCard();
        user = IMatDataHandler.getInstance().getUser();
        
        //initFlowPane();

        //String login_name = text_input_loginname.getText();



    }

    public void saveUserInput(){
        customer.setAddress(text_input_address.getText());
        customer.setPostCode(text_input_postCode.getText());
        customer.setPhoneNumber(text_input_phone.getText());
        customer.setPostAddress(text_input_city.getText());
        customer.setEmail(text_input_email.getText());
        customer.setMobilePhoneNumber(text_input_phone.getText());

    }
<<<<<<< HEAD
    
    public void setScrollPanePosition(double position) {
        vBarPosition = position;
    }

    public void testData() {
        System.out.println(userScrollPane.getVmin());
        System.out.println(userScrollPane.getVmax());
        System.out.println(userScrollPane.getVvalue());
        System.out.println(userScrollPane.getVbarPolicy());
        System.out.println("---------------------");
    }


    public void saveCardInput(){
        user.setUserName(firstName.getText() + " " + lastName.getText());
        userName.setText(firstName.getText());
        creditCard.setHoldersName(firstName.getText() + " " + lastName.getText());
        customer.setFirstName(firstName.getText());
        customer.setLastName(lastName.getText());

        creditCard.setCardNumber(text_input_kortnr.getText());
        if (text_input_cvc.getText() != "") {
            creditCard.setVerificationCode(Integer.parseInt(text_input_cvc.getText()));
        }
        if (text_input_year.getText() != "") {
            creditCard.setValidMonth(Integer.parseInt(text_input_month.getText()));
        }
        if (text_input_month.getText() != "") {
            creditCard.setValidYear(Integer.parseInt(text_input_year.getText()));
        }

        System.out.println("hello hello" + creditCard.getCardNumber());
    }
=======
>>>>>>> parent of eb538de (Merge branch 'not_broken_2_pls')

    public void showUserData(){
        firstName.setText(customer.getFirstName());
        lastName.setText(customer.getLastName());
        text_input_kortnr.setText(creditCard.getCardNumber());
        text_input_month.setText(String.valueOf(creditCard.getValidMonth()));
        text_input_year.setText(String.valueOf(creditCard.getValidYear()));
        text_input_cvc.setText(String.valueOf(creditCard.getVerificationCode()));
        text_input_postCode.setText(customer.getPostCode());
        text_input_address.setText(customer.getAddress());
        text_input_city.setText(customer.getPostAddress());
        text_input_email.setText(customer.getEmail());
        text_input_phone.setText(customer.getPhoneNumber());
    }


    private void updateOrderHistoryList() {
        orderHistoryList = model.getOrderHistory();
    }

    private void initFlowPane() {
        historyFlowPane.getChildren().clear();
        historyFlowPane.setHgap(20);
        historyFlowPane.setVgap(20);
    }

    public void updateFlowPane() {
        updateOrderHistoryList();
        historyFlowPane.getChildren().clear();

        for (Order order : orderHistoryList) {
            historyFlowPane.getChildren().add(new OrderHistoryCardController(order));
        }
    }
}
