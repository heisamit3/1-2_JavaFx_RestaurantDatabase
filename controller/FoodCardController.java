package controller;

import part1.*;
import sample.*;

import java.util.ArrayList;

import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class FoodCardController {

    @FXML
    private Button addToCart;

    @FXML
    private Label cardCatagoryName;

    @FXML
    private Label cardFoodName;

    @FXML
    private Label cardPrice;

    @FXML
    public VBox foodCard;

    private Main main;

    private Food food;

    public void setMain(Main main) {
        this.main = main;
    }

    private String customerName;

    public VBox init(Food f, String customerName) {

        cardCatagoryName.setText(f.getCatagory());
        cardFoodName.setText(f.getName());
        cardPrice.setText("Price: " + f.getPrice());
        food = f;
        this.customerName = customerName;

        return foodCard;
    }

    // private static HashMap<String, List<Pair<Food, Integer>>> cartMap = new
    // HashMap<>();

    // @FXML
    // public void addToCartAction() {

    // if (cartMap.get(customerName) == null) {

    // List<Food> foodList = new ArrayList<>();
    // foodList.add(food);

    // cartMap.put(customerName, foodList);
    // } else {
    // List<Food> foodList = cartMap.get(customerName);
    // foodList.add(food);
    // cartMap.put(customerName, foodList);
    // }

    // }

    @FXML
    public void addToCartAction() {

        if (!main.orderMap.containsKey(customerName)) {
            // Create a new cart for the customer
            List<Pair<Food, Integer>> cart = new ArrayList<>();
            cart.add(new Pair<>(food, 1)); // Add the new food item with a quantity of 1
            main.orderMap.put(customerName, cart);
            main.totalCost += food.getPrice();
            main.refreshTotalPriceInRSearch();
            main.refreshTotalPriceInFoodSearch();

        } else {
            // Customer's cart already exists

            if (food.getRestaurantId() != main.orderMap.get(customerName).get(0).getFirst().getRestaurantId()) {

                main.showAddToCartSameRestAlert(food);

            } else {

                List<Pair<Food, Integer>> cart = main.orderMap.get(customerName);
                boolean foodExists = false;

                // Check if the same food item exists in the cart
                for (Pair<Food, Integer> cartItem : cart) {
                    if (cartItem.getFirst().equals(food)) {
                        // Food already exists, update quantity
                        cartItem.setSecond(cartItem.getSecond() + 1);
                        foodExists = true;
                        break;
                    }
                }

                main.totalCost += food.getPrice();

                // if add to cart is called , invoke a function here that will call
                // refreshTotalPriceInRSearch in homeController of this main
                main.refreshTotalPriceInRSearch();
                main.refreshTotalPriceInFoodSearch();

                if (!foodExists) {
                    // Food doesn't exist, add it to the cart
                    cart.add(new Pair<>(food, 1)); // Add the new food item with a quantity of 1
                }
            }
        }

    }

    ///////////////////////////////////////////////////////////////////////

    @FXML
    private AnchorPane cartPane;

    @FXML
    private AnchorPane mainMenuPane;

    @FXML
    public void viewCartAction() {

        cartPane.setVisible(true);
        mainMenuPane.setVisible(false);
    }

}
