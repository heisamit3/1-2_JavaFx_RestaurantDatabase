package sample;

import javafx.application.Platform;
import part1.Food;
import part1.Restaurant;
import util.LoginDTO;
import util.RegisterDTO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ReadThread implements Runnable {
    private final Thread thr;
    private final Main main;

    public ReadThread(Main main) {
        this.main = main;
        this.thr = new Thread(this);
        thr.start();
    }

    public void run() {
        try {
            while (true) {
                Object o = main.getNetworkUtil().read();
                if (o != null) {
                    if (o instanceof LoginDTO) {
                        LoginDTO loginDTO = (LoginDTO) o;

                        System.out.println(loginDTO.getUserName());
                        System.out.println(loginDTO.isStatus());
                        // the following is necessary to update JavaFX UI components from user created
                        // threads
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                if (loginDTO.isStatus() && loginDTO.isCustomer()) {
                                    try {
                                        main.showHomePage(loginDTO.getUserName());
                                        main.setCustomer(true);
                                        main.setCustomerName(loginDTO.getUserName());

                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                } else if (loginDTO.isStatus() && !loginDTO.isCustomer()) {
                                    try {
                                        main.showRestaurantHomePage(loginDTO.getPassword());
                                        main.setCustomer(false);
                                        main.setRestaurantName(loginDTO.getPassword());
                                        main.setRestaurantId(Integer.parseInt(loginDTO.getUserName()));

                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                } else if (!loginDTO.isStatus()) {

                                    main.showAlert();
                                }

                            }
                        });
                    } else if (o instanceof RegisterDTO) {

                        var registerDTO = (RegisterDTO) o;
                        System.out.println(registerDTO.getUserName());
                        System.out.println(registerDTO.isStatus());
                        System.out.println("registerDTO");

                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                if (registerDTO.isStatus()) {
                                    main.showAlertRegisterSuccessfull();
                                } else {
                                    main.showAlertRegisterUnsuccessfull();
                                }
                            }
                        });

                    } else if (o instanceof HashMap<?, ?>) {

                        System.out.println("read thread orderMap");

                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {

                                HashMap<String, List<Pair<Food, Integer>>> newOrderMap = (HashMap<String, List<Pair<Food, Integer>>>) o;

                                // Merge the newOrderMap into main.orderMap
                                for (var entry : newOrderMap.entrySet()) {
                                    String key = entry.getKey();
                                    List<Pair<Food, Integer>> newValue = entry.getValue();

                                    // If main.orderMap already contains the key, append to the existing list
                                    if (main.orderMap.containsKey(key)) {
                                        main.orderMap.get(key).addAll(newValue);
                                    } else {
                                        // Otherwise, create a new list for the key
                                        main.orderMap.put(key, new ArrayList<>(newValue));
                                    }
                                }

                                // Now main.orderMap contains both old and new data

                                for (var order : main.orderMap.entrySet()) {
                                    System.out.println(order.getKey());
                                    System.out.println(order.getValue().get(0).getFirst().getName());
                                    System.out.println(order.getValue().get(0).getSecond());
                                }

                                main.refreshOrderCountInRestaurant();
                                main.refreshOrderListInRestaurant();
                            }
                        });

                    }
                    // else if (o instanceof HashMap<?, ?>) {

                    // HashMap<String, Pair<Food, Integer>> orderMap = new HashMap<>();
                    // orderMap = (HashMap) o;

                    // main.orderMap = orderMap;

                    // for (var order : orderMap.entrySet()) {

                    // System.out.println(order.getKey());
                    // System.out.println(order.getValue().getFirst().getName());
                    // System.out.println(order.getValue().getSecond());
                    // }
                    // }

                    // ---------------// when i wrote in readThread server (first line in run);
                    else if (o instanceof List<?>) {

                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                main.restaurants = new ArrayList<>();

                                main.restaurants = (List<Restaurant>) o;

                                // System.out.println("customers got from add food-------------"+
                                // main.getCustomerName());
                            }
                        });
                    } else if (o instanceof Food) {

                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                Food food = (Food) o;

                                System.out.println("food got from add food-------------" + food);

                                for (var r : main.restaurants) {

                                    if (r.getId() == food.getRestaurantId()) {

                                        r.getMenu().add(food);
                                        break;
                                    }
                                }
                            }
                        });

                    } else if (o instanceof Integer) {

                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                int id = (Integer) o;

                                if(id == 1) {
                                    main.showAlertMyOrderConfirmed();
                                } else {
                                    main.showAlertMyOrderNotConfirmed();
                                }
                            }
                        });

                    }

                }
            }
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("read thread exception" + e.getMessage());
        } finally {
            try {
                main.getNetworkUtil().closeConnection();
            } catch (IOException e) {
                e.printStackTrace();

                System.out.println("ReadThread.java: IOException: " + e.getMessage());
            }
        }
    }
}
