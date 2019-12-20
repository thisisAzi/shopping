package cn.mmvtc.shopping;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import cn.mmvtc.shopping.Adapter.ShoppingCategoryAdapter;
import cn.mmvtc.shopping.Fragment.frag_ShoppingMessage;
import cn.mmvtc.shopping.Fragment.frag_Shopping_ShowCart;
import cn.mmvtc.shopping.Fragment.frag_Shopping_me;
import cn.mmvtc.shopping.Fragment.frag_Shopping_shopping;
import cn.mmvtc.shopping.OnRecyclerItemClickListener.OnRecyclerItemClickListener_shoppingCategory;
import cn.mmvtc.shopping.bean.Shopping;
import cn.mmvtc.shopping.bean.ShoppingCategory;

public class LoginActivity extends BaseActivity  implements View.OnClickListener {
    private RecyclerView rvShowShoppingCategory;
    private RecyclerView rvShowShopping;
    private Button btnOneShowShopping;
    private Button btnTwoShowShoppingCar;
    private Button btnTreeMy;
    private List<ShoppingCategory> scList=new ArrayList<>();
    private List<Shopping> shoppingList=new ArrayList<>();
    public  static String AccountNumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //初始化控件
        init();
        //初始化数据
        initCategory();//选择栏数据
        AccountNumber=getIntent().getStringExtra("AccountNumber");
        //选择栏Recyclerview
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rvShowShoppingCategory.setLayoutManager(layoutManager);
        final ShoppingCategoryAdapter scAdapter = new ShoppingCategoryAdapter(scList);
        rvShowShoppingCategory.setAdapter(scAdapter);
        scAdapter.setRecyclerItemClickListener(new OnRecyclerItemClickListener_shoppingCategory() {
            @Override
            public void OnRecyclerItemClickListener_shoppingCategory(int Position, List<ShoppingCategory> slist) {
                ShoppingCategory sc=slist.get(Position);
                String CategoryName = sc.getName();
                //刷新显示商品信息的适配器 传数据过去碎片
                Bundle bundle = new Bundle();
                bundle.putString("Category",CategoryName);
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                frag_Shopping_shopping frag_shopping_shopping = new frag_Shopping_shopping();
                frag_shopping_shopping.setArguments(bundle);
                transaction.replace(R.id.LineLayout_ShowShoppingMessage,frag_shopping_shopping);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        //切换碎片  显示商品信息
        replaceFragment(new frag_Shopping_shopping());
        //显示商品Recyclerview
//        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
//        ShoppingMessageAdapter shoppingMessageAdapter = new ShoppingMessageAdapter(shoppingList);
//        rvShowShopping.setLayoutManager(staggeredGridLayoutManager);
//        rvShowShopping.setAdapter(shoppingMessageAdapter);
    }
    //初始化数据
    private void init(){
        rvShowShoppingCategory = (RecyclerView) findViewById(R.id.rv_showShoppingCategory);
        rvShowShopping = (RecyclerView) findViewById(R.id.rv_showShopping);
        btnOneShowShopping = (Button) findViewById(R.id.btn_one_showShopping);
        btnTwoShowShoppingCar = (Button) findViewById(R.id.btn_two_showShoppingCar);
        btnTreeMy = (Button) findViewById(R.id.btn_three_My);
        btnOneShowShopping.setOnClickListener(this);
        btnTwoShowShoppingCar.setOnClickListener(this);
        btnTreeMy.setOnClickListener(this);
    }
    //选择栏数据
    private void initCategory(){
        ShoppingCategory sc1 = new ShoppingCategory();
        sc1.setName("全部");
        scList.add(sc1);
        ShoppingCategory sc2 = new ShoppingCategory();
        sc2.setName("鞋子");
        scList.add(sc2);
        ShoppingCategory sc3 = new ShoppingCategory();
        sc3.setName("上衣");
        scList.add(sc3);
        ShoppingCategory sc4 = new ShoppingCategory();
        sc4.setName("裤子");
        scList.add(sc4);
        ShoppingCategory sc5 = new ShoppingCategory();
        sc5.setName("包包");
        scList.add(sc5);
        ShoppingCategory sc6 = new ShoppingCategory();
        sc6.setName("外套");
        scList.add(sc6);
        ShoppingCategory sc7 = new ShoppingCategory();
        sc7.setName("书籍");
        scList.add(sc7);
    }
    //按钮点击事件
    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.btn_one_showShopping:
                replaceFragment(new frag_Shopping_shopping());
                break;
            case R.id.btn_two_showShoppingCar:
                replaceFragment(new frag_Shopping_ShowCart());
                break;
            case R.id.btn_three_My:
                replaceFragment(new frag_Shopping_me());
            break;
        }
    }
    public static void stionStart(Context context,String AccountNumber){
        Intent intent = new Intent(context,LoginActivity.class);
        intent.putExtra("AccountNumber",AccountNumber);
        context.startActivity(intent);
    }
    //切换碎片
    public void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.LineLayout_ShowShoppingMessage,fragment);
        transaction.commit();
    }
}
