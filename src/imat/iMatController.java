package imat;

import java.util.HashMap;
import java.util.ResourceBundle;

import se.chalmers.cse.dat216.project.*;
import javafx.fxml.FXML;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.Initializable;
import java.net.URL;



public class IMatController implements Initializable, ShoppingCartListener {
    
    @FXML
    private FlowPane productListFlowPane;

    HashMap<String, ProductCardController> productCardController = new HashMap<>();


    private final Model model = Model.getInstance();
    
    public void initialize(URL url, ResourceBundle rb) {
        initProducts();
        model.clearShoppingCart();
        model.getShoppingCart().addShoppingCartListener(this);
    }

    private void initProducts() {
        productListFlowPane.getChildren().clear();
        productListFlowPane.setHgap(20);
        productListFlowPane.setVgap(20);
        
        for (Product product : model.getProducts()){
            ProductCardController productCardController1 = new ProductCardController(product, this);
            productCardController.put(product.getName(), productCardController1);
            productListFlowPane.getChildren().add(productCardController1);
        }
    }

    @Override
    public void shoppingCartChanged(CartEvent event) {

    }

    public void fillProductListFlowPane(AnchorPane card){
        
      //  productListFlowPane.getChildren().add(card);
    }
}
