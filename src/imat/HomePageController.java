package imat;

import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;

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

    private HashMap<String, ProductCardController> productCardHashMap;

    private final Model model = Model.getInstance();

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
        initFlowPane();
        searchBar.setFocusTraversable(false);

        homePageScrollPane.setFitToWidth(true);
        //searchBar.textProperty().addListener(null);
    }


    private void initFlowPane() {
        productListFlowPane.getChildren().clear();
        productListFlowPane.setHgap(20);
        productListFlowPane.setVgap(20);
    }

    public void fillProductListFlowPane() {
        productListFlowPane.getChildren().clear();

        int i = 0;
        for (Product product : model.getProducts()) {
            if (i>20) break;
            
            productListFlowPane.getChildren().add(this.productCardHashMap.get(product.getName()));
            
            i++;
        }

        // flowPaneAnchorPane.setMaxHeight(productListFlowPane.getHeight());
        // flowPaneAnchorPane.setPrefHeight(productListFlowPane.getHeight());

        //productListFlowPane.getChildren().addAll(this.productCardHashMap.values());
    }

    private void fillProductListFlowPane(List<Product> products) {
        productListFlowPane.getChildren().clear();

        for (Product product : products) {
            productListFlowPane.getChildren().add(this.productCardHashMap.get(product.getName()));
        }

        // flowPaneAnchorPane.setMaxHeight(productListFlowPane.getHeight());
        // flowPaneAnchorPane.setPrefHeight(productListFlowPane.getHeight());
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
