package imat;

import java.io.IOException;
import java.util.Comparator;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import se.chalmers.cse.dat216.project.*;

public class ProductCardController extends AnchorPane implements ShoppingCartListener {
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
    private ShoppingItem shoppingItem;
    private Model model = Model.getInstance();
    private MainViewController parentController;

    public ProductCardController(Product product, ShoppingItem shoppingItem, MainViewController parentController) {
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
        this.parentController = parentController;

        productCardProductName.setText(product.getName());
        productCardPrice.setText(String.format("%.2f", product.getPrice()) + product.getUnit());
        productCardImage.setImage(model.getImage(product));
        productCardAmountOfItems.setText("0");
        /*if (!product.isEcological()) { //TODO Fixa eko-möjligheter?
            itemCardBrandLabel.setText("");
        }*/ 


        model.getShoppingCart().addShoppingCartListener(this);
    }


    @FXML
    public void productCardClick() {
        parentController.productCardClick(this);
    }

    private void setAmountOfItemsText() {
        productCardAmountOfItems.setText((int) shoppingItem.getAmount() + "");
    }

    public String getName() {
        return product.getName();
    }

    public Product getProduct() {
        return this.product;
    }

    public ShoppingItem getShoppingItem() {
        return this.shoppingItem;
    }

    public void increaseAmountOfProducts() {
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

    public void decreaseAmountOfProducts() {
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
        if(model.getShoppingCart().getItems().contains(shoppingItem)) {
            int i = model.getShoppingCart().getItems().indexOf(shoppingItem);
            shoppingItem.setAmount(model.getShoppingCart().getItems().get(i).getAmount());
        }
        setAmountOfItemsText();
    }

    @FXML
    public void increaseProductClick(Event event) {
        increaseAmountOfProducts();
        event.consume();
    }
    
    @FXML
    public void decreaseProductClick(Event event) {
        decreaseAmountOfProducts();
        event.consume();
    }

    @FXML
    public void favouriteButtonClick(Event event) {
        if (!model.isFavourite(product)) {
            model.setFavourite(product);
            favourite.toFront();
        } else {
            model.removeFavourite(product);
            notFavourite.toFront();
        }

        event.consume();
    }

}
