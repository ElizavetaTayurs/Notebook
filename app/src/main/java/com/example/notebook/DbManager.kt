package com.example.notebook

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.example.notebook.DBHelper

class DbManager(val context: Context) {
    val DBHelper = DBHelper(context)
    var db: SQLiteDatabase? = null

    fun openDb() {
        db =  DBHelper.writableDatabase
    }

  /*  fun insertToDb( name: String, surname: String, birthday: String, phone: String) {
        val values = ContentValues().apply {
            put(MyDbNameClass.COLUMN_NAME_NAME, name)
            put(MyDbNameClass.COLUMN_NAME_SURNAME, surname)
            put(MyDbNameClass.COLUMN_NAME_BIRTHDATE, birthday)
            put(MyDbNameClass.COLUMN_NAME_PHONE, phone)
        }
        db?.insert(MyDbNameClass.TABLE_NAME, null, values)

    }*/

  //  fun getAllPerson(std: MyDbNameClass): Long {

   // }

   /* fun readDbData() : ArrayList<String> {
        val dataList = ArrayList<String>()
        val cursor = db?.query(MyDbNameClass.TABLE_NAME, null, null,
            null, null, null, null)


            while (cursor?.moveToNext()!!) {
                val dataText = cursor.getString(cursor.getColumnIndexOrThrow(MyDbNameClass.COLUMN_NAME_NAME))
                dataList.add(dataText.toString())
            }
        return dataList
    } */

}