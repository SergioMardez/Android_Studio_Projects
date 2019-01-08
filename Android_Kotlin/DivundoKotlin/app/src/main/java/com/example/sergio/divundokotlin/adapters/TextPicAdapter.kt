package com.example.sergio.divundokotlin.adapters

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.example.sergio.divundokotlin.R
import com.example.sergio.divundokotlin.inflate
import com.example.sergio.divundokotlin.listeners.RecyclerTextPicListener
import com.example.sergio.divundokotlin.loadByURL
import com.example.sergio.divundokotlin.models.TextPic
import kotlinx.android.synthetic.main.recycler_textpic.view.*

class TextPicAdapter(private val textPic: List<TextPic>, private val listener: RecyclerTextPicListener)
    : RecyclerView.Adapter<TextPicAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(parent.inflate(R.layout.recycler_textpic))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(textPic[position], listener)

    override fun getItemCount() = textPic.size

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        // = with(itemView) sirve para poner el contexto y no tener que poner itemView.textViewCityName o los otros.
        fun bind(textPic: TextPic, listener: RecyclerTextPicListener) = with(itemView){
            //Load image and text
            imageViewPic.loadByURL(textPic.picture)
            textViewText.text = textPic.text

            //Clicks events
            setOnClickListener { listener.onClick(textPic, adapterPosition) }
        }
    }
}