package controller;

import sample.*;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import part1.Food;

public class myCartFoodController {

    @FXML
    private Label cardCatagoryName;

    @FXML
    private Label cardFoodName;

    @FXML
    private Label cardPrice;

    @FXML
    private Label cardQuantity;

    @FXML
    private VBox foodCard;

    @FXML
    private Button minusCart;

    @FXML
    private Button plusCart;

    Food food;

    @FXML
    void minusCartAction(ActionEvent event) {

        
        for(var foodPair : main.orderMap.get(main.getCustomerName())){

            if(foodPair.getFirst().equals(food)){

                if(foodPair.getSecond() == 1){
                    
                    main.orderMap.get(main.getCustomerName()).remove(foodPair);
                    main.totalCost -= food.getPrice();
                    main.refreshCart();
                    return;
                }
                foodPair.setSecond(foodPair.getSecond()-1);
                main.totalCost -= food.getPrice();

                
                main.refreshCart();
                return;
            }
            
        }
    }

    @FXML
    void plusCartAction(ActionEvent event) {

        for(var foodPair : main.orderMap.get(main.getCustomerName())){
            
            if(foodPair.getFirst().equals(food)){
                foodPair.setSecond(foodPair.getSecond()+1);
                main.totalCost += food.getPrice();


                
                main.refreshCart();
                return;
            }
            
        }
        
    }

    public VBox init(Food f,int quantity) {

        cardCatagoryName.setText(f.getCatagory());
        cardFoodName.setText(f.getName());
        cardPrice.setText("Price: " + f.getPrice());
        cardQuantity.setText(quantity+"");
        
        this.food = f;
        return foodCard;
    }

    private Main main;

    public void setMain(Main main) {
        this.main = main;
    }

}
