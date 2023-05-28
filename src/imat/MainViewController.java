
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
    private fullWizardController deliveryPage;
    @FXML
    private shoppingCart shoppingCartPage;

    private ProductCardInfoController productCardInfo;

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
        //model.resetData();

        deliveryPage = new fullWizardController(this, shoppingCartCardHashMap);

        categoryPageController = new CategoryPageController(productCardHashMap, this);
        shoppingCartPage = new shoppingCart(this, shoppingCartCardHashMap);
        homePage = new HomePageController(productCardHashMap, this);
        offersPage = new OffersPageController(productCardHashMap, this);
        favouritesPage = new FavouritesPageController(productCardHashMap, this);
        userPage = new UserController(historyCardHashMap);
        productCardInfo = new ProductCardInfoController(this);
      
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

        if (currentProductCard != null) {
            productInfoAmount.setText(String.format("%.0f", currentProductCard.getShoppingItem().getAmount()));
        }
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

    @FXML
    public void decreaseProductAmount() {
        currentProductCard.decreaseAmountOfProducts();
    }
    
    @FXML
    public void increaseProductAmount() {
        currentProductCard.increaseAmountOfProducts();
    }

    

    // ---------------- GO TO DIFFERENT PAGES ------------------------------------

    public void purchaseHistoryClick() {
        setPage(userPage);
        
        userPage.testData();

        userPage.setScrollPanePosition(0.77);
        userPage.updateFlowPane();

        userPage.testData();

        closeHamburgerMenu();
                
    }

    private void test() {
        setPage(userPage);

        closeHamburgerMenu();
        userPage.updateFlowPane();
        userPage.setScrollPanePosition(0.77);
    }

    public void productCardClick() {
        productDescriptionBackground.toFront();
    }

    public void productCardClick(ProductCardController productCard) {
        currentProductCard = productCard;
        updateProductCardInfo();
        
        productDescriptionBackground.toFront();

    }

    private void updateProductCardInfo() {
        Product product = currentProductCard.getProduct();
        productInfoName.setText(product.getName());
        productInfoImage.setImage(model.getImage(product));
        productInfoPrice.setText(String.format("%.0f", product.getPrice()) + product.getUnit());
        productInfoAmount.setText(String.format("%.0f", currentProductCard.getShoppingItem().getAmount()));
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
    public void hamburgerShoppingCartClick() {
        setPage(shoppingCartPage);
        shoppingCartPage.fillShoppingCartFlowPane();
        cart_marker.setVisible(true);
        closeHamburgerMenu();
    }

    @FXML
    public void toPayment() {
        deliveryPage.reset();
        setPage(deliveryPage);
    }

    @FXML
    public void offersPageClick() {
        setPage(offersPage);
        offersPage.fillProductListFlowPane();
        offer_marker.setVisible(true);
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
        homePage.setScrollPanePosition(0);
        homePage.clearSearchBar();
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
        userPage.showUserData();
        setPage(userPage);
        profile_marker.setVisible(true);

        userPage.updateFlowPane();

    }

    public void onPlaceOrder() { // resets HashMaps so new order can start to be built
        
        model.placeOrder(true);
        productCardHashMap.clear();
        shoppingCartCardHashMap.clear();
        historyCardHashMap.clear();
        initProductCardHashMap();

        categoryPageController = new CategoryPageController(productCardHashMap, this);
        shoppingCartPage = new shoppingCart(this, shoppingCartCardHashMap);
        homePage = new HomePageController(productCardHashMap, this);
        offersPage = new OffersPageController(productCardHashMap, this);
        favouritesPage = new FavouritesPageController(productCardHashMap, this);
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