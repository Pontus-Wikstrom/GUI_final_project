package imat;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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

    private boolean flowPaneIsOpen = false;

    private Order order;
    private HashMap<String, HistoryCardController> historyCardHashMap = new HashMap<>();

    public OrderHistoryCardController(Order order) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("profile_search_history.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        this.order = order;
        initHistoryCardHashMap();

        Date date = order.getDate();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
        String strDate = dateFormat.format(date);
        purchaseDateText.setText(strDate);

        double amount = 0;
        double totPrice = 0;
        for (ShoppingItem item : order.getItems()) {
            amount += item.getAmount();
            totPrice += (item.getProduct().getPrice() * item.getAmount());
        }

        if (order.getItems().size() == 1) {
            totalProductAmount.setText("1 produkt");    
        } else {
            totalProductAmount.setText(order.getItems().size() + " st produkter");
        }
        
        totalCostText.setText(String.format("%.2f", totPrice) + " kr");
        
    }

    private void initHistoryCardHashMap() {
        for (ShoppingItem item : order.getItems()) {
            Product product = item.getProduct();
            ShoppingItem itemToAdd = new ShoppingItem(product, item.getAmount());
            HistoryCardController cardToAdd = new HistoryCardController(product, itemToAdd);

            historyCardHashMap.put(product.getName(), cardToAdd);
        }
    }

    private void fillFlowPane() {
        productFlowPane.getChildren().clear();
        
        for (ShoppingItem item : order.getItems()) {
            productFlowPane.getChildren().add(this.historyCardHashMap.get(item.getProduct().getName()));
        }
    }

    private void clearFlowPane() {
        productFlowPane.getChildren().clear();
    }

    @FXML
    public void updateFlowPane() {
        if (!flowPaneIsOpen) {
            fillFlowPane();
            flowPaneIsOpen = true;
        } else {
            clearFlowPane();
            flowPaneIsOpen = false;
        }
    }
}
