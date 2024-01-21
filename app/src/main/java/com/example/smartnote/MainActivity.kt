package com.example.smartnote

import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.databinding.DataBindingUtil
import com.example.smartnote.adapter.TodayNotesAdapter
import com.example.smartnote.database.DatabaseOpenHelper
import com.example.smartnote.databinding.ActivityMainBinding
import com.example.smartnote.model.TodayNotes
import com.example.smartnote.ui.AddNotesActivity

class MainActivity : AppCompatActivity() {
    lateinit var binding:ActivityMainBinding
    var notesList = arrayListOf<TodayNotes>()
    lateinit var mDbOpenHelper: DatabaseOpenHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        mDbOpenHelper = DatabaseOpenHelper(this)
        if (notesList.isEmpty()) binding.txtNoNewNotesAdded.visibility =View.VISIBLE
        openAddNotesScreen()
    }

    override fun onDestroy() {
        mDbOpenHelper.close()
        super.onDestroy()
    }

    fun openAddNotesScreen(){
        binding.plusBtn.setOnClickListener {
            val intent = Intent(this, AddNotesActivity::class.java)
            startActivityForResult(intent,1)
        }
    }
    fun addNotes() {
        val recyclerView = binding.todayNotesList
        recyclerView.adapter = TodayNotesAdapter(notesList)
        val db:SQLiteDatabase = mDbOpenHelper.readableDatabase

    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == RESULT_OK && requestCode == 1) {
            val receivedNote = data?.getParcelableExtra<TodayNotes>("note")
            Log.d("title", receivedNote!!.title)
            notesList.add(receivedNote!!)
            binding.txtNoNewNotesAdded.visibility = View.GONE
            addNotes()
        }
    }
}