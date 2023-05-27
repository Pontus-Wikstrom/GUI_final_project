package imat;

import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
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

public class HomePageController extends AnchorPane{
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

    private HashMap<String, ProductCardController> productCardHashMap;

    private final Model model = Model.getInstance();

    private int sideIndex;

    public HomePageController(HashMap<String, ProductCardController> productCardHashMap) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("sökSida.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

    
        this.productCardHashMap = productCardHashMap;
        sideIndex = 0;

        initFlowPane();
        searchBar.setFocusTraversable(false);

        homePageScrollPane.setFitToWidth(true);
        

        //searchBar.textProperty().addListener(null);
    }

    public void setScrollPanePosition(double position) {
        homePageScrollPane.setVvalue(position);
    }

    public void clearSearchBar() {
        searchBar.clear();
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
            Product product = model.getProducts().get(i);
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

        for (Product product : products) {
            productListFlowPane.getChildren().add(this.productCardHashMap.get(product.getName()));
        }
    }

    @FXML
    public void nextPageClick() {
        if (sideIndex < Math.floor(model.getProducts().size() / 20)) sideIndex++;
        fillProductListFlowPane();
    }

    @FXML
    public void previousPageClick() {
        if (sideIndex > 0) sideIndex--;
        fillProductListFlowPane();
    }

    @FXML
    public void displaySearchResults() {
        if (searchBar.getText() == "") {
            fillProductListFlowPane();
            return;
        }

        List<Product> products = model.findProducts(searchBar.getText());
        fillProductListFlowPane(products);
    }
}
