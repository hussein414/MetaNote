package com.example.meta.ui.fragment.note

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.meta.MainActivity
import com.example.meta.R
import com.example.meta.data.model.NoteModel
import com.example.meta.databinding.FragmentNewNoteBinding
import com.example.meta.ui.viewmodel.NoteViewModel
import com.example.meta.utils.sendEmailData
import com.example.meta.utils.sendEmailDataSMPT
import com.google.android.material.snackbar.Snackbar

class NewNoteFragment : Fragment() {
    private lateinit var binding: FragmentNewNoteBinding
    private lateinit var noteViewModel: NoteViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentNewNoteBinding.inflate(inflater, container, false)
        bindViews()
        return binding.root
    }


    @Deprecated("Deprecated in Java")
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_new_note, menu)
    }

    @Deprecated("Deprecated in Java")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_save -> {
                setNoteSave()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }


    private fun bindViews() {
        binding.fabSaveNote.setOnClickListener {
            val noteTitle = binding.etNoteTitle.text.toString().trim()
            val noteBody = binding.etNoteBody.text.toString().trim()
            setNoteSave()

        }
    }

    private fun setNoteSave() {
        noteViewModel = (activity as MainActivity).noteViewModel
        val noteTitle = binding.etNoteTitle.text.toString().trim()
        val noteBody = binding.etNoteBody.text.toString().trim()
        if (noteTitle.isNotEmpty() && noteBody.isNotEmpty()) {
            val note = NoteModel(0, noteTitle, noteBody)
            noteViewModel.addNote(note)
            sendEmailDataSMPT("husseinebrahimzadeh82@gmail.com", noteTitle, noteBody)
            Snackbar.make(binding.root, "اطلاعات با موفقیت به طور کامل اضافه شد", Snackbar.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_newNoteFragment_to_homeFragment)
        } else {
            Toast.makeText(requireContext(), "لطفا اطلاعات را وارد کنید", Toast.LENGTH_SHORT)
                .show()
        }
    }
}