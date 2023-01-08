package com.daelim.ourlifeproject.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.daelim.ourlifeproject.R
import com.daelim.ourlifeproject.adapter.PostAdapter
import com.daelim.ourlifeproject.data.UserData
import com.daelim.ourlifeproject.decorator.VerticalItemDecorator

class MainPostFragment : Fragment(R.layout.fragment_main_post) {
    val TAG : String = "MainPostFragment"
    private lateinit var postAdapter: PostAdapter
    private val datas = mutableListOf<UserData>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_main_post,container,false)
        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val txUserId = view.findViewById<TextView>(R.id.tvMainUserID)
        val txMainPost = view.findViewById<TextView>(R.id.tvMainPost)
        val txCreatedAt = view.findViewById<TextView>(R.id.tvMainCreatedAt)
        val imgPost = view.findViewById<ImageView>(R.id.imgMainPost)
        val imgProfile = view.findViewById<ImageView>(R.id.imgUserPro)

        //datas.add(UserData("김진솔","메인 게시글","12:00",R.drawable.profile_no_click,R.drawable.profile))
        datas.apply {
            datas.add(UserData("김진솔","메인 게시글","12:00",R.drawable.profile,R.drawable.profile_no_click))
            datas.add(UserData("김진솔","메인 게시글","12:00",R.drawable.profile,R.drawable.profile_no_click))
            datas.add(UserData("김진솔","메인 게시글","12:00",R.drawable.profile,R.drawable.profile_no_click))
            datas.add(UserData("김진솔","메인 게시글","12:00",R.drawable.profile,R.drawable.profile_no_click))
        }
        postAdapter = PostAdapter(requireContext(),datas)
        val recycler_view = view.findViewById<RecyclerView>(R.id.recycler_view)
        recycler_view.layoutManager = LinearLayoutManager(activity,RecyclerView.VERTICAL,false)
        recycler_view.setHasFixedSize(true)
        recycler_view.adapter = postAdapter
        recycler_view.addItemDecoration(VerticalItemDecorator(20))
        postAdapter.datas = datas
        postAdapter.notifyDataSetChanged() // 데이터 전체 갱신
    }

}