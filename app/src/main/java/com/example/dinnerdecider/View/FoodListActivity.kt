package com.example.dinnerdecider.View

import android.app.AlertDialog
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
import com.example.dinnerdecider.Controllers.AddFoodItem
import com.example.dinnerdecider.Controllers.DbManager
import com.example.dinnerdecider.Entities.FoodItem
import com.example.dinnerdecider.R
import kotlinx.android.synthetic.main.activity_food_list.*
import kotlinx.android.synthetic.main.ticket_new_food.view.*


//Food list
class FoodListActivity : AppCompatActivity() {
    var foodListPerisit = arrayListOf<FoodItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_food_list)
        //Load from database
        LoadQuery("%")

    }

    //method to refresh list on resume
    override fun onResume() {
        super.onResume()
        LoadQuery("%")
    }
    //to load from database
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

                foodListPerisit.add(FoodItem(ID, FoodName, Description,Price,Calories))

            }while(cursor.moveToNext())
        }
        //display data
        var foodListAdapter = MyFoodAdapter(this ,foodListPerisit)
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
                LoadQuery("%"+ query+"%")
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })


            return super.onCreateOptionsMenu(menu)
    }

    //when item selection in menu
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

    //Adpter for myFood page
    inner class MyFoodAdapter : BaseAdapter {

        //list of foods
        var foodListPerisitAdapter = ArrayList<FoodItem>()
        var context: Context?=null
        //constructor accepting ArrayList and context
        constructor(context: Context, foodListPerisit: ArrayList<FoodItem>) : super() {
            println("Running FoodListActivity - MyFoodAdapter")
            this.foodListPerisitAdapter = foodListPerisit
            this.context=context
        }

        //getting view - function where we update buttons - delete or edit
        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            println("Running MyFoodAdapter - getView")
            var myView = layoutInflater.inflate(R.layout.ticket_new_food, null)
            var item = foodListPerisitAdapter[position]


            println( "Running MyFoodAdapter - adding nodes")
            //adding nodes to view
//            var foodNameTemp:String =item.foodName.toString()
            myView.foodItemName.text = item.foodName

            //check the lengh of desc
            var desTextTemp: String = item.foodDescription.toString()
            if(desTextTemp.length>33) {
                myView.foodItemDescription.text = item.foodDescription.toString().substring(0, 35) + "..."
            }else{
                myView.foodItemDescription.text = item.foodDescription+"..."
            }
            //delete button
            println( "Running MyFoodAdapter - delete button ")
            myView.foodItemButtonDelete.setOnClickListener(View.OnClickListener {
                var dbManager = DbManager(this.context!!)
                var selectionArgs= arrayOf(item.foodId.toString()) //selection argument
                dbManager.Delete("ID=?",selectionArgs)
                LoadQuery("%")
            })
            //edit button
            myView.foodItemButtonEdit.setOnClickListener(View.OnClickListener {
                GoToUpdate(item)

            })

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
    //to make intent work
    fun GoToUpdate(item: FoodItem){
        var intent  = Intent(this, AddFoodItem::class.java) // create intent
        intent.putExtra("ID", item.foodId)
        intent.putExtra("foodName", item.foodName)
        intent.putExtra("foodDesc", item.foodDescription)
        println("Adding food extra items")
        intent.putExtra("foodPrice", item.price)
        intent.putExtra("foodCalories", item.calories)
        startActivity(intent)  //start activity
    }

}
