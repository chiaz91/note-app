package com.example.note.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.note.R
import com.example.note.adapter.NotesAdapter
import com.example.note.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {
    private val viewModel: HomeViewModel by viewModels {HomeViewModel.Factory}
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
        binding.emptyView.btnImportData.setOnClickListener {
            viewModel.importDummyData()
        }


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        registerMenu()

        viewModel.notes.observe(viewLifecycleOwner) {
            adapter.setNotes(it)
            binding.emptyView.root.visibility = if (it.isNotEmpty()) View.GONE else View.VISIBLE
        }
    }


    private fun registerMenu() {
        // to add optional menu on toolbar
        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.home_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId) {
                    R.id.action_setting -> {
                        onSettingClicked()
                        true
                    }
                    else -> false
                }
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    private fun onSettingClicked() {
        // FIXME: to test only, fix me to navigate to setting later
//        Toast.makeText(
//            context,
//            "setting clicked",
//            Toast.LENGTH_SHORT
//        ).show()


        viewModel.deleteAllNotes()
    }

}