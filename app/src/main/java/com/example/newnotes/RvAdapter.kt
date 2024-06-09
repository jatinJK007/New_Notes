package com.example.newnotes

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.ContextMenu
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.newnotes.database.Note
import com.example.newnotes.database.NoteDatabase
import com.example.newnotes.databinding.ActivityMainBinding
import com.example.newnotes.databinding.RvItemBinding

class RvAdapter(var noteList: List<Note>, var context: Context) :
    RecyclerView.Adapter<RvAdapter.MyViewHolder>() {

    inner class MyViewHolder(var binding: RvItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        var binding = RvItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        Log.d("TAG", "on create view holder 1st function of adapter ")

        return MyViewHolder(binding)

    }

    override fun getItemCount(): Int {
        return noteList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.binding.tvTitle.text = noteList.get(position).title
        holder.binding.tvDesc.text = noteList.get(position).desc
        holder.itemView.setOnClickListener {
            var intent = Intent(context, AddEditActivity::class.java)
            intent.putExtra("note_edit", noteList.get(position))
            Log.d("TAG", "on bind view holder 1st function of adapter ")

            context.startActivity(intent)

        }
    }
}