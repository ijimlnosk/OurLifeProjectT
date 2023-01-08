package com.daelim.ourlifeproject

import android.Manifest.permission.*
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.daelim.ourlifeproject.adapter.PostAdapter
import com.daelim.ourlifeproject.data.UserData
import com.daelim.ourlifeproject.databinding.ActivityNewPostBinding
import java.io.File
import java.net.URI
import java.time.LocalDate

class NewPostActivity : AppCompatActivity() {

    private val datas = mutableListOf<UserData>()
    private lateinit var imageFile : File
    lateinit var binding : ActivityNewPostBinding
    //private val edtPost = findViewById<EditText>(R.id.edt_mainPost)

    companion object{
        const val REVIEW_MIN_LENGTH = 10
        //갤러리 권한 요청
        const val REQ_GALLERY = 1

        //API 호출시 Parameter key 값
        const val PARAM_KEY_IMAGE = "image"
        const val PARAM_KEY_PRODUCT_ID = "product_id"
        const val PARAM_KEY_REVIEW = "review_content"
        const val PARAM_KEY_RATING = "rating"
    }
    //이미지를 결과값으로 받는 변수
    private val imageResult = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ){
        result ->
        if(result.resultCode == RESULT_OK){
            //이미지를 받으면 ImageView에 적용한다
            val imageUri = result.data?.data
            imageUri?.let {
                //서버 업로드를 위해 파일 형태로 변환한다.
                imageFile = File(getRealPathFromURI(it))
                //이미지를 불러온다
                Glide.with(this)
                    .load(imageUri)
                    .fitCenter()
                    .apply(RequestOptions.overrideOf(500,500))
                    .into(binding.imgPhoto)
            }
        }
    }
    private val requestMultiplePermission = registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()){results ->
        results.forEach{
            if(!it.value){
                Toast.makeText(applicationContext,"${it.key} 권한 허용 필요", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }
    private val permissionList = arrayOf(
        android.Manifest.permission.CAMERA,
        android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
        android.Manifest.permission.READ_EXTERNAL_STORAGE)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_post)

        val recycler_view = findViewById<RecyclerView>(R.id.recycler_view)
        val savePost = findViewById<Button>(R.id.btn_savePost)
        val addPhoto = findViewById<Button>(R.id.btn_addPhoto)
        val backPress = findViewById<Button>(R.id.btn_backPress)
        val postAdapter = PostAdapter(this,datas)

        backPress.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }

        addPhoto.setOnClickListener {
            //requestMultiplePermission.launch(permissionList)
            selectGallery()
        }

        savePost.setOnClickListener {
            val intent = Intent(this@NewPostActivity,MainActivity::class.java)
            val edtMainPost = findViewById<EditText>(R.id.edt_mainPost)
            val now = LocalDate.now()
            val nowY = now.year.toString()
            val nowM = now.monthValue.toString()
            val nowD = now.dayOfMonth.toString()
            postAdapter.addItem(UserData("김진솔",edtMainPost.toString(),nowY+nowM+nowD,R.drawable.profile,R.drawable.profile_no_click))
            startActivity(intent)
        }

    }

    //이미지 실제 경로 반환
    fun getRealPathFromURI(uri: Uri) : String{
        val buildName = Build.MANUFACTURER
        if(buildName.equals("Xiaomi")){
            return uri.path!!
        }
        var columnIndex = 0
        val proj = arrayOf(MediaStore.Images.Media.DATA)
        val cursor = contentResolver.query(uri,proj,null,null,null)
        if(cursor!!.moveToFirst()){
            columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
        }
        val result = cursor.getString(columnIndex)
        cursor.close()
        return result
    }

    // 갤러리를 부르는 메서드
    private fun selectGallery(){
        val writePermission = ContextCompat.checkSelfPermission(this,android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
        val readPermission = ContextCompat.checkSelfPermission(this,android.Manifest.permission.READ_EXTERNAL_STORAGE)

        //권한 확인
        if(writePermission == PackageManager.PERMISSION_DENIED || readPermission == PackageManager.PERMISSION_DENIED){
            //권한 요청
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE,android.Manifest.permission.READ_EXTERNAL_STORAGE),
                REQ_GALLERY)
        }else{
            //권한이 있는 경우 갤러리 실행
            val intent = Intent(Intent.ACTION_PICK)
            //intent의 data와 type을 동시에설정하는 메서드
            intent.setDataAndType(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                "image/*"
            )
            imageResult.launch(intent)
        }
    }
}