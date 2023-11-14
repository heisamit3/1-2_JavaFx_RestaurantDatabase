package controller;

import sample.*;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import part1.Restaurant;
import util.LoginDTO;
import util.RegisterDTO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LoginController {

    private Main main;

    @FXML
    private AnchorPane restaurantLogin;

    @FXML
    private Button customerBack;

    @FXML
    private AnchorPane customerLogin;

    @FXML
    private AnchorPane initialTwoLogin;

    @FXML
    private Button customerLoginMain;

    @FXML
    private Button loginButton;

    @FXML
    private Button loginButton1;

    @FXML
    private PasswordField passwordText;

    @FXML
    private PasswordField passwordText1;

    @FXML
    private Button regesterButton;

    @FXML
    private Button regesterButton1;

    @FXML
    private Button restaurantBack;

    @FXML
    private Button restaurantLoginMain;

    @FXML
    private TextField userText;

    @FXML
    private TextField userText1;

    @FXML
    void loginActionForCustomer(ActionEvent event) {

        String userName = userText.getText();
        String password = passwordText.getText();
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setUserName(userName);
        loginDTO.setPassword(password);
        loginDTO.setIsCustomer(true);

        try {
            main.getNetworkUtil().write(loginDTO);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void regesterActionForCustomer(ActionEvent event) {

        String userName = userText.getText();
        String password = passwordText.getText();
        var registerDTO = new RegisterDTO();

        registerDTO.setUserName(userName);
        registerDTO.setPassword(password);

        try {
            main.getNetworkUtil().write(registerDTO);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private AnchorPane addRestaurantPane;

    @FXML
    void loginActionForRestaurant(ActionEvent event) {

        String userName = userText1.getText();
        String password = passwordText1.getText();
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setUserName(userName);
        loginDTO.setPassword(password);
        loginDTO.setIsCustomer(false);

        try {
            main.getNetworkUtil().write(loginDTO);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void regesterActionForRestaurant(ActionEvent event) {

        initialTwoLogin.setVisible(false);
        addRestaurantPane.setVisible(true);
        customerLogin.setVisible(false);
        restaurantLogin.setVisible(false);

    }

    @FXML
    private Button addRBtn;

    @FXML
    private TextField addRCata1;

    @FXML
    private TextField addRCata2;

    @FXML
    private TextField addRCata3;

    @FXML
    private TextField addRName;

    @FXML
    private TextField addRPrice;

    @FXML
    private TextField addRScore;

    @FXML
    private TextField addRZC;

    @FXML
    public void addRBtnAction(ActionEvent event) {

        String name = addRName.getText();
        String price = addRPrice.getText();
        String score = addRScore.getText();
        String zc = addRZC.getText();
        String cata1 = addRCata1.getText();
        String cata2 = addRCata2.getText();
        String cata3 = addRCata3.getText();

        if (cata1 == null || cata1.equals("")) {
            cata1 = "";
        }
        if (cata2 == null || cata2.equals("")) {
            cata2 = "";
        }
        if (cata3 == null || cata3.equals("")) {
            cata3 = "";
        }

        addRName.clear();
        addRPrice.clear();
        addRScore.clear();
        addRZC.clear();
        addRCata1.clear();
        addRCata2.clear();
        addRCata3.clear();

        try {
            Integer.parseInt(zc);
            Double.parseDouble(score);
            Double.parseDouble(price);
        } catch (Exception e) {
            main.showAddRestaurantErrorAlert();

            return;
        }

        List<String> list = new ArrayList<>();

        list.addAll(List.of(cata1, cata2, cata3));

        Restaurant restaurant = new Restaurant(0, name, Double.parseDouble(score), price, Integer.parseInt(zc), list);

        try {
            main.getNetworkUtil().write(restaurant);
        } catch (IOException e) {
            e.printStackTrace();
        }

        addRName.clear();
        addRPrice.clear();
        addRScore.clear();
        addRZC.clear();
        addRCata1.clear();
        addRCata2.clear();
        addRCata3.clear();

        addRestaurantPane.setVisible(false);
        initialTwoLogin.setVisible(true);
        main.showAddRestaurantSuccessAlert();

    }

    @FXML
    void restaurantLoginMainAction(ActionEvent event) {
        initialTwoLogin.setVisible(false);
        restaurantLogin.setVisible(true);
    }

    @FXML
    void customerLoginMainAction(ActionEvent event) {

        customerLogin.setVisible(true);
        initialTwoLogin.setVisible(false);

    }

    @FXML
    void customerBackAction(ActionEvent event) {
        customerLogin.setVisible(false);
        initialTwoLogin.setVisible(true);
        addRestaurantPane.setVisible(false);
    }

    @FXML
    void restaurantBackAction(ActionEvent event) {
        restaurantLogin.setVisible(false);
        initialTwoLogin.setVisible(true);
        addRestaurantPane.setVisible(false);
    }

    public void setMain(Main main) {
        this.main = main;
    }

}
