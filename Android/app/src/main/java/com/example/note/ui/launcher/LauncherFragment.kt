package com.example.note.ui.launcher

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.example.note.R
import com.example.note.databinding.FragmentLauncherBinding
import com.example.note.ui.add_note.AddNoteFragment
import com.example.note.ui.home.HomeFragment
import com.example.note.ui.summary.SummaryFragment


class LauncherFragment : Fragment() {
    private lateinit var binding: FragmentLauncherBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLauncherBinding.inflate(inflater)
        binding.menuHome.setOnClickListener { onHomeClicked() }
        binding.menuAddNote.setOnClickListener { onAddNoteClicked() }
        binding.menuSummary.setOnClickListener { onSummaryClicked() }


        return binding.root
    }

    /*
    TODO: try Jetpack Navigation Component?
    instead of manu manage fragment transition, can nav component allow definition of navGraph.xml, and ensure safe arguments in app navigation
     */
    private fun onHomeClicked() {
        childFragmentManager.commit {
            replace<HomeFragment>(R.id.launcherFragmentContainerView)
        }
    }
    private fun onSummaryClicked() {
        childFragmentManager.commit {
            replace<SummaryFragment>(R.id.launcherFragmentContainerView)
        }
    }

    private fun onAddNoteClicked() {
        parentFragmentManager.commit {
            addToBackStack(null)
            replace<AddNoteFragment>(R.id.rootFragmentContainerView)
        }
    }



}