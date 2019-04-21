package com.example.dinnerdecider.Controllers

import android.content.ContentValues
import android.content.Intent
import android.graphics.Bitmap
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Toast
import com.example.dinnerdecider.R
import kotlinx.android.synthetic.main.activity_add_food_item.*
import java.lang.Exception

class AddFoodItem : AppCompatActivity() {
    val IMAGE_CAPTURE_CODE: Int = 1001
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

        //camera
        var t = 123
        buttonTakePic.setOnClickListener{
            var i =Intent(MediaStore.ACTION_IMAGE_CAPTURE)

            i.putExtra(MediaStore.EXTRA_OUTPUT, IMAGE_CAPTURE_CODE)
            startActivityForResult(i, IMAGE_CAPTURE_CODE)
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        println("Running  - OnActivityResult")
        println("Running - testing for null")
        if(requestCode!=null){
            var bpm = data!!.extras.get("data") as Bitmap
            imageCamera.setImageBitmap(bpm)
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
