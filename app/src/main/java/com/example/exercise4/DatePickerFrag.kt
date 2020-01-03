package com.example.exercise4

import android.app.*
import android.os.Bundle
import android.widget.TextView
import android.widget.DatePicker
import java.text.DateFormat
import java.util.Calendar

class DatePickerFrag: DialogFragment(), DatePickerDialog.OnDateSetListener {

    private lateinit var calendar: Calendar

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)



        return DatePickerDialog(activity, android.R.style.Theme_Holo_Light_Dialog_NoActionBar_MinWidth,this,year, month, day )
    }



    override fun onDateSet(view: DatePicker, year: Int, month: Int, day: Int) {
        val minSaving: TextView = activity.findViewById(R.id.minSavingText)
        val withdraw: TextView = activity.findViewById(R.id.withdrawText)

        activity.findViewById<TextView>(R.id.birthdateText).text = String.format("Date of birth: " + formatDate(year,month,day))
        activity.findViewById<TextView>(R.id.ageText).text = String.format("Age: "+ getAge(year,month,day))
        val num = Integer.parseInt(getAge(year, month, day))
        val minAmount = when(num){
            in 16..20 -> 5000
            in 21..25 -> 14000
            in 26..30 -> 29000
            in 31..35 -> 50000
            in 36..40 -> 78000
            in 41..45 -> 116000
            in 46..50 -> 165000
            in 51..55 -> 228000
            else -> 0
        }
        val maxAmount = when(num){
            in 16..20 -> 1500
            in 21..25 -> 4200
            in 26..30 -> 8700
            in 31..35 -> 15000
            in 36..40 -> 23400
            in 41..45 -> 34800
            in 46..50 -> 49500
            in 51..55 -> 68400
            else -> 0
        }
        minSaving.text = "RM "+ minAmount
        withdraw.text = "RM "+ maxAmount
    }


    private fun formatDate(year:Int, month:Int, day:Int):String{

        calendar.set(year, month, day, 0, 0, 0)
        val date = calendar.time

        val df = DateFormat.getDateInstance(DateFormat.MEDIUM)
        return df.format(date)
    }
    private fun getAge(year:Int, month:Int, day:Int):String {
        val birthdate = Calendar.getInstance()
        val today = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        birthdate.set(year, month, day)
        var age = today.get(Calendar.YEAR) - birthdate.get(Calendar.YEAR)
        if (today.get(Calendar.DAY_OF_YEAR) < birthdate.get(Calendar.DAY_OF_YEAR))
        {
            age-=1
        }
        val ageInt = age
        val ageS = ageInt.toString()

        return ageS
    }
}