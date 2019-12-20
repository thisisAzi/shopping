package cn.mmvtc.shopping;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import cn.mmvtc.shopping.MyApplication.MyApplication;
import cn.mmvtc.shopping.MyDatabases.MyDatabases;
import cn.mmvtc.shopping.R;
import cn.mmvtc.shopping.ToastUtils.ToaslUtils;

public class RegisterActivity extends BaseActivity {
    private EditText etAccountNumber;
    private EditText etPassword;
    private Button btnLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        //初始化控件
        init();
    }
    private void init(){
        etAccountNumber = (EditText) findViewById(R.id.et_AccountNumber);
        etPassword = (EditText) findViewById(R.id.et_Password);
        btnLogin = (Button) findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int AccountNumber=Integer.parseInt(etAccountNumber.getText().toString());
                int password=Integer.parseInt(etPassword.getText().toString());
                boolean accountNumberExit = MyDatabases.selectFindUser(AccountNumber);
                if(accountNumberExit){
                    ToaslUtils.getToast(MyApplication.getContext(),"账号已存在");
                }else{
                    long request = MyDatabases.registerUser(AccountNumber, password);
                    if(request>0){
                        AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this)
                                .setTitle("通知")
                                .setMessage("注册成功")
                                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        finish();
                                        MainActivity.actionStart(RegisterActivity.this);
                                    }
                                });
                            builder.show();


                    }
                }

            }
        });

    }
    public static void actionStart(Context context){
        Intent intent = new Intent(context,RegisterActivity.class);
        context.startActivity(intent);
    }
}
