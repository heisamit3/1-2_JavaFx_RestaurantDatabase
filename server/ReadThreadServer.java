package server;

import util.LoginDTO;
import util.NetworkUtil;
import util.RegisterDTO;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import sample.Pair;
import part1.Food;
import part1.Restaurant;


public class ReadThreadServer implements Runnable {
    private final Thread thr;
    private final NetworkUtil networkUtil;
    public HashMap<String, String> userMap;
    public HashMap<Integer, String> restaurantsMap;
    public List<Restaurant> restaurants;
    // private HashMap<String, NetworkUtil> restaurantMain, customerMain;
    public HashMap<String, List<Pair<Food, Integer>>> orderMap;
    private final Object orderMapLock = new Object();

    public ReadThreadServer(List<Restaurant> restaurants, HashMap<String, String> map, HashMap<Integer, String> rMap,
            NetworkUtil networkUtil) {
        this.userMap = map;
        this.restaurantsMap = rMap;
        this.restaurants = restaurants;
        this.networkUtil = networkUtil;
        this.thr = new Thread(this);
        thr.start();

        // this.restaurantMain = new HashMap<String, NetworkUtil>();
        // this.customerMain = new HashMap<String, NetworkUtil>();
    }

    public void run() {
        try {

            networkUtil.write(restaurants);

            while (true) {
                Object o = networkUtil.read();

                if (o != null) {
                    if (o instanceof LoginDTO) {
                        LoginDTO loginDTO = (LoginDTO) o;

                        if (loginDTO.isCustomer()) {
                            String password = userMap.get(loginDTO.getUserName());
                            loginDTO.setStatus(loginDTO.getPassword().equals(password));
                            // networkUtil.write(loginDTO);

                            if (loginDTO.isStatus()) {

                                if(Server.customerMain.get(loginDTO.getUserName()) != null){

                                    Server.customerMain.remove(loginDTO.getUserName());
                                    System.out.println("existing user networkutil removed");
                                }

                                Server.customerMain.put(loginDTO.getUserName(), networkUtil);
                                Server.customerMain.get(loginDTO.getUserName()).write(loginDTO);
                            }
                            else{
                                    
                                    networkUtil.write(loginDTO);
                            }

                        } else {

                            // String password =
                            // restaurantsMap.get(Integer.parseInt(loginDTO.getUserName()));
                            // loginDTO.setStatus(loginDTO.getPassword().equals(password));
                            // // networkUtil.write(loginDTO);

                            String restaurantID = loginDTO.getUserName(); // Get the restaurant name

                            String password = restaurantsMap.get(Integer.parseInt(loginDTO.getUserName()));
                            loginDTO.setStatus(loginDTO.getPassword().equals(password));

                            if (loginDTO.isStatus()) {

                                if(Server.restaurantMain.get(Integer.parseInt(restaurantID)) != null){

                                    Server.restaurantMain.remove(Integer.parseInt(restaurantID));
                                    System.out.println("existing restaurant networkutil removed");
                                }

                                Server.restaurantMain.put(Integer.parseInt(restaurantID), networkUtil);
                                System.out.println(restaurantID + "just logged in");

                                Server.restaurantMain.get(Integer.parseInt(restaurantID)).write(loginDTO); 
                            }
                            else{

                                networkUtil.write(loginDTO);
                            }

                        }

                    } else if (o instanceof RegisterDTO) {

                        var registerDTO = (RegisterDTO) o;

                        if (registerDTO.getPassword().equals("") || userMap.get(registerDTO.getUserName()) != null
                                || registerDTO.getUserName().equalsIgnoreCase("")) {
                            registerDTO.setStatus(false);
                            networkUtil.write(registerDTO);

                        } else {

                            registerDTO.setStatus(true);
                            this.userMap.put(registerDTO.getUserName(), registerDTO.getPassword());
                            networkUtil.write(registerDTO);
                        }

                    }
                    // else if (o instanceof HashMap<?, ?>) {

                    // HashMap<String, List<Pair<Food, Integer>>> receivedOrderMap =
                    // (HashMap<String, List<Pair<Food, Integer>>>) o;

                    // // Synchronize access to orderMap
                    // synchronized (orderMapLock) {
                    // // Merge the received orderMap into the existing orderMap
                    // orderMap = receivedOrderMap;
                    // }

                    // // Extract the restaurant name from the orderMap
                    // Entry<String, List<Pair<Food, Integer>>> firstEntry =
                    // receivedOrderMap.entrySet().iterator().next();
                    // String key = firstEntry.getKey();
                    // Pair<Food, Integer> pair = (Pair<Food, Integer>) firstEntry.getValue();
                    // Food food = pair.getKey();
                    // String restaurantName = food.getRestaurantName();

                    // // Send the orderMap to the restaurant
                    // NetworkUtil restaurantNetworkUtil = restaurantMain.get(restaurantName);
                    // if (restaurantNetworkUtil != null) {
                    // // Synchronize the write operation as well
                    // synchronized (restaurantNetworkUtil) {
                    // restaurantNetworkUtil.write(orderMap);
                    // }
                    // }
                    // }
                    else if (o instanceof HashMap<?, ?>) {
                        // HashMap<?, ?> rawMap = (HashMap<?, ?>) o;

                        // Check if the HashMap contains Pair<Food, Integer> values
                        // if (!rawMap.isEmpty() && rawMap.values().iterator().next() instanceof Pair<?,
                        // ?>) {

                        HashMap<String, List<Pair<Food, Integer>>> orderMap = (HashMap<String, List<Pair<Food, Integer>>>) o;

                        // Extract the restaurant name from the orderMap
                        // Entry<String, List<Pair<Food, Integer>>> firstEntry =
                        // orderMap.entrySet().iterator().next();

                        // String key = firstEntry.getKey();
                        // Pair<Food, Integer> pair = firstEntry.getValue();
                        // Food food = pair.getKey();
                        // String restaurantName = food.getRestaurantName();

                        // System.out.println(restaurantName);

                        // Send the orderMap to the restaurant

                        // Extract the restaurant name from the orderMap

                        Entry<String, List<Pair<Food, Integer>>> firstEntry = orderMap.entrySet().iterator().next();
                        String key = firstEntry.getKey();
                        List<Pair<Food, Integer>> foodList = (List<Pair<Food, Integer>>) firstEntry.getValue();
                        Food food = foodList.get(0).getFirst();

                        System.out.println(food.getRestaurantId() + " to order");

                        Server.restaurantMain.get(food.getRestaurantId()).write(orderMap);
                        orderMap.clear();

                    } else if (o instanceof Restaurant) {

                        Restaurant restaurant = (Restaurant) o;

                        System.out.println(restaurant.getName() + " just added");

                        restaurant.setId(Server.restaurants.size() + 1);

                        Server.restaurants.add(restaurant);

                        Server.restaurantsMap.put(restaurant.getId(), restaurant.getName());

                        for (Restaurant r : Server.restaurants) {

                            System.out.println(r.getName() + " " + r.getId());
                        }
                    } else if (o instanceof Food) {

                        Food food = (Food) o;

                        for (Restaurant r : Server.restaurants) {

                            if (r.getId() == food.getRestaurantId()) {

                                r.getMenu().add(food);

                                System.out.println(food + " just added in server");
                                break;
                            }
                        }
                        // // iterate thourh customerMain

                        for (Map.Entry<String, NetworkUtil> entry : Server.customerMain.entrySet()) {
                            String key = entry.getKey();
                            NetworkUtil value = entry.getValue();

                            synchronized (value) {
                                value.write(food);
                            }
                            System.out.println("Sent to all customers");
                        }

                    }
                    // else if(o instanceof List<?>){

                    // List<Restaurant> restaurants = (List<Restaurant>) o;

                    // Server.restaurants = restaurants;
                    

                    // for (Map.Entry<String, NetworkUtil> entry : Server.customerMain.entrySet()) {
                    // String key = entry.getKey();
                    // NetworkUtil value = entry.getValue();

                    // value.write(Server.restaurants);
                    // System.out.println("Sent to all customers" + key);
                    // }
                    // }
                    else if(o instanceof Pair<?,?>){

                        Pair<String,Integer> pair = (Pair<String,Integer>) o;

                        String customerName = pair.getFirst();
                        

                        NetworkUtil customerNetworkUtil = Server.customerMain.get(customerName);

                        if(customerNetworkUtil != null){
                            synchronized(customerNetworkUtil){
                                customerNetworkUtil.write(pair.getSecond());
                            }
                        }

                    }

                }
            }
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("---------An error occurred: " + e.getMessage() + "---------");

        } finally {
            try {
                networkUtil.closeConnection();
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("---------An error occurred: " + e.getMessage() + "---------");
            }
        }
    }
}
