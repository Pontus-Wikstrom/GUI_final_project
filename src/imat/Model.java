package imat;

import se.chalmers.cse.dat216.project.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import javafx.scene.image.Image;

/*
 * Wrapper for iMatDataHandler to make it easier to implement wanted features
 * 
 */
public class Model {
    private static Model instance = null;
    private IMatDataHandler iMatDataHandler;


    /*
     * Can only instansiate one Model
     * This method will return said model
     */
    public static Model getInstance() {
        if (instance == null) {
            instance = new Model();
            instance.init();
        }

        return instance;
    }

    private void init() {
        iMatDataHandler = IMatDataHandler.getInstance();
    }

    public List<Product> getProducts() {
        return iMatDataHandler.getProducts();
    }

    public Product getProduct(int idNbr) {
        return iMatDataHandler.getProduct(idNbr);
    }
    
    public List<Product> findProducts(java.lang.String s) {
        return iMatDataHandler.findProducts(s);
    }

    public Image getImage(Product p) {
        return iMatDataHandler.getFXImage(p);
    }

    public Image getImage(Product p, double width, double height) {
        return iMatDataHandler.getFXImage(p, width, height);
    }




    public ShoppingCart getShoppingCart() {
        return iMatDataHandler.getShoppingCart();
    }

    public void addToShoppingCart(Product product) {
        ShoppingItem shoppingItem = new ShoppingItem(product);
        getShoppingCart().addItem(shoppingItem);
    }

    public void addToShoppingCart(ShoppingItem shoppingItem) {
        getShoppingCart().addItem(shoppingItem);
    }

    public void removeFromShoppingCart(Product product) {
        ShoppingItem shoppingItem = new ShoppingItem(product);
        getShoppingCart().removeItem(shoppingItem);
    }


    public void clearShoppingCart() {
        iMatDataHandler.getShoppingCart().clear();
    }

}

