package com.example.note.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.note.databinding.ViewItemSettingsBinding
import com.example.note.model.Note
import com.example.note.model.SettingMenu


class SettingsAdapter: RecyclerView.Adapter<SettingsAdapter.ViewHolder>() {
    private var data = listOf<SettingMenu>()

    fun setMenu(notes: List<SettingMenu>) {
        data = notes
        notifyDataSetChanged()
    }

    override fun getItemCount() = data.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ViewItemSettingsBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val note = data[position]
        holder.bind(note)
    }

    class ViewHolder(
        private val binding: ViewItemSettingsBinding,
        private val onItemClick: ((Note) -> Unit)? = null
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(menu: SettingMenu) {
            binding.name.text = itemView.context.getString(menu.name)
            binding.icon.setImageResource(menu.icon)
        }
    }

}