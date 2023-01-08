package com.daelim.ourlifeproject.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.daelim.ourlifeproject.data.PostImgData
import com.daelim.ourlifeproject.data.UserData
import com.daelim.ourlifeproject.databinding.UserPostListItemBinding

class MyPostAdadpter: RecyclerView.Adapter<MyPostAdadpter.myViewHolder>() {

    private lateinit var items : MutableList<PostImgData>
    lateinit var binding : UserPostListItemBinding

    fun build(i: MutableList<PostImgData>) : MyPostAdadpter{
        items = i
        return this
    }

    class myViewHolder(val binding: UserPostListItemBinding,val context: Context) : RecyclerView.ViewHolder(binding.root){
        //private val imgUser = binding.imgUserPostItem
        fun bind(item : PostImgData){
            with(binding){
                //Glide.with(context).load(item.postImgData).into(imgUser)
                rvUserPostListItem.apply {
                    adapter = MyPostListAdapter().build(item.postImgData)
                    layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myViewHolder {
        binding = UserPostListItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return myViewHolder(binding,parent.context)
    }

    override fun onBindViewHolder(holder: myViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        Log.d(items.size.toString(),"items.size")
        return items.size
    }

}