package com.example.adaptertp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editTextSearch: EditText = findViewById(R.id.editTextSearch)

        val spinner: Spinner by lazy { findViewById(R.id.spinner) }

        val matieres = listOf("Cours", "TP")

        spinner.adapter = ArrayAdapter(
            this,
            android.R.layout.simple_dropdown_item_1line,
            matieres
        )

        recyclerView = findViewById(R.id.recyclerViewStudents)
        val items = generateStudentList()


        recyclerView.layoutManager=LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)


        val adapter = StudentAdapter(this,items)
        recyclerView.adapter = adapter

        editTextSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(charSequence: CharSequence?, start: Int, before: Int, count: Int) {
                adapter.filter.filter(charSequence.toString())
            }

            override fun afterTextChanged(editable: Editable?) {}
        })

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                adapterView: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selectedItem = spinner.getItemAtPosition(position).toString()

                /*Toast.makeText(
                    applicationContext,
                    "Selected Item: $selectedItem",
                    Toast.LENGTH_SHORT
                ).show()*/
                adapter.filter.filter(selectedItem)

            }

            override fun onNothingSelected(adapterView: AdapterView<*>?) {


                Toast.makeText(
                    applicationContext,
                    "No Item Selected",
                    Toast.LENGTH_SHORT
                ).show()
            }

        }







    }

    private fun generateStudentList(): List<Student> {
        // Populate the list of students with your student data
        return listOf(
            Student("John", "Doe", "Male", Matieres.COURS),
            Student("Jane", "Smith", "Female", Matieres.TP),
            Student("Oumayma", "Ouerfelli", "Female", Matieres.COURS),
            Student("Mohamed", "Lasswed", "Male", Matieres.TP),
            Student("Arij", "Kouki", "Female", Matieres.COURS),
            Student("Omar", "Jabloun", "Male", Matieres.TP),
        )
    }

}