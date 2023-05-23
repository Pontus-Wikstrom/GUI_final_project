
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
    private AnchorPane categoryPage;
    @FXML
    private AnchorPane homePage;
    @FXML
    private AnchorPane userPage;


    private HomePageController homePageController;
    private OffersPageController offersPageController;
    private FavouritesPageController favouritesPageController;

    HashMap<String, ProductCardController> productCardHashMap = new HashMap<>();

    private final Model model = Model.getInstance();

    public void initialize(URL url, ResourceBundle rb) {
        initProductCardHashMap();

        homePageController = new HomePageController(productCardHashMap);
        offersPageController = new OffersPageController(productCardHashMap);
        favouritesPageController = new FavouritesPageController(productCardHashMap);

        model.clearShoppingCart();
        model.getShoppingCart().addShoppingCartListener(this);

        AnchorPane deliveryPage = new leverans(this);
        homePage = homePageController;
        offersPage = offersPageController;
        favouritesPage = favouritesPageController;
        helpPage = new SitePane(this, new FXMLLoader(getClass().getResource("help_content.fxml")));
        categoryPage = new SitePane(this, new FXMLLoader(getClass().getResource("kategorier.fxml")));
        userPage = new SitePane(this, new FXMLLoader(getClass().getResource("user.fxml")));

        setPage(homePage);

    } 

    private void initProductCardHashMap() {
        for (Product product : model.getProducts()){
            ProductCardController productCardController1 = new ProductCardController(product);
            productCardHashMap.put(product.getName(), productCardController1);
        }
    }

    @Override
    public void shoppingCartChanged(CartEvent arg0) {
        // TODO Auto-generated method stub
        
    }



    // ---------------- GO TO DIFFERENT PAGES ------------------------------------

    @FXML
    public void shoppingCartPageClick() {

    }

    @FXML
    public void offersPageClick() {
        setPage(offersPage);
        offersPageController.fillProductListFlowPane();
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
    }

    @FXML
    public void hamburgerFavouritesPageClick() {
        closeHamburgerMenu();
        favouritesPageClick();
        favouritesPageController.fillProductListFlowPane();
    }

    @FXML
    public void categoryPageClick() {
        setPage(categoryPage);
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
    }


    @FXML
    private void setPage(AnchorPane page){
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