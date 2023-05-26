
package imat;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.HashMap;
import java.util.List;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.fxml.FXMLLoader;
import javafx.event.Event;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Text;
import javafx.scene.shape.Rectangle;
import se.chalmers.cse.dat216.project.*;


public class MainViewController implements Initializable, ShoppingCartListener {
    @FXML
    private AnchorPane categoryButton;
    @FXML 
    private AnchorPane helpButton;
    @FXML
    private AnchorPane hamburgerMenuButton;
    @FXML 
    private AnchorPane contentAnchorPane;
    @FXML
    private AnchorPane hamburgerMenu;
    @FXML
    private AnchorPane hamburgerMenuBackground;


    @FXML
    private AnchorPane productDescription;
    @FXML
    private AnchorPane productDescriptionBackground;
    @FXML
    private AnchorPane baseSite;
    @FXML 
    private AnchorPane productCard;
    
    @FXML
    private OffersPageController offersPage;
    @FXML
    private AnchorPane helpPage;
    @FXML 
    private FavouritesPageController favouritesPage;
    @FXML
    private HomePageController homePage;
    @FXML
    private UserController userPage;
    @FXML
    private AnchorPane deliveryPage;
    @FXML
    private shoppingCart shoppingCartPage;


    @FXML
    private Text shoppingCartItemAmount;
    @FXML
    private Text shoppingCartCostAmount;
    @FXML
    private Text hamburgerCartItemAmount;
    @FXML
    private Text hamburgerCartCostAmount;

    @FXML
    private Rectangle category_marker;
    @FXML
    private Rectangle favourite_marker;
    @FXML
    private Rectangle offer_marker;
    @FXML
    private Rectangle help_marker;
    @FXML
    private Rectangle cart_marker;
    @FXML
    private Rectangle profile_marker;
    @FXML
    private Rectangle mainsiteMarker;

    @FXML
    private Text productInfoName;
    @FXML
    private ImageView productInfoImage;
    @FXML
    private Text productInfoPrice;
    @FXML
    private AnchorPane productInfoDecreaseAmount;
    @FXML
    private Text productInfoAmount;
    
    private ProductCardController currentProductCard;

    
    private CategoryPageController categoryPageController;
    
    HashMap<String, ProductCardController> productCardHashMap = new HashMap<>();
    HashMap<String, ShoppingCartCardController> shoppingCartCardHashMap = new HashMap<>();
    HashMap<String, HistoryCardController> historyCardHashMap = new HashMap<>();

    List<Order> orderHistory;

    private final Model model = Model.getInstance();

    public void initialize(URL url, ResourceBundle rb) {
        initProductCardHashMap();

        deliveryPage = new fullWizardController(this);

        categoryPageController = new CategoryPageController(productCardHashMap);
        shoppingCartPage = new shoppingCart(this, shoppingCartCardHashMap);
        homePage = new HomePageController(productCardHashMap);
        offersPage = new OffersPageController(productCardHashMap);
        favouritesPage = new FavouritesPageController(productCardHashMap);
        userPage = new UserController(historyCardHashMap);
        productDescription = new ProductCardInfoController(this);
      
        //shoppingCartPage = new fullWizardController(this);
        helpPage = new SitePane(this, new FXMLLoader(getClass().getResource("help_content.fxml")));
        //fillProductListFlowPane(productCard);

        model.clearShoppingCart();
        model.getShoppingCart().addShoppingCartListener(this);

        orderHistory = model.getOrderHistory();
        
        homePageClick();

        shoppingCartCostAmount.setText("0 kr");
        hamburgerCartCostAmount.setText("0 kr");

    } 

    private void initProductCardHashMap() {
        for (Product product : model.getProducts()){
            ShoppingItem itemToAdd = new ShoppingItem(product, 0);
            ProductCardController productCardController1 = new ProductCardController(product, itemToAdd, this);
            ShoppingCartCardController shoppingCartCardController1 = new ShoppingCartCardController(product, itemToAdd, this);
            productCardHashMap.put(product.getName(), productCardController1);
            shoppingCartCardHashMap.put(product.getName(), shoppingCartCardController1);
        }
    }

    @Override
    public void shoppingCartChanged(CartEvent arg0) {
        String totalCost = String.format("%.0f" ,model.getShoppingCart().getTotal());
        int amountOfItems = model.getShoppingCart().getItems().size();

        shoppingCartCostAmount.setText(totalCost + " kr");
        shoppingCartItemAmount.setText(amountOfItems + "");

        hamburgerCartCostAmount.setText(totalCost + " kr");
        hamburgerCartItemAmount.setText(amountOfItems + "");

        updateProductCardInfo();
    }

    public void clear_marked_sites(){
    category_marker.setVisible(false);
    profile_marker.setVisible(false);
    help_marker.setVisible(false);
    favourite_marker.setVisible(false);
    cart_marker.setVisible(false);
    offer_marker.setVisible(false);
    mainsiteMarker.setVisible(false);
    
    }

    public void refreshShoppingCartFlowPane() {
        shoppingCartPage.fillShoppingCartFlowPane();
    }

    // ---------------- GO TO DIFFERENT PAGES ------------------------------------

    public void productCardClick(ProductCardController productCard) {
        currentProductCard = productCard;
        updateProductCardInfo();
        
        productDescriptionBackground.toFront();

    }

    private void updateProductCardInfo() {
        productInfoName.setText(currentProductCard.getProduct().getName());
        productInfoImage.setImage(model.getImage(currentProductCard.getProduct()));
        productInfoPrice.setText(currentProductCard.getProduct().getPrice() + currentProductCard.getProduct().getUnit());
        productInfoAmount.setText(String.format("%.0f", currentProductCard.getShoppingItem().getAmount()));
    }

    @FXML
    public void productInfoIncreaseAmount() {
        currentProductCard.increaseAmountOfProducts();
    }

    @FXML
    public void productInfoDecreaseAmount() {
        currentProductCard.decreaseAmountOfProducts();
    }

    @FXML
    public void productDescriptionClose() {
        productDescriptionBackground.toBack();
    }

    @FXML
    public void shoppingCartPageClick() {
        setPage(shoppingCartPage);
        shoppingCartPage.fillShoppingCartFlowPane();
        cart_marker.setVisible(true);
    }

    @FXML
    public void toPayment() {
        setPage(deliveryPage);
    }

    @FXML
    public void offersPageClick() {
        setPage(offersPage);
        offersPage.fillProductListFlowPane();
        offer_marker.setVisible(true);;
    }

    @FXML
    public void hamburgerOffersPageClick() {
        closeHamburgerMenu();
        offersPageClick();
        offersPage.fillProductListFlowPane();
    }

    @FXML
    public void helpPageClick() {
        setPage(helpPage);
        help_marker.setVisible(true);
    }

    @FXML
    public void hamburgerHelpPageClick() {
        closeHamburgerMenu();
        helpPageClick();
    }

    @FXML
    public void favouritesPageClick() {
        setPage(favouritesPage);
        favouritesPage.fillProductListFlowPane();
        favourite_marker.setVisible(true);
    }

    @FXML
    public void hamburgerFavouritesPageClick() {
        closeHamburgerMenu();
        favouritesPageClick();
        favouritesPage.fillProductListFlowPane();
    }

    @FXML
    public void categoryPageClick() {
        categoryPageController.setCategoryToFront();
        setPage(categoryPageController);
        category_marker.setVisible(true);
    }

    @FXML
    public void hamburgerCategoryPageClick() {
        closeHamburgerMenu();
        categoryPageClick();
    }

    @FXML
    public void homePageClick() {
        setPage(homePage);
        homePage.fillProductListFlowPane();
        mainsiteMarker.setVisible(true);
    }

    @FXML
    public void hamburgerHomePageClick() {
        closeHamburgerMenu();
        homePageClick();
        homePage.fillProductListFlowPane();
    }

    @FXML
    public void userPageClick() {
        setPage(userPage);
        profile_marker.setVisible(true);

        model.placeOrder(true);
        userPage.updateFlowPane();
        onPlaceOrder();

    }

    public void onPlaceOrder() { // resets HashMaps so new order can start to be built
        productCardHashMap.clear();
        shoppingCartCardHashMap.clear();
        historyCardHashMap.clear();
        initProductCardHashMap();

        categoryPageController = new CategoryPageController(productCardHashMap);
        shoppingCartPage = new shoppingCart(this, shoppingCartCardHashMap);
        homePage = new HomePageController(productCardHashMap);
        offersPage = new OffersPageController(productCardHashMap);
        favouritesPage = new FavouritesPageController(productCardHashMap);
        userPage = new UserController(historyCardHashMap);

    }


    @FXML
    private void setPage(AnchorPane page){
        clear_marked_sites();
        contentAnchorPane.getChildren().clear();
        contentAnchorPane.getChildren().add(page);
    }


    @FXML
    public void openHamburgerMenu() {
        hamburgerMenuBackground.toFront();
    }

    @FXML
    public void closeHamburgerMenu() {
        hamburgerMenuBackground.toBack();
    }

    @FXML
    public void mouseTrap(Event event) {
        event.consume();
    }
}