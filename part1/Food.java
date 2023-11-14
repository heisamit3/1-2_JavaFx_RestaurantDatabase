package part1;

public class Food implements java.io.Serializable{
    
    private int restaurantId;
    private String restaurantName;
    private String catagory;
    private String name;
    private float price;

    public Food(int restaurantId,String restaurantName,String catagory,String name,float price){

        this.restaurantId=restaurantId;
        this.catagory=catagory;
        this.name=name;
        this.price=price;
    }

    public int getRestaurantId(){
        return restaurantId;
    }

    public String getCatagory(){
        return catagory;
    }

    public String getName(){

        return name;
    }

    public String getRestaurantName(){
        return restaurantName;
    }

    public float getPrice(){
        return price;
    }

    void  display(){

        System.out.println("Name          : "+ name+ "\n"+"Category      : "+ catagory 
        +"\n"+"Price         : " + price +"\n"+"Restaurant Name : "+ restaurantName +"\n");

    }

    @Override
    public String toString(){

        return restaurantId +","+ catagory +","+ name +","+ price;
    }

}
