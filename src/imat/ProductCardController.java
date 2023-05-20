package imat;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import se.chalmers.cse.dat216.project.*;

public class ProductCardController extends AnchorPane implements ShoppingCartListener{
    @FXML
    private Text productCardProductName;
    @FXML
    private Text productCardPrice;
    @FXML
    private ImageView productCardImage;
    @FXML
    private Text productCardAmountOfItems;

    private Product product;
    private final static double kImageWidth = 100.0;
    private final static double kImageRatio = 0.75;
    private ShoppingItem shoppingItem;
    private Model model = Model.getInstance();

    public ProductCardController(Product product, IMatController parentController) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("product_card.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        this.product = product;
        this.shoppingItem = new ShoppingItem(product, 0);
        productCardProductName.setText(product.getName());
        productCardPrice.setText(String.format("%.2f", product.getPrice()) + product.getUnit());
        //itemCardReferencePriceLabel.setText(product.getUnit()); //TODO Lägg till så att man kan se kg/pris och sånt?
        productCardImage.setImage(model.getImage(product));
        /*if (!product.isEcological()) { //TODO Fixa eko-möjligheter?
            itemCardBrandLabel.setText("");
        }*/ 

        model.getShoppingCart().addShoppingCartListener(this);
    }




    @Override
    public void shoppingCartChanged(CartEvent event) {
        productCardAmountOfItems.setText((int) shoppingItem.getAmount() + "");
    }


}
