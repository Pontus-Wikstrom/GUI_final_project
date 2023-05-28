package imat;

import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import se.chalmers.cse.dat216.project.*;

public class HomePageController extends AnchorPane implements ShoppingCartListener {
    @FXML
    private TextField searchBar;
    @FXML
    private FlowPane productListFlowPane;
    @FXML
    private AnchorPane flowPaneAnchorPane;
    @FXML
    private ScrollPane homePageScrollPane;
    @FXML
    private Text currentSideText;
    @FXML
    private AnchorPane leftButton;
    @FXML
    private AnchorPane rightButton;
    @FXML
    private AnchorPane toPayment;

    private HashMap<String, ProductCardController> productCardHashMap;

    private final Model model = Model.getInstance();

    private int sideIndex;
    private List<Product> currentProducts;
    private MainViewController parentController;

    public HomePageController(HashMap<String, ProductCardController> productCardHashMap, MainViewController parentController) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("sökSida.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

    
        this.productCardHashMap = productCardHashMap;
        this.parentController = parentController;
        currentProducts = model.getProducts();
        sideIndex = 0;
        leftButton.setVisible(false);

        initFlowPane();
        searchBar.setFocusTraversable(false);

        homePageScrollPane.setFitToWidth(true);

        model.getShoppingCart().addShoppingCartListener(this);
        

        //searchBar.textProperty().addListener(null);
    }

    public void setScrollPanePosition(double position) {
        homePageScrollPane.setVvalue(position);
    }

    public void clearSearchBar() {
        searchBar.clear();
    }

    @FXML
    public void toPayment() {
        if (model.getShoppingCart().getItems().size() != 0) parentController.toPayment();
    }


    private void initFlowPane() {
        productListFlowPane.getChildren().clear();
        productListFlowPane.setHgap(20);
        productListFlowPane.setVgap(20);
    }

    public void fillProductListFlowPane() {
        productListFlowPane.getChildren().clear();

        int startIndex = sideIndex * 21;
        int endIndex = startIndex + 20;
        currentSideText.setText((sideIndex + 1) + "");

        for (int i = startIndex; i <= endIndex; i++) {
            Product product = currentProducts.get(i);
            productListFlowPane.getChildren().add(this.productCardHashMap.get(product.getName()));
            
        }

        // for (int i = 0; i<5; i++) {

        // }
        // for (Product product : model.getProducts()) {
        //     if (i>20) break;
            
        //     productListFlowPane.getChildren().add(this.productCardHashMap.get(product.getName()));
            
        // }
    }

    private void fillProductListFlowPane(List<Product> products) {
        productListFlowPane.getChildren().clear();

        int startIndex = sideIndex * 21;
        int endIndex = startIndex + 20;
        currentSideText.setText((sideIndex + 1) + "");

        for (int i = startIndex; i <= endIndex; i++) {
            Product product = model.getProducts().get(i);
            productListFlowPane.getChildren().add(this.productCardHashMap.get(product.getName()));
            
        }
    }

    @FXML
    public void nextPageClick() {
        int oldIndex = sideIndex;
        if (sideIndex < Math.floor(currentProducts.size() / 20)) sideIndex++;

        if (sideIndex >= Math.floor(currentProducts.size() / 20)) rightButton.setVisible(false);
        leftButton.setVisible(true);

        if (oldIndex != sideIndex )setScrollPanePosition(0);
        fillProductListFlowPane(); 
    }

    @FXML
    public void previousPageClick() {
        int oldIndex = sideIndex;
        if (sideIndex > 0) sideIndex--;
        if ((sideIndex) == 0) leftButton.setVisible(false);
        rightButton.setVisible(true);

        if (oldIndex != sideIndex) setScrollPanePosition(0);
        fillProductListFlowPane();
    }

    @FXML
    public void displaySearchResults() {
        sideIndex = 0;

        if (searchBar.getText() == "") {
            currentProducts = model.getProducts();
            fillProductListFlowPane();
            return;
        }

        leftButton.setVisible(false);

        currentProducts = model.findProducts(searchBar.getText());

        fillProductListFlowPane();
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
