package com.example.newnotes

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
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

        var observer = Observer<List<Note>> {
            noteList = it
            adapter = RvAdapter(noteList, this)
            binding.rv.layoutManager = LinearLayoutManager(this)
            binding.rv.adapter = adapter
            Log.d("TAG", "observer ")

        }
        viewModel.noteList.observe(this, observer)


        adapter = RvAdapter(noteList, this)
        binding.rv.layoutManager = LinearLayoutManager(this)
        binding.rv.adapter = adapter



        binding.floatingActionButton.setOnClickListener {
            var intent = Intent(this, AddEditActivity::class.java)
            startActivity(intent)
        }


        registerForContextMenu(binding.rv)
        binding.rv.setOnClickListener {
            openContextMenu(binding.rv)
        }
    }
}