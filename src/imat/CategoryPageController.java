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

public class CategoryPageController extends AnchorPane{
    
    @FXML
    private FlowPane categoryFlowPane;
    @FXML
    private Text valdKategori;
    @FXML
    private ScrollPane category1ScrollPane;
    @FXML
    private ScrollPane category2ScrollPane;

    private HashMap<String, ProductCardController> productCardHashMap;

    private final Model model = Model.getInstance();

    public CategoryPageController(HashMap<String, ProductCardController> productCardHashMap) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("kategorier.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        this.productCardHashMap = productCardHashMap;
        initFlowPane();
        

        category1ScrollPane.setFitToWidth(true);
        category2ScrollPane.setFitToWidth(true);
        //searchBar.textProperty().addListener(null);
    }
    @FXML
    public void setCategoryToFront(){
    category2ScrollPane.toFront();
    }

    private void initFlowPane() {
        
        categoryFlowPane.getChildren().clear();
        categoryFlowPane.setHgap(20);
        categoryFlowPane.setVgap(20);
    }
    

    
    public void populateProductsByCategory(ProductCategory category){
        List<Product> products = model.getCategoryProducts(category);
        fillProductListFlowPane(products);
    }
    @FXML
public void getMeat() {
    category1ScrollPane.toFront();
    valdKategori.setText("Kött");
    populateProductsByCategory(ProductCategory.MEAT);
}
@FXML
public void getBerry() {
    category1ScrollPane.toFront();
    valdKategori.setText("Bär");
    populateProductsByCategory(ProductCategory.BERRY);
}
@FXML
public void getBread() {
    category1ScrollPane.toFront();
    valdKategori.setText("Bröd");
    populateProductsByCategory(ProductCategory.BREAD);
}
@FXML
public void getCabbage() {
    category1ScrollPane.toFront();
    valdKategori.setText("Kål");
    populateProductsByCategory(ProductCategory.CABBAGE);
}
@FXML
public void getCitrus() {
    category1ScrollPane.toFront();
    valdKategori.setText("Citrus");
    populateProductsByCategory(ProductCategory.CITRUS_FRUIT);
}
@FXML
public void getColdDrinks() {
    category1ScrollPane.toFront();
    valdKategori.setText("Kalla drycker");
    populateProductsByCategory(ProductCategory.COLD_DRINKS);
}
@FXML
public void getDairies() {
    category1ScrollPane.toFront();
    valdKategori.setText("Mejeri");
    populateProductsByCategory(ProductCategory.DAIRIES);
}
@FXML
public void getExcoticFruit() {
    category1ScrollPane.toFront();
    valdKategori.setText("Exotisk Frukt");
    populateProductsByCategory(ProductCategory.EXOTIC_FRUIT);
}
@FXML
public void getFish() {
    category1ScrollPane.toFront();
    valdKategori.setText("Fisk");
    populateProductsByCategory(ProductCategory.FISH);
}
@FXML
public void getSugar() {
    category1ScrollPane.toFront();
    valdKategori.setText("Mjöl&Socker&Salt");
    populateProductsByCategory(ProductCategory.FLOUR_SUGAR_SALT);
}
@FXML
public void getFruit() {
    category1ScrollPane.toFront();
    valdKategori.setText("Frukt");
    populateProductsByCategory(ProductCategory.FRUIT);
}
@FXML
public void getHerb() {
    category1ScrollPane.toFront();
    valdKategori.setText("Ört");
    populateProductsByCategory(ProductCategory.HERB);
}
@FXML
public void getHotDrinks() {
    category1ScrollPane.toFront();
    valdKategori.setText("Varma drycker");
    populateProductsByCategory(ProductCategory.HOT_DRINKS);
}
@FXML
public void getMelons() {
    category1ScrollPane.toFront();
    valdKategori.setText("Meloner");
    populateProductsByCategory(ProductCategory.MELONS);
}
@FXML
public void getNutsSeeds() {
    category1ScrollPane.toFront();
    valdKategori.setText("Nötter");
    populateProductsByCategory(ProductCategory.NUTS_AND_SEEDS);
}
@FXML
public void getPasta() {
    category1ScrollPane.toFront();
    valdKategori.setText("Pasta");
    populateProductsByCategory(ProductCategory.PASTA);
}
@FXML
public void getPod() {
    category1ScrollPane.toFront();
    valdKategori.setText("Pod");
    populateProductsByCategory(ProductCategory.POD);
}
@FXML
public void getPotatoRice() {
    category1ScrollPane.toFront();
    valdKategori.setText("Potatis&Ris");
    populateProductsByCategory(ProductCategory.POTATO_RICE);
}
@FXML
public void getRootVeg() {
    category1ScrollPane.toFront();
    valdKategori.setText("Rotfrukter");
    populateProductsByCategory(ProductCategory.ROOT_VEGETABLE);
}
@FXML
public void getSweet() {
    category1ScrollPane.toFront();
    valdKategori.setText("Sött");
    populateProductsByCategory(ProductCategory.SWEET);
}
@FXML
public void getVegFruit() {
    category1ScrollPane.toFront();
    valdKategori.setText("Grönsaker&Frukt");
    populateProductsByCategory(ProductCategory.VEGETABLE_FRUIT);
}

    private void fillProductListFlowPane(List<Product> products) {
        categoryFlowPane.getChildren().clear();

        for (Product product : products) {
            categoryFlowPane.getChildren().add(this.productCardHashMap.get(product.getName()));
        }

       
    }
}
