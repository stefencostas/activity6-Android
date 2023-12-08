package com.costas.activity6

import android.os.Bundle
import android.widget.EditText
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.costas.activity6.databinding.ActivityMainBinding
import java.util.Calendar

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val calendar = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnAlertDialog.setOnClickListener {
            showAlertDialog()
        }

        binding.btnDatePicker.setOnClickListener {
            showDatePickerDialog()
        }

        binding.btnTimePicker.setOnClickListener {
            showTimePickerDialog()
        }
    }

    private fun showAlertDialog() {
        val customLayout = layoutInflater.inflate(R.layout.custom_dialog_layout, null)
        val editText = customLayout.findViewById<EditText>(R.id.editText)

        val builder = AlertDialog.Builder(this)
        builder.setTitle("AlertDialog Title")
        builder.setMessage("This is a sample AlertDialog.")
        builder.setPositiveButton("OK") { _, _ ->
            showToast("OK Button Clicked. Entered text: ${editText.text}")
        }
        builder.setNegativeButton("Cancel") { _, _ ->
            showToast("Cancel Button Clicked")
        }
        builder.setView(customLayout)
        builder.setCancelable(false)
        builder.show()
    }

    private fun showDatePickerDialog() {
        val datePickerDialog = DatePickerDialog(
            this,
            { _, year, month, dayOfMonth ->
                showToast("Date selected: $dayOfMonth/${month + 1}/$year")
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )

        datePickerDialog.setButton(DatePickerDialog.BUTTON_NEGATIVE, "Cancel") { _, _ ->
            showToast("Cancel Button Clicked")
        }

        datePickerDialog.show()
    }

    private fun showTimePickerDialog() {
        val timePickerDialog = TimePickerDialog(
            this,
            { _, hourOfDay, minute ->
                showToast("Time selected: $hourOfDay:$minute")
            },
            calendar.get(Calendar.HOUR_OF_DAY),
            calendar.get(Calendar.MINUTE),
            false
        )

        timePickerDialog.setButton(TimePickerDialog.BUTTON_NEGATIVE, "Cancel") { _, _ ->
            showToast("Cancel Button Clicked")
        }

        timePickerDialog.show()
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
