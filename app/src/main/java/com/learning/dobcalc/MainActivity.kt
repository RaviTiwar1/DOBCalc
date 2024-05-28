package com.learning.dobcalc

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class MainActivity : AppCompatActivity() {

     private var tvSelectedDate : TextView? = null
        private var tvAgeInMinutes : TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)




        val btnDatePicker : Button = findViewById(R.id.btndatePicker)
        tvSelectedDate = findViewById(R.id.tvSelectedDate)
        tvAgeInMinutes = findViewById(R.id.tvAgeInMinutes)
        btnDatePicker.setOnClickListener {
            clickDatePicker()
        }



    }
   private fun clickDatePicker(){

       val myCalendar = Calendar.getInstance()
       val year = myCalendar.get(Calendar.YEAR)
       val month = myCalendar.get(Calendar.MONTH)
       val day = myCalendar.get(Calendar.DAY_OF_MONTH)
       val dpd =  DatePickerDialog(this, { view, selectedYear, selectedMonth, dayOfMonth ->

           Toast.makeText(this,
               "Year is $selectedYear, Month is ${selectedMonth+1}, Day is $dayOfMonth",
               Toast.LENGTH_LONG).show()

           val selectedDate = "${dayOfMonth}/${selectedMonth+1}/${selectedYear}"

           tvSelectedDate?.text = selectedDate

           val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)

           val theDate = sdf.parse(selectedDate)

           theDate?.let {
               val selectedDateInMinutes = theDate.time / 60000

               val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))

               currentDate?.let {
                   val currentDateInMinutes = currentDate.time / 60000

                   val differenceInMinutes = currentDateInMinutes - selectedDateInMinutes

                   tvAgeInMinutes?.text = differenceInMinutes.toString()

               }


           }


       },
           year,
           month,
           day
       )
       dpd.datePicker.maxDate = System.currentTimeMillis() - 86400000
       dpd.show()




   }


}