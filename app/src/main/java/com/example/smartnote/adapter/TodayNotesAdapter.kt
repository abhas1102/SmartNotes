package com.example.smartnote.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.smartnote.databinding.RowTodayNotesBinding
import com.example.smartnote.model.TodayNotes

class TodayNotesAdapter(val todayNotesList:List<TodayNotes>):RecyclerView.Adapter<TodayNotesAdapter.ViewHolder>() {

    class ViewHolder(val binding: RowTodayNotesBinding):RecyclerView.ViewHolder(binding.root){
        val title = binding.notesTitle
        val description = binding.notesDescription
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TodayNotesAdapter.ViewHolder {
        val binding = RowTodayNotesBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TodayNotesAdapter.ViewHolder, position: Int) {
        holder.title.text = todayNotesList[position].title
        holder.description.text = todayNotesList[position].description
    }

    override fun getItemCount(): Int {
        return todayNotesList.size
    }

}