package com.example.dinnerdecider.View

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.dinnerdecider.Controllers.DbManager
import com.example.dinnerdecider.Entities.FoodItem
import com.example.dinnerdecider.R
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

//    val foodList = arrayListOf("Hamburger", "Chineese", "Schabowy", "Kebab")

    var foodListPerisit = arrayListOf<FoodItem>()

    //calling on create method
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //loading up array of foods
        LoadQuery("%")

        //settcing up decide button
        decideButton.setOnClickListener {
            println(foodListPerisit.toString())
            if(foodListPerisit.size>0) {
                val random = Random()
                val randomFood = random.nextInt(foodListPerisit.count())
                selectedFoodText.text = foodListPerisit[randomFood].foodName.toString()
            }else{
                selectedFoodText.text = "No food in your list !"
            }
        }

        listFood.setOnClickListener {
            val foodListIntent = Intent(this, FoodListActivity::class.java)
//            foodListIntent.putExtra("List", foodListPerisit)
            startActivity(foodListIntent)
        }



    }
    override fun onResume() {
        super.onResume()
        LoadQuery("%")
    }

    //Loading data
    fun LoadQuery(title:String){
        println("Running LoadQuery - FoodListActivity")

        println("Creating DB MANAGER")
        var dbManager= DbManager(this)

        println("val create projection")
        val projection = arrayOf("ID", "FoodName", "Description","Price","Calories")
        //select everything

        var selectionArgs= arrayOf(title)
        println("Running create cursor")
        val cursor=dbManager.Query(projection,"FoodName LIKE ?",selectionArgs, "FoodName")
        // need all columns
        foodListPerisit.clear()
        //cleaning array
        if(cursor.moveToFirst()){
            do{
                val ID=cursor.getInt(cursor.getColumnIndex("ID"))
                val FoodName=cursor.getString(cursor.getColumnIndex("FoodName"))
                val Description=cursor.getString(cursor.getColumnIndex("Description"))
                val Price=cursor.getString(cursor.getColumnIndex("Price"))
                val Calories=cursor.getString(cursor.getColumnIndex("Calories"))

                foodListPerisit.add(FoodItem(ID, FoodName, Description, Price, Calories))

            }while(cursor.moveToNext())
        }
        //display data
//        var foodListAdapter = MyFoodAdapter(this ,foodListPerisit)
//        foodListView.adapter = foodListAdapter
    }



}
