
package imat;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import se.chalmers.cse.dat216.project.IMatDataHandler;

public class MainViewController implements Initializable {

    // @FXML
    // Label pathLabel;
    @FXML 
    private AnchorPane contentAnchorPane;

    IMatDataHandler iMatDataHandler = IMatDataHandler.getInstance();

    public void initialize(URL url, ResourceBundle rb) {
        AnchorPane offersPage = new OffersSite(this);

        String iMatDirectory = iMatDataHandler.imatDirectory();

        // pathLabel.setText(iMatDirectory);

        setPage(offersPage);
    }

    
    public void setPage(AnchorPane page){
        contentAnchorPane.getChildren().clear();
        contentAnchorPane.getChildren().add(page);
    }
}