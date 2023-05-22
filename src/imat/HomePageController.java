package imat;

import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import se.chalmers.cse.dat216.project.*;

public class HomePageController extends AnchorPane{
    @FXML
    private TextField searchBar;
    @FXML
    private FlowPane productListFlowPane;

    HashMap<String, ProductCardController> productCardController;

    private final Model model = Model.getInstance();

    public HomePageController(HashMap<String, ProductCardController> productCardController) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("sökSida.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        this.productCardController = productCardController;
        initProducts();
    }

    /*public void initialize(URL url, ResourceBundle rb) {
        initProducts();
    }*/

    @FXML
    public void mouseClickTest() {
        System.out.println("Clicked successfully");
    }

    private void initProducts() {
        productListFlowPane.getChildren().clear();
        productListFlowPane.setHgap(20);
        productListFlowPane.setVgap(20);
        
        for (Product product : model.getProducts()){
            ProductCardController productCardController1 = new ProductCardController(product);
            productCardController.put(product.getName(), productCardController1);
            productListFlowPane.getChildren().add(productCardController1);
        }
    }

    public void fillProductListFlowPane() {
        productListFlowPane.getChildren().clear();
        
        for (Product product : model.getProducts()){
            //productListFlowPane.getChildren().add(new ProductCardController(product));
            ProductCardController productCardController1 = new ProductCardController(product);
            productListFlowPane.getChildren().add(productCardController1);
        }

        /*for (Product product: model.getProducts()) {
            productListFlowPane.getChildren().add(productCardController.get(product.getName()));
        }*/
    }

}
