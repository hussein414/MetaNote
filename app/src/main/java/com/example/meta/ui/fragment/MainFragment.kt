package com.example.meta.ui.fragment

import android.icu.util.IslamicCalendar
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.meta.R
import com.example.meta.data.event.ItemClickListener
import com.example.meta.data.model.ItemModel
import com.example.meta.databinding.FragmentMainBinding
import com.example.meta.ui.adapter.ItemCardAdapter
import com.example.meta.utils.Constance
import ir.hamsaa.persiandatepicker.api.PersianPickerDate
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale


class MainFragment : Fragment(), ItemClickListener {

    private lateinit var binding: FragmentMainBinding
    private lateinit var mainAdapter: ItemCardAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentMainBinding.inflate(inflater, container, false)
        bindViews()
        return binding.root
    }

    private fun bindViews() {
        mainAdapter = ItemCardAdapter(Constance.mainWigetData, this)
        binding.MainRecyclerView.apply {
            adapter = mainAdapter
            layoutManager =
                GridLayoutManager(requireContext(), 3, GridLayoutManager.VERTICAL, false)
            setHasFixedSize(true)
        }
        setCalender()
    }

    private fun setCalender() {
        val handler = Handler(Looper.getMainLooper())
        handler.post(object :Runnable{
            override fun run() {
                val calendar = Calendar.getInstance()
                val dateFormat = SimpleDateFormat("yyyy/MM/dd", Locale.getDefault())
                val formattedDate = dateFormat.format(calendar.time)
                binding.calenderCard.date.text = formattedDate
                val dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK)
                binding.calenderCard.day.text = getPersianDayName(dayOfWeek)
                val month = calendar.get(Calendar.MONTH)
                binding.calenderCard.month.text = getPersianMonthName(month)
                val islamicCalendar = IslamicCalendar()
                islamicCalendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH))
                val islamicYear = islamicCalendar.get(IslamicCalendar.YEAR)
                binding.calenderCard.year.text = islamicYear.toString()
                handler.postDelayed(this, 1000)
            }
        })
    }

    private fun getPersianDayName(dayOfWeek: Int): String {
        val persianDays = arrayOf("یک‌شنبه", "دوشنبه", "سه‌شنبه", "چهارشنبه", "پنج‌شنبه", "جمعه", "شنبه")
        return persianDays[dayOfWeek - 1]
    }

    private fun getPersianMonthName(month: Int): String {
        val persianMonths = arrayOf("فروردین", "اردیبهشت", "خرداد", "تیر", "مرداد", "شهریور", "مهر", "آبان", "آذر", "دی", "بهمن", "اسفند")
        return persianMonths[month]
    }

    override fun itemClick(model: ItemModel) {
        if (model.name == "طالع و فال") {
            findNavController().navigate(R.id.action_mainFragment_to_horoscopeFragment)
        } else {
            Toast.makeText(requireContext(), "ایکون طالع فال و بزن", Toast.LENGTH_SHORT).show()
        }
    }

}