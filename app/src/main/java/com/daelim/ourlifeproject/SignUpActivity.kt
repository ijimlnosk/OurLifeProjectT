package com.daelim.ourlifeproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.daelim.ourlifeproject.`object`.ApiObject.signUpService
import com.daelim.ourlifeproject.data.SignUpRequestBody
import com.daelim.ourlifeproject.data.SignUpResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.regex.Pattern

val TAG : String = "SingUpActivity"

class SignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        var signUp : SignUpResponse?=null

        val edtEmail = findViewById<EditText>(R.id.edt_SignEmail)
        val edtName = findViewById<EditText>(R.id.edt_SignName)
        val edtBirth = findViewById<EditText>(R.id.edt_SignBirth1)
        val edtPw = findViewById<EditText>(R.id.edt_SignPw)
        val edtPwOk = findViewById<EditText>(R.id.edt_SignPwOk)
        val btnGoToMainList = findViewById<Button>(R.id.btn_goToMainList)
        //isRegularPW(edtPw.toString())

        btnGoToMainList.setOnClickListener {
            Log.d(TAG, "가입 버튼 클릭")
            signUpService.requestSignUp(SignUpRequestBody(edtBirth.toString(),
                edtPw.toString(),
                edtName.toString(),
                edtBirth.toString()))
                .enqueue(object : Callback<SignUpResponse>{
                override fun onResponse(
                    call: Call<SignUpResponse>,
                    response: Response<SignUpResponse>
                ) {
                    signUp = response.body()
                    Log.d("SignUp","message : " + signUp?.message)
                    val intent = Intent(this@SignUpActivity,SignInActivity::class.java)

                    startActivity(intent)

                }

                override fun onFailure(call: Call<SignUpResponse>, t: Throwable) {
                    Log.d("SignUp",t.message.toString())
                }

            })
        }

    }
    //비밀번호 검증
    private fun isRegularPW(password: String): Boolean{
        //val pwPattern1 = "^(?=.*[A-Za_z])(?=.*[0-9])[A-Za-z[0-9]]{8,20}$" //영문,숫자
        //val pwPattern2 = "^(?=.*[0-9])(?=.*[$@$!%*#?&.])[[0-9]$@$!%*#?&.]{8,20}$" //숫자,특수문자
        //val pwPattern3 = "^(?=.*[A-Za-z])(?=.*[$@$!%*#?&.])[A-Za-z$@$!%*#?&.]{8,20}$" //영문,특수문자
        val pwPattern4 = "^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[$@$!%*#?&.])[A-Za-z[0-9]$@$!%*#?&.]{8,20}$" //영문,숫자,특수문자

        val pattern = Pattern.compile(pwPattern4)
        val matcher = pattern.matcher(pwPattern4)
        Log.d("Match", matcher.find().toString())

        return (Pattern.matches(pwPattern4, password))
    }
}