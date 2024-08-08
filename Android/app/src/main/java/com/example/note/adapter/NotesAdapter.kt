package com.example.note.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.note.databinding.ItemNoteBinding
import com.example.note.model.Note


class NotesAdapter(private val onItemClick: ((Note) -> Unit)? = null ): RecyclerView.Adapter<NotesAdapter.ViewHolder>() {
    private var data = listOf<Note>()

    fun setNotes(notes: List<Note>) {
        data = notes
        notifyDataSetChanged()
    }

    override fun getItemCount() = data.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemNoteBinding.inflate(inflater, parent, false)
        return ViewHolder(binding, onItemClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val note = data[position]
        val shouldShowCategory = position == 0 || (data[position-1].category != note.category)
        holder.bind(note, shouldShowCategory)
    }

    class ViewHolder(
        private val binding: ItemNoteBinding,
        private val onItemClick: ((Note) -> Unit)? = null
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(note: Note, showCategory: Boolean) {
            binding.category.text = note.category
            binding.headerView.visibility = if (showCategory) View.VISIBLE else View.GONE
            binding.content.text = if (note.content.length > 20) {
                "${note.content.take(20)}..."
            } else {
                note.content
            }
            binding.root.setOnClickListener {
                onItemClick?.invoke(note)
            }
        }
    }

}