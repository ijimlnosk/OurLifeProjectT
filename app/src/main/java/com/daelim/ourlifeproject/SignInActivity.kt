package com.daelim.ourlifeproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.daelim.ourlifeproject.`object`.ApiObject.signInService
import com.daelim.ourlifeproject.data.SignInRequest
import com.daelim.ourlifeproject.data.SignInResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignInActivity : AppCompatActivity() {

    val TAG : String = "SignInActivity"

    var signIn : SignInResponse ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin)

        val btnSignUp = findViewById<Button>(R.id.btn_SignUp)
        val btnSignIn = findViewById<Button>(R.id.btn_join)

        btnSignUp.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
        btnSignIn.setOnClickListener {
           /* val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)*/

            val txEmail = findViewById<EditText>(R.id.edt_Email)
            val txPw = findViewById<EditText>(R.id.edt_Pw)

            val edtEmail = txEmail.text.toString()
            val edtPw = txPw.text.toString()

            signInService.requestSignIn(SignInRequest(edtEmail,edtPw)).enqueue(object : Callback<SignInResponse>{
                override fun onResponse(
                    call: Call<SignInResponse>,
                    response: Response<SignInResponse>
                ) {
                    signIn = response.body()
                    if (signIn?.result.equals("성공")){
                        Log.d("Login","result : " + signIn?.result)
                        val token = signIn?.Authorization.toString()
                        App.prefs.token = token
                        Log.d("Login","token"+signIn?.Authorization)
                        intent = Intent(this@SignInActivity,MainActivity::class.java)
                        startActivity(intent)
                    }
                    else if(signIn?.result.equals("실패")){
                        Toast.makeText(this@SignInActivity,"오류", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<SignInResponse>, t: Throwable) {
                    Log.d("SignUp",t.message.toString())
                }

            })

        }

    }
}