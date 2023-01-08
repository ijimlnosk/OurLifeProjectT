package com.daelim.ourlifeproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.daelim.ourlifeproject.databinding.ActivityMainBinding
import com.daelim.ourlifeproject.fragment.*

private const val TAG_Home = "main_post_fragment"
private const val TAG_Search = "search-fragment"
private const val TAG_Chat = "chat_fragment"
private const val TAG_MyProfile = "myProfile_fragment"
private const val TAG_Setting = "setting_fragment"
private const val TAG_UserProfile = "userprofile_fragment"

class MainActivity : AppCompatActivity() {
    val TAG : String ="MainActivity"
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setNaviFragment(TAG_Home, MainPostFragment())

        binding.mainNavi.setOnItemSelectedListener {
            when(it.itemId){
                R.id.ic_home -> setNaviFragment(TAG_Home, MainPostFragment())
                R.id.ic_search -> setNaviFragment(TAG_Search, SearchFragment())
                R.id.ic_chat -> setNaviFragment(TAG_Chat, ChatFragment())
                R.id.ic_profile -> setNaviFragment(TAG_MyProfile, MyProfileFragment())
                R.id.ic_setting -> setNaviFragment(TAG_Setting, SettingFragment())
            }
            true
        }

        binding.btnNewPost.setOnClickListener {
            val intent = Intent(this@MainActivity, NewPostActivity::class.java)
            startActivity(intent)
        }

        /*val txUserPro = view.findViewById<TextView>(R.id.tvMainUserID)
        txUserPro.setOnClickListener{
            setNaviFragment(TAG_UserProfile,UserProfileFragment())
        }*/

    }

    fun setFragment(f: MainPostFragment){
        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.frameLayout,f)
        ft.commit()
    }

    //네비 프래그먼트 컨트롤
    fun setNaviFragment(tag: String, fragment: Fragment){
        val manager: FragmentManager = supportFragmentManager
        val ft: FragmentTransaction = manager.beginTransaction()

        // transaction에 tag로 전달된 fragment가 없을 경우 add
        if(manager.findFragmentByTag(tag) == null){
            ft.add(R.id.frameLayout,fragment,tag)
        }

        // manager에 add되어있는 fragment들을 변수로 할당
        val homeFt = manager.findFragmentByTag(TAG_Home)
        val searchFt = manager.findFragmentByTag(TAG_Search)
        val chatFt = manager.findFragmentByTag(TAG_Chat)
        val myProfileFt = manager.findFragmentByTag(TAG_MyProfile)
        val settingFt = manager.findFragmentByTag(TAG_Setting)

        // 모든 fragment hide
        if(homeFt!=null){
            ft.hide(homeFt)
        }
        if(searchFt!=null){
            ft.hide(searchFt)
        }
        if(chatFt!=null){
            ft.hide(chatFt)
        }
        if(myProfileFt!=null){
            ft.hide(myProfileFt)
        }
        if(settingFt!=null){
            ft.hide(settingFt)
        }

        // 선택한 항목에 따라 맞는 fragment show
        if(tag == TAG_Home){
            if(homeFt!=null){
                ft.show(homeFt)
            }
        }else if(tag == TAG_Search){
            if (searchFt!=null){
                ft.show(searchFt)
            }
        }else if(tag == TAG_Chat){
            if (chatFt!=null){
                ft.show(chatFt)
            }
        }else if(tag == TAG_MyProfile){
            if (myProfileFt!=null){
                ft.show(myProfileFt)
            }
        }else if(tag == TAG_Setting){
            if (settingFt!=null){
                ft.show(settingFt)
            }
        }
        //end
        ft.commitAllowingStateLoss()

    }

}