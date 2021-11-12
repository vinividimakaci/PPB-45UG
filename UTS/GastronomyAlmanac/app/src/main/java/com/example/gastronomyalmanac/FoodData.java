package com.example.gastronomyalmanac;

import java.util.ArrayList;

public class FoodData {
    private static String [] foodName = {
            "Fish & Chips",
            "Animal Style Fries",
            "Marinara Pizza",
            "Deluxe Pizza",
            "Garlic Bread",
            "California Roll",
            "Tteokbokki",
            "Fishcake Soup"
    };

    private static String [] foodDetail = {
            "Rp33.000,00",
            "Rp28.000,00",
            "Rp45.000,00",
            "Rp60.000,00",
            "Rp17.000,00",
            "Rp21.000,00",
            "Rp21.000,00",
            "Rp15.000,00"
    };

    private static int [] foodImage = {
            R.drawable.fish_chips,
            R.drawable.animal_style,
            R.drawable.marinara_pizza,
            R.drawable.deluxe_pizza,
            R.drawable.garlic_bread,
            R.drawable.california_roll,
            R.drawable.tteokbokki,
            R.drawable.fishcake_soup
    };

    static ArrayList<Food> getListData() {
        ArrayList<Food> list = new ArrayList<>();
        for (int position = 0; position <foodName.length; position++) {
            Food food = new Food();
            food.setName(foodName[position]);
            food.setDetail(foodDetail[position]);
            food.setImage(foodImage[position]);
            list.add(food);
        }

        return list;
    }
}
