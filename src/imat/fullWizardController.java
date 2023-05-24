package imat;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import se.chalmers.cse.dat216.project.Customer;
import se.chalmers.cse.dat216.project.User;

import java.io.IOException;
import java.util.Random;

public class fullWizardController extends AnchorPane{

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
    Customer customer;
    User user;
    int datum = 10;
    int month = 5;
    int weekday = 0;
    String day;
    String date;
    String bokningsbar;
    String timeSpot;

    String[] veckodagar = {"Måndag", "Tisdag", "Onsdag", "Torsdag", "Fredag", "Lördag", "Söndag"};

    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;
    @FXML
    private TextField email;
    @FXML
    private TextField phoneNumber;
    @FXML
    private TextField city;
    @FXML
    private TextField address;
    @FXML
    private TextField postNumber;
    @FXML
    private TextField portinfo;
    @FXML
    private TextArea meddelanden;
    @FXML
    private Text dag1;
    @FXML
    private Text dag2;
    @FXML
    private Text dag3;
    @FXML
    private Text dag4;
    @FXML
    private Text datum1;
    @FXML
    private Text datum2;
    @FXML
    private Text datum3;
    @FXML
    private Text datum4;
    @FXML
    private AnchorPane day1;
    @FXML
    private AnchorPane day2;
    @FXML
    private AnchorPane day3;
    @FXML
    private AnchorPane day4;
    @FXML
    private AnchorPane tid1;
    @FXML
    private AnchorPane tid2;
    @FXML
    private AnchorPane tid3;
    @FXML
    private AnchorPane tid4;
    @FXML
    private AnchorPane tid5;
    @FXML
    private AnchorPane tid6;
    @FXML
    private AnchorPane tid7;
    @FXML
    private AnchorPane tid8;
    @FXML
    private AnchorPane tid9;
    @FXML
    private Text t1;
    @FXML
    private Text t2;
    @FXML
    private Text t3;
    @FXML
    private Text t4;
    @FXML
    private Text t5;
    @FXML
    private Text t6;
    @FXML
    private Text t7;
    @FXML
    private Text t8;
    @FXML
    private Text t9;
    @FXML
    private Text b1;
    @FXML
    private Text b2;
    @FXML
    private Text b3;
    @FXML
    private Text b4;
    @FXML
    private Text b5;
    @FXML
    private Text b6;
    @FXML
    private Text b7;
    @FXML
    private Text b8;
    @FXML
    private Text b9;

    private final Model model = Model.getInstance();
    @FXML
    private FlowPane varukorgWizardLista;
    @FXML
    private Label totalpris;
    @FXML
    private AnchorPane wizardLeveransSteg;
    @FXML
    private AnchorPane wizardVarukorgSteg;
    @FXML
    private AnchorPane wizardBetalSteg;
    @FXML
    private AnchorPane wizardDoneSteg;


    public fullWizardController(MainViewController controller){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fullWizard.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        System.out.println("hello");

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        if (user != null) {
            loadDataFromAccount();
        }

        this.controller = controller;
        setBokningsbar();
        setTimes();
        setDates();
        setWeekday();

        addVara();
        setPrice();
    }

    public void addVara(){

    }

    public void setPrice() {

    }

    public void loadDataFromAccount() {
        firstName.setText(customer.getFirstName());
        lastName.setText(customer.getLastName());
        email.setText(customer.getEmail());
        phoneNumber.setText(customer.getPhoneNumber());
        city.setText(customer.getPostAddress());
        address.setText(customer.getAddress());
        postNumber.setText(customer.getPostCode());
    }

    public void getDataFromCustomer(){
        customer.setFirstName(firstName.getText());
        customer.setLastName(lastName.getText());
        customer.setAddress(address.getText());
        customer.setPostCode(postNumber.getText());
        customer.setPostAddress(city.getText());
        customer.setEmail(email.getText());
        customer.setPhoneNumber(phoneNumber.getText());
        String portInfo = portinfo.getText();
        String meddelande = meddelanden.getText();
    }

    public void stepBack(){if (firstName != null) {
        customer.setFirstName(firstName.getText());
    }
        if (firstName != null) {
            customer.setLastName(lastName.getText());
        }
        if (firstName != null) {
            customer.setPostCode(postNumber.getText());
        }
        if (firstName != null) {
            customer.setPostAddress(city.getText());
        }
        if (firstName != null) {
            customer.setAddress(address.getText());
        }
        if (firstName != null) {
            customer.setPhoneNumber(phoneNumber.getText());
        }
        if (firstName != null) {
            customer.setEmail(email.getText());
        }

        //Get back to shoppingCart
        wizardVarukorgSteg.toFront();

    }
    public void backToMain(){
        if (firstName != null) {
            customer.setFirstName(firstName.getText());
        }
        if (firstName != null) {
            customer.setLastName(lastName.getText());
        }
        if (firstName != null) {
            customer.setPostCode(postNumber.getText());
        }
        if (firstName != null) {
            customer.setPostAddress(city.getText());
        }
        if (firstName != null) {
            customer.setAddress(address.getText());
        }
        if (firstName != null) {
            customer.setPhoneNumber(phoneNumber.getText());
        }
        if (firstName != null) {
            customer.setEmail(email.getText());
        }
        //Get back to main
        controller.homePageClick();
    }

    public void nextStep1(){
        getDataFromCustomer();
        if (customer.getFirstName() != null & customer.getLastName() != null & customer.getAddress() != null & customer.getEmail() != null & customer.getPhoneNumber() != null & customer.getPostAddress() != null & customer.getPostCode() != null & bokningsbar != "Fullbokad") {
            //go to pay
            wizardBetalSteg.toFront();
        }

    }
    public void toDelivery(){
        wizardLeveransSteg.toFront();
    }
    public void backToDelivery(){
        wizardLeveransSteg.toFront();
    }

    public void backToShoppingCart(){
        if (firstName != null) {
            customer.setFirstName(firstName.getText());
        }
        if (firstName != null) {
            customer.setLastName(lastName.getText());
        }
        if (firstName != null) {
            customer.setPostCode(postNumber.getText());
        }
        if (firstName != null) {
            customer.setPostAddress(city.getText());
        }
        if (firstName != null) {
            customer.setAddress(address.getText());
        }
        if (firstName != null) {
            customer.setPhoneNumber(phoneNumber.getText());
        }
        if (firstName != null) {
            customer.setEmail(email.getText());
        }
        wizardVarukorgSteg.toFront();
    }

    public void toFinishPage(){
        wizardDoneSteg.toFront();
    }

    public void clicked1() {
        date = datum1.getText();
        day = dag1.getText();
        setBokningsbar();
        day1.setStyle("-fx-background-color: #2B9EB3");
        day2.setStyle("-fx-background-color: #f5f5f5");
        day3.setStyle("-fx-background-color: #f5f5f5");
        day4.setStyle("-fx-background-color: #f5f5f5");
    }

    public void clicked2() {
        date = datum2.getText();
        day = dag2.getText();
        setBokningsbar();
        day2.setStyle("-fx-background-color: #2B9EB3");
        day1.setStyle("-fx-background-color: #f5f5f5");
        day3.setStyle("-fx-background-color: #f5f5f5");
        day4.setStyle("-fx-background-color: #f5f5f5");
    }
    public void clicked3() {
        date = datum3.getText();
        day = dag3.getText();
        setBokningsbar();
        day3.setStyle("-fx-background-color: #2B9EB3");
        day4.setStyle("-fx-background-color: #f5f5f5");
        day1.setStyle("-fx-background-color: #f5f5f5");
        day2.setStyle("-fx-background-color: #f5f5f5");
    }

    public void clicked4() {
        date = datum4.getText();
        day = dag4.getText();
        setBokningsbar();
        day4.setStyle("-fx-background-color: #2B9EB3");
        day1.setStyle("-fx-background-color: #f5f5f5");
        day2.setStyle("-fx-background-color: #f5f5f5");
        day3.setStyle("-fx-background-color: #f5f5f5");

    }

    public void time1() {
        timeSpot = t1.getText();
        bokningsbar = b1.getText();
        tid1.setStyle("-fx-background-color: #2B9EB3");

    }

    public void time2() {
        timeSpot = t2.getText();
        bokningsbar = b2.getText();
        tid4.setStyle("-fx-background-color: #2B9EB3");
    }

    public void time3() {
        timeSpot = t3.getText();
        bokningsbar = b3.getText();
        tid7.setStyle("-fx-background-color: #2B9EB3");
    }

    public void time4() {
        timeSpot = t4.getText();
        bokningsbar = b4.getText();
        tid2.setStyle("-fx-background-color: #2B9EB3");
    }

    public void time5() {
        timeSpot = t5.getText();
        bokningsbar = b5.getText();
        tid5.setStyle("-fx-background-color: #2B9EB3");
    }

    public void time6() {
        timeSpot = t6.getText();
        bokningsbar = b6.getText();
        tid8.setStyle("-fx-background-color: #2B9EB3");
    }

    public void time7() {
        timeSpot = t7.getText();
        bokningsbar = b7.getText();
        tid3.setStyle("-fx-background-color: #2B9EB3");
    }

    public void time8() {
        timeSpot = t8.getText();
        bokningsbar = b8.getText();
        tid6.setStyle("-fx-background-color: #2B9EB3");
    }

    public void time9() {
        timeSpot = t9.getText();
        bokningsbar = b9.getText();
        tid9.setStyle("-fx-background-color: #2B9EB3");
    }

    public void setTimes() {
        t1.setText("08:00 - 10:00");
        t2.setText("09:00 - 11:00");
        t3.setText("10:00 - 12:00");
        t4.setText("11:00 - 13:00");
        t5.setText("12:00 - 14:00");
        t6.setText("13:00 - 15:00");
        t7.setText("14:00 - 16:00");
        t8.setText("15:00 - 17:00");
        t9.setText("16:00 - 18:00");
    }

    public void setBokningsbar(){
        Random random = new Random();
        int avaliable = random.nextInt(2);
        if (avaliable == 1){
            b1.setText("79.00kr");
        }else{
            b1.setText("Fullbokad");
        }
        avaliable = random.nextInt(2);
        if (avaliable == 1){
            b2.setText("79.00kr");
        }else{
            b2.setText("Fullbokad");
        }
        avaliable = random.nextInt(2);
        if (avaliable == 1){
            b3.setText("79.00kr");
        }else{
            b3.setText("Fullbokad");
        }
        avaliable = random.nextInt(2);
        if (avaliable == 1){
            b4.setText("79.00kr");
        }else{
            b4.setText("Fullbokad");
        }
        avaliable = random.nextInt(2);
        if (avaliable == 1){
            b5.setText("79.00kr");
        }else{
            b5.setText("Fullbokad");
        }
        avaliable = random.nextInt(2);
        if (avaliable == 1){
            b6.setText("79.00kr");
        }else{
            b6.setText("Fullbokad");
        }
        avaliable = random.nextInt(2);
        if (avaliable == 1){
            b7.setText("79.00kr");
        }else{
            b7.setText("Fullbokad");
        }
        avaliable = random.nextInt(2);
        if (avaliable == 1){
            b8.setText("79.00kr");
        }else{
            b8.setText("Fullbokad");
        }
        avaliable = random.nextInt(2);
        if (avaliable == 1){
            b9.setText("79.00kr");
        }else{
            b9.setText("Fullbokad");
        }

    }

    public void setDates() {
        datum1.setText(getDate());
        datum2.setText(getDate());
        datum3.setText(getDate());
        datum4.setText(getDate());
    }

    public String getDate(){
        datum += 1;
        if (month == 1 | month == 3 | month == 5 | month == 7 | month == 8 | month == 10 | month == 12) {
            if (datum % 32 == 0) {
                month++;
                datum = 1;
            }
        }else if (month == 2) {
            if (datum % 29 == 0) {
                month++;
                datum = 1;
            }
        }else {
            if (datum % 31 == 0) {
                month++;
                datum = 1;
            }
        }
        if (month == 13) {
            month = 1;
        }

        return datum + "/" + month;
    }

    public void earlier() {
        datum -= 8;
        if (datum < 0) {
            month -= 1;
            if (month == 0) {
                month = 12;
            }
            if (month == 1 | month == 3 | month == 5 | month == 7 | month == 8 | month == 10 | month == 12) {
                datum = 31 + datum;
            }else if (month == 2) {
                datum = 28 + datum;
            }else {
                datum = 30 + datum;
            }
        }
        weekday -= 8;
        while (weekday < 0) {
            weekday += 7;
        }
        setDates();
        setWeekday();
    }

    public void later() {
        setDates();
        setWeekday();
    }

    public void setWeekday() {
        dag1.setText(getWeekday());
        dag2.setText(getWeekday());
        dag3.setText(getWeekday());
        dag4.setText(getWeekday());
        System.out.println("no");
    }

    public String getWeekday() {
        weekday++;
        if (weekday == 7) {
            weekday = 0;
        }
        return veckodagar[weekday];
    }

    public void payPalOption(){
        paypal.setStyle("-fx-background-color: #ffffff");
        klarna.setStyle("-fx-background-color: #f5f5f5");
        card.setStyle("-fx-background-color: #f5f5f5");
        swish.setStyle("-fx-background-color: #f5f5f5");
    }
    public void klarnaOption(){
        paypal.setStyle("-fx-background-color: #f5f5f5");
        klarna.setStyle("-fx-background-color: #ffffff");
        card.setStyle("-fx-background-color: #f5f5f5");
        swish.setStyle("-fx-background-color: #f5f5f5");
    }
    public void cardOption(){
        paypal.setStyle("-fx-background-color: #f5f5f5");
        klarna.setStyle("-fx-background-color: #f5f5f5");
        card.setStyle("-fx-background-color: #ffffff");
        swish.setStyle("-fx-background-color: #f5f5f5");
    }
    public void swishOption(){
        paypal.setStyle("-fx-background-color: #f5f5f5");
        klarna.setStyle("-fx-background-color: #f5f5f5");
        card.setStyle("-fx-background-color: #f5f5f5");
        swish.setStyle("-fx-background-color: #ffffff");
    }
}
