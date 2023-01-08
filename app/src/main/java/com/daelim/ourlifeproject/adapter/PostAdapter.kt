package com.daelim.ourlifeproject.adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.daelim.ourlifeproject.UserProfileActivity
import com.daelim.ourlifeproject.data.UserData
import com.daelim.ourlifeproject.databinding.ListItemBinding

private const val TAG_MyProfile = "myProfile_fragment"

class PostAdapter(private val context: Context, data : MutableList<UserData>):
    RecyclerView.Adapter<PostAdapter.myViewHolder>() {

    var datas = mutableListOf<UserData>()
    var mPosition = 0
    private lateinit var binding: ListItemBinding

    inner class myViewHolder(binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root){

        private val txUserId = binding.tvMainUserID
        private val txMainPost = binding.tvMainPost
        private val txCreatedAt = binding.tvMainCreatedAt
        private val imgPost = binding.imgMainPost
        private val imgProfile = binding.imgUserPro

        fun bind(item: UserData){
            txUserId.text = item.userId
            txMainPost.text = item.mainPost
            txCreatedAt.text = item.createdAt
            Glide.with(itemView).load(item.imgPost).into(imgPost)
            Glide.with(itemView).load(item.imgProfile).into(imgProfile)

        }

        /*private val txUerId = view.findViewById<TextView>(R.id.tvMainUserID)
        private val txMainPost = view.findViewById<TextView>(R.id.tvMainPost)
        private val txCreatedAt = view.findViewById<TextView>(R.id.tvMainCreatedAt)
        private val imgPost = view.findViewById<ImageView>(R.id.imgMainPost)
        private val imgProfile = view.findViewById<ImageView>(R.id.imgUserPro)

        fun bind(item : UserData){
            txUerId.text = item.userId
            txMainPost.text = item.mainPost
            txCreatedAt.text = item.createdAt
            Glide.with(itemView).load(item.imgPost).into(imgPost)
            Glide.with(itemView).load(item.imgProfile).into(imgProfile)

            val pos = adapterPosition
            if(pos != RecyclerView.NO_POSITION){
                itemView.setOnClickListener {
                    listener?.onItemClick(itemView,item,pos)
                }
            }

        }*/
    }


    interface OnItemClickListener{
        fun onItemClick(v: View, data: UserData, pos:Int)
    }
    private var listener : OnItemClickListener?=null

    fun getPosition():Int{
        return mPosition
    }
    private fun setPosition(position: Int){
        mPosition = position
    }
    fun addItem(listRequest: UserData){
        datas.add(listRequest)
        notifyDataSetChanged()
    }
    fun removeItem(position: Int){
        if (position>0){
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myViewHolder {
       binding = ListItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return myViewHolder(binding)
        /*val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item,parent,false)
        return myViewHolder(view)*/
    }

    override fun onBindViewHolder(holder: myViewHolder, position: Int) {
        holder.bind(datas[position])
        val item = datas[position]
        holder.bind(item)
        binding.root.setOnClickListener {
            val intent = Intent(context,UserProfileActivity::class.java)
            //intent.putExtra("data",item) 데이터 형식 변환 필요
            intent.run { context.startActivity(this) }


        }
        /*
        holder.itemView.setOnClickListener { view ->
            setPosition(position)
            Toast.makeText(view.context, "$position 아이템 클릭", Toast.LENGTH_SHORT).show()
        }
        holder.itemView.setOnLongClickListener { view ->
            setPosition(position)
            Toast.makeText(view.context,"$position 아이템 롱클릭", Toast.LENGTH_SHORT).show()
            return@setOnLongClickListener true
        }
        holder.apply {
            bind(item)
            itemView.tag = item
        } */
    }

    override fun getItemCount(): Int {
        Log.d("DataSize",datas.size.toString())
        return datas.size
    }

    fun setOnItemClickListener(listener: PostAdapter.OnItemClickListener, function: () -> Unit){
        this.listener = listener
    }

}