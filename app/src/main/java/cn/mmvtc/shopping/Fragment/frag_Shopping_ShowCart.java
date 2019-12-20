package cn.mmvtc.shopping.Fragment;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import cn.mmvtc.shopping.Adapter.ShoppingCaryAdapter;
import cn.mmvtc.shopping.LoginActivity;
import cn.mmvtc.shopping.MyApplication.MyApplication;
import cn.mmvtc.shopping.MyDatabases.MyDatabases;
import cn.mmvtc.shopping.OnRecyclerItemClickListener.OnRecycleritemClickListener_ShoppingCart;
import cn.mmvtc.shopping.R;
import cn.mmvtc.shopping.ToastUtils.ToaslUtils;
import cn.mmvtc.shopping.bean.Shopping;
import cn.mmvtc.shopping.bean.ShoppingCart;

public class frag_Shopping_ShowCart extends Fragment {
    View view;
    private static TextView tvShowShoppingNumber;
    private RecyclerView rvShowShoppingCart;
    private Button btnExit;
    private List<ShoppingCart> shoppingCartList=new ArrayList<>();
    private ShoppingCaryAdapter  shoppingCaryAdapter;
    private  List<ShoppingCart> SQLshoppingCartList=new ArrayList<>();

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch(msg.what){
                case 1:
                    tvShowShoppingNumber.setText(String.valueOf(msg.arg1));
                    break;
            }
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable final Bundle savedInstanceState) {
       view=inflater.inflate(R.layout.fragment_shoopingcart,container,false);
       //初始化控件
        init();
        //读取用户在购物车表的数据返回一个数组获取对象得到属性值设置
        SQLshoppingCartList = MyDatabases.selectShoppingCartList(Integer.parseInt(LoginActivity.AccountNumber));
        LinearLayoutManager layoutManager = new LinearLayoutManager(MyApplication.getContext());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        rvShowShoppingCart.setLayoutManager(layoutManager);
        shoppingCaryAdapter = new ShoppingCaryAdapter(SQLshoppingCartList);
        rvShowShoppingCart.setAdapter(shoppingCaryAdapter);
        tvShowShoppingNumber.setText(String.valueOf(SQLshoppingCartList.size()));
        //设置适配器点击事件
        shoppingCaryAdapter.setRecyclerItemClickListener(new OnRecycleritemClickListener_ShoppingCart() {
            @Override
            public void OnRecyclerItemClickListener_shoppingCart(int position, List<ShoppingCart> sCartlist) {
                ShoppingCart sc=sCartlist.get(position);

                boolean b = MyDatabases.deleteSimpleUserShoppingCart_(Integer.valueOf(sc.getId()));
                if(b){
                    ToaslUtils.getToast(getActivity(),"删除成功");
                }
            }
        });
        return view;
    }
    private void init(){
        tvShowShoppingNumber = (TextView) view.findViewById(R.id.tv_showShoppingNumber);
        rvShowShoppingCart = (RecyclerView) view.findViewById(R.id.rv_showShoppingCart);
        btnExit = (Button) view.findViewById(R.id.btn_exit);
        //点击清空用户购物车表
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("通知");
                builder.setMessage("确定清空购物车吗？");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        MyDatabases.clearUserShoppingCart(Integer.parseInt(LoginActivity.AccountNumber));
                        SQLshoppingCartList.clear();
                        shoppingCaryAdapter.notifyDataSetChanged();
                        tvShowShoppingNumber.setText(String.valueOf(SQLshoppingCartList.size()));

                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
                builder.show();
            }
        });
    }
    private void initShoppingCart(){
        ShoppingCart sc1 = new ShoppingCart();
        sc1.setMessage("伯希和户外冲锋衣男女潮牌外套三合一加绒加");
        sc1.setPrice("123");
        shoppingCartList.add(sc1);
        ShoppingCart sc2 = new ShoppingCart();
        sc2.setMessage("北欧现代简约茶几电视柜组合小户型客厅迷你木质方形组装日式方几");
        sc2.setPrice("231");
        shoppingCartList.add(sc2);
        ShoppingCart sc3 = new ShoppingCart();
        sc3.setMessage("简约百搭束脚休闲裤潮流男士百搭收口小脚裤  ");
        sc3.setPrice("121");
        shoppingCartList.add(sc3);
        ShoppingCart sc4 = new ShoppingCart();
        sc4.setMessage("秋冬户外软壳冲锋裤男女加绒保暖登山裤潮  ");
        sc4.setPrice("201");
        shoppingCartList.add(sc4);
    }
    //提供方法设置购物车条件数目
    public  void setShoppingCartNumber( final  int number){
        Message message = new Message();
        message.what=1;
        message.arg1=number;
        handler.sendMessage(message);
    }

}
