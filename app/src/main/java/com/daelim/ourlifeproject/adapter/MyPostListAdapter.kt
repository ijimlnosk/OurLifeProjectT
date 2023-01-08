package com.daelim.ourlifeproject.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.daelim.ourlifeproject.data.PostImgData
import com.daelim.ourlifeproject.databinding.InnerUserPostListItemBinding

class MyPostListAdapter : RecyclerView.Adapter<MyPostListAdapter.myViewHolder>() {

    lateinit var items: MutableList<String>
    lateinit var binding : InnerUserPostListItemBinding

    fun build(i: MutableList<String>): MyPostListAdapter{
        items = i
        return this
    }

    class myViewHolder(val binding : InnerUserPostListItemBinding): RecyclerView.ViewHolder(binding.root) {
        //val userPostImg = binding.userPostImg
        fun bind(item: String){
            //Glide.with(context).load(item).into(userPostImg)
            binding.tvPostText.text = item
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):myViewHolder{
        binding = InnerUserPostListItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyPostListAdapter.myViewHolder(binding)
    }


    override fun onBindViewHolder(holder: myViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        Log.d(items.size.toString(),"items.size")
        return items.size
    }

}