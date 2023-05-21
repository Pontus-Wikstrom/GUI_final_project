package imat;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import se.chalmers.cse.dat216.project.Customer;
import se.chalmers.cse.dat216.project.User;

public class leverans extends AnchorPane{
    MainViewController controller;
    Customer customer;
    User user;
    int datum = 10;
    int month = 5;
    int weekday = 0;

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

    public leverans(MainViewController controller){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("leverans.fxml"));
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

    public void back(){
        getDataFromCustomer();
        //Get back to shoppingCart

    }

    public void backToMain(){
        getDataFromCustomer();
        //Get back to main
    }

    public void next(){
        getDataFromCustomer();
        //go to pay
    }

    public void clicked1() {
        String date = datum1.getText();
        String day = dag1.getText();
        setBokningsbar();
    }

    public void clicked2() {
        String date = datum2.getText();
        String day = dag2.getText();
        setBokningsbar();
    }
    public void clicked3() {
        String date = datum3.getText();
        String day = dag3.getText();
        setBokningsbar();
    }

    public void clicked4() {
        String date = datum4.getText();
        String day = dag4.getText();
        setBokningsbar();
    }

    public void time1() {
        String timeSpot = t1.getText();
        String bokningsbar = b1.getText();
    }

    public void time2() {
        String timeSpot = t2.getText();
        String bokningsbar = b2.getText();
    }

    public void time3() {
        String timeSpot = t3.getText();
        String bokningsbar = b3.getText();
    }

    public void time4() {
        String timeSpot = t4.getText();
        String bokningsbar = b4.getText();
    }

    public void time5() {
        String timeSpot = t5.getText();
        String bokningsbar = b5.getText();
    }

    public void time6() {
        String timeSpot = t6.getText();
        String bokningsbar = b6.getText();
    }

    public void time7() {
        String timeSpot = t7.getText();
        String bokningsbar = b7.getText();
    }

    public void time8() {
        String timeSpot = t8.getText();
        String bokningsbar = b8.getText();
    }

    public void time9() {
        String timeSpot = t9.getText();
        String bokningsbar = b9.getText();
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

}
