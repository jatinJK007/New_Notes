package com.example.newnotes

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.example.newnotes.database.Note
import com.example.newnotes.databinding.ActivityAddEditBinding
import com.example.newnotes.viewmodels.AddEditActivityModel

class AddEditActivity : AppCompatActivity() {

    val binding by lazy {
        ActivityAddEditBinding.inflate(layoutInflater)
    }

    lateinit var addEditActivityModel: AddEditActivityModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)


        addEditActivityModel = ViewModelProvider(this).get(AddEditActivityModel::class.java)

//
        if (intent.hasExtra("note")) {
            var note: Note = intent.getSerializableExtra("note") as Note
            binding.title.setText(note.title)
            binding.disp.setText(note.desc)

            binding.AddEdit.setOnClickListener {
                var title = binding.title.text.toString()
                var desc = binding.disp.text.toString()

                note.desc = desc
                note.title = title

                var note = Note(title = title, desc = desc)

                addEditActivityModel.update(note, this)
                finish()

            }

            binding.delete.setOnClickListener {
                addEditActivityModel.delete(note,this)
                finish()
            }
        }
        else{
            binding.AddEdit.setOnClickListener {
                var title = binding.title.text.toString()
                var desc = binding.disp.text.toString()
                var note = Note(title = title, desc = desc)

                addEditActivityModel.insert(note, this)
                finish()
            }
        }
    }
}