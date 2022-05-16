package com.example.myapplication

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment

class SimpleDialogClass : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(activity)
        builder.setTitle("delete notification").setMessage("この項目を消しますか？")
            .setPositiveButton("delete"){dialog, id ->
                println("dialog:$dialog which:$id")
            }
            .setNegativeButton("No"){dialog,id ->
                println("dialog:$dialog which:$id")
            }

        return builder.create()
    }
}