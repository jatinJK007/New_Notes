package com.example.newnotes

import android.os.Bundle
import android.util.Log
import android.widget.Toast
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


        if (intent.hasExtra("note_edit")) {

            var note: Note = intent.getSerializableExtra("note_edit") as Note
            binding.title.setText(note.title)
            binding.disp.setText(note.desc)

            binding.AddEdit.setOnClickListener {
                var title = binding.title.text.toString()
                var desc = binding.disp.text.toString()

                note.desc = desc
                note.title = title


                addEditActivityModel.update(note, this)
                finish()

            }

            binding.delete.setOnClickListener {

//                val toast=Toast.makeText(this,"deleted successfully",Toast.LENGTH_SHORT)
//                toast.show()
                Log.d("TAG", "MESSAGE in edit wala delete")
                addEditActivityModel.delete(note, this)
                finish()
            }
        } else {
            binding.AddEdit.setOnClickListener {

                var title = binding.title.text.toString()
                var desc = binding.disp.text.toString()

                var note = Note(title = title, desc = desc)
                Log.d("TAG", "MESSAGE in add matlab new wala")

                addEditActivityModel.insert(note, this)
                finish()
            }
        }
    }
}