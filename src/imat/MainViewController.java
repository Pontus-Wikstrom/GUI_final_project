
package imat;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
    private AnchorPane contentAnchorPane;
    @FXML
    private FlowPane productListFlowPane;
    @FXML 
    private AnchorPane productCard;
    @FXML
    private AnchorPane offersPage;


    IMatDataHandler iMatDataHandler = IMatDataHandler.getInstance();

    public void initialize(URL url, ResourceBundle rb) {
        
        AnchorPane deliveryPage = new leverans(this);
        AnchorPane offersPage 
        String iMatDirectory = iMatDataHandler.imatDirectory();

        // pathLabel.setText(iMatDirectory);
        fillProductListFlowPane(productCard);
        setPage(deliveryPage);
    }

    public void fillProductListFlowPane(AnchorPane card){
        
      //  productListFlowPane.getChildren().add(card);
    }

    public void categoryClick() {
        setPage(offersPage);
    }
    
    public void setPage(AnchorPane page){
        contentAnchorPane.getChildren().clear();
        contentAnchorPane.getChildren().add(page);
    }
}