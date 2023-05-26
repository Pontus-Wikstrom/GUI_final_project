package imat;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import se.chalmers.cse.dat216.project.Product;
import se.chalmers.cse.dat216.project.ShoppingItem;

public class HistoryCardController extends AnchorPane {
    @FXML
    private Label productNameText;
    @FXML
    private Label productPriceText;
    @FXML
    private Label productAmountText;
    @FXML
    private Label productSumPriceText;
    @FXML
    private ImageView productImage;

    private Product product;
    private ShoppingItem shoppingItem;
    private Model model= Model.getInstance();

    public HistoryCardController(Product product, ShoppingItem shoppingItem) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("information_product_buy_history.fxml"));
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
        productPriceText.setText(String.format("%.2f", product.getPrice()) + product.getUnit());
        productImage.setImage(model.getImage(product));
        productAmountText.setText(shoppingItem.getAmount() + "");
        productSumPriceText.setText(shoppingItem.getAmount() * product.getPrice() + "");

        
    }
}
