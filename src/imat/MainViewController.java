
package imat;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.fxml.FXMLLoader;
import javafx.event.Event;
import javafx.scene.layout.AnchorPane;


public class MainViewController {
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

    public void initialize(URL url, ResourceBundle rb) {

        AnchorPane deliveryPage = new leverans(this);
        offersPage = new SitePane(this, new FXMLLoader(getClass().getResource("erbjudanden.fxml")));
        helpPage = new SitePane(this, new FXMLLoader(getClass().getResource("help_content.fxml")));
        favouritesPage = new SitePane(this, new FXMLLoader(getClass().getResource("favoriter.fxml")));
        categoryPage = new SitePane(this, new FXMLLoader(getClass().getResource("kategorier.fxml")));
        homePage = new SitePane(this, new FXMLLoader(getClass().getResource("sökSida.fxml")));
        userPage = new SitePane(this, new FXMLLoader(getClass().getResource("user.fxml")));

        setPage(homePage);

    }

    /*public void init() {
        offersPage = new SitePane(this, new FXMLLoader(getClass().getResource("erbjudanden.fxml")));
        helpPage = new SitePane(this, new FXMLLoader(getClass().getResource("help_content.fxml")));
        favouritesPage = new SitePane(this, new FXMLLoader(getClass().getResource("favoriter.fxml")));
        categoryPage = new SitePane(this, new FXMLLoader(getClass().getResource("kategorier.fxml")));
        homePage = new SitePane(this, new FXMLLoader(getClass().getResource("sökSida.fxml")));
        userPage = new SitePane(this, new FXMLLoader(getClass().getResource("user.fxml")));

        setPage(homePage);
    }*/


    // ---------------- GO TO DIFFERENT PAGES ------------------------------------

    @FXML
    private void setPage(AnchorPane page){
        contentAnchorPane.getChildren().clear();
        contentAnchorPane.getChildren().add(page);
    }

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
        //iMatController.updateProductList(model.getProducts());
        setPage(homePage);
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