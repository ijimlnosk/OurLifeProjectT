package com.daelim.ourlifeproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.daelim.ourlifeproject.adapter.MyPostAdapter
import com.daelim.ourlifeproject.data.PostImgData
import com.daelim.ourlifeproject.data.UserData
import com.daelim.ourlifeproject.databinding.ActivityUserProfileBinding
import kotlinx.android.synthetic.main.activity_user_profile.*
import kotlinx.android.synthetic.main.fragment_main_post.*

class UserProfileActivity : AppCompatActivity() {

    private val datas = mutableListOf<PostImgData>()
    lateinit var binding : ActivityUserProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_profile)

        var data = mutableListOf<Int>(1,2,3,4,5,6,7,8,9,10)
        Log.d("dataSize",datas.size.toString())
        var dataManager = GridLayoutManager(this,3)
        var dataAdapter = MyPostAdapter(data)

        var recyclerData = user_recycler_view.apply {
            setHasFixedSize(true)
            layoutManager = dataManager
            adapter = dataAdapter
        }

        //setContentView(R.layout.activity_user_profile)
        /*val rvItem = findViewById<RecyclerView>(R.id.user_recycler_view)
        val layoutManager = LinearLayoutManager(this@UserProfileActivity)
        val postAdadpter = MyPostAdadpter(this, datas)
        rvItem.adapter = postAdadpter
        rvItem.layoutManager = layoutManager
        */
        /*binding = ActivityUserProfileBinding.inflate(layoutInflater)
        //setContentView(binding.root)

        binding.userRecyclerView.apply {
            adapter = MyPostAdapter.build(postImgData) // MyPostAdapter의 build가 안붙음
        }*/
    }
    /*private fun buildItemList(listItem : List<ClipData.Item>){
        val itemList = ArrayList<List<ClipData.Item>>()


    }*/
}