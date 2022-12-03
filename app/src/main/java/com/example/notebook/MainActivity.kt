package com.example.notebook

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var edName: EditText
    private lateinit var edBirthday: EditText
    private lateinit var edPhone: EditText
    lateinit var buttonInfo: Button
    private lateinit var buttonView: Button
    private lateinit var buttonVie: Button

    private lateinit var dbHelper: DBHelper
    private lateinit var recyclerView: RecyclerView
    private var adapter: PersonAdapter? = null
    private var std:PersonModel? = null

   @SuppressLint("MissingInflatedId")

    companion object {
        const val EXTRA_KEY_NAME = "EXTRA"
        const val EXTRA_KEY_BIRTHDAY = "EXTRA"
        const val EXTRA_KEY_PHONE = "EXTRA"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()
        initRecuclerView()
        dbHelper = DBHelper(this)

        //buttonView.setOnClickListener { getPerson() }
        adapter?.setOnClickItem {
            Toast.makeText(this, it.name, Toast.LENGTH_SHORT).show()

            edName.setText(it.name)
            edBirthday.setText(it.birthday)
            edPhone.setText(it.phone)
            std = it
        }


        buttonInfo.setOnClickListener {
            val intent = Intent(this, PersonActivity::class.java)
            intent.putExtra(EXTRA_KEY_NAME, edName.toString())
            intent.putExtra(EXTRA_KEY_BIRTHDAY, edBirthday.toString())
            intent.putExtra(EXTRA_KEY_PHONE, edPhone.toString())
            getPerson()
            startActivity(intent)
        }

        buttonView.setOnClickListener {
            val intent = Intent(this, CreateActivity::class.java)
            startActivity(intent)
        }

    }


    private fun getPerson() {
        val stdList = dbHelper.getAllPerson()

        adapter?.addItems(stdList)
    }


    private fun infoPerson(id:Int) {
            dbHelper.deletePersonById(id)
            getPerson()

        val infoIntent = Intent(this, PersonActivity::class.java)
        startActivity(infoIntent)
    }

    private fun clearEditText() {
        edName.setText("")
        edBirthday.setText("")
        edPhone.setText("")
        edName.requestFocus()
    }

    private fun initRecuclerView() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = PersonAdapter()
        recyclerView.adapter = adapter
    }

    private fun initView() {
        edName = findViewById(R.id.edName)
        edBirthday = findViewById(R.id.edBirthday)
        edPhone = findViewById(R.id.edPhone)
        buttonView = findViewById(R.id.buttonView)
        buttonVie = findViewById(R.id.buttonVie)
        recyclerView = findViewById(R.id.recyclerView)
        buttonInfo = findViewById(R.id.buttonInfo)
    }
}