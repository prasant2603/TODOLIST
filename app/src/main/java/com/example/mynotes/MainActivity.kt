package com.example.mynotes

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mynotes.NoteAdapter.rvAdapter
import com.example.mynotes.ViewModel.NoteViewModel
import com.example.mynotes.roomDataBase.Note

class MainActivity : AppCompatActivity(), rvAdapter.INotesRVAdapter {
    lateinit var viewModel: NoteViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recyclerView : RecyclerView =findViewById(R.id.recyclerview)
        recyclerView.layoutManager= LinearLayoutManager(this)
        val adapter = rvAdapter(this,this)
        recyclerView.adapter = adapter
        viewModel = ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory.getInstance(application))
            .get(NoteViewModel::class.java)
        viewModel.allNotes.observe(this, Observer {list ->
            list?.let {
                adapter.updateList(it)
            }
        })
        val button: Button=findViewById(R.id.addButton)
        val input:EditText=findViewById(R.id.input)
        button.setOnClickListener {
            val noteText=input.text.toString()
            if(noteText.isNotEmpty())
            {
                viewModel.insert(Note(noteText))
                input.setText("")
                Toast.makeText(this,"${noteText} Added",Toast.LENGTH_SHORT).show()
            }
        }
    }
    override fun onItemClicked(note: Note) {
        viewModel.delete(note)
        Toast.makeText(this,"${note.text} Deleted",Toast.LENGTH_SHORT).show()
    }
}