package cn.mmvtc.shopping;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import cn.mmvtc.shopping.MyApplication.MyApplication;
import cn.mmvtc.shopping.MyDatabases.MyDatabases;
import cn.mmvtc.shopping.ToastUtils.ToaslUtils;

public class MainActivity extends BaseActivity  implements View.OnClickListener {
    private EditText etAccountNumber;
    private EditText etPassword;
    private Button btnLogin;
    private Button btnRegister;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化控件
        init();

    }
    public void init(){
        etAccountNumber = (EditText) findViewById(R.id.et_AccountNumber);
        etPassword = (EditText) findViewById(R.id.et_Password);
        btnLogin = (Button) findViewById(R.id.btn_login);
        btnRegister = (Button) findViewById(R.id.btn_register);
        btnLogin.setOnClickListener(this);
        btnRegister.setOnClickListener(this);
        //初始化数据库
        MyDatabases myDatabases = new MyDatabases(MyApplication.getContext(),"Shopping.db",null,2);
        SQLiteDatabase db = myDatabases.getWritableDatabase();



    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            //注册按钮  向数据库用户表添加文件
            case R.id.btn_register:
                MyDatabases myDatabases = new MyDatabases(MyApplication.getContext(),"Shopping.db",null,2);
                SQLiteDatabase db = myDatabases.getWritableDatabase();
                RegisterActivity.actionStart(MainActivity.this);
                break;
            case R.id.btn_login:
                //获取输入的账号和名称
                String  AccounNumber =etAccountNumber.getText().toString().trim();
                String  password=etPassword.getText().toString().trim();
                if("".equals(AccounNumber) && "".equals(password)){
                    ToaslUtils.getToast(getApplicationContext(),"账号或者密码不能为空");
                }else{
                    boolean b = MyDatabases.selectFindUser(Integer.parseInt(AccounNumber), Integer.parseInt(password));
                    if(b){
                        ToaslUtils.getToast(MyApplication.getContext(),"登陆成功");
                        finish();
                        LoginActivity.stionStart(this,AccounNumber);
                    }else{
                        ToaslUtils.getToast(this,"账号密码输入错误");
                    }
                }
                break;
        }
    }
    public static void actionStart(Context context ){
        Intent intent = new Intent(context,MainActivity.class);
        context.startActivity(intent);
    }

}
