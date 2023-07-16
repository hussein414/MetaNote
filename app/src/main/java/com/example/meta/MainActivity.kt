package com.example.meta

import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.meta.data.db.NoteDatabase
import com.example.meta.data.repository.NoteRepository
import com.example.meta.databinding.ActivityMainBinding
import com.example.meta.ui.viewmodel.NoteViewModel
import com.example.meta.ui.viewmodel.factory.NoteViewModelFactory


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var noteViewModel: NoteViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        setStatusBar()
        setViewModel()
    }

    private fun setStatusBar() {
        val window: Window = window
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor =getColor(R.color.main_color)
    }

    private fun setViewModel() {
        val noteRepository = NoteRepository(
            NoteDatabase(applicationContext)
        )

        val viewModelProviderFactory =
            NoteViewModelFactory(
                application, noteRepository
            )

        noteViewModel = ViewModelProvider(
            this,
            viewModelProviderFactory
        )[NoteViewModel::class.java]
    }
}