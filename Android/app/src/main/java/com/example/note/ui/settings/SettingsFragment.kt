package com.example.note.ui.settings

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.note.R
import com.example.note.adapter.SettingsAdapter
import com.example.note.databinding.FragmentSettingsBinding
import com.example.note.model.ExternalSettingMenu
import com.google.android.material.dialog.MaterialAlertDialogBuilder


class SettingsFragment : Fragment() {
    private val viewModel: SettingsViewModel by viewModels { SettingsViewModel.Factory }
    private lateinit var binding: FragmentSettingsBinding
    private lateinit var adapter: SettingsAdapter //TODO: create new view and adapter later

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        adapter = SettingsAdapter {
            if (it is ExternalSettingMenu) {
                val intent = Intent(Intent.ACTION_VIEW)
                intent.setData(Uri.parse(it.url))
                startActivity(intent)
            }

        }.also {
            it.setMenu(viewModel.getMenus())
        }
        binding = FragmentSettingsBinding.inflate(inflater)
        binding.settingsList.let {
            it.adapter = adapter
            it.layoutManager = LinearLayoutManager(context).apply {
                orientation = LinearLayoutManager.VERTICAL
            }
        }

        binding.btnDeleteNotes.setOnClickListener {
            deleteNotes()
        }

        return binding.root
    }

    private fun deleteNotes() {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle(getString(R.string.warning))
            .setMessage(getString(R.string.notes_will_be_deleted))
            .setPositiveButton(getString(R.string.delete)) { _, _ ->
                viewModel.deleteNotes()

                // since Toast.setView is deprecated , it might require 3rd party library or rewrite entire toast message framework to customise it
                // https://developer.android.com/reference/android/widget/Toast#setView(android.view.View)
                Toast.makeText(
                    requireContext(),
                    getString(R.string.all_notes_have_been_cleared),
                    Toast.LENGTH_SHORT
                ).show()

            }
            .show()
    }


}