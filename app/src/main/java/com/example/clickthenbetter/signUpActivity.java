package com.example.clickthenbetter;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import javax.annotation.CheckForNull;

class User {

    public ArrayList<Integer> Icon;
    public String email;


    public User() {

    }

    public User( String email) {
        Icon=new ArrayList<>();
        this.email = email;
    }

}

public class signUpActivity extends AppCompatActivity{
    private FirebaseAuth mAuth;
    private RadioGroup upper_group;
    private RadioGroup down_group;
    private RadioGroup color_group;
    private FirebaseDatabase  fdb = FirebaseDatabase.getInstance();
    private DatabaseReference daRef = fdb.getReference();
    CheckBox cb1,cb2,cb3,cb4;
    ArrayList<CheckBox> chArr=new ArrayList<>();

    String email;
    String password;
    String passwordCheck;

    private RadioButton u_rb1, u_rb2, u_rb3, d_rb1, d_rb2, d_rb3, c_rb1, c_rb2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();//초기화 문장==> 회원가입을 위한


        cb1=findViewById(R.id.re1);
        cb2=findViewById(R.id.re2);
        cb3=findViewById(R.id.re3);
        cb4=findViewById(R.id.re4);

        chArr.add(cb1);
        chArr.add(cb2);
        chArr.add(cb3);
        chArr.add(cb4);

        findViewById(R.id.signupButton).setOnClickListener(onClickListener);
    }

    View.OnClickListener onClickListener = new View.OnClickListener(){
        @Override
        public void onClick(View v){

            switch(v.getId()){

                case R.id.signupButton:


                    signUp();
                    writeNewUser("","",email);
                    setOption();
                    break;

            }
        }
    };

    private void checkB(){

    }

    private void signUp(){

        email = ((EditText)findViewById(R.id.EmaileditText)).getText().toString();
        password = ((EditText)findViewById(R.id.passwordeditText)).getText().toString();
        //일반뷰는 gettext를 사용 못한다 에딧텍스트만 사용
        passwordCheck = ((EditText)findViewById(R.id.passwordcheckeditText)).getText().toString();

        if(email.length() > 0 && password.length() > 0 && passwordCheck.length() >0){
            if(password.equals(passwordCheck)) {
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    startToast("회원가입을 성공했습니다.");
                                    FirebaseUser user = mAuth.getCurrentUser();

                                    startLoginActivity();
                                    // 성공 UI
                                } else {
                                    if(task.getException()!=null)
                                        startToast("기존에 가입된 email입니다.");
                                    // 실패 UI
                                }
                            }
                        });
            }

            else{
                startToast("비밀번호가 일치하지 않습니다.");
            }
        }

        else{
            startToast("email 또는 비밀번호를 입력해주세요.");
        }
    }

    private void startToast(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    private void startLoginActivity(){
        Intent intent=new Intent(this,LoginActivity.class);
        startActivity(intent);
    }
    private void writeNewUser(String userId, String name, String email) {
        User user = new User(email);
        for(int i=0;i<chArr.size();i++) {
            if (chArr.get(i).isChecked()) {
                user.Icon.add(1);
            }else{
                user.Icon.add(0);
            }
        }
        daRef.child("USER").child(email).setValue(user);
    }
    private void setOption(){
        int upper = 0;
        int down = 0;
        int color = 0;

    }

}


