package imat;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.scene.Node;

import java.util.ArrayList;
import java.util.Random;


public class rec_datum_tid {
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

    public void clicked1() {
        String date = datum1.getText();
        String day = dag1.getText();
    }

    public void clicked2() {
        String date = datum2.getText();
        String day = dag2.getText();
    }
    public void clicked3() {
        String date = datum3.getText();
        String day = dag3.getText();
    }

    public void clicked4() {
        String date = datum4.getText();
        String day = dag4.getText();
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
        ArrayList<Text> all = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i <10; i++) {
            int avaliable = random.nextInt(2);
            if (avaliable == 1) {
                all.get(i).setText("79.00kr");
            }else {
                all.get(i).setText("Fullbokad");
            }
        }
    }
}
