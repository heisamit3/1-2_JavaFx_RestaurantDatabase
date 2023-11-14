package server;


import util.NetworkUtil;
import file.*;
import part1.Restaurant;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Server {

    private ServerSocket serverSocket;
    public HashMap<String, String> userMap;
    public static HashMap<Integer, String> restaurantsMap;
    public static List<Restaurant> restaurants;
    public static HashMap<String, NetworkUtil> customerMain;
    public static HashMap<Integer, NetworkUtil> restaurantMain;

    public void setUser(String userName, String password) {

        userMap.put(userName, password);
    }

    Server() {

        FileOperations fOp = new FileOperations();
        userMap = new HashMap<>();
        restaurantsMap = new HashMap<>();
        restaurants = new ArrayList<>();
        restaurantMain = new HashMap<Integer, NetworkUtil>();
        customerMain = new HashMap<String, NetworkUtil>();


        Thread consoleInputThread = new Thread(() -> {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Press 'q' and Enter to shut down the server...");

            String input = scanner.nextLine();

            if ("q".equalsIgnoreCase(input)) {
                    
                    System.out.println("Shutting down the server...");

                    try {
                        fOp.writeRestaurants(restaurants);
                        fOp.writeCustomer(userMap);
                        fOp.writeMenuFile(restaurants);
                        
                    } catch (Exception e) {
                        System.out.println("---------An error occurred: " + e.getMessage() + "---------");
                        e.printStackTrace();
                        System.exit(0);
                    }
                    System.exit(0);
                }

            scanner.close();
        });
        consoleInputThread.start();

        try {

            restaurants = fOp.readRestaurantFile();
            fOp.readMenuFile(restaurants);
            userMap = fOp.readCustomerFile();

            for(Restaurant r : restaurants) {
                restaurantsMap.put(r.getId(), r.getName());

              
            }

            

        } catch (Exception e) {

            System.out.println("---------An error occurred: " + e.getMessage() + "---------");
            e.printStackTrace();
            System.exit(0);
        }

        try {
            serverSocket = new ServerSocket(33333);
            while (true) {
                Socket clientSocket = serverSocket.accept();
                serve(clientSocket);
            }
        } catch (Exception e) {
            System.out.println("Server starts:" + e);
        }
    }

    public void serve(Socket clientSocket) throws IOException {
        NetworkUtil networkUtil = new NetworkUtil(clientSocket);
        new ReadThreadServer(restaurants,userMap, restaurantsMap, networkUtil);
    }

    public static void main(String[] args) {
        new Server();
    }
}
