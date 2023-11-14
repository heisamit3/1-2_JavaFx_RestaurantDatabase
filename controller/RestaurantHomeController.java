package controller;

import sample.*;
import java.io.IOException;
import java.util.List;
import java.util.Map;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import part1.Food;

public class RestaurantHomeController {

    @FXML
    private Button rDashboardBtn;

    @FXML
    private Text orderCount;

    @FXML
    private AnchorPane restaurantDashboardPane;

    @FXML
    private AnchorPane restaurantLeftPane;

    @FXML
    private AnchorPane restaurantLogoutPane;

    @FXML
    private AnchorPane restaurantMenuPane;

    @FXML
    private AnchorPane restaurantOrdersPane;

    @FXML
    private ImageView rImage;

    @FXML
    private Label rProfiLabel;

    @FXML
    private ListView<String> rProfileList;

    private Main main;

    @FXML
    void rDashboardBtnAction() {

        restaurantDashboardPane.setVisible(true);
        restaurantMenuPane.setVisible(false);
        restaurantOrdersPane.setVisible(false);

    }

    @FXML
    private Button viewOrders;

    @FXML
    private ListView<AnchorPane> restaurantOrderList;

    public void refreshOrderCountInRestaurantController() {

        orderCount.setText(String.valueOf(main.orderMap.size()));
    }

    @FXML
    public void viewOrdersAction(ActionEvent event) {

        restaurantOrdersPane.setVisible(true);
        restaurantDashboardPane.setVisible(false);
        restaurantMenuPane.setVisible(false);
        addFoodPane.setVisible(false);

        restaurantOrderList.getItems().clear(); // Clear existing items

        for (Map.Entry<String, List<Pair<Food, Integer>>> entry : main.orderMap.entrySet()) {

            String customerName = entry.getKey();
            List<Pair<Food, Integer>> orderItems = entry.getValue();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../fxmls/minumalOrderInRestaurant.fxml"));

            try {
                AnchorPane orderItemPane = loader.load();
                RestaurantOrderController controller = loader.getController();

                // Initialize the order item and set the main instance
                controller.init(customerName, orderItems, main);

                // Add the order item to the list
                restaurantOrderList.getItems().add(orderItemPane);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    @FXML
    private Button viewMenu;

    @FXML
    public void viewMenuAction(ActionEvent event) {

        restaurantMenuPane.setVisible(true);
        restaurantOrdersPane.setVisible(false);
        restaurantDashboardPane.setVisible(false);

        addFoodPane.setVisible(false);

        for (var r : main.restaurants) {

            if (r.getName().equals(main.getRestaurantName())) {

                for (var f : r.getMenu()) {

                    VBox vb = new VBox();
                    Label label = new Label(f.getName());
                    label.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 18; -fx-text-fill: #0078d4;");
                    vb.getChildren().add(label);
                    Text t1 = new Text("Price: " + String.valueOf(f.getPrice()));
                    vb.getChildren().add(t1);

                    restaurantMenu.getItems().add(vb);

                }

                break;

            }
        }

    }

    @FXML
    ListView<VBox> restaurantMenu;

    @FXML
    private Button addFoodBtn;

    @FXML
    private TextField addFoodCata;

    @FXML
    private Label addFoodIn;

    @FXML
    private TextField addFoodName;

    @FXML
    private AnchorPane addFoodPane;

    @FXML
    private TextField addFoodPrice;

    @FXML
    private Button addFood;

    @FXML
    public void addFoodAction(ActionEvent event) {

        addFoodPane.setVisible(true);
        restaurantMenuPane.setVisible(false);
        restaurantOrdersPane.setVisible(false);

    }

    @FXML
    void addFoodBtnAction(ActionEvent event) {
        String name = addFoodName.getText();
        String cata = addFoodCata.getText();
        String price = addFoodPrice.getText();

        if (name.isEmpty() || cata.isEmpty() || price.isEmpty()) {
            addFoodIn.setText("Please fill up all the fields");
            System.out.println("add food error");
        } else {
            try {
                // Create a new Food object
                Food food = new Food(main.getRestaurantId(), main.getRestaurantName(), cata, name,
                        Float.parseFloat(price));

                // Find the restaurant in main.restaurants and add the food
                synchronized (main.restaurants) {
                    for (var r : main.restaurants) {
                        if (r.getName().equals(main.getRestaurantName())) {
                            r.getMenu().add(food);
                            System.out.println(food + " food added");

                            main.getNetworkUtil().write(food);
                            break;
                        }
                    }
                }

            } catch (Exception e) {
                // Handle the case where price is not a valid number
                // addFoodIn.setText("Price must be a valid number");
                System.out.println("Invalid price");
            }
        }

        // Clear input fields
        addFoodName.clear();
        addFoodCata.clear();
        addFoodPrice.clear();
        main.showAlertFoodAdded();
    }

    @FXML
    private Label rProfileLabel;

    public void init(String msg) {
        //orderCount.setText("0");
        String imagePath =  "../photo/"+ msg + ".png";
        //String imagePath =  "../photo/KFC.png";
        
        try {
            // Try to load the specified image
            Image img = new Image(Main.class.getResourceAsStream(imagePath));
            rImage.setImage(img);
        } catch (NullPointerException e) {
            // Image not found, load a fallback image (dummy.png)
            Image fallbackImg = new Image(Main.class.getResourceAsStream("../photo/dummy.png"));
            rImage.setImage(fallbackImg);
        }
    }
    

    private void profileShow() {
        String toShowInRProfileLabel = "Name : " + main.getRestaurantName() + "\n" + "Id : " + main.getRestaurantId();
        rProfileList.getItems().clear();

        for (var r : main.restaurants) {
            if (r.getName().equals(main.getRestaurantName())) {

                toShowInRProfileLabel += "\n" + "Price : " + r.getPrice();
                toShowInRProfileLabel += "\n" + "Score : " + r.getScore();
                toShowInRProfileLabel += "\n" + "Zip Code : " + r.getZipCode();

                rProfileList.getItems().addAll(r.getCatagory());

                break;
            }
        }

        rProfileLabel.setText(toShowInRProfileLabel);
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
    private Button backToMainMenu;

    public void backToMainMenuAction() {

        restaurantOrdersPane.setVisible(false);
        restaurantMenuPane.setVisible(false);
        addFoodPane.setVisible(false);
        restaurantDashboardPane.setVisible(true);

    }

    public void refreshOrderListInRestaurant() {

        viewOrdersAction(null);

    }

    @FXML
    public void rProfileBtnAction(ActionEvent event) {
        restaurantDashboardPane.setVisible(true);
        restaurantMenuPane.setVisible(false);
        restaurantOrdersPane.setVisible(false);
        addFoodPane.setVisible(false);
        profileShow();
    }

    public void setMain(Main main) {
        this.main = main;
        
        
    }

}
