package com.example.dinnerdecider

import android.content.ContentValues
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_add_food_item.*
import java.lang.Exception

class AddFoodItem : AppCompatActivity() {
    var id = 0 //to check update statement id value
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_food_item)

        var bundle: Bundle = intent.extras

        //default value for id = 0
        id = bundle.getInt("ID", 0)
        try {
            if (id != 0) { //check
                addFoodDescription.setText(bundle.getString("foodName").toString())
                newFoodName.setText(bundle.getString("foodDesc").toString())
            }
        }catch (ex:Exception){

        }


    }

    fun addFood(view: View) {
        println("Running add food")
        var dbManager = DbManager(this)

        var values = ContentValues()
        values.put("Description", addFoodDescription.text.toString())
        values.put("FoodName", newFoodName.text.toString())

        Toast.makeText(this, "test", Toast.LENGTH_LONG).show()

        if (id == 0) {
            val ID = dbManager.Insert(values)
            if (ID > 0) {
                Toast.makeText(this, " food is added ", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, " cannot add food ", Toast.LENGTH_LONG).show()
            }
        } else {
            var selectionArgs = arrayOf(id.toString())
            val ID = dbManager.Update(values, "ID=?", selectionArgs)
            if (ID > 0) {
                Toast.makeText(this, " food is added ", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, " cannot add food ", Toast.LENGTH_LONG).show()
            }
        }
    }

}
