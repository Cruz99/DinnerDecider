package com.example.dinnerdecider

import android.widget.BaseAdapter

class FoodItem {

    var foodId: Int? = null
    var foodName: String? = null
    var foodDescription: String? = null

    constructor(foodId: Int, FoodName: String, FoodDescription: String) {
        println("Creating food object")
        this.foodId = foodId
        this.foodName = FoodName
        this.foodDescription = FoodDescription
    }


}

