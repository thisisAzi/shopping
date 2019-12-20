package cn.mmvtc.shopping.ToastUtils;

import android.content.Context;
import android.widget.Toast;

public class ToaslUtils {
    private static  Toast toast=null;
    public static  void getToast(Context context,String message){
         if(toast==null){
             toast=Toast.makeText(context,message,Toast.LENGTH_LONG);
         }else{
             toast.setText(message);
         }
         toast.show();
    }
}
