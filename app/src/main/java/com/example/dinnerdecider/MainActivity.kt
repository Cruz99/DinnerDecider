package com.example.dinnerdecider

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.activity_food_list.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.ticket_new_food.view.*
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    val foodList = arrayListOf("Hamburger", "Chineese", "Schabowy", "Kebab")

    var foodListPerisit = arrayListOf<FoodItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        decideButton.setOnClickListener {
            val random = Random()
            val randomFood = random.nextInt(foodList.count())

            selectedFoodText.text = foodList[randomFood]
        }
//        addFoodButton.setOnClickListener {
//            foodListPerisit.add(FoodItem(1, "Kotlet", "Polish Speciality"))
//            foodListPerisit.add(FoodItem(2, "Pizza", "Italian Speciality"))
//            foodListPerisit.add(FoodItem(3, "Goulash", "Hungarian Speciality"))
//
//            var foodListAdapter = MyFoodAdapter(foodListPerisit)
//            foodListView.adapter = foodListAdapter
//        }
//        addFoodButton.setOnClickListener{
//            val newFood = addFoodText.text.toString()
//            foodList.add(newFood)
//            addFoodText.text.clear()
//        }

        listFood.setOnClickListener {
            val foodListIntent = Intent(this, FoodListActivity::class.java)
            foodListIntent.putExtra("List", foodList)
            startActivity(foodListIntent)
        }


    }


}
