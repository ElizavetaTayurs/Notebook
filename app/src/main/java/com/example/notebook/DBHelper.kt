package com.example.notebook

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context): SQLiteOpenHelper(context, DATABASE_NAME,
    null, DATABASE_VERSION
) {

    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "person.db"
        private const val TBL_PERSON = "tbl_person"
        private const val ID = "id"
        private const val NAME = "name"
        private const val SURNAME = "surname"
        private const val BIRTHDAY = "birthday"
        private const val PHONE = "phone"
    }


    override fun onCreate(db: SQLiteDatabase?) {
      //  db?.execSQL(MyDbNameClass.CREATE_TABLE)
        val createTblPerson = ("CREATE TABLE ${TBL_PERSON} ("
                + ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + NAME + " TEXT,"
                + SURNAME + " TEXT," + BIRTHDAY + " TEXT," + PHONE + " TEXT" + ")")
        db?.execSQL(createTblPerson)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        //db?.execSQL(MyDbNameClass.SQL_DELETE_TABLE)
        db!!.execSQL("DROP TABLE IF EXISTS $TBL_PERSON")
        onCreate(db)
    }

    fun insertPerson(std: PersonModel): Long {
        val db = this.writableDatabase

        val contentValues = ContentValues()
        contentValues.put(ID, std.id)
        contentValues.put(NAME, std.name)
        contentValues.put(SURNAME, std.surname)
        contentValues.put(BIRTHDAY, std.birthday)
        contentValues.put(PHONE, std.phone)

        val success = db.insert(TBL_PERSON, null, contentValues)
        db.close()
        return success
    }

    fun getAllPerson(): ArrayList<PersonModel>{
        val stdList: ArrayList<PersonModel> = ArrayList()
        val selectQuery = "SELECT * FROM $TBL_PERSON"
        val db = this.readableDatabase

        val cursor: Cursor?

        try {
            cursor = db.rawQuery(selectQuery, null)
        }catch (e: Exception){
            e.printStackTrace()
            db.execSQL(selectQuery)
            return ArrayList()
        }

        var id: Int
        var name: String
        var surname: String
        var birthday: String
        var phone: String

        if (cursor.moveToFirst()) {
            do {
                id = cursor.getInt(cursor.getColumnIndexOrThrow("id"))
                name = cursor.getString(cursor.getColumnIndexOrThrow("name"))
                surname = cursor.getString(cursor.getColumnIndexOrThrow("surname"))
                birthday = cursor.getString(cursor.getColumnIndexOrThrow("birthday"))
                phone = cursor.getString(cursor.getColumnIndexOrThrow("phone"))

                val std = PersonModel(id = id, name = name, surname = surname, birthday = birthday, phone = phone)
                stdList.add(std)
            } while (cursor.moveToNext())
        }
        return stdList
    }

    fun updatePerson(std: PersonModel): Int {
        val db = this.writableDatabase

        val contentValues = ContentValues()
        contentValues.put(ID, std.id)
        contentValues.put(NAME, std.name)
        contentValues.put(SURNAME, std.surname)
        contentValues.put(BIRTHDAY, std.birthday)
        contentValues.put(PHONE, std.phone)

        val success = db.update(TBL_PERSON, contentValues, "id+" + std.id, null)
        db.close()
        return success
    }

    fun deletePersonById(id:Int): Int {
        val db = this.writableDatabase

        val contentValues = ContentValues()
        contentValues.put(ID, id)

        val success = db.delete(TBL_PERSON, "id-$id", null)
        db.close()
        return success
    }

}