package part1;

import java.util.List;

public class SearchFood implements java.io.Serializable{

    public void searchFoodByName(String name, List<Restaurant> rl, List<Food> tFO) {

        for (var restaurant : rl) {

            for (var food : restaurant.getMenu()) {

                if (food.getName().toLowerCase().contains(name.toLowerCase())) {
                    tFO.add(food);
                }

            }
        }
    }

    public void searchFoodByNameInRestaurant(String fName, String rName, List<Restaurant> rl, List<Food> tFO) {

        // SearchRestaurant sRObj = new SearchRestaurant();
        // List<Restaurant> tempRObj = new ArrayList<>();
        // sRObj.searchRestaurantByName(rName, rl, tempRObj);

        // if (!tempRObj.isEmpty()) {

        // for (var food : tempRObj.get(0).getMenu()) {

        // String tFoodString = food.getName().toLowerCase();
        // fName = fName.toLowerCase();

        // if (tFoodString.contains(fName)) {
        // tFO.add(food);
        // }

        // }
        // }

        for (Restaurant restaurant : rl) {

            if (restaurant.getName().equalsIgnoreCase(rName)) {

                for (var food : restaurant.getMenu()) {

                    if (food.getName().toLowerCase().contains(fName.toLowerCase())) {
                        tFO.add(food);
                    }
                }
                break;
            }
        }

    }

    public void searchFoodByCatagory(String name, List<Restaurant> rl, List<Food> tFO) {

        for (var restaurant : rl) {

            for (var food : restaurant.getMenu()) {

                if (food.getCatagory().toLowerCase().contains(name.toLowerCase())) {
                    tFO.add(food);
                }

            }
        }
    }

    public void searchFoodByCatagoryInRestaurant(String cName, String rName, List<Restaurant> rl, List<Food> tFO) {

        for (Restaurant restaurant : rl) {

            if (restaurant.getName().equalsIgnoreCase(rName)) {

                for (var food : restaurant.getMenu()) {

                    if (food.getCatagory().toLowerCase().contains(cName.toLowerCase())) {
                        tFO.add(food);
                    }
                }
                break;
            }
        }

    }

    public void searchFoodByPriceRange(float lPrice, float uPrice, List<Restaurant> rl, List<Food> tFO) {

        for (var restaurant : rl) {

            for (var food : restaurant.getMenu()) {

                if (food.getPrice() >= lPrice && food.getPrice() <= uPrice) {
                    tFO.add(food);
                }

            }
        }
    }

    public void searchFoodByPriceRangeInRestaurant(float lPrice, float uPrice, String rName, List<Restaurant> rl,
            List<Food> tFO) {

        for (Restaurant restaurant : rl) {

            if (restaurant.getName().equalsIgnoreCase(rName)) {

                for (var food : restaurant.getMenu()) {

                    if (food.getPrice() >= lPrice && food.getPrice() <= uPrice) {
                        tFO.add(food);
                    }
                }
                break;
            }
        }

    }

    public void showCostliestFoodInRestaurant(String rName, List<Restaurant> rl, List<Food> tFO) {

        // SearchRestaurant sRObj = new SearchRestaurant();
        // List<Restaurant> tempRObj = new ArrayList<>();
        // sRObj.searchRestaurantByName(rName, rl, tempRObj);

        // float tmp = -1;

        // if (!tempRObj.isEmpty()) {

        //     for (var food : tempRObj.get(0).getMenu()) {

        //         if (food.getPrice() > tmp) {

        //             tmp = food.getPrice();
        //         }

        //     }

        //     for (var food : tempRObj.get(0).getMenu()) {

        //         if (tmp == food.getPrice()) {

        //             tFO.add(food);
        //         }
        //     }
        // }

        float tmp = -1;

        for (Restaurant restaurant : rl) {

            if (restaurant.getName().equalsIgnoreCase(rName)) {

                for (var food : restaurant.getMenu()) {

                    if(food.getPrice()>tmp){
                        tmp=food.getPrice();
                    }
                }

                for (var food : restaurant.getMenu()) {

                    if(food.getPrice()==tmp){
                        
                        tFO.add(food);
                    }
                }
                break;
            }
        }

        

    }

}
