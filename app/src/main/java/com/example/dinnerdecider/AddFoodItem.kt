package com.example.dinnerdecider

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class AddFoodItem : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_food_item)
    }

    fun addFood(view: View){
        finish()
    }

}
