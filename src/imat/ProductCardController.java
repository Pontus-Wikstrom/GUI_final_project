package imat;

import java.io.IOException;
import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import se.chalmers.cse.dat216.project.*;

public class ProductCardController extends AnchorPane implements ShoppingCartListener, Comparable<ProductCardController>{
    @FXML
    private Text productCardProductName;
    @FXML
    private Text productCardPrice;
    @FXML
    private ImageView productCardImage;
    @FXML
    private Text productCardAmountOfItems;

    @FXML 
    private AnchorPane favourite;
    @FXML
    private AnchorPane notFavourite;
    private Product product;
    private final static double kImageWidth = 100.0;
    private final static double kImageRatio = 0.75;
    private ShoppingItem shoppingItem;
    private Model model = Model.getInstance();

    public ProductCardController(Product product, ShoppingItem shoppingItem) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("product_card.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        this.product = product;
        this.shoppingItem = shoppingItem;
        productCardProductName.setText(product.getName());
        productCardPrice.setText(String.format("%.2f", product.getPrice()) + product.getUnit());
        productCardImage.setImage(model.getImage(product));
        productCardAmountOfItems.setText("0");
        /*if (!product.isEcological()) { //TODO Fixa eko-möjligheter?
            itemCardBrandLabel.setText("");
        }*/ 


        model.getShoppingCart().addShoppingCartListener(this);
    }


    private void setAmountOfItemsText() {
        productCardAmountOfItems.setText((int) shoppingItem.getAmount() + "");
    }

    public String getName() {
        return product.getName();
    }


    private void increaseAmountOfProducts() {
        shoppingItem.setAmount((int) shoppingItem.getAmount() + 1);
        setAmountOfItemsText();

        model.removeFromShoppingCart(product);
        model.addToShoppingCart(shoppingItem);
        
        model.getShoppingCart().fireShoppingCartChanged(shoppingItem, true);

        System.out.println("in increase");
        System.out.println(model.getShoppingCart().getItems());
        System.out.println(model.getShoppingCart().getTotal());

        System.out.println(shoppingItem.getAmount());
        System.out.println("--------------------------------");
    }

    private void decreaseAmountOfProducts() {
        if (shoppingItem.getAmount() < 1) return;

        shoppingItem.setAmount((int) shoppingItem.getAmount() - 1);
        model.removeFromShoppingCart(product);

        if (shoppingItem.getAmount() > 0) model.addToShoppingCart(shoppingItem);


        model.getShoppingCart().fireShoppingCartChanged(shoppingItem, true);
        
        System.out.println("in decrease");
        System.out.println(model.getShoppingCart().getItems());
        System.out.println(model.getShoppingCart().getTotal());

        System.out.println("----------------------");
    }


    @Override
    public void shoppingCartChanged(CartEvent event) {
        productCardAmountOfItems.setText((int) this.shoppingItem.getAmount() + "");
    }

    @FXML
    public void increaseProductClick() {
        increaseAmountOfProducts();
    }
    
    @FXML
    public void decreaseProductClick() {
        decreaseAmountOfProducts();
    }

    @FXML
    public void favouriteButtonClick() {
        if (!model.isFavourite(product)) {
            model.setFavourite(product);
            favourite.toFront();
        } else {
            model.removeFavourite(product);
            notFavourite.toFront();
        }

    
    }

    @Override
    public int compareTo(ProductCardController o) {
        return this.getName().compareTo(o.getName());
    }


}
