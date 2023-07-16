package com.example.meta.ui.fragment.note

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.meta.MainActivity
import com.example.meta.R
import com.example.meta.data.model.NoteModel
import com.example.meta.databinding.FragmentUpdateNoteBinding
import com.example.meta.ui.viewmodel.NoteViewModel
import com.example.meta.utils.sendEmailData

class UpdateNoteFragment : Fragment() {
    private lateinit var binding: FragmentUpdateNoteBinding
    private val argument: UpdateNoteFragmentArgs by navArgs()
    private lateinit var currentNote: NoteModel
    private lateinit var viewModel: NoteViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentUpdateNoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindViews()
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        inflater.inflate(R.menu.menu_update_note, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_delete -> {
                deleteNote()
            }
        }
        return super.onOptionsItemSelected(item)

    }


    private fun bindViews() {
        viewModel = (activity as MainActivity).noteViewModel
        currentNote = argument.note!!
        binding.etNoteBodyUpdate.setText(currentNote.body)
        binding.etNoteTitleUpdate.setText(currentNote.title)
        binding.fabDone.setOnClickListener {
            updateNote()
        }
    }

    private fun updateNote() {
        val title = binding.etNoteTitleUpdate.text.toString().trim()
        val body = binding.etNoteBodyUpdate.text.toString().trim()
        if (title.isNotEmpty() && body.isNotEmpty()) {
            val note = NoteModel(currentNote.id, title, body)
            viewModel.updateNote(note)
            Toast.makeText(requireContext(), "داده مورد نظر بروز شد ", Toast.LENGTH_SHORT)
                .show()
        //    sendEmailData("uonescoo@gmail.com", title, body,requireActivity())
            findNavController().navigate(R.id.action_updateNoteFragment_to_homeFragment)
        } else {
            Toast.makeText(requireContext(), "لطفا عنوان و متن را وارد کنید ", Toast.LENGTH_SHORT)
                .show()
        }
    }

    private fun deleteNote() {
        AlertDialog.Builder(activity).apply {
            setTitle("حذف نام")
            setMessage("ایا از حذف این پرونده مطمعن هستید ؟")
            setPositiveButton("بله") { _, _ ->
                viewModel.deleteNote(currentNote)
                findNavController().navigate(R.id.action_updateNoteFragment_to_homeFragment)
            }
            setNegativeButton("خیر", null)
        }.create().show()
    }

}