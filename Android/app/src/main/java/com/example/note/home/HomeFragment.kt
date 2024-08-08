package com.example.note.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.note.adapter.NotesAdapter
import com.example.note.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {
    private val viewModel: HomeViewModel by viewModels()
    private lateinit var binding: FragmentHomeBinding
    private lateinit var adapter: NotesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        adapter = NotesAdapter {
            Toast.makeText(
                context,
                "note clicked: ${it.category} / ${it.content}",
                Toast.LENGTH_SHORT
            ).show()
        }
        binding = FragmentHomeBinding.inflate(inflater)
        binding.notesList.let {
            it.adapter = adapter
            it.layoutManager = LinearLayoutManager(context).apply {
                orientation = LinearLayoutManager.VERTICAL
            }
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // TODO: change later
        val data = viewModel.getDummyData()
        adapter.setNotes(data)
    }

}