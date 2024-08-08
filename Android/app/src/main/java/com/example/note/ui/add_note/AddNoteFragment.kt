package com.example.note.ui.add_note

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.note.Constants
import com.example.note.R
import com.example.note.databinding.FragmentAddNoteBinding
import com.example.note.model.Note
import com.google.android.material.dialog.MaterialAlertDialogBuilder


class AddNoteFragment : Fragment() {
    private val viewModel: AddNoteViewModel by viewModels { AddNoteViewModel.Factory }
    private lateinit var binding: FragmentAddNoteBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddNoteBinding.inflate(inflater)
        binding.btnSave.setOnClickListener {
            val errorMessage = validateInput()
            if (errorMessage!=null) {
                MaterialAlertDialogBuilder(requireContext())
                    .setTitle(getString(R.string.error))
                    .setMessage(errorMessage)
                    .setPositiveButton(getString(R.string.dismiss)) { dialog, which ->
                        dialog.dismiss()
                    }
                    .show()
            } else {
                val category = binding.categorySpinner.selectedItem.toString()
                val content = binding.inputContent.text.toString()
                val newNote = Note(category, content)
                viewModel.saveNote(newNote)
                Toast.makeText(context, "Note added", Toast.LENGTH_SHORT).show()
            }
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.showLoading.observe(viewLifecycleOwner) {
            binding.loadingView.root.visibility = if (it)  View.VISIBLE else View.GONE
        }
    }

    private fun validateInput(): String? {
        // base checks, if form become complex and has repeating checks, can create validator(s) for each input
        val content = binding.inputContent.text
        if (content.isNullOrBlank()) {
            return getString(R.string.content_cannot_be_blank)
        }
        if (content.length > Constants.MAX_CONTENT_CHARACTERS) {
            return getString(R.string.content_cannot_be_more_than_n_characters, Constants.MAX_CONTENT_CHARACTERS)
        }
        return null
    }

}