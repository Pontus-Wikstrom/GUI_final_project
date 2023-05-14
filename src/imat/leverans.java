package imat;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

public class leverans extends AnchorPane{
    MainViewController controller;
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

        this.controller = controller;
    }

}
