package com.example.notebook

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class CreateActivity : AppCompatActivity() {

    private lateinit var edName: EditText
    private lateinit var edBirthday: EditText
    private lateinit var edPhone: EditText
    private lateinit var buttonCancel: Button
    private lateinit var buttonSave: Button
    private lateinit var buttonUpdate: Button

    private lateinit var dbHelper: DBHelper
    private var adapter: PersonAdapter? = null
    private var std:PersonModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create2)

        initView()
        dbHelper = DBHelper(this)

        buttonSave.setOnClickListener { addPerson() }
        buttonUpdate.setOnClickListener { updatePerson() }
        adapter?.setOnClickItem {
            Toast.makeText(this, it.name, Toast.LENGTH_SHORT).show()

            edName.setText(it.name)
            edBirthday.setText(it.birthday)
            edPhone.setText(it.phone)
            std = it
        }

        buttonCancel.setOnClickListener {
            finish()
        }
    }


    private fun getPerson() {
        val stdList = dbHelper.getAllPerson()

        adapter?.addItems(stdList)
    }

    private fun clearEditText() {
        edName.setText("")
        edBirthday.setText("")
        edPhone.setText("")
        edName.requestFocus()
    }

    private fun addPerson() {
        val name = edName.text.toString()
        val birthday = edBirthday.text.toString()
        val phone = edPhone.text.toString()

        if (name.isEmpty() || birthday.isEmpty() || phone.isEmpty()) {
            Toast.makeText(this, "Please enter reguried fiend ", Toast.LENGTH_SHORT).show()
        } else {
            val std = PersonModel(name = name, birthday = birthday, phone = phone)
            val status = dbHelper.insertPerson(std)

            if (status > -1) {
                Toast.makeText(this, "Person added...", Toast.LENGTH_SHORT).show()
                clearEditText()
                getPerson()
            }else{
                Toast.makeText(this, "Record not saved", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun updatePerson() {
        val name = edName.text.toString()
        val birthday = edBirthday.text.toString()
        val phone = edPhone.text.toString()

        if (name == std?.name && birthday == std?.birthday && phone == std?.phone) {
            Toast.makeText(this, "Record not changed...", Toast.LENGTH_SHORT).show()
            return
        }

        if (std == null) return

        val std = PersonModel(id = std!!.id, name = name , birthday = birthday, phone = phone)
        val status = dbHelper.updatePerson(std)
        if (status > -1)
        {
            clearEditText()
            getPerson()
        } else {
            Toast.makeText(this, "Update failed", Toast.LENGTH_SHORT).show()
        }
    }

    private fun initView() {
        edName = findViewById(R.id.edName)
        edBirthday = findViewById(R.id.edBirthday)
        edPhone = findViewById(R.id.edPhone)
        buttonSave = findViewById(R.id.buttonSave)
        buttonCancel = findViewById(R.id.buttonCancel)
        buttonUpdate = findViewById(R.id.buttonUpdate)
    }
}
