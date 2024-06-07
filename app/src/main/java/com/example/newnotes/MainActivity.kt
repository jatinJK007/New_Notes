package com.example.newnotes

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newnotes.database.Note
import com.example.newnotes.databinding.ActivityMainBinding
import com.example.newnotes.viewmodels.MainActivityModel

class MainActivity : AppCompatActivity() {

    val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    lateinit var viewModel: MainActivityModel
    lateinit var adapter: RvAdapter
    lateinit var noteList: List<Note>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)

        noteList = ArrayList()
        viewModel = ViewModelProvider(this).get(MainActivityModel::class.java)

        adapter = RvAdapter(noteList, this)

        binding.rv.layoutManager = LinearLayoutManager(this)
        binding.rv.adapter = adapter

        var observer = Observer<List<Note>>{
            noteList= it
            adapter= RvAdapter(noteList,this)
            binding.rv.layoutManager= LinearLayoutManager(this)
            binding.rv.adapter=adapter
        }


        viewModel.noteList.observe(this , observer)


        binding.floatingActionButton.setOnClickListener {
            var intent = Intent(this, AddEditActivity::class.java)
            startActivity(intent)
        }
    }
}