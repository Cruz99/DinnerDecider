package com.example.dinnerdecider.Entities

import android.widget.BaseAdapter


//entity/food class
class FoodItem {

    var foodId: Int? = null
    var foodName: String? = null
    var foodDescription: String? = null
    var price: String? = null
    var calories: String?=null


    constructor(foodId: Int, FoodName: String, FoodDescription: String, Price:String, Calories:String) {
        println("Creating food object")
        this.foodId = foodId
        this.foodName = FoodName
        this.foodDescription = FoodDescription
        this.price = Price
        this.calories = Calories
    }


}

