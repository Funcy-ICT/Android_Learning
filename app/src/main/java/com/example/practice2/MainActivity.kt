package com.example.practice2

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import androidx.appcompat.app.AlertDialog
import org.json.JSONArray
import java.util.stream.IntStream.builder

class MainActivity : AppCompatActivity() {
    var array: ArrayList<String> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sharedPref = getSharedPreferences("weatherFile", Context.MODE_PRIVATE)
        val JSONArray = JSONArray(sharedPref.getString("weatherInfo", "[]"))
        for(i in 0 until JSONArray.length()){
            array.add(JSONArray.get(i).toString())
        }

        val lvCity = findViewById<ListView>(R.id.lvCity)
        val btAdd = findViewById<Button>(R.id.btAdd)
        val etCity = findViewById<EditText>(R.id.etCity)

        val adapter: ArrayAdapter<String> =
            ArrayAdapter(this, android.R.layout.simple_list_item_1, array)
        lvCity.adapter = adapter

        val dialog = AlertDialog.Builder(this)
            .setTitle("警告")
            .setMessage("本当に消しますか？")
            .setNegativeButton("No") { dialog, id -> }

        lvCity.setOnItemLongClickListener { parent, view, position, id ->
            dialog.setPositiveButton("Yes") { dialog, id ->
                array.removeAt(position)
                adapter.notifyDataSetChanged()
            }.show()
            return@setOnItemLongClickListener true
        }

        btAdd.setOnClickListener {
            if (etCity.text.toString() != "") {
                array.add(etCity.text.toString())
                adapter.notifyDataSetChanged()
                etCity.setText("")
            }
        }
    }

    override fun onStop() {
        super.onStop()
        val sharedPref = getSharedPreferences("weatherFile", Context.MODE_PRIVATE)
        val spEditor = sharedPref.edit()
        val JSONArray = JSONArray(array)
        spEditor.putString("weatherInfo", JSONArray.toString()).apply()
    }
}