package cn.mmvtc.shopping;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import cn.mmvtc.shopping.MyApplication.MyApplication;
import cn.mmvtc.shopping.ToastUtils.ToaslUtils;

public class ShoppingMessageActivity extends AppCompatActivity {
    private Button btnBack;
    private ImageView ivShowShoppingImage;
    private TextView tvShowShoppingMessage;
    private TextView tvShowShoppingPrice;
    private Button btnAddCart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_message);
        //初始化控件
        init();
        //得到意图获取数据
        int imageSrc=  getIntent().getIntExtra("imageSrc",0);
        String ShoppingMessage=getIntent().getStringExtra("Message");
        String ShoppingPrice=getIntent().getStringExtra("Price");
        ivShowShoppingImage.setImageResource(imageSrc);
        tvShowShoppingMessage.setText(ShoppingMessage);
        tvShowShoppingPrice.setText(ShoppingPrice);
        //加入购物车
        btnAddCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToaslUtils.getToast(MyApplication.getContext(),"点点击了返回");
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToaslUtils.getToast(MyApplication.getContext(),"点点击了返回");
            }
        });
    }
    private void init(){
        btnBack = (Button) findViewById(R.id.btn_back);
        ivShowShoppingImage = (ImageView) findViewById(R.id.iv_showShoppingImage);
        tvShowShoppingMessage = (TextView) findViewById(R.id.tv_showShoppingMessage);
        tvShowShoppingPrice = (TextView) findViewById(R.id.tv_showShoppingPrice);
        btnAddCart = (Button) findViewById(R.id.btn_addCart);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToaslUtils.getToast(MyApplication.getContext(),"点击了");
                finish();
            }
        });
    }
    public static void staionStart(Context context,int imageSrc,String Message,String Price){
        Intent intent = new Intent();
        intent.putExtra("imageSrc",imageSrc);
        intent.putExtra("Message",Message);
        intent.putExtra("Price",Price);
        context.startActivity(intent);
    }
}
