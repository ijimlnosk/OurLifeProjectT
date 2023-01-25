package com.daelim.ourlifeproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.daelim.ourlifeproject.adapter.MyPostAdapter
import com.daelim.ourlifeproject.data.PostImgData
import com.daelim.ourlifeproject.data.UserData
import com.daelim.ourlifeproject.databinding.ActivityMainBinding
import com.daelim.ourlifeproject.databinding.ActivityUserProfileBinding
import com.daelim.ourlifeproject.fragment.*
import kotlinx.android.synthetic.main.activity_user_profile.*
import kotlinx.android.synthetic.main.fragment_main_post.*

private const val TAG_Home = "main_post_fragment"
private const val TAG_Search = "search-fragment"
private const val TAG_Chat = "chat_fragment"
private const val TAG_MyProfile = "myProfile_fragment"
private const val TAG_Setting = "setting_fragment"
private const val TAG_UserProfile = "userprofile_fragment"

class UserProfileActivity : AppCompatActivity() {

    val TAG : String = "Userprofile"
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


    }

}