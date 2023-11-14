package controller;

import part1.*;
import sample.*;



import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class HomeController {

    private Main main;

    @FXML
    private Button viewProfile;

    @FXML
    private Button orderNow;

    @FXML
    private Button viewCart;

    @FXML
    private Button offers;

    @FXML
    private ImageView image;

    @FXML
    private Button button;

    @FXML
    private Button searchFood;

    @FXML
    private Button goButtonBackToMain;

    @FXML
    private Button goButtonCatagoryWise;

    @FXML
    private Button goButtonSRBC;

    @FXML
    private Button goButtonSRBN;

    @FXML
    private Button goButtonSRBP;

    @FXML
    private Button goButtonSRBS;

    @FXML
    private Button goButtonSRBZC;

    @FXML
    private AnchorPane paneOfSRBC;

    @FXML
    private AnchorPane paneOfSRBN;

    @FXML
    private AnchorPane paneOfSRBP;

    @FXML
    private AnchorPane paneOfSRBS;

    @FXML
    private AnchorPane paneOfSRBZC;

    @FXML
    private Button searchButtonSRBC;

    @FXML
    private Button searchButtonSRBN;

    @FXML
    private Button searchButtonSRBP;

    @FXML
    private Button searchButtonSRBS;

    @FXML
    private Button searchButtonSRBZC;

    @FXML
    private Button searchRestaurants;

    @FXML
    private TextField getSRBN;

    @FXML
    private AnchorPane selectionBoxRestaurants;

    @FXML
    private ListView<String> restaurantList;

    // ************************************************* */
    private SearchRestaurant sRObj = new SearchRestaurant();
    private SearchFood sFObj = new SearchFood();
    private List<Restaurant> tRL = new ArrayList<>();
    private List<Food> tFO = new ArrayList<>();
    private String customerName;

    @FXML
    private Label viewProfileName;

    public void init(String customerName) {

        this.customerName = customerName;
        Image img = new Image(Main.class.getResourceAsStream("../photo/1.png"));
        image.setImage(img);
        viewProfileName.setText(customerName);

    }

    @FXML
    void logoutAction(ActionEvent event) {


        try {
            main.showLoginPage();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void mainMenuAction(ActionEvent event) {

        mainMenuPane.setVisible(true);
        selectionBoxRestaurants.setVisible(false);
        paneOfSRBN.setVisible(false);
        paneOfSRBC.setVisible(false);
        paneOfSRBP.setVisible(false);
        paneOfSRBS.setVisible(false);
        paneOfSRBZC.setVisible(false);
        cardRMenuPane.setVisible(false);
        cardRPane.setVisible(false);
        resultRestaurant.setVisible(false);
        cardRMenuList.getItems().clear();
        cardRMenuList.getItems().clear();
        viewCartPane.setVisible(false);
        selectionBoxFood.setVisible(false);
        catagoryWiseRPane.setVisible(false);
        viewProfilePane.setVisible(true);

    }

    @FXML
    private AnchorPane viewProfilePane;

    @FXML
    public AnchorPane SearchRestaurantsPane;

    @FXML
    public void searchRestaurantsAction(ActionEvent event) {

        SearchRestaurantsPane.setVisible(true);
        selectionBoxRestaurants.setVisible(true);
        paneOfSRBS.setVisible(false);
        paneOfSRBC.setVisible(false);
        paneOfSRBP.setVisible(false);
        paneOfSRBN.setVisible(false);
        paneOfSRBZC.setVisible(false);
        cardRMenuPane.setVisible(false);
        cardRPane.setVisible(false);
        resultRestaurant.setVisible(false);
        selectionBoxFood.setVisible(false);
        viewCartPane.setVisible(false);
        catagoryWiseRPane.setVisible(false);
        viewProfilePane.setVisible(false);

    }

    @FXML
    public void SRBN_PaneAction(ActionEvent event) {

        selectionBoxRestaurants.setVisible(false);
        paneOfSRBN.setVisible(true);
        paneOfSRBC.setVisible(false);
        paneOfSRBP.setVisible(false);
        paneOfSRBS.setVisible(false);
        paneOfSRBZC.setVisible(false);
        catagoryWiseRPane.setVisible(false);
        viewProfilePane.setVisible(false);
        

    }

    @FXML
    public void SRBS_PaneAction(ActionEvent event) {

        selectionBoxRestaurants.setVisible(false);
        paneOfSRBS.setVisible(true);
        paneOfSRBC.setVisible(false);
        paneOfSRBP.setVisible(false);
        paneOfSRBN.setVisible(false);
        paneOfSRBZC.setVisible(false);
        catagoryWiseRPane.setVisible(false);
        viewProfilePane.setVisible(false);

    }

    @FXML
    public void SRBC_PaneAction(ActionEvent event) {

        selectionBoxRestaurants.setVisible(false);
        paneOfSRBC.setVisible(true);
        paneOfSRBN.setVisible(false);
        paneOfSRBP.setVisible(false);
        paneOfSRBS.setVisible(false);
        paneOfSRBZC.setVisible(false);
        catagoryWiseRPane.setVisible(false);
        viewProfilePane.setVisible(false);

    }

    @FXML
    public void SRBP_PaneAction(ActionEvent event) {

        selectionBoxRestaurants.setVisible(false);
        paneOfSRBP.setVisible(true);
        paneOfSRBC.setVisible(false);
        paneOfSRBN.setVisible(false);
        paneOfSRBS.setVisible(false);
        paneOfSRBZC.setVisible(false);
        catagoryWiseRPane.setVisible(false);
        viewProfilePane.setVisible(false);

    }

    @FXML
    public void SRBZC_PaneAction(ActionEvent event) {

        selectionBoxRestaurants.setVisible(false);
        paneOfSRBZC.setVisible(true);
        paneOfSRBC.setVisible(false);
        paneOfSRBP.setVisible(false);
        paneOfSRBS.setVisible(false);
        paneOfSRBN.setVisible(false);
        catagoryWiseRPane.setVisible(false);
        viewProfilePane.setVisible(false);

    }

    // @FXML
    // public void searchRestaurantCatagoryWIseAction(ActionEvent event){

    // selectionBoxRestaurants.setVisible(false);
    // searchRestaurantCatagoryWIse.setVisible(true);

    // }

    @FXML
    public void backToMainMenuAction(ActionEvent event) {

        selectionBoxRestaurants.setVisible(false);

    }

    //////////

    @FXML
    private AnchorPane resultRestaurant;

    @FXML
    private Button resultRViewBtn;

    @FXML
    private AnchorPane cardRPane;

    @FXML
    private Label cardRName;

    @FXML
    private Label cardRScore;

    @FXML
    private Label cardRPrice;

    @FXML
    private Label cardRZip;

    @FXML
    private ListView<String> cardRCatagories;

    @FXML
    public void searchButtonSRBNAction(ActionEvent event) {

        paneOfSRBN.setVisible(false);
        resultRestaurant.setVisible(true);

        tRL.clear();
        restaurantList.getItems().clear();

        String restaurantName = getSRBN.getText();

        if(restaurantName.equals("")){

            main.showAleartEmptyField();
            return;
        }

        sRObj.searchRestaurantByName(restaurantName, main.restaurants, tRL);

        for (var restaurant : tRL) {

            restaurantList.getItems().add(restaurant.getName());
        }

        getSRBN.clear();

    }

    @FXML
    private TextField getSRBC;

    @FXML
    public void searchButtonSRBCAction(ActionEvent event) {

        paneOfSRBC.setVisible(false);
        resultRestaurant.setVisible(true);

        tRL.clear();
        restaurantList.getItems().clear();

        String restaurantCatagory = getSRBC.getText();

        if(restaurantCatagory.equals("")){

            main.showAleartEmptyField();
            return;
        }

        sRObj.searchRestaurantByCatagory(restaurantCatagory, main.restaurants, tRL);

        for (var restaurant : tRL) {

            restaurantList.getItems().add(restaurant.getName());
        }

        getSRBC.clear();

    }

    @FXML
    private TextField getSRBP;

    @FXML
    public void searchButtonSRBPAction(ActionEvent event) {

        paneOfSRBP.setVisible(false);
        resultRestaurant.setVisible(true);

        tRL.clear();
        restaurantList.getItems().clear();

        String restaurantPrice = getSRBP.getText();

        if(restaurantPrice.equals("")){

            main.showAleartEmptyField();
            return;
        }

        sRObj.searchRestaurantByPrice(restaurantPrice, main.restaurants, tRL);

        for (var restaurant : tRL) {

            restaurantList.getItems().add(restaurant.getName());
        }

        getSRBP.clear();

    }

    @FXML
    private TextField getSRBS1;

    @FXML
    private TextField getSRBS2;

    public void searchButtonSRBSAction(ActionEvent event) {

        paneOfSRBS.setVisible(false);
        resultRestaurant.setVisible(true);

        tRL.clear();
        restaurantList.getItems().clear();

        String restaurantScore1 = getSRBS1.getText();
        String restaurantScore2 = getSRBS2.getText();

        if(restaurantScore1.equals("") || restaurantScore2.equals("")){

            main.showAleartEmptyField();
            return;
        }

        sRObj.searchRestaurantByScore(Double.parseDouble(restaurantScore1), Double.parseDouble(restaurantScore2),
                main.restaurants, tRL);

        for (var restaurant : tRL) {

            restaurantList.getItems().add(restaurant.getName());
        }

        getSRBS1.clear();

    }

    @FXML
    private TextField getSRBZC;

    public void searchButtonSRBZCAction(ActionEvent event) {

        paneOfSRBZC.setVisible(false);
        resultRestaurant.setVisible(true);

        tRL.clear();
        restaurantList.getItems().clear();

        String restaurantZipCode = getSRBZC.getText();

        if(restaurantZipCode.equals("")){

            main.showAleartEmptyField();
            return;
        }

        sRObj.searchRestaurantByZipCode(Integer.parseInt(restaurantZipCode), main.restaurants, tRL);

        for (var restaurant : tRL) {

            restaurantList.getItems().add(restaurant.getName());
        }

        getSRBZC.clear();

    }

    @FXML
    private Label cardRMenuFoodsIn;

    @FXML
    public void resultRViewBtnAction(ActionEvent event) {

        String restaurantName = restaurantList.getSelectionModel().getSelectedItem();

        tRL.clear();
        restaurantList.getItems().clear();

        cardRMenuFoodsIn.setText(restaurantName);

        for (var restaurant : main.restaurants) {

            if (((Restaurant) restaurant).getName().equals(restaurantName)) {

                resultRestaurant.setVisible(false);
                cardRPane.setVisible(true);

                cardRName.setText(((Restaurant) restaurant).getName());
                cardRScore.setText(String.valueOf(((Restaurant) restaurant).getScore()));
                cardRPrice.setText(((Restaurant) restaurant).getPrice());
                cardRZip.setText(String.valueOf(((Restaurant) restaurant).getZipCode()));

                cardRCatagories.getItems().clear();
                cardRCatagories.getItems().addAll(((Restaurant) restaurant).getCatagory());

                forPopulate = (Restaurant) restaurant;

                break;

            }
        }

    }

    private Restaurant forPopulate;

    @FXML
    private ListView<VBox> cardRMenuList;

    @FXML
    private AnchorPane cardRMenuPane;

    public void populateCardRMenuList() throws IOException {

        cardRPane.setVisible(false);
        cardRMenuPane.setVisible(true);

        for (var food : forPopulate.getMenu()) {

            FXMLLoader loader = new FXMLLoader();
            // Provide the correct path to the FXML file
            loader.setLocation(getClass().getResource("../fxmls/food.fxml"));

            try {
                // Load the FXML and create a new instance of the controller
                loader.load();
                FoodCardController controller = loader.getController();

                // Initialize the food item and set the main instance

                VBox foodCard = controller.init(food, customerName);
                controller.setMain(main);

                // Add the food item to the list
                cardRMenuList.getItems().add(foodCard);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private ListView<VBox> myCart;

    @FXML
    private AnchorPane viewCartPane;

    @FXML
    public void viewCartAction(ActionEvent event) {

        if (main.orderMap.isEmpty()) {

            main.showCartIsEmptyAlert();
            return;
        }

        myCart.getItems().clear();

        viewCartPane.setVisible(true);
        selectionBoxRestaurants.setVisible(false);
        paneOfSRBN.setVisible(false);
        paneOfSRBC.setVisible(false);
        paneOfSRBP.setVisible(false);
        paneOfSRBS.setVisible(false);
        paneOfSRBZC.setVisible(false);
        cardRMenuPane.setVisible(false);
        cardRPane.setVisible(false);
        resultRestaurant.setVisible(false);
        catagoryWiseRPane.setVisible(false);
        selectionBoxFood.setVisible(false);
        viewProfilePane.setVisible(false);

        for (Pair<Food, Integer> food : main.orderMap.get(customerName)) {

            FXMLLoader loader = new FXMLLoader();
            // Provide the correct path to the FXML file
            loader.setLocation(getClass().getResource("../fxmls/foodInCart.fxml"));

            try {
                // Load the FXML and create a new instance of the controller
                loader.load();
                myCartFoodController controller = loader.getController();

                // Initialize the food item and set the main instance

                VBox foodCard = controller.init(food.getFirst(), food.getSecond());
                controller.setMain(main);

                // Add the food item to the list
                myCart.getItems().add(foodCard);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        viewCartTotalPrice.setText(String.format("%.2f", main.totalCost) + " $");

        // for (List<Pair<Food, Integer>> orderList : main.orderMap.values()) {
        // for (Pair<Food, Integer> foodPair : orderList) {
        // Food food = foodPair.getFirst();
        // int quantity = foodPair.getSecond();
        // System.out.println("Food Name: " + food.getName());
        // System.out.println("Quantity: " + quantity);
        // }
        // }

    }

    @FXML
    private Label viewCartTotalPrice;

    @FXML
    private Button placeOrder;

    @FXML
    private Button clearCartBtn;

    @FXML
    public void clearCartBtnAction(ActionEvent event) throws IOException {

        main.totalCost = 0.0f;
        main.orderMap.clear();
        myCart.getItems().clear();

        viewCartAction(null);

    }

    @FXML
    public void placeOrderAction() throws IOException {

        System.out.println("place order clicked");

        main.getNetworkUtil().write(main.orderMap);

        System.out.println("order placed");

        main.totalCost = 0.0f;
        main.orderMap.clear();
        myCart.getItems().clear();
        viewCartPane.setVisible(false);
        main.refreshTotalPriceInRSearch();
        main.refreshTotalPriceInFoodSearch();
        
        //if(!main.orderMap.isEmpty()){

        main.showOrderPlacedAlert();
        
    }

    @FXML
    private AnchorPane catagoryWiseRPane;

    @FXML
    private Label catagWLabel;

    @FXML
    public void goButtonCatagoryWiseAction() {

        catagoryWiseRPane.setVisible(true);
        selectionBoxRestaurants.setVisible(false);
        paneOfSRBN.setVisible(false);
        paneOfSRBC.setVisible(false);
        paneOfSRBP.setVisible(false);
        paneOfSRBS.setVisible(false);
        paneOfSRBZC.setVisible(false);
        cardRMenuPane.setVisible(false);
        cardRPane.setVisible(false);
        resultRestaurant.setVisible(false);
        viewCartPane.setVisible(false);
        selectionBoxFood.setVisible(false);
        viewProfilePane.setVisible(false);

        List<String> rCatagories = new ArrayList<>();

        for (var restaurant : main.restaurants) {

            for (var catagory : ((Restaurant) restaurant).getCatagory()) {

                if (!rCatagories.contains(catagory)) {

                    rCatagories.add(catagory);
                }
            }
        }

        for (String string : rCatagories) {

            sRObj.catagoryWiseRestaurantNames(string, main.restaurants, tRL);

            String catagoryWiseRestaurants = "";

            for (int i = 0; i < tRL.size(); i++) {

                if (i > 0) {
                    catagoryWiseRestaurants += ", ";
                }

                catagoryWiseRestaurants += tRL.get(i).getName();
            }

            catagWLabel.setText(catagWLabel.getText() + "\n" + string + ": " + catagoryWiseRestaurants);

            tRL.clear();
        }

    }

    // public void populateCardRMenuList() {

    // cardRPane.setVisible(false);
    // cardRMenuPane.setVisible(true);

    // for (var restaurant : restaurants) {
    // if (restaurant.getName().equals("KFC")) {
    // List<String> menuItems = new ArrayList<>();

    // for (var food : restaurant.getMenu()) {
    // menuItems.add(food.getName());

    // System.out.println(food.getName());
    // }

    // cardRMenuList.getItems().addAll(menuItems);
    // break;
    // }
    // }
    // }

    /*
     * public void initiate(HashMap<String, Integer> countryWiseCount) throws
     * IOException {
     * for (var they : countryWiseCount.entrySet()){
     * var newLoader = new FXMLLoader();
     * newLoader.setLocation(getClass().getResource(
     * "/ViewFX/CountryCountSingleView.fxml"));
     * newLoader.load();
     * CountryCountSingleViewController cDetail = newLoader.getController();
     * cDetail.setMain(main);
     * countryListView.getItems().add(cDetail.initiate(they.getKey(),
     * they.getValue()));
     * }
     * }
     */

    @FXML
    private Button mainMenu;

    @FXML
    private AnchorPane mainMenuPane;

    @FXML
    private Label totalPriceInRSearch;

    public void refreshTotalPriceInRSearch() {

        totalPriceInRSearch.setText(String.format("%.2f", main.totalCost) + " $");

    }

    public void refreshCart() {

        viewCartAction(null);
    }

    /////////////////////////////////////////////////////////////////////////////////////////
    // search food part

    @FXML
    private AnchorPane selectionBoxFood;

    @FXML
    private AnchorPane searchFoodBCPane;

    @FXML
    private AnchorPane searchFoodBCPane2;

    @FXML
    private AnchorPane searchFoodBNPane;

    @FXML
    private AnchorPane searchFoodBNPane2;

    @FXML
    private ListView<VBox> searchFoodList;

    @FXML
    private MenuButton searchFoodMenuButton;

    @FXML
    private AnchorPane searchFoodPRPane;

    @FXML
    private AnchorPane searchFoodPRPane2;

    @FXML
    private Label searchFoodTotal;

    @FXML
    private TextField sRBC2TF1;

    @FXML
    private TextField sRBC2TF2;

    @FXML
    private TextField sRBCTF;

    @FXML
    private TextField sRBN2TF1;

    @FXML
    private TextField sRBN2TF2;

    @FXML
    private TextField sRBNTF;

    @FXML
    private TextField sRBPR2TF1;

    @FXML
    private TextField sRBPR2TF2;

    @FXML
    private TextField sRBPR2TF3;

    @FXML
    private TextField sRBPRTF1;

    @FXML
    private TextField sRBPRTF2;

    @FXML
    private TextField sRBPRTF3;

    // @FXML
    // public void searchFoodBNkeyAction(KeyEvent event) {

    // searchFoodList.getItems().clear();

    // String foodName = sRBNTF.getText();

    // sFObj.searchFoodByName(foodName,main.restaurants, tFO);

    // for (var food : tFO) {

    // FXMLLoader loader = new FXMLLoader();
    // // Provide the correct path to the FXML file
    // loader.setLocation(getClass().getResource("/sample/food.fxml"));

    // try {
    // // Load the FXML and create a new instance of the controller
    // loader.load();
    // FoodCardController controller = loader.getController();

    // // Initialize the food item and set the main instance

    // VBox foodCard = controller.init(food, customerName);
    // controller.setMain(main);

    // // Add the food item to the list
    // searchFoodList.getItems().add(foodCard);

    // } catch (IOException e) {
    // e.printStackTrace();
    // }
    // }

    // }

    public void setMain(Main main) {
        this.main = main;
    }

    // ...

    @FXML
    private ChoiceBox<String> choiceBox;

    public void initialize() {
        // Initialize the ChoiceBox with food search options
        choiceBox.getItems().addAll(
                "By Name",
                "By Name in a Given Restaurant",
                "By Category",
                "By Category in a Given Restaurant",
                "By Price Range",
                "By Price Range in a Given Restaurant",
                "Costliest Food Item(s) on the Menu in a Given Restaurant");
        choiceBox.setValue("By Name"); // Set default selection
    }

    @FXML
    private void handleChoiceBoxAction(ActionEvent event) {
        String selectedOption = choiceBox.getValue();

        // Check the selected option and toggle the visibility of the Pane
        if (selectedOption.equals("By Name")) {

            searchFoodBNPane.setVisible(true);
            searchFoodBNPane2.setVisible(false);
            // make every pane false except this one

            searchFoodBCPane.setVisible(false);
            searchFoodBCPane2.setVisible(false);
            searchFoodPRPane.setVisible(false);
            searchFoodPRPane2.setVisible(false);
            costliestPane.setVisible(false);

            searchFoodList.getItems().clear();

        } else if (selectedOption.equals("By Name in a Given Restaurant")) {
            searchFoodBNPane2.setVisible(true);
            searchFoodBNPane.setVisible(false);
            searchFoodBCPane.setVisible(false);
            searchFoodBCPane2.setVisible(false);
            searchFoodPRPane.setVisible(false);
            searchFoodPRPane2.setVisible(false);
            costliestPane.setVisible(false);
            searchFoodList.getItems().clear();

        } else if (selectedOption.equals("By Category")) {
            searchFoodBCPane.setVisible(true);
            searchFoodBNPane.setVisible(false);
            searchFoodBNPane2.setVisible(false);
            searchFoodBCPane2.setVisible(false);
            searchFoodPRPane.setVisible(false);
            searchFoodPRPane2.setVisible(false);
            costliestPane.setVisible(false);
            searchFoodList.getItems().clear();

        } else if (selectedOption.equals("By Category in a Given Restaurant")) {
            searchFoodBCPane2.setVisible(true);
            searchFoodBNPane.setVisible(false);
            searchFoodBNPane2.setVisible(false);
            searchFoodBCPane.setVisible(false);
            searchFoodPRPane.setVisible(false);
            searchFoodPRPane2.setVisible(false);
            costliestPane.setVisible(false);
            searchFoodList.getItems().clear();
        } else if (selectedOption.equals("By Price Range")) {
            searchFoodPRPane.setVisible(true);
            searchFoodBNPane.setVisible(false);
            searchFoodBNPane2.setVisible(false);
            searchFoodBCPane.setVisible(false);
            searchFoodBCPane2.setVisible(false);
            searchFoodPRPane2.setVisible(false);
            costliestPane.setVisible(false);
            searchFoodList.getItems().clear();
        } else if (selectedOption.equals("By Price Range in a Given Restaurant")) {
            searchFoodPRPane2.setVisible(true);
            searchFoodBNPane.setVisible(false);
            searchFoodBNPane2.setVisible(false);
            searchFoodBCPane.setVisible(false);
            searchFoodBCPane2.setVisible(false);
            searchFoodPRPane.setVisible(false);
            costliestPane.setVisible(false);
            searchFoodList.getItems().clear();
        } else if (selectedOption.equals("Costliest Food Item(s) on the Menu in a Given Restaurant")) {

            costliestPane.setVisible(true);
            searchFoodBNPane.setVisible(false);
            searchFoodBNPane2.setVisible(false);
            searchFoodBCPane.setVisible(false);
            searchFoodBCPane2.setVisible(false);
            searchFoodPRPane.setVisible(false);
            searchFoodPRPane2.setVisible(false);
            searchFoodList.getItems().clear();
        }

    }

    @FXML
    public void searchFoodAction() {

        selectionBoxFood.setVisible(true);
        mainMenuPane.setVisible(true);
        viewCartPane.setVisible(false);
        resultRestaurant.setVisible(false);
        selectionBoxRestaurants.setVisible(false);
        viewCartPane.setVisible(false);
        cardRMenuPane.setVisible(false);
        viewProfilePane.setVisible(false);
    }

    @FXML
    private TextField costliestRName;

    private ObservableList<VBox> foodItems = FXCollections.observableArrayList();

    @FXML
    public void searchFoodBtnAction(ActionEvent event) {

        mainMenuPane.setVisible(true);
        selectionBoxFood.setVisible(true);
        foodItems.clear();
        tFO.clear();
        System.out.println("search food button clicked");

        if (searchFoodBNPane.isVisible()) {

            // tFO.clear();
            sFObj.searchFoodByName(sRBNTF.getText(), main.restaurants, tFO);
            sRBNTF.clear();

        }

        else if (searchFoodBNPane2.isVisible()) {

            sFObj.searchFoodByNameInRestaurant(sRBN2TF1.getText(), sRBN2TF2.getText(), main.restaurants, tFO);
            sRBN2TF1.clear();
        }

        else if (searchFoodBCPane.isVisible()) {

            sFObj.searchFoodByCatagory(sRBCTF.getText(), main.restaurants, tFO);
            sRBCTF.clear();
        }

        else if (searchFoodBCPane2.isVisible()) {

            sFObj.searchFoodByCatagoryInRestaurant(sRBC2TF1.getText(), sRBC2TF2.getText(), main.restaurants, tFO);
            sRBC2TF1.clear();
        }

        else if (searchFoodPRPane.isVisible()) {

            sFObj.searchFoodByPriceRange(Float.parseFloat(sRBPRTF1.getText()), Float.parseFloat(sRBPRTF2.getText()),
                    main.restaurants, tFO);
            sRBPRTF1.clear();
            sRBPRTF2.clear();

        }

        else if (searchFoodPRPane2.isVisible()) {

            sFObj.searchFoodByPriceRangeInRestaurant(Float.parseFloat(sRBPR2TF1.getText()),
                    Float.parseFloat(sRBPR2TF2.getText()), sRBPR2TF3.getText(), main.restaurants, tFO);
            sRBPR2TF1.clear();
            sRBPR2TF2.clear();
            sRBPR2TF3.clear();

        }


        else if (costliestPane.isVisible()) {

            
            sFObj.showCostliestFoodInRestaurant(costliestRName.getText(),main.restaurants, tFO);
            costliestRName.clear();


        }

        for (var food : tFO) {

            FXMLLoader loader = new FXMLLoader();
            // Provide the correct path to the FXML file
            loader.setLocation(getClass().getResource("../fxmls/food.fxml"));

            try {
                // Load the FXML and create a new instance of the controller
                loader.load();
                FoodCardController controller = loader.getController();

                // Initialize the food item and set the main instance

                VBox foodCard = controller.init(food, customerName);
                controller.setMain(main);

                foodItems.add(foodCard);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        searchFoodList.setItems(foodItems);
    }

    public void refreshTotalPriceInFoodSearch() {

        searchFoodTotal.setText(String.format("%.2f", main.totalCost) + " $");

    }

    //////////////////////////////////////
    // order now

    @FXML
    public void orderNowAction() {

        System.out.println("order now clicked");

        SearchRestaurantsPane.setVisible(true);
        selectionBoxRestaurants.setVisible(false);
        paneOfSRBN.setVisible(false);
        paneOfSRBC.setVisible(false);
        paneOfSRBP.setVisible(false);
        paneOfSRBS.setVisible(false);
        paneOfSRBZC.setVisible(false);
        cardRPane.setVisible(false);
        cardRMenuPane.setVisible(false);
        viewCartPane.setVisible(false);
        selectionBoxFood.setVisible(false);
        cardRMenuList.getItems().clear();
        resultRestaurant.setVisible(true);
        catagoryWiseRPane.setVisible(false);
        viewProfilePane.setVisible(false);

        restaurantList.getItems().clear();

        for (var restaurant : main.restaurants) {

            restaurantList.getItems().add(((Restaurant) restaurant).getName());
        }

    }

    @FXML
    private Button viewProfileBtn;

    @FXML
    public void viewProfileBtnAction(){

        viewProfilePane.setVisible(true);
        selectionBoxRestaurants.setVisible(false);
        paneOfSRBN.setVisible(false);
        paneOfSRBC.setVisible(false);
        paneOfSRBP.setVisible(false);
        paneOfSRBS.setVisible(false);
        paneOfSRBZC.setVisible(false);
        cardRMenuPane.setVisible(false);
        cardRPane.setVisible(false);
        resultRestaurant.setVisible(false);
        viewCartPane.setVisible(false);
        selectionBoxFood.setVisible(false);
        catagoryWiseRPane.setVisible(false);
        mainMenuPane.setVisible(true);

    }

    @FXML
    private AnchorPane costliestPane;

}
