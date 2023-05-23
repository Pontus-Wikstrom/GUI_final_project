package imat;

import java.io.IOException;
import java.util.HashMap;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.FlowPane;

public class OffersPageController extends AnchorPane{
    @FXML
    private FlowPane offersListFlowPane;

    private HashMap<String, ProductCardController> productCardHashMap;
    private final Model model = Model.getInstance();

    public OffersPageController(HashMap<String, ProductCardController> productCardHashMap) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("erbjudanden.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        this.productCardHashMap = productCardHashMap;

        initFlowPane();
    }

    private void initFlowPane() {
        offersListFlowPane.getChildren().clear();
        offersListFlowPane.setHgap(20);
        offersListFlowPane.setVgap(20);     
    }

    public void fillProductListFlowPane() {
        offersListFlowPane.getChildren().clear();

        offersListFlowPane.getChildren().addAll(this.productCardHashMap.values());
    }
}
