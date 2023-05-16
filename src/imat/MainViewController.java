
package imat;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import se.chalmers.cse.dat216.project.IMatDataHandler;

public class MainViewController implements Initializable {

    // @FXML
    // Label pathLabel;
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
    private AnchorPane productDescription;
    @FXML
    private AnchorPane baseSite;
    @FXML
    private FlowPane productListFlowPane;
    @FXML 
    private AnchorPane productCard;
    @FXML
    private AnchorPane offersPage;
    @FXML
    private AnchorPane helpPage;


    IMatDataHandler iMatDataHandler = IMatDataHandler.getInstance();

    public void initialize(URL url, ResourceBundle rb) {
        
        AnchorPane deliveryPage = new leverans(this);
        offersPage = new OffersSite(this);
        helpPage = new SitePane(this, new FXMLLoader(getClass().getResource("help_content.fxml")));


        String iMatDirectory = iMatDataHandler.imatDirectory();

        // pathLabel.setText(iMatDirectory);
        fillProductListFlowPane(productCard);
        setPage(deliveryPage);
    }

    public void fillProductListFlowPane(AnchorPane card){
        
      //  productListFlowPane.getChildren().add(card);
    }

    public void offersPageClick() {
        setPage(offersPage);
    }

    public void helpPageClick() {
        setPage(helpPage);
    }
    
    public void setPage(AnchorPane page){
        contentAnchorPane.getChildren().clear();
        contentAnchorPane.getChildren().add(page);
    }

    public void openHamburgerMenu() {
        hamburgerMenu.toFront();
    }

    public void closeHamburgerMenu() {
        hamburgerMenu.toBack();
    }
}