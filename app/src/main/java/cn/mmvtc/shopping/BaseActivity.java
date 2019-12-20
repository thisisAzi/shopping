package cn.mmvtc.shopping;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import cn.mmvtc.shopping.ActivityCollector.ActivityCollector;
import cn.mmvtc.shopping.MyApplication.MyApplication;

public class BaseActivity extends AppCompatActivity {
    private ExitActivityReceiver et;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCollector.addActivity(this);
    }

    @Override
    protected void onResume() {
        //注册广播接收者
        super.onResume();
        et=new ExitActivityReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("finishActivity");
        registerReceiver(et,intentFilter);

    }
    @Override
    protected void onPause() {
        //销毁广播接收者
        super.onPause();
        if(et!=null){
            unregisterReceiver(et);
        }
        et=null;

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }
    public class ExitActivityReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(final Context context, Intent intent) {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("通知");
            builder.setMessage("退出成功");
            builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    ActivityCollector.finishActivity();
                    MainActivity.actionStart(context);
                }
            });
            builder.show();
        }
    }
}
