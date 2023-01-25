package com.daelim.ourlifeproject.adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.daelim.ourlifeproject.InnerPostActivity
import com.daelim.ourlifeproject.R
import com.daelim.ourlifeproject.data.PostImgData
import com.daelim.ourlifeproject.databinding.UserPostListItemBinding
import kotlinx.android.synthetic.main.user_post_list_item.view.*

class MyPostAdapter(var data: MutableList<Int>): RecyclerView.Adapter<MyPostAdapter.myViewHolder>() {

    private lateinit var binding : UserPostListItemBinding

    class myViewHolder(val layout : View) : RecyclerView.ViewHolder(layout)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.user_post_list_item,parent,false)
        return myViewHolder(view)
    }

    override fun onBindViewHolder(holder: myViewHolder, position: Int) {
        holder.layout.user_post_list_item.setOnClickListener {
            Toast.makeText(holder.layout.context, "${data[position]} Click!", Toast.LENGTH_SHORT).show()
            //val intent = Intent(Context,InnerPostActivity::class.java)

        }
    }

    override fun getItemCount(): Int {
        return data.size
    }
}