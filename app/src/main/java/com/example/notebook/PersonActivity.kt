package com.example.notebook

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class PersonActivity : AppCompatActivity() {

    private lateinit var edName: TextView
    private lateinit var edBirthday: TextView
    private lateinit var edPhone: TextView
    private lateinit var buttonCreate: Button
    private lateinit var buttonBack: Button

    private lateinit var dbHelper: DBHelper
    private lateinit var recyclerView: RecyclerView
    private var adapter: PersonAdapter? = null
    private var std:PersonModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_person)

        initView()
        dbHelper = DBHelper(this)


        adapter?.setOnClickItem {
            Toast.makeText(this, it.name, Toast.LENGTH_SHORT).show()

            edName.setText(it.name)
            edBirthday.setText(it.birthday)
            edPhone.setText(it.phone)
            std = it
        }

        adapter?.setOnClickDeleteItem {
            deletePerson(it.id)
            finish()
        }


        val nameIntent = intent.getStringExtra(MainActivity.EXTRA_KEY_NAME)
        val birthdayIntent = intent.getStringExtra(MainActivity.EXTRA_KEY_BIRTHDAY)
        val phoneIntent = intent.getStringExtra(MainActivity.EXTRA_KEY_PHONE)

        val tvName = findViewById<TextView>(R.id.edName)
        val tvBirthday = findViewById<TextView>(R.id.edBirthday)
        val tvPhone = findViewById<TextView>(R.id.edPhone)
        tvName.text = nameIntent
        tvBirthday.text = birthdayIntent
        tvPhone.text = phoneIntent


        buttonBack.setOnClickListener {
            finish()
        }

        buttonCreate.setOnClickListener {
            val intent = Intent(this, PersonActivity::class.java)
            startActivity(intent)
            finish()
        }

    }

    private fun getPerson() {
        val stdList = dbHelper.getAllPerson()

        adapter?.addItems(stdList)
    }


    private fun deletePerson(id:Int) {
        val builder = AlertDialog.Builder(this)
        builder.setMessage("Are you sure you want to delete item?")
        builder.setCancelable(true)
        builder.setPositiveButton("Yes") {dialog, _ ->
            dbHelper.deletePersonById(id)
            getPerson()
            dialog.dismiss()
        }
        builder.setNegativeButton("No") {dialog, _ ->

            dialog.dismiss()
        }

        val alert = builder.create()
        alert.show()
    }

    private fun clearEditText() {
        edName.setText("")
        edBirthday.setText("")
        edPhone.setText("")
        edName.requestFocus()
    }

    private fun initView() {
        edName = findViewById(R.id.edName)
        edBirthday = findViewById(R.id.edBirthday)
        edPhone = findViewById(R.id.edPhone)
        buttonCreate = findViewById(R.id.buttonCreate)
        buttonBack = findViewById(R.id.buttonBack)
        recyclerView = findViewById(R.id.recyclerView)
    }
}