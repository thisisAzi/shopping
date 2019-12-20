package cn.mmvtc.shopping.Fragment;

import android.content.ContentValues;
import android.os.Bundle;
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
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import cn.mmvtc.shopping.LoginActivity;
import cn.mmvtc.shopping.MyApplication.MyApplication;
import cn.mmvtc.shopping.MyDatabases.MyDatabases;
import cn.mmvtc.shopping.R;
import cn.mmvtc.shopping.ToastUtils.ToaslUtils;
import cn.mmvtc.shopping.bean.ShoppingCart;

public class frag_ShoppingMessage extends Fragment {
    View view;
    private Button btnBack;
    private ImageView ivShowShoppingImage;
    private TextView ivShowShoppingMessage;
    private TextView ivShowShoppingPrice;
    private Button btnAddCart;
    private static final String TAG = "frag_ShoppingMessage";
    private  Bundle arguments;
    private   int imageSrc;
    private String Message;
    private String Price;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_shoppingmessage,container,false);
        //初始化控件
        init();
        arguments = getArguments();
        if(arguments!=null){
             imageSrc=arguments.getInt("imageSrc");
             Message=arguments.getString("Message");
             Price=arguments.getString("Price");
             ivShowShoppingImage.setImageResource(imageSrc);
             ivShowShoppingMessage.setText(Message);
            ivShowShoppingPrice.setText(Price);
        }
        return view;
    }
    private void init(){
        ivShowShoppingImage = (ImageView) view.findViewById(R.id.iv_showShoppingImage);
        ivShowShoppingMessage = (TextView) view.findViewById(R.id.iv_showShoppingMessage);
        ivShowShoppingPrice = (TextView) view.findViewById(R.id.iv_showShoppingPrice);
        btnBack = (Button) view.findViewById(R.id.btn_back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.LineLayout_ShowShoppingMessage,new frag_Shopping_shopping());
                transaction.commit();
            }
        });
        btnAddCart = (Button) view.findViewById(R.id.btn_addCart);
        btnAddCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContentValues values = new ContentValues();
                values.put("number", LoginActivity.AccountNumber);
                values.put("message",Message);
                values.put("price",Price);
                long id = MyDatabases.insertShoppingCartList(values);
                if(id>0){
                    ToaslUtils.getToast(MyApplication.getContext(),"添加成功");
                }

            }
        });
    }
    public void refresh(int srcImage,String Message,String Price){
        View view_content = view.findViewById(R.id.fragment_shoppingmessage_content);
        ivShowShoppingImage.setImageResource(srcImage);
        ivShowShoppingMessage.setText(Message);
        ivShowShoppingPrice.setText(Price);

    }

}
