package com.example.meta.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.meta.data.model.NoteModel
import com.example.meta.data.repository.NoteRepository
import kotlinx.coroutines.launch

class NoteViewModel(app: Application, private val repository: NoteRepository) :
    AndroidViewModel(app) {

    fun addNote(note: NoteModel) = viewModelScope.launch {
        repository.addNote(note)
    }

    fun deleteNote(note: NoteModel) = viewModelScope.launch {
        repository.deleteNote(note)
    }

    fun updateNote(note: NoteModel) = viewModelScope.launch {
        repository.updateNote(note)
    }

    fun getAllNotes() = repository.getAllNotes()

    fun searchNotes(query: String?) = repository.searchNotes(query)
}