package com.runningcherry.test

import android.app.Activity
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.runningcherry.test.retrofit.SeriesItem
import com.squareup.picasso.Picasso
import android.widget.Toast




class MyAdapter (val context : Context) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {
    private var list : List<SeriesItem> = arrayListOf()

    inner class MyViewHolder internal constructor (itemView : View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        private var image : ImageView = itemView.findViewById(R.id.imageView)
        private var title : TextView = itemView.findViewById(R.id.title)
        private var number : TextView = itemView.findViewById(R.id.number)
        private lateinit var _item : SeriesItem

        fun bind (item : SeriesItem) {
            try {
                Picasso.with(itemView.context).load(item.image.original).resize(500, 9 * 500 / 16)
                    .into(image)
            }
            catch (e : Exception) {
                Log.e ("kek", e.message!!)
            }
            title.text = item.name
            number.text = "season ${item.season} episode ${item.number}"
            _item = item
        }

        override fun onClick(v: View?) {
            Log.e ("kek", "adapter = $_item")
            (context as MainActivity).onClickItem(_item)
        }

        init {
            itemView.setOnClickListener(this)
        }
    }

    fun setList (_list : List<SeriesItem>) {
        list = _list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.my_item, parent, false)
        return MyViewHolder (view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }
}