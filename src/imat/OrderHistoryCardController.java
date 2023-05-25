package imat;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Text;
import se.chalmers.cse.dat216.project.*;

public class OrderHistoryCardController extends AnchorPane {
    @FXML
    private Text purchaseDateText;
    @FXML
    private Text totalProductAmount;
    @FXML
    private Text totalCostText;
    @FXML
    private ImageView flowPaneButton;
    @FXML
    private FlowPane productFlowPane;

    private Order order;
    private HashMap<String, HistoryCardController> historyCardHashMap;

    public OrderHistoryCardController(Order order, HashMap<String, HistoryCardController> historyCardHashMap) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("profile_search_history.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        this.order = order;
        this.historyCardHashMap = historyCardHashMap;

        Date date = order.getDate();
        String formattedDate = String.format("%1$tb %1$te, %1$tY %1$tI:%1$tM %1$Tp", date);
        purchaseDateText.setText(formattedDate);

        double amount = 0;
        double totPrice = 0;
        for (ShoppingItem item : order.getItems()) {
            amount += item.getAmount();
            totPrice += (item.getProduct().getPrice() * item.getAmount());
        }

        totalProductAmount.setText(String.format("%.0f", amount) + " st");
        totalCostText.setText(String.format("%.2f", totPrice) + " kr");
        
        
    }

    @FXML
    public void fillFlowPane() {
        productFlowPane.getChildren().clear();
        
        for (ShoppingItem item : order.getItems()) {
            productFlowPane.getChildren().add(this.historyCardHashMap.get(item.getProduct().getName()));
        }
    }
}
