package com.example.practice2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val lvCity = findViewById<ListView>(R.id.lvCity)
        var array:ArrayList<String> = ArrayList()
        array.add("函館")
        array.add("札幌")
        array.add("旭川")
        array.add("釧路")
        array.add("石狩")

        val adapter:ArrayAdapter<String> = ArrayAdapter(this, android.R.layout.simple_list_item_1, array)
        lvCity.adapter = adapter
    }
}