package com.example.dinnerdecider

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.database.sqlite.SQLiteQueryBuilder
import android.media.projection.MediaProjection
import android.widget.Toast

val dbName ="DinnerDeciderBla"
val dbTable = "Foods"
val colID ="ID"
val colName = "FoodName"
val colDescription = "Description"
val dbVersion =1

val sqlCreateTable="CREATE TABLE IF NOT EXISTS "+dbTable+ " ("+
        colID + " INTEGER PRIMARY KEY , "+
        colName + " TEXT, "+
        colDescription+ " TEXT);"

var sqlDB:SQLiteDatabase?=null

class DbManager{

    constructor(context: Context){
        println("Runnign DBManager constructor")
        var db=DatabaseHelperFoods(context)
        sqlDB=db.writableDatabase
    }


    inner class DatabaseHelperFoods:SQLiteOpenHelper{
        var context:Context?=null
        constructor(context: Context):super(context, dbName,null, dbVersion){
            println("Running inner DB Manager constructor")
            this.context=context
        }
        override fun onCreate(db: SQLiteDatabase?) {
            println("Running onCreate Inner class")
            if (db != null) {
                println("Running null check")
                db.execSQL(sqlCreateTable)
            }
            Toast.makeText(this.context, " database is created", Toast.LENGTH_LONG).show()
        }

        override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
            db!!.execSQL("Drop table if exists "+ dbTable)
            onCreate(db)
        }

    }

    fun Insert(value:ContentValues):Long{
        println("Running INsert in DB Manager")
//        val sqlDB = this.writableDatabase
        val ID =sqlDB!!.insert(dbTable, "", value)
        return ID
    }

    //delete function
    fun Delete(selection:String, selectionArgs: Array<String>):Int{
        val count= sqlDB!!.delete(dbTable,selection, selectionArgs)
        return count
    }

    fun Query (projection:Array<String>, selection:String,   selectionArgs:Array<String>,sortOrNot:String): Cursor {
        val qb = SQLiteQueryBuilder()
        qb.tables= dbTable
        val cursor=qb.query(sqlDB, projection,selection,selectionArgs,null,null,sortOrNot)
        return cursor
    }

    fun Update(values:ContentValues, selection: String, selectionArgs: Array<String>):Int{
        val count = sqlDB!!.update(dbTable,values,selection,selectionArgs)
        return count
    }

}