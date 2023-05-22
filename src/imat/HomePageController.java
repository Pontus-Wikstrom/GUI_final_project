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

    private HashMap<String, ProductCardController> productCardController;

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
            // productListFlowPane.getChildren().add(productCardController1);
        }
        System.out.println("in init");
        System.out.println(productListFlowPane);
        productListFlowPane.getChildren().addAll(productCardController.values());
        
    }

    public void fillProductListFlowPane() {
        System.out.println("in fillproducts");
        System.out.println(productListFlowPane);

        productListFlowPane.getChildren().clear();
        //productListFlowPane.getChildren().add(new ProductCardController(model.getProducts().get(0)));
        productListFlowPane.getChildren().addAll(productCardController.values());
        
    }

}
