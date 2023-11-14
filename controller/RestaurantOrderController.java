package controller;
import part1.*;

import sample.*;
import java.io.IOException;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class RestaurantOrderController {

    @FXML
    private Button confirmOrderButton;

    @FXML
    private Label orderPlacer;

    @FXML
    private Label placedOrderPrice;

    @FXML
    private ListView<String> orderedItems;

    String customerName;
    String orderPrice;
    List<Pair<Food, Integer>> cartList;
    Float totalCost=0.0f;


    public void init(String customerName, List<Pair<Food, Integer>> cartList,Main main) {
        
        this.customerName = customerName;
        this.cartList = cartList;
        orderPlacer.setText(customerName);
        placedOrderPrice.setText("100$");
        this.main = main;   

        for(Pair<Food,Integer> p : cartList) {
            orderedItems.getItems().add(p.getFirst().getName() + " x " + p.getSecond());
            totalCost += p.getFirst().getPrice() * p.getSecond();
        }

        placedOrderPrice.setText(String.format("%.2f",totalCost)+ " $");
    }

    @FXML
    void confirmOrderButtonAction() {

        main.orderMap.remove(customerName);

        Pair<String,Integer> pair = new Pair<>(customerName,1);

        try {
            main.getNetworkUtil().write(pair);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        main.refreshOrderCountInRestaurant();
        main.refreshOrderListInRestaurant();
        

    }

    

    @FXML
    private Button declineOrderButton;

    @FXML
    public void declineOrderButtonAction(){

        main.orderMap.remove(customerName);

        Pair<String,Integer> pair = new Pair<>(customerName,0);

        try {
            main.getNetworkUtil().write(pair);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        main.refreshOrderCountInRestaurant();
        main.refreshOrderListInRestaurant();
        
    }

    private Main main;
}
