package com.example.clickthenbetter;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
    public String addT;


    public User() {

    }

    public User( String email,String addT) {
        Icon=new ArrayList<>();
        this.email = email;
        this.addT=addT;
    }

}

public class signUpActivity extends AppCompatActivity{
    private FirebaseAuth mAuth;
    private RadioGroup upper_group;
    private RadioGroup down_group;
    private RadioGroup color_group;
    private FirebaseDatabase  fdb = FirebaseDatabase.getInstance();
    private DatabaseReference daRef = fdb.getReference();
    String addedText;
    CheckBox cb1,cb2,cb3,cb4;
    ArrayList<CheckBox> chArr=new ArrayList<>();
    Button btnAdd;

    //int c1=0,c2=0,c3=0,c4=0;


    String reT;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();//초기화 문장==> 회원가입을 위한
        btnAdd=findViewById(R.id.btn_add);


        cb1=findViewById(R.id.re1);
        cb2=findViewById(R.id.re2);
        cb3=findViewById(R.id.re3);
        cb4=findViewById(R.id.re4);
//
//        cb2.setOnClickListener(new CheckBox.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                c2=1;
//            }
//        });
//        cb3.setOnClickListener(new CheckBox.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                c3=1;
//            }
//        });
//
//        cb4.setOnClickListener(new CheckBox.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                c4=1;
//            }
//        });
//
//        cb1.setOnClickListener(new CheckBox.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                c1=1;
//            }
//        });



        chArr.add(cb1);
        chArr.add(cb2);
        chArr.add(cb3);


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addedText=((EditText)findViewById(R.id.EmaileditText)).getText().toString();
            }
        });

        findViewById(R.id.signupButton).setOnClickListener(onClickListener);

//        if(cb4.isChecked()){
//            String addedText=((EditText)findViewById(R.id.EmaileditText)).getText().toString();
//            cb1.setChecked(false);
//            cb2.setChecked(false);
//            cb3.setChecked(false);
//        }
    }


    View.OnClickListener onClickListener = new View.OnClickListener(){
        @Override
        public void onClick(View v){

            switch(v.getId()){

                case R.id.signupButton:


                    signUp();

                    setOption();
                    break;

            }
        }
    };

    private void checkB(){

    }

    private void signUp(){

        final String email = ((EditText)findViewById(R.id.EmaileditText)).getText().toString();
        final String password = ((EditText)findViewById(R.id.passwordeditText)).getText().toString();
        //일반뷰는 gettext를 사용 못한다 에딧텍스트만 사용
        final String passwordCheck = ((EditText)findViewById(R.id.passwordcheckeditText)).getText().toString();

        if(email.length() > 0 && password.length() > 0 && passwordCheck.length() >0){
            if(password.equals(passwordCheck)) {
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    startToast("회원가입을 성공했습니다.");
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    writeNewUser("","",email.substring(0,email.length()-4));
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
        User user = new User(email,addedText);
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


