package imat;

import java.io.IOException;
import java.util.HashMap;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.FlowPane;
import se.chalmers.cse.dat216.project.CartEvent;
import se.chalmers.cse.dat216.project.Product;
import se.chalmers.cse.dat216.project.ShoppingCartListener;

public class OffersPageController extends AnchorPane implements ShoppingCartListener {
    @FXML
    private FlowPane offersListFlowPane;
    @FXML
    private AnchorPane toPayment;

    private HashMap<String, ProductCardController> productCardHashMap;
    private final Model model = Model.getInstance();
    private MainViewController parentController;

    public OffersPageController(HashMap<String, ProductCardController> productCardHashMap, MainViewController parentController) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("erbjudanden.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        this.productCardHashMap = productCardHashMap;
        this.parentController = parentController;

        initFlowPane();
        model.getShoppingCart().addShoppingCartListener(this);
    }

    @FXML
    public void toPayment() {
        if (model.getShoppingCart().getItems().size() != 0) parentController.toPayment();
    }

    private void initFlowPane() {
        offersListFlowPane.getChildren().clear();
        offersListFlowPane.setHgap(20);
        offersListFlowPane.setVgap(20);     
    }

    public void fillProductListFlowPane() {
        offersListFlowPane.getChildren().clear();

        int i = 0;
        for (Product product : model.getProducts()) {
            if (i<20) {
                offersListFlowPane.getChildren().add(this.productCardHashMap.get(product.getName()));
            }
            i++;
        }
    }

    @Override
    public void shoppingCartChanged(CartEvent arg0) {
        if (model.getShoppingCart().getItems().size() == 0) {
            toPayment.setVisible(false);
        } else {
            toPayment.setVisible(true);
        }
    }
}
