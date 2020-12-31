package com.tp.android.noteapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tp.android.noteapp.databinding.ItemNoteBinding

class NoteAdapter(private val listItem: ArrayList<NoteEntity>) : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {
    fun removeItem(position: Int) {
        this.listItem.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, this.listItem.size)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false)
        return NoteViewHolder(view)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bind(listItem[position])
    }

    override fun getItemCount(): Int = this.listItem.size

    inner class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemNoteBinding.bind(itemView)
        fun bind(note: NoteEntity) {
            binding.tvItemTitle.text = note.title
            binding.tvItemDate.text = note.date
            binding.tvItemDescription.text = note.description
            itemView.setOnClickListener { onItemClickCallback?.onItemClicked(note) }
        }
    }

    private var onItemClickCallback: OnItemClickCallback? = null
    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }
    interface OnItemClickCallback {
        fun onItemClicked(data: NoteEntity)
    }
}