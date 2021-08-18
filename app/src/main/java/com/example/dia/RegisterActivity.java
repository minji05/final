package com.example.dia;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

//회원가입 화면
public class RegisterActivity extends AppCompatActivity {
    private EditText et_id_1, et_pass_1, et_passck, et_name;
    private Button btn_register,validateButton;
    private AlertDialog dialog;
    private boolean validate=false;
    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        //아이디 값 찾아주기
        et_id_1 = findViewById(R.id.et_id_1);
        et_pass_1 = findViewById(R.id.et_pass_1);
        et_passck = findViewById(R.id.et_passck_1);
        et_name=findViewById(R.id.et_name);

        btn_register=findViewById(R.id.btn_register_1);

        //파이어베이스 접근 설정
        firebaseAuth= FirebaseAuth.getInstance();

        //가입 버튼을 눌렀을때
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //가입 정보 가져오기
                final String email=et_id_1.getText().toString().trim();
                String pwd=et_pass_1.getText().toString().trim();
                String ch_pwd=et_passck.getText().toString().trim();

                if(pwd.equals(ch_pwd)){
                    final ProgressDialog mDialog=new ProgressDialog(RegisterActivity.this);
                    mDialog.setMessage("가입중입니다.");
                    mDialog.show();

                    //파이어베이스 신규계정 등록하기
                    firebaseAuth.createUserWithEmailAndPassword(email,pwd).addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            //가입 성공시
                            if(task.isSuccessful()) {
                                mDialog.dismiss();

                                FirebaseUser user = firebaseAuth.getCurrentUser();
                                String email = user.getEmail();
                                String uid = user.getUid();
                                String name = et_name.getText().toString().trim();

                                //해쉬맵 테이블을 파이어베이스 데이터베이스에 저장
                                HashMap<Object, String> hashMap = new HashMap<>();

                                hashMap.put("uid", uid);
                                hashMap.put("email", email);
                                hashMap.put("name", name);

                                FirebaseDatabase database = FirebaseDatabase.getInstance();
                                DatabaseReference reference = database.getReference("Users");
                                reference.child(uid).setValue(hashMap);


                                //가입이 이루어져을시 가입 화면을 빠져나감.
                                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                startActivity(intent);
                                finish();
                                Toast.makeText(RegisterActivity.this, "회원가입에 성공하셨습니다.", Toast.LENGTH_SHORT).show();
                            }
                            else{
                                mDialog.dismiss();
                                Toast.makeText(RegisterActivity.this, "이미 존재하는 아이디 입니다.", Toast.LENGTH_SHORT).show();
                                return;

                            }
                        }
                    });

                    //비밀번호 오류 시
                } else{

                    Toast.makeText(RegisterActivity.this, "비밀번호가 틀렸습니다. 다시 입력해 주세요.", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        });
    }
}
