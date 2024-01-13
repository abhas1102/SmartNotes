package com.example.smartnote.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.smartnote.MainActivity
import com.example.smartnote.R
import com.example.smartnote.databinding.AddNotesBinding
import com.example.smartnote.model.TodayNotes

class AddNotesActivity : AppCompatActivity() {
    lateinit var binding:AddNotesBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.add_notes)
        submitNotes()
    }
    fun submitNotes() {
        binding.btnSubmitNote.setOnClickListener {
            val note = TodayNotes(binding.addTitle.text.toString(), binding.addDescription.text.toString())
            val intent = Intent()
            intent.putExtra("note",note)
            setResult(RESULT_OK,intent)
            finish()
        }
    }


}