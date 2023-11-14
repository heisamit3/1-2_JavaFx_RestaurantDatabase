package part1;

import java.util.ArrayList;
import java.util.List;

public class Restaurant implements java.io.Serializable {

    private int id;
    private String name;
    private double score;
    private String price;
    private int zipCode;
    private List<String> catagory;
    private List<Food> menu;

    public Restaurant(int id, String name, double score, String price, int zipCode, List<String> catagory) {
        this.id = id;
        this.name = name;
        this.score = score;
        this.price = price;
        this.zipCode = zipCode;
        this.catagory = new ArrayList<>();
        this.catagory.addAll(catagory);
        this.menu = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getScore() {
        return score;
    }

    public int getZipCode() {
        return zipCode;
    }

    public List<Food> getMenu() {
        return menu;
    }

    public List<String> getCatagory() {
        return catagory;
    }

    public void addFoodToMenu(Food food) {
        menu.add(food);
    }



    public void display() {
        System.out.print("Name     : " + name + "\n" + "Score    : " + score + "\n" + "Price    : " + price + "\n"
                + "Zip Code : " + zipCode + "\n");

        System.out.print("Catagory : ");

        for (int i = 0; i < catagory.size(); i++) {
            if (i > 0) {
                System.out.print(",");
            }
            System.out.print(catagory.get(i));
        }

        System.out.println();
        System.out.println();
    }

    @Override
    public String toString() {
        String categories = "";
        for (int i = 0; i < catagory.size(); i++) {
            if (i > 0) {
                categories += ",";
            }
            categories += catagory.get(i);
        }

        return id + "," + name + "," + score + "," + price + "," + zipCode + "," + categories;
    }
}
