package com.example.note.ui.add_note

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
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
                    .setTitle("Error")
                    .setMessage(errorMessage)
                    .setPositiveButton("Dismiss") { dialog, which ->
                        dialog.dismiss()
                    }
                    .show()
            } else {
                val category = binding.categorySpinner.selectedItem.toString()
                val content = binding.inputContent.text.toString()
                val newNote = Note(category, content)
                viewModel.saveNote(newNote)
            }
        }

        return binding.root
    }


    private fun validateInput(): String? {
        // base checks, if form become complex and has repeating checks, can create validator(s) for each input
        val content = binding.inputContent.text
        if (content.isNullOrBlank()) {
            return "Content cannot be blank"
        }
        if (content.length > 200) {
            return "Content cannot be more than 200 characters."
        }
        return null
    }

}