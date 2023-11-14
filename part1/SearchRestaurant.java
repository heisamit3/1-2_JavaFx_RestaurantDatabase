package part1;


import java.util.List;



public class SearchRestaurant implements java.io.Serializable  {

    public void searchRestaurantByName(String name,List<Restaurant> rl,List<Restaurant> tRL) {

        for (Restaurant restaurant : rl) {
        

            if(restaurant.getName().toLowerCase().contains(name.toLowerCase())){

                tRL.add(restaurant);
            }
        }
    }

    public void searchRestaurantByScore(double lScore,double uScore,List<Restaurant> rl,List<Restaurant> tRL) {

        for (Restaurant restaurant : rl) {
            

            if(restaurant.getScore()>=lScore && restaurant.getScore()<=uScore){

                tRL.add(restaurant);
            }
        }

    }

    public void searchRestaurantByCatagory(String catagory,List<Restaurant> rl,List<Restaurant> tRL) {



        for (Restaurant restaurant : rl) {

            for (var rCatagory : restaurant.getCatagory()) {

                if(rCatagory.toLowerCase().contains(catagory.toLowerCase())){

                    tRL.add(restaurant);
                    break;
                }
                
            }
        }

    }

    public void searchRestaurantByPrice(String price,List<Restaurant> rl,List<Restaurant> tRL) {

        for (Restaurant restaurant : rl) {
            

            if(restaurant.getPrice().equals(price)){

                tRL.add(restaurant);
            }
        }

    }

    public void searchRestaurantByZipCode(int zipCode,List<Restaurant> rl,List<Restaurant> tRL) {

        for (Restaurant restaurant : rl) {
            
            if(restaurant.getZipCode()==zipCode){

                tRL.add(restaurant);
            }
        }

    }

    public void catagoryWiseRestaurantNames(String catagory,List<Restaurant> rl,List<Restaurant> tRL){

        for(var restaurant : rl){

            if(restaurant.getCatagory().contains(catagory)){

                tRL.add(restaurant);
            }
        }
        
    }
}
