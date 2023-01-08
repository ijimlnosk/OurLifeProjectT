package com.daelim.ourlifeproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import java.util.regex.Pattern

class SignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        val edtPw = findViewById<EditText>(R.id.edt_SignPw)
        val edtPwOk = findViewById<EditText>(R.id.edt_SignPwOk)
        val btngoToMainList = findViewById<Button>(R.id.btn_goToMainList)
        //isRegularPW(edtPw.toString())

        btngoToMainList.setOnClickListener {
            if(edtPw.equals(null)||edtPwOk.equals(null)||edtPw.equals("최소 8자 이상 입력해 주세요.")||edtPwOk.equals("위 비밀번호와 같아야 합니다.")){
                Toast.makeText(this@SignUpActivity,"비밀번호 또는 비밀번호 확인을 입력해주세요.", Toast.LENGTH_SHORT).show()
            }
            else if(!edtPw.equals(edtPwOk)){
                Toast.makeText(this@SignUpActivity,"비밀번호가 다릅니다.", Toast.LENGTH_SHORT).show()
            }
            else{
                val intent = Intent(this@SignUpActivity,MainActivity::class.java)
                startActivity(intent)
            }
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