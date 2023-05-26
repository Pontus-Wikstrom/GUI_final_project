package imat;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

public class ProductCardInfoController extends AnchorPane {

    
    private MainViewController parentController;

    public ProductCardInfoController(MainViewController parentController) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("product_info.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        this.parentController = parentController;
    }

    @FXML
    public void productDescriptionClose() {
        parentController.productDescriptionClose();
    }
}
