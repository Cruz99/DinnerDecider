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


        //default value for id = 0
        try {
            var bundle: Bundle = intent.extras
            id = bundle.getInt("ID", 0)
            if (id != 0) { //check
                newFoodName.setText(bundle.getString("foodName").toString())
                addFoodDescription.setText(bundle.getString("foodDesc").toString())
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


        ///checking if update or add
        if (id ==0) { //for adding new
            val ID = dbManager.Insert(values)
            if (ID > 0) {
                Toast.makeText(this, " food is added ", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, " cannot add food ", Toast.LENGTH_LONG).show()
            }
        } else {  /// for updating
            var selectionArgs = arrayOf(id.toString())
            val ID = dbManager.Update(values, "ID=?", selectionArgs)
            if (ID > 0) {
                Toast.makeText(this, " food is updated ", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, " cannot update food ", Toast.LENGTH_LONG).show()
            }
        }
    }

}
