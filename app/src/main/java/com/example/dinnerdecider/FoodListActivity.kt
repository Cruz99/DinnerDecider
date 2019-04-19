package com.example.dinnerdecider

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.*
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

    // initialize menu items
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)

        //working on search button
        val sv = menu!!.findItem(R.id.app_bar_search).actionView as SearchView
        val sm = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        sv.setSearchableInfo(sm.getSearchableInfo(componentName))
        sv.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                Toast.makeText(applicationContext, query, Toast.LENGTH_LONG).show()
                //TODO: search database
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })


            return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        if (item != null) {
            when (item.itemId) {
                R.id.menuAddFood -> {
                    //go to add page
                    var intent = Intent(this, AddFoodItem::class.java)
                    startActivity(intent)
                }
            }
        }

        return super.onOptionsItemSelected(item)
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
