package imat;

import java.io.IOException;
import java.util.HashMap;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import se.chalmers.cse.dat216.project.Product;

public class FavouritesPageController extends AnchorPane{
    @FXML
    private FlowPane favouritesPageFlowPane;

    private HashMap<String, ProductCardController> productCardHashMap;

    private final Model model = Model.getInstance();

    public FavouritesPageController(HashMap<String, ProductCardController> productCardHashMap) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("favoriter.fxml"));
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
        favouritesPageFlowPane.getChildren().clear();
        favouritesPageFlowPane.setHgap(20);
        favouritesPageFlowPane.setVgap(20);    
    }

    public void fillProductListFlowPane() {
        favouritesPageFlowPane.getChildren().clear();

        for (Product product : model.getFavourites()) {
            favouritesPageFlowPane.getChildren().add(this.productCardHashMap.get(product.getName()));
        }
    }
}
