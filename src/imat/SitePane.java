package imat;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

public class SitePane extends AnchorPane{
    private MainViewController controller;
    FXMLLoader fxmlLoader;

    public SitePane(MainViewController controller, FXMLLoader fxmlLoader) {
        this.fxmlLoader = fxmlLoader;
        this.fxmlLoader.setController(this);

        try {
            this.fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        this.controller = controller;
    }
}
