package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val etMemo:EditText = findViewById(R.id.etMemo)
        val lvMemo:ListView = findViewById(R.id.lvMemo)
        val btAdd:Button = findViewById(R.id.btAdd)

        val sharedPref = this.getSharedPreferences("PreferenceFile", MODE_PRIVATE)

        var array : ArrayList<String> = ArrayList()
        array.add("first")
        array.add("second")
        array.add("third")
        array.add("fourth")

        val adapter : ArrayAdapter<String> = ArrayAdapter(this,android.R.layout.simple_list_item_1,array)
        lvMemo.adapter=adapter

        val dialog = AlertDialog.Builder(this)
            .setTitle("delete notification")
            .setMessage("消しますか")
            .setNegativeButton("no"){dialog,id->

            }

        btAdd.setOnClickListener {
            if(etMemo.text.toString() != ""){
                array.add(etMemo.text.toString())
                etMemo.setText("")
                adapter.notifyDataSetChanged()
            }
        }

        lvMemo.setOnItemLongClickListener { parent, view, position, id ->
            dialog.setPositiveButton("yes"){dialog,id ->
                array.removeAt(position)
                adapter.notifyDataSetChanged()
            }.show()
            return@setOnItemLongClickListener true
        }
    }

    override fun onDestroy() {
        super.onDestroy()

    }
}