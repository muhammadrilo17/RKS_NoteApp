package com.tp.android.noteapp

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.view.Menu
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.tp.android.noteapp.databinding.ActivityAddUpdateBinding

class AddUpdateActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityAddUpdateBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)

        when {
            intent.getIntExtra("result", 1) == 100 -> {
                // edit
                val data = intent.getParcelableExtra<NoteEntity>("data")
                if (data != null) {
                    binding.edtTitle.setText(data.title)
                    binding.edtDate.setText(data.date)
                    binding.edtDescription.setText(data.description)
                }
            }
            intent.getIntExtra("result", 1) == 102 -> {
                // detail
                val data = intent.getParcelableExtra<NoteEntity>("data")
                binding.btnSubmit.visibility = View.INVISIBLE
                if (data != null) {
                    binding.edtTitle.setText(data.title)
                    binding.edtTitle.isEnabled = false
                    binding.edtDate.setText(data.date)
                    binding.edtDate.isEnabled = false
                    binding.edtDescription.setText(data.description)
                    binding.edtDescription.isEnabled = false
                }

            }
        }
        binding.btnSubmit.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_submit -> {
                MainActivity.dateAdded++
                startActivity(Intent(this, MainActivity::class.java))
                this.finish()
            }
        }
    }
}