package imat;

import java.io.IOException;
import java.util.HashMap;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import se.chalmers.cse.dat216.project.*;

public class ShoppingCartCardController extends AnchorPane implements ShoppingCartListener, Comparable<ShoppingCartCardController>{
    @FXML
    private Text productNameText;
    @FXML
    private Text productCostText;
    @FXML
    private Text productAmountText;
    @FXML
    private Text productCostsSumText;
    @FXML
    private ImageView productImage;

    private Product product;
    private ShoppingItem shoppingItem;
    private Model model = Model.getInstance();
    private MainViewController parentController;

    public ShoppingCartCardController (Product product, ShoppingItem shoppingItem, MainViewController parenController) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("vara_kort.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        this.product = product;
        this.shoppingItem = shoppingItem;
        this.parentController = parenController;

        productNameText.setText(product.getName());
        productCostText.setText(String.format("%.2f", product.getPrice()) + " " + product.getUnit());
        productImage.setImage(model.getImage(product));
        productAmountText.setText("0");
        productCostsSumText.setText("0");



        model.getShoppingCart().addShoppingCartListener(this);

    }

    @FXML
    public void removeProduct() {
        model.removeFromShoppingCart(product);
        parentController.refreshShoppingCartFlowPane();
        model.getShoppingCart().fireShoppingCartChanged(shoppingItem, true);
    }

    private void setAmountOfItemsText() {
        String totalCost = String.format("%.2f" , shoppingItem.getTotal());
        productAmountText.setText((int) shoppingItem.getAmount() + "");
        productCostsSumText.setText(totalCost + " kr");
    }


    private void increaseAmountOfProducts() {
        shoppingItem.setAmount((int) shoppingItem.getAmount() + 1);
        setAmountOfItemsText();


        model.removeFromShoppingCart(product);
        model.addToShoppingCart(shoppingItem);
        
        model.getShoppingCart().fireShoppingCartChanged(shoppingItem, true);

        System.out.println("in history increase");
        System.out.println(model.getShoppingCart().getItems());
        System.out.println(model.getShoppingCart().getTotal());
    }

    private void decreaseAmountOfProducts() {
        if (shoppingItem.getAmount() < 1) return;

        shoppingItem.setAmount((int) shoppingItem.getAmount() - 1);
        model.removeFromShoppingCart(product);

        if (shoppingItem.getAmount() > 0) model.addToShoppingCart(shoppingItem);


        model.getShoppingCart().fireShoppingCartChanged(shoppingItem, true);
        
        System.out.println("in history decrease");
        System.out.println(model.getShoppingCart().getItems());
        System.out.println(model.getShoppingCart().getTotal());
    }


    @Override
    public void shoppingCartChanged(CartEvent event) {
        setAmountOfItemsText();
    }

    @FXML
    public void increaseProductClick() {
        increaseAmountOfProducts();
    }

    @FXML
    public void decreaseProductClick() {
        decreaseAmountOfProducts();
    }

    public String getName() {
        return product.getName();
    }

    @Override
    public int compareTo(ShoppingCartCardController o) {
        return this.getName().compareTo(o.getName());
    }

    

    //TODO Lägg till metod för att ta bort vara helt och hållet
}
