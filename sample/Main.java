package sample;

import part1.*;
import controller.*;
import controller.RestaurantHomeController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import util.NetworkUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Main extends Application {

    private Stage stage;
    private NetworkUtil networkUtil;
    private boolean isCustomer;
    private String customerName = null;
    public String restaurantName = null;
    public int restaurantId;
    public List<Restaurant> restaurants;
    public HashMap<String, List<Pair<Food, Integer>>> orderMap;
    public Float totalCost = 0.0f;
    private HomeController homeController;
    private RestaurantHomeController restaurantHomeController;

    public boolean isCustomer() {
        return isCustomer;
    }

    public void setCustomer(boolean customer) {
        isCustomer = customer;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public Stage getStage() {
        return stage;
    }

    public NetworkUtil getNetworkUtil() {
        return networkUtil;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        connectToServer();
        showLoginPage();

        orderMap = new HashMap<>();
    }

    private void connectToServer() throws IOException {
        String serverAddress = "127.0.0.1";
        int serverPort = 33333;
        networkUtil = new NetworkUtil(serverAddress, serverPort);
        new ReadThread(this);
    }

    public void showLoginPage() throws Exception {

        // XML Loading using FXMLLoader
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../fxmls/login.fxml"));
        Parent root = loader.load();

        // Loading the controller
        LoginController controller = loader.getController();
        controller.setMain(this);

        Scene scene = new Scene(root, 600, 450);

        // Add the stylesheet to the scene
        scene.getStylesheets().add(getClass().getResource("Styles.css").toExternalForm());

        // Set the primary stage
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();
    }

    public void showHomePage(String userName) throws Exception {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../fxmls/home.fxml"));
        Parent root = loader.load();

        // Loading the controller
        HomeController controller = loader.getController();
        controller.init(userName);
        controller.setMain(this);
        this.homeController = controller;

        Scene scene = new Scene(root, 600, 450);

        // Add the stylesheet to the scene
        scene.getStylesheets().add(getClass().getResource("Styles.css").toExternalForm());

        // Set the primary stage
        stage.setTitle("Home");
        stage.setScene(scene);
        stage.show();
    }

    public void refreshTotalPriceInRSearch() {

        homeController.refreshTotalPriceInRSearch();
    }

    public void refreshOrderCountInRestaurant() {

        restaurantHomeController.refreshOrderCountInRestaurantController();
    }

    public void refreshTotalPriceInFoodSearch() {

        homeController.refreshTotalPriceInFoodSearch();
    }

    public void refreshOrderListInRestaurant() {

        restaurantHomeController.refreshOrderListInRestaurant();
    }

    public void refreshCart() {

        homeController.refreshCart();
    }

    // public void showFoodsPage(String userName) throws Exception {

    // FXMLLoader loader = new FXMLLoader();
    // loader.setLocation(getClass().getResource("foods.fxml"));
    // Parent root = loader.load();

    // // Loading the controller
    // FoodController controller = loader.getController();
    // controller.init(userName);
    // controller.setMain(this);
    // controller.hello();

    // // Set the primary stage
    // stage.setTitle("Home");
    // stage.setScene(new Scene(root, 600, 450));
    // stage.show();
    // }

    public void showRestaurantHomePage(String userName) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        //FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxmls/HomeRestaurant.fxml"));
        //RestaurantHomeController controller = loader.getController();

        loader.setLocation(getClass().getResource("../fxmls/HomeRestaurant.fxml"));
        Parent root = loader.load();

        RestaurantHomeController controller = loader.getController();
        controller.init(userName);
        controller.setMain(this);

        this.restaurantHomeController = controller;

        // Create the scene
        Scene scene = new Scene(root, 600, 450);

        // Add the stylesheet to the scene
        scene.getStylesheets().add(getClass().getResource("Styles.css").toExternalForm());

        // Set the primary stage
        stage.setTitle("Home");
        stage.setScene(scene);
        stage.show();
    }

    public void showFoodList() throws Exception {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../fxmls/foods.fxml"));
        Parent root = loader.load();

        stage.setTitle("foods");
        stage.setScene(new Scene(root, 600, 450));
        stage.show();
    }

    public void showAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Incorrect Credentials");
        alert.setHeaderText("Incorrect Credentials");
        alert.setContentText("The username and password you provided is not correct.");
        alert.showAndWait();
    }

    public void showAlertRegisterSuccessfull() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Customer Registration");
        alert.setHeaderText("Correct Credentials");
        alert.setContentText("Registration Successfull");
        alert.showAndWait();
    }

    public void showAlertRegisterUnsuccessfull() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Incorrect Credentials");
        alert.setHeaderText("Incorrect Credentials");
        alert.setContentText("The username already exists");
        alert.showAndWait();
    }

    public void showAddRestaurantSuccessAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Restaurant Registration");
        alert.setHeaderText("Correct Credentials");
        alert.setContentText("Registration Successfull");
        alert.showAndWait();
    }

    public void showAddToCartSameRestAlert(Food f) {

        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Different Restaurants");
        alert.setHeaderText("Order Compromised");
        alert.setContentText("Do you want to clear the cart and add the new item?");

        // Create custom buttons for the alert
        ButtonType okButton = new ButtonType("OK");
        ButtonType cancelButton = new ButtonType("Cancel");

        alert.getButtonTypes().setAll(okButton, cancelButton);

        // Show the alert and wait for the user's choice
        alert.showAndWait().ifPresent(response -> {
            if (response == okButton) {
                // Perform action for OK button (e.g., close the window)
                // You can add your logic here

                orderMap.clear();
                totalCost = 0.0f;

                showOrderClearedAlert();

                List<Pair<Food, Integer>> cart = new ArrayList<>();
                cart.add(new Pair<>(f, 1)); // Add the new food item with a quantity of 1
                orderMap.put(customerName, cart);
                totalCost += f.getPrice();

                refreshTotalPriceInRSearch();
                refreshTotalPriceInFoodSearch();

            } else if (response == cancelButton) {

            }
        });
    }

    public void showOrderClearedAlert() {

        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Order Cleared");
        alert.setHeaderText("Order Cleared");
        alert.setContentText("Now you can add items from this restaurant");
        alert.showAndWait();
    }

    public void showCartIsEmptyAlert() {

        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Cart is Empty");
        alert.setHeaderText("Cart is Empty");
        alert.setContentText("Please add items to the cart");
        alert.showAndWait();
    }

    public void showAlertMyOrderConfirmed() {

        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Order Confirmed");
        alert.setHeaderText("Order of " + customerName + "");
        alert.setContentText("Your order has been confirmed");
        alert.showAndWait();
    }

    public void showAlertMyOrderNotConfirmed() {

        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Order Not Confirmed");
        alert.setHeaderText("Order of " + customerName);
        alert.setContentText("Your order has not been declined");
        alert.showAndWait();
    }

    public void showAddRestaurantErrorAlert() {

        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Add Restaurant Error");
        alert.setHeaderText("Add Restaurant Error");
        alert.setContentText("Please fill up all the fields correctly");
        alert.showAndWait();
    }

    public void showAleartEmptyField() {

        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Empty Field");
        alert.setHeaderText("Empty Field");
        alert.setContentText("Please fill up all the fields correctly");
        alert.showAndWait();
    }

    public void showAlertFoodAdded() {

        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Food Added");
        alert.setHeaderText("Food Added");
        alert.setContentText("Food Added Successfully");
        alert.showAndWait();
    }

    public void showOrderPlacedAlert() {

        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Order Placed");
        alert.setHeaderText("Order Placed");
        alert.setContentText("Order Placed Successfully");
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);

    }

}
