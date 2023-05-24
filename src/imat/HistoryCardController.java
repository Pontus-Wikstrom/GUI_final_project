package imat;

import java.io.IOException;
import java.util.HashMap;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import se.chalmers.cse.dat216.project.*;

public class HistoryCardController extends AnchorPane implements ShoppingCartListener{
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

    public HistoryCardController (Product product, ShoppingItem shoppingItem) {
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

        productNameText.setText(product.getName());
        productCostText.setText(String.format("%.2f", product.getPrice()) + product.getUnit());
        productImage.setImage(model.getImage(product));
        productAmountText.setText("0");



        model.getShoppingCart().addShoppingCartListener(this);

    }

    private void setAmountOfItemsText() {
        productAmountText.setText((int) shoppingItem.getAmount() + "");
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
        productAmountText.setText((int) this.shoppingItem.getAmount() + "");
    }

    @FXML
    public void increaseProductClick() {
        increaseAmountOfProducts();
    }

    @FXML
    public void decreaseProductClick() {
        decreaseAmountOfProducts();
    }

    //TODO Lägg till metod för att ta bort vara helt och hållet
}
