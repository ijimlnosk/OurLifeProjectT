package com.daelim.ourlifeproject.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.daelim.ourlifeproject.R
import com.daelim.ourlifeproject.data.PostImgData
import kotlinx.android.synthetic.main.user_post_list_item.view.*

class MyPostAdapter(var data: MutableList<Int>): RecyclerView.Adapter<MyPostAdapter.myViewHolder>() {

    class myViewHolder(val layout: View) : RecyclerView.ViewHolder(layout)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.user_post_list_item,parent,false)

        return myViewHolder(view)
    }

    override fun onBindViewHolder(holder: myViewHolder, position: Int) {
        holder.layout.user_post_list_item.setOnClickListener{
            Toast.makeText(holder.layout.context, "${data[position]} Click!", Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }


    /*lateinit var items : MutableList<PostImgData>
    //lateinit var binding : UserPostListItemBinding

    fun build(i : MutableList<PostImgData>) : MyPostAdapter{
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
        val binding = UserPostListItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return myViewHolder(binding,parent.context)
    }

    override fun onBindViewHolder(holder: myViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        Log.d(items.size.toString(),"items.size")
        return items.size
    }*/

}