package file;
import part1.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Iterator;

public class FileOperations {

    private static final String RESTAURANT_FILE = "src\\file\\restaurant.txt";
    private static final String MENU_FILE = "src\\file\\menu.txt";
    private static final String CUSTOMER_FILE = "src\\file\\customer.txt";

    public HashMap<String, String> readCustomerFile() throws Exception {

        HashMap<String, String> customerMap = new HashMap<>();

        BufferedReader br = new BufferedReader(new FileReader(CUSTOMER_FILE));

        while (true) {

            String line = br.readLine();
            if (line == null) {
                break;
            }
            String[] cDetails = line.split(",", 0);

            customerMap.put(cDetails[0], cDetails[1]);
        }

        br.close();

        return customerMap;
    }

    public void writeCustomer(HashMap<String, String> customerMap) throws Exception {

        BufferedWriter bw = new BufferedWriter(new FileWriter(CUSTOMER_FILE));

        Iterator<Map.Entry<String,String>> iterator = customerMap.entrySet().iterator();

        while (iterator.hasNext()) {
            Map.Entry<String, String> entry = iterator.next();
            String key = entry.getKey();
            String value = entry.getValue();

            bw.write(key);
            bw.write(",");
            bw.write(value);
            bw.write(System.lineSeparator());
        }

        bw.close();
    }

    public List<Restaurant> readRestaurantFile() throws Exception {

        List<Restaurant> rOb = new ArrayList<>();

        BufferedReader br = new BufferedReader(new FileReader(RESTAURANT_FILE));

        while (true) {

            String line = br.readLine();
            if (line == null)
                break;
            String[] rDetails = line.split(",", 0);

            String id = rDetails[0];
            String name = rDetails[1];
            String score = rDetails[2];
            String price = rDetails[3];
            String zipCode = rDetails[4];
            List<String> catagory = new ArrayList<>();

            for (int i = 5; i < rDetails.length; i++) {
                catagory.add(rDetails[i]);
            }

            Restaurant ob = new Restaurant(Integer.parseInt(id), name, Double.parseDouble(score), price,
                    Integer.parseInt(zipCode), catagory);

            rOb.add(ob);
        }
        br.close();

        return rOb;
    }

    public void readMenuFile(List<Restaurant> rl) throws Exception {

        // List<Food> menu = new ArrayList<>();

        BufferedReader br = new BufferedReader(new FileReader(MENU_FILE));

        while (true) {

            String line = br.readLine();
            if (line == null)
                break;
            String[] fDetails = line.split(",", 0);

            String rId = fDetails[0];
            String category = fDetails[1];
            String name = fDetails[2];
            String price = fDetails[3];
            String rName = "";

            // for(var r : rl) {

            //     if(r.getId() == Integer.parseInt(rId)) {

            //         rName = r.getName();
            //         System.out.println(rName);
            //         break;
            //     }
            // }

            Food fOb = new Food(Integer.parseInt(rId) , rName, category, name, Float.parseFloat(price));

            for (int i = 0; i < rl.size(); i++) {

                var item = rl.get(i);

                if (item.getId() == Integer.parseInt(rId)) {

                    item.addFoodToMenu(fOb);

                    break;
                }
            }

            // menu.add(fOb);

        }
        br.close();

        return;
    }

    public void writeRestaurants(List<Restaurant> rl) throws Exception {

        System.out.println("Writing restaurants");

        BufferedWriter bw = new BufferedWriter(new FileWriter(RESTAURANT_FILE));

        for (var restaurant : rl) {

            bw.write(restaurant.toString());
            bw.write(System.lineSeparator());
        }

        bw.close();
    }

    public void writeMenuFile(List<Restaurant> rl) throws Exception {

        BufferedWriter bw = new BufferedWriter(new FileWriter(MENU_FILE));

        for (var restaurant : rl) {

            for (var food : restaurant.getMenu()) {

                bw.write(food.toString());
                bw.write(System.lineSeparator());
            }
        }

        bw.close();
    }
}