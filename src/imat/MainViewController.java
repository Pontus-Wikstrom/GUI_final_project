
package imat;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.HashMap;
import java.util.List;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.fxml.FXMLLoader;
import javafx.event.Event;
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
    private AnchorPane baseSite;
    @FXML 
    private AnchorPane productCard;
    
    @FXML
    private AnchorPane offersPage;
    @FXML
    private AnchorPane helpPage;
    @FXML 
    private AnchorPane favouritesPage;
    @FXML
    private AnchorPane homePage;
    @FXML
    private AnchorPane userPage;
    @FXML
    private AnchorPane deliveryPage;
    @FXML
    private AnchorPane shoppingCartPage;

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
    

    private HomePageController homePageController;
    private OffersPageController offersPageController;
    private FavouritesPageController favouritesPageController;
    private CategoryPageController categoryPageController;
    HashMap<String, ProductCardController> productCardHashMap = new HashMap<>();

    private final Model model = Model.getInstance();

    public void initialize(URL url, ResourceBundle rb) {
        initProductCardHashMap();

        homePageController = new HomePageController(productCardHashMap);
        offersPageController = new OffersPageController(productCardHashMap);
        favouritesPageController = new FavouritesPageController(productCardHashMap);
        categoryPageController = new CategoryPageController(productCardHashMap); // Lade till men vet ej om det fungerar
        model.clearShoppingCart();
        model.getShoppingCart().addShoppingCartListener(this);

        deliveryPage = new fullWizardController(this);
        shoppingCartPage = new shoppingCart(this);
        homePage = homePageController;
        offersPage = offersPageController;
        favouritesPage = favouritesPageController;
        //shoppingCartPage = new fullWizardController(this);
        helpPage = new SitePane(this, new FXMLLoader(getClass().getResource("help_content.fxml")));
      
        userPage = new SitePane(this, new FXMLLoader(getClass().getResource("user.fxml")));

        //fillProductListFlowPane(productCard);
        setPage(deliveryPage);
        

    } 

    private void initProductCardHashMap() {
        for (Product product : model.getProducts()){
            ProductCardController productCardController1 = new ProductCardController(product);
            productCardHashMap.put(product.getName(), productCardController1);
        }
    }

    @Override
    public void shoppingCartChanged(CartEvent arg0) {
        String totalCost = String.format("%.2f" ,model.getShoppingCart().getTotal());
        int amountOfItems = model.getShoppingCart().getItems().size();

        shoppingCartCostAmount.setText(totalCost);
        shoppingCartItemAmount.setText(amountOfItems + "");

        hamburgerCartCostAmount.setText(totalCost);
        hamburgerCartItemAmount.setText(amountOfItems + "");
    }

    public void clear_marked_sites(){
    category_marker.setVisible(false);
    profile_marker.setVisible(false);
    help_marker.setVisible(false);
    favourite_marker.setVisible(false);
    cart_marker.setVisible(false);
    offer_marker.setVisible(false);
    

    }

    // ---------------- GO TO DIFFERENT PAGES ------------------------------------

    @FXML
    public void shoppingCartPageClick() {
        setPage(shoppingCartPage);
    }

    @FXML
    public void toPayment() {
        setPage(deliveryPage);
    }

    @FXML
    public void offersPageClick() {
        setPage(offersPage);
        offersPageController.fillProductListFlowPane();
        offer_marker.setVisible(true);;
    }

    @FXML
    public void hamburgerOffersPageClick() {
        closeHamburgerMenu();
        offersPageClick();
        offersPageController.fillProductListFlowPane();
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
        favouritesPageController.fillProductListFlowPane();
        favourite_marker.setVisible(true);
    }

    @FXML
    public void hamburgerFavouritesPageClick() {
        closeHamburgerMenu();
        favouritesPageClick();
        favouritesPageController.fillProductListFlowPane();
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
        homePageController.fillProductListFlowPane();
    }

    @FXML
    public void hamburgerHomePageClick() {
        closeHamburgerMenu();
        homePageClick();
        homePageController.fillProductListFlowPane();
    }

    @FXML
    public void userPageClick() {
        setPage(userPage);
        profile_marker.setVisible(true);
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