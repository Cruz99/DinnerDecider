package com.example.dinnerdecider

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    val foodList = arrayListOf("Hamburger", "Chineese", "Schabowy", "Kebab")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        decideButton.setOnClickListener {
            val random = Random()
            val randomFood = random.nextInt(foodList.count())

            selectedFoodText.text = foodList[randomFood]
        }
        addFoodButton.setOnClickListener{
            val newFood = addFoodText.text.toString()
            foodList.add(newFood)
            addFoodText.text.clear()
        }



        listFood.setOnClickListener{
            val foodListIntent = Intent(this, FoodListActivity::class.java)
            foodListIntent.putExtra("List", foodList)
            startActivity(foodListIntent)
        }


    }
}
