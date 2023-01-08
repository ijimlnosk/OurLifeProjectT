package com.daelim.ourlifeproject

import android.content.ClipData
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.daelim.ourlifeproject.adapter.MyPostAdadpter
import com.daelim.ourlifeproject.data.UserData
import com.daelim.ourlifeproject.databinding.ActivityUserProfileBinding

class UserProfileActivity : AppCompatActivity() {

    private val datas = mutableListOf<UserData>()
    lateinit var binding : ActivityUserProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_user_profile)
        /*val rvItem = findViewById<RecyclerView>(R.id.user_recycler_view)
        val layoutManager = LinearLayoutManager(this@UserProfileActivity)
        val postAdadpter = MyPostAdadpter(this, datas)
        rvItem.adapter = postAdadpter
        rvItem.layoutManager = layoutManager
        */
        binding = ActivityUserProfileBinding.inflate(layoutInflater)
        //setContentView(binding.root)

        binding.userRecyclerView.apply {
            //adapter = MyPostAdadpter. // MyPostAdapter의 build가 안붙음
        }
    }
    /*private fun buildItemList(listItem : List<ClipData.Item>){
        val itemList = ArrayList<List<ClipData.Item>>()


    }*/
}