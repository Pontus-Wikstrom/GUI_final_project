
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

    HashMap<String, ProductCardController> productCardController = new HashMap<>();

    private final Model model = Model.getInstance();

    public void initialize(URL url, ResourceBundle rb) {
        homePageController = new HomePageController(productCardController);
        model.clearShoppingCart();
        model.getShoppingCart().addShoppingCartListener(this);

        AnchorPane deliveryPage = new leverans(this);
        offersPage = new SitePane(this, new FXMLLoader(getClass().getResource("erbjudanden.fxml")));
        helpPage = new SitePane(this, new FXMLLoader(getClass().getResource("help_content.fxml")));
        favouritesPage = new SitePane(this, new FXMLLoader(getClass().getResource("favoriter.fxml")));
        categoryPage = new SitePane(this, new FXMLLoader(getClass().getResource("kategorier.fxml")));
        homePage = homePageController;
        userPage = new SitePane(this, new FXMLLoader(getClass().getResource("user.fxml")));

        fillProductListFlowPane(productCard);
        setPage(deliveryPage);

    } 




    public void fillProductListFlowPane(AnchorPane card){
        
      //  productListFlowPane.getChildren().add(card);
    }

    @Override
    public void shoppingCartChanged(CartEvent arg0) {
        // TODO Auto-generated method stub
        
    }



    // ---------------- GO TO DIFFERENT PAGES ------------------------------------

    @FXML
    public void offersPageClick() {
        setPage(offersPage);
    }

    @FXML
    public void hamburgerOffersPageClick() {
        closeHamburgerMenu();
        offersPageClick();
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
    }

    @FXML
    public void hamburgerFavouritesPageClick() {
        closeHamburgerMenu();
        favouritesPageClick();
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