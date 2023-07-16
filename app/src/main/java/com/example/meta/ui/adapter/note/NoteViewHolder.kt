package com.example.meta.ui.adapter.note

import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.meta.data.model.NoteModel
import com.example.meta.databinding.NoteItemBinding
import com.example.meta.ui.fragment.note.HomeNoteFragmentDirections

class NoteViewHolder(private val binding: NoteItemBinding) : ViewHolder(binding.root) {
    fun bindViews(note: NoteModel) {
        binding.apply {
            tvNoteTitle.text = note.title
            tvNoteBody.text = note.body
            cardView.setOnClickListener {view->
                val direction = HomeNoteFragmentDirections.actionHomeFragmentToUpdateNoteFragment(note)
              view.findNavController().navigate(direction)
            }

        }
    }
}