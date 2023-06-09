package imat;

import java.io.IOException;
import java.util.HashMap;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import se.chalmers.cse.dat216.project.CartEvent;
import se.chalmers.cse.dat216.project.Product;
import se.chalmers.cse.dat216.project.ShoppingCartListener;

public class FavouritesPageController extends AnchorPane implements ShoppingCartListener{
    @FXML
    private FlowPane favouritesPageFlowPane;

    @FXML 
    private Label noFavouritesLabel;
    @FXML
    private AnchorPane toPayment;

    private HashMap<String, ProductCardController> productCardHashMap;

    private final Model model = Model.getInstance();
    private MainViewController parentController;

    public FavouritesPageController(HashMap<String, ProductCardController> productCardHashMap, MainViewController parentController) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("favoriter.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        this.productCardHashMap = productCardHashMap;
        this.parentController = parentController;

        model.getShoppingCart().addShoppingCartListener(this);
        initFlowPane();
    }

    @FXML
    public void toPayment() {
        if (model.getShoppingCart().getItems().size() != 0) parentController.toPayment();
    }

    private void initFlowPane() {
        favouritesPageFlowPane.getChildren().clear();
        favouritesPageFlowPane.setHgap(20);
        favouritesPageFlowPane.setVgap(20);    
    }

    public void fillProductListFlowPane() {
        favouritesPageFlowPane.getChildren().clear();
        if(model.getFavourites().isEmpty()){
            noFavouritesLabel.setVisible(true);
        }else{
        noFavouritesLabel.setVisible(false);
        }
        for (Product product : model.getFavourites()) {
            
            favouritesPageFlowPane.getChildren().add(this.productCardHashMap.get(product.getName()));
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
