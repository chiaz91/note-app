package com.example.note.ui.summary

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.note.adapter.SummaryAdapter
import com.example.note.databinding.FragmentSummaryBinding


class SummaryFragment : Fragment() {
    private val viewModel: SummaryViewModel by viewModels { SummaryViewModel.Factory }
    private lateinit var binding: FragmentSummaryBinding
    private lateinit var adapter: SummaryAdapter //TODO: create new view and adapter later

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        adapter = SummaryAdapter {
            Toast.makeText(context, "$it clicked", Toast.LENGTH_SHORT).show()
        }
        binding = FragmentSummaryBinding.inflate(inflater)
        binding.summaryList.let {
            it.adapter = adapter
            it.layoutManager = LinearLayoutManager(context).apply {
                orientation = LinearLayoutManager.VERTICAL
            }
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.summary.observe(viewLifecycleOwner) {
            adapter.setNotes(it)
        }
    }

}