package com.tp.android.noteapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.tp.android.noteapp.databinding.ActivityMainBinding
import com.tp.android.noteapp.databinding.DialogLayoutBinding

class MainActivity : AppCompatActivity() {
    private lateinit var adapter: NoteAdapter
    private val list = ArrayList<NoteEntity>()
    private lateinit var binding: ActivityMainBinding

    companion object {
        var dateAdded = 0
    }

    override fun onRestart() {
        super.onRestart()
        adapter.notifyDataSetChanged()
        binding.progressbar.visibility = View.VISIBLE
        Handler().postDelayed({
            binding.progressbar.visibility = View.INVISIBLE
            for (i in 0 until dateAdded){
                list.add(DataDummy.generateNote()[i])
            }
            showRecyclerView(list)

            binding.fabAdd.setOnClickListener {
                val intent = Intent(this@MainActivity, AddUpdateActivity::class.java)
                startActivity(intent)
            }

        }, 2000)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.progressbar.visibility = View.VISIBLE
        Handler().postDelayed({
            binding.progressbar.visibility = View.INVISIBLE
            for (i in 0 until dateAdded){
                list.add(DataDummy.generateNote()[i])
            }
            if (list.isEmpty()) showSnackbarMessage("Data is empty")
            showRecyclerView(list)

            binding.fabAdd.setOnClickListener {
                val intent = Intent(this@MainActivity, AddUpdateActivity::class.java)
                startActivity(intent)
            }

        }, 2000)
        binding.rvNotes.setHasFixedSize(true)
    }

    private fun showRecyclerView(list: ArrayList<NoteEntity>) {
        binding.rvNotes.layoutManager = LinearLayoutManager(this)
        adapter = NoteAdapter(list)
        binding.rvNotes.adapter = adapter

        adapter.setOnItemClickCallback(object : NoteAdapter.OnItemClickCallback {
            override fun onItemClicked(data: NoteEntity) {
                val alertDialogView = LayoutInflater.from(this@MainActivity).inflate(R.layout.dialog_layout, null)
                val dialogBinding = DialogLayoutBinding.inflate(LayoutInflater.from(this@MainActivity))

                val builder = AlertDialog.Builder(this@MainActivity)
                    .setView(alertDialogView)
                    .setTitle("Choice")
                builder.setView(dialogBinding.root)

                val alertDialog = builder.show()
                dialogBinding.edit.setOnClickListener {
                    val intent = Intent(this@MainActivity, AddUpdateActivity::class.java)
                    intent.putExtra("result", 100)
                    intent.putExtra("data", data)
                    startActivity(intent)
                }
                dialogBinding.delete.setOnClickListener {
                    adapter.removeItem(data.id.toInt())
                    showSnackbarMessage("Satu item berhasil dihapus")
                    alertDialog.dismiss()
                }
                dialogBinding.detail.setOnClickListener {
                    val intent = Intent(this@MainActivity, AddUpdateActivity::class.java)
                    intent.putExtra("result", 102)
                    intent.putExtra("data", data)
                    startActivity(intent)
                }
                dialogBinding.cancel.setOnClickListener {
                    alertDialog.dismiss()
                }
            }
        })
    }

    override fun onBackPressed() {
        val alertDialogBuilder = AlertDialog.Builder(this)
        alertDialogBuilder.setTitle("Attention!")
        alertDialogBuilder.setMessage("Do you want to exit?")
        alertDialogBuilder.setPositiveButton("Yes") { _, _ ->
            super.onBackPressed()
        }
        alertDialogBuilder.setNegativeButton("No") { _, _ ->
        }
        alertDialogBuilder.show()
    }
    private fun showSnackbarMessage(message: String) {
        Snackbar.make(binding.rvNotes, message, Snackbar.LENGTH_SHORT).show()
    }
}