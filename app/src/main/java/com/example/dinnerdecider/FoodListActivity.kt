package com.example.dinnerdecider

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.BaseAdapter
import android.widget.ListView
import kotlinx.android.synthetic.main.activity_food_list.*
import kotlinx.android.synthetic.main.ticket_new_food.view.*

class FoodListActivity : AppCompatActivity() {
    var foodListPerisit = arrayListOf<FoodItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_food_list)

        foodListPerisit.add(FoodItem(1, "Kotlet", "Polish Speciality"))
        foodListPerisit.add(FoodItem(2, "Pizza", "Italian Speciality"))
        foodListPerisit.add(FoodItem(3, "Goulash", "Hungarian Speciality"))

        var foodListAdapter = MyFoodAdapter(foodListPerisit)
        foodListView.adapter = foodListAdapter


    }

    inner class MyFoodAdapter : BaseAdapter {

        var foodListPerisitAdapter = ArrayList<FoodItem>()

        constructor(foodListPerisit: ArrayList<FoodItem>) : super() {
            this.foodListPerisitAdapter = foodListPerisit
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            var myView = layoutInflater.inflate(R.layout.ticket_new_food, null)
            var item = foodListPerisitAdapter[position]

            myView.foodItemName.text = item.foodName
            myView.foodItemDescription.text = item.foodDescription

            return myView

        }

        override fun getItem(position: Int): Any {
            return foodListPerisitAdapter[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getCount(): Int {
            return foodListPerisitAdapter.size
        }
    }

}
