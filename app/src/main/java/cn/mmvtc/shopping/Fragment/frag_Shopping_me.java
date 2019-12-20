package cn.mmvtc.shopping.Fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.io.IOException;
import java.io.InputStream;

import cn.mmvtc.shopping.LoginActivity;
import cn.mmvtc.shopping.MyApplication.MyApplication;
import cn.mmvtc.shopping.OkhttpUtils.OkhttpUtils;
import cn.mmvtc.shopping.R;
import cn.mmvtc.shopping.bean.UserBean;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class frag_Shopping_me extends Fragment {
    View view;
    private TextView tvShowUserName;
    private Button btnExit;
    private UserBean userBean;private ImageView ivMeImage;
    private static final String TAG = "frag_Shopping_me";
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch(msg.what){
                case 1:
                    byte []bytes=(byte[])msg.obj;
                    Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                    ivMeImage.setImageBitmap(bitmap);
                    break;
            }
        }
    };


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
      view=inflater.inflate(R.layout.fragment_me,container,false);
        //网络请求
        OkhttpUtils okhttpUtils = new OkhttpUtils();
        okhttpUtils.sendSendRequestWithOkhttp("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1576816356903&di=6499fb22b4766fac27539731646850d0&imgtype=0&src=http%3A%2F%2Fb.hiphotos.baidu.com%2Fbaike%2Fw%3D268%2Fsign%3D012e54d7afc379317d68812fd3c6b784%2F29381f30e924b899d0dfefe96e061d950b7bf604.jpg", new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d(TAG, e.getMessage());
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.d(TAG, "有数据");
                Message message = handler.obtainMessage();//声明一个传递信息的Message
                if(response.isSuccessful()){//成功
                    message.what=1;//设置成功指令
                    message.obj=response.body().bytes();
                    handler.sendMessage(message);
                }
            }
        });
      //初始化控件
         init();

        return view;
    }
    private void init(){
        tvShowUserName = (TextView) view.findViewById(R.id.tv_showUserName);
        tvShowUserName.setText(String.valueOf(LoginActivity.AccountNumber));
        btnExit = (Button) view.findViewById(R.id.btn_exit);
        ivMeImage = (ImageView) view.findViewById(R.id.iv_me_image);
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent("finishActivity");
                MyApplication.getContext().sendBroadcast(intent);
            }
        });
    }

}
