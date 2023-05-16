package imat;

import javafx.scene.layout.AnchorPane;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;;

public class HelpSite extends AnchorPane {
    MainViewController controller;
    HelpSite(MainViewController controller) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("help_content.fxml"));
        //fxmlLoader.setRoot(this);
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
