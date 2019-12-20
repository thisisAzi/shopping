package cn.mmvtc.shopping.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cn.mmvtc.shopping.Adapter.ShoppingMessageAdapter;

import cn.mmvtc.shopping.OnRecyclerItemClickListener.OnRecycleritemClickListener_Shopping;
import cn.mmvtc.shopping.R;
import cn.mmvtc.shopping.bean.Shopping;

public class frag_Shopping_shopping extends Fragment {
    View view;
    private RecyclerView rvShowShopping;
    private OnRecycleritemClickListener_Shopping onRecyclerItemClickListener;
    private static final String TAG = "frag_Shopping_shopping";
    private frag_ShoppingMessage f_sm;
    private FragmentManager fm;

    private List<Shopping> trousersShoppingList= new ArrayList<>();
    private List<Shopping> bookShoppingListList= new ArrayList<>();
    private List<Shopping> jacketShoppingList= new ArrayList<>();
    private List<Shopping> loosecaotShoppingList= new ArrayList<>();
    private List<Shopping> shoesShoppingList= new ArrayList<>();
    private List<Shopping> bigShoppingList= new ArrayList<>();
    private List<Shopping>  ShoppingList=new ArrayList<>();
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         fm = getFragmentManager();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_shopping,container,false);

        //添加到商品总集合
//        initShoppingList(loosecaotShoppingList);
//        initShoppingList(bigShoppingList);
//        initShoppingList(bookShoppingListList);
//        initShoppingList(jacketShoppingList);
//        initShoppingList(trousersShoppingList);
        //设置recvclerView控件的布局和适配器
        //如果点击了选择栏目 就传递过来具体选择了哪个栏目 根据栏目添加数据
        Bundle arguments = getArguments();
        if(arguments!=null){
            String Category=arguments.getString("Category");
            switch(Category){
                case "鞋子":
                    initshoesShoppingList();
                    break;
                case "上衣":
                    initJackrtShoppingList();
                    break;
                case "裤子":
                    initTrousersShoppingListList();
                    break;
                case "书籍":
                    initBookShoppingListListList();
                    break;
                case "外套":
                    initLoosecoatShoppingList();
                    break;
                case "包包":
                    initBigShoppingList();
                    break;
                case "全部":
                    initBigShoppingList();
                    initBookShoppingListListList();
                    initJackrtShoppingList();
                    initLoosecoatShoppingList();
                    initTrousersShoppingListList();
                    initshoesShoppingList();
                    break;
            }
        }else{
            initBigShoppingList();
            initBookShoppingListListList();
            initJackrtShoppingList();
            initLoosecoatShoppingList();
            initTrousersShoppingListList();
            initshoesShoppingList();
            Collections.shuffle(ShoppingList);
        }
        refresh();
        return view;
    }

    public void refresh(){
        rvShowShopping = (RecyclerView) view.findViewById(R.id.rv_showShopping);
        ShoppingMessageAdapter ShoppingMessageAdapter= new ShoppingMessageAdapter(ShoppingList);
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        rvShowShopping.setAdapter(ShoppingMessageAdapter);
        rvShowShopping.setLayoutManager(staggeredGridLayoutManager);
        ShoppingMessageAdapter.setRecyclerItemClickListener(new OnRecycleritemClickListener_Shopping() {
            @Override
            public void OnRecyclerItemClickListener_shopping(int Position, List<Shopping> slist) {
                Shopping s=slist.get(Position);
                int imageSrc=s.getImageSrc();
                String Message=s.getMessage();
                String Price=s.getPrice();
                replaceFragment(new frag_ShoppingMessage());
                // showFragment(new frag_Shopping_shopping(),new frag_ShoppingMessage());
                Bundle bundle = new Bundle();
                bundle.putInt("imageSrc",imageSrc);
                bundle.putString("Message",Message);
                bundle.putString("Price",Price);
                FragmentTransaction transaction = fm.beginTransaction();
                frag_ShoppingMessage frag_shoppingMessage = new frag_ShoppingMessage();
                frag_shoppingMessage.setArguments(bundle);
                transaction.replace(R.id.LineLayout_ShowShoppingMessage,frag_shoppingMessage);
                transaction.addToBackStack(null);
                transaction.commit();
            }


        });


    }
    //初始化裤子商品数据
    private void initTrousersShoppingListList(){
        Shopping trousersShopping_one = new Shopping();
        trousersShopping_one.setCategory("裤子");
        trousersShopping_one.setImageSrc(R.mipmap.trousers_one);
        trousersShopping_one.setMessage("秋冬季牛仔裤男潮牌小脚宽松大码裤子弹力潮 ");
        trousersShopping_one.setPrice("98");
        ShoppingList.add(trousersShopping_one);
        Shopping trousersShopping_two = new Shopping();
        trousersShopping_two.setCategory("裤子");
        trousersShopping_two.setImageSrc(R.mipmap.trousers_two );
        trousersShopping_two.setMessage("嘿马七作日系复古束脚休闲收口小脚潮工装裤");
        trousersShopping_two.setPrice("168");
        ShoppingList.add(trousersShopping_two);
        Shopping trousersShopping_three = new Shopping();
        trousersShopping_three.setCategory("裤子");
        trousersShopping_three.setImageSrc(R.mipmap.trousers_three);
        trousersShopping_three.setMessage("男士休闲裤秋冬款男西裤宽松直筒加绒加厚商务男裤中年秋季长裤   ");
        trousersShopping_three.setPrice("184");
        ShoppingList.add(trousersShopping_three);
        Shopping trousersShopping_four = new Shopping();
        trousersShopping_four.setCategory("裤子");
        trousersShopping_four.setImageSrc(R.mipmap.trousers_four);
        trousersShopping_four.setMessage("加绒高腰牛仔裤女2019秋冬季新款显瘦宽松哈伦萝卜    ");
        trousersShopping_four.setPrice("40");
        ShoppingList.add(trousersShopping_four);
        Shopping trousersShopping_five = new Shopping();
        trousersShopping_five.setCategory("裤子");
        trousersShopping_five.setImageSrc(R.mipmap.trousers_fir);
        trousersShopping_five.setMessage("枫叶印花套头短袖T恤男士加大码宽松半袖胖子潮牌    ");
        trousersShopping_five.setPrice("70");
        ShoppingList.add(trousersShopping_five);
        Shopping trousersShopping_six = new Shopping();
        trousersShopping_six.setCategory("裤子");
        trousersShopping_six.setImageSrc(R.mipmap.trousers_six);
        trousersShopping_six.setMessage("潮牌INS国潮休闲裤多口袋直筒韩版长裤   ");
        trousersShopping_six.setPrice("79");
        ShoppingList.add(trousersShopping_six);
        Shopping trousersShopping_seven = new Shopping();
        trousersShopping_seven.setCategory("裤子");
        trousersShopping_seven.setImageSrc(R.mipmap.trousers_seven);
        trousersShopping_seven.setMessage("装 防风弹力修身无褶长裤 418919 优衣库UNIQLO  ");
        trousersShopping_seven.setPrice("128");
        ShoppingList.add(trousersShopping_seven);
        Shopping trousersShopping_eig = new Shopping();
        trousersShopping_eig.setCategory("裤子");
        trousersShopping_eig.setImageSrc(R.mipmap.trousers_eig);
        trousersShopping_eig.setMessage("装秋冬加绒加厚休闲裤    ");
        trousersShopping_eig.setPrice("19");
        ShoppingList.add(trousersShopping_eig);
        Shopping trousersShopping_nine = new Shopping();
        trousersShopping_nine.setCategory("裤子");
        trousersShopping_nine.setImageSrc(R.mipmap.trousers_nine);
        trousersShopping_nine.setMessage("士休闲宽松直筒小脚破洞韩版潮流长裤  ");
        trousersShopping_nine.setPrice("122");
        ShoppingList.add(trousersShopping_nine);
        Shopping trousersShopping_ten = new Shopping();
        trousersShopping_ten.setCategory("裤子");
        trousersShopping_ten.setImageSrc(R.mipmap.trouser_tent);
        trousersShopping_ten.setMessage("装 EZY束脚工装运动裤 420806 优衣库UNIQLO  ");
        trousersShopping_ten.setPrice("180");
        ShoppingList.add(trousersShopping_ten);
    }
    //初始化包包商品信息
    private void initBigShoppingList(){
        Shopping bigShopping_one = new Shopping();
        bigShopping_one.setCategory("包包");
        bigShopping_one.setImageSrc(R.mipmap.big_one);
        bigShopping_one.setMessage("真皮斜挎包牛皮竖款商务休闲小背包 ");
        bigShopping_one.setPrice("29");
        ShoppingList.add(bigShopping_one);
        Shopping bookShopping_two = new Shopping();
        bookShopping_two.setCategory("包包");
        bookShopping_two.setImageSrc(R.mipmap.big_two );
        bookShopping_two.setMessage("单肩包休闲防水牛津布斜挎包");
        bookShopping_two.setPrice("118");
        ShoppingList.add(bookShopping_two);
        Shopping bookShopping_three = new Shopping();
        bookShopping_three.setCategory("包包");
        bookShopping_three.setImageSrc(R.mipmap.bgg_three);
        bookShopping_three.setMessage("潮牌胸包新款街头跨休闲多功能小背包时尚单肩   ");
        bookShopping_three.setPrice("94");
        ShoppingList.add(bookShopping_three);
        Shopping bookShopping_four = new Shopping();
        bookShopping_four.setCategory("包包");
        bookShopping_four.setImageSrc(R.mipmap.big_four);
        bookShopping_four.setMessage("加绒高腰牛仔裤女2019秋冬季新款显瘦宽松哈伦萝卜    ");
        bookShopping_four.setPrice("40");
        ShoppingList.add(bookShopping_four);
        Shopping bookShopping_five = new Shopping();
        bookShopping_five.setCategory("包包");
        bookShopping_five.setImageSrc(R.mipmap.big_five);
        bookShopping_five.setMessage("士斜挎包潮牌ins小挎包女背包日系邮差帆布单肩包休闲学生    ");
        bookShopping_five.setPrice("56");
        ShoppingList.add(bookShopping_five);
        Shopping bookShopping_six = new Shopping();
        bookShopping_six.setCategory("包包");
        bookShopping_six.setImageSrc(R.mipmap.big_six);
        bookShopping_six.setMessage("牛皮包商务休闲小背包潮牌   ");
        bookShopping_six.setPrice("69");
        ShoppingList.add(bookShopping_six);
        Shopping bookShopping_seven = new Shopping();
        bookShopping_seven.setCategory("包包");
        bookShopping_seven.setImageSrc(R.mipmap.big_seven);
        bookShopping_seven.setMessage("Subtle AVANT胸包单肩斜跨包欧美休闲   ");
        bookShopping_seven.setPrice("96");
        ShoppingList.add(bookShopping_seven);
        Shopping bookShopping_eig = new Shopping();
        bookShopping_eig.setCategory("包包");
        bookShopping_eig.setImageSrc(R.mipmap.big_eig);
        bookShopping_eig.setMessage("士斜挎包大容量横款帆布包背包    ");
        bookShopping_eig.setPrice("60");
        ShoppingList.add(bookShopping_eig);
        Shopping bookShopping_nine = new Shopping();
        bookShopping_nine.setCategory("包包");
        bookShopping_nine.setImageSrc(R.mipmap.big_nine);
        bookShopping_nine.setMessage("背包休闲尼龙牛津布挎包潮牌 ");
        bookShopping_nine.setPrice("142");
        ShoppingList.add(bookShopping_nine);
        Shopping bookShopping_ten = new Shopping();
        bookShopping_ten.setCategory("包包");
        bookShopping_ten.setImageSrc(R.mipmap.big_ten);
        bookShopping_ten.setMessage("韩版时尚潮牌小背包休闲男包2019新款  ");
        bookShopping_ten.setPrice("180");
        ShoppingList.add(bookShopping_ten);
    }
    //初始化书籍商品信息
    private void initBookShoppingListListList(){
        Shopping bookShopping_one = new Shopping();
        bookShopping_one.setCategory("书籍");
        bookShopping_one.setImageSrc(R.mipmap.book_one);
        bookShopping_one.setMessage("学习黄金组合套装零基础学精彩编程200例   ");
        bookShopping_one.setPrice("55");
        ShoppingList.add(bookShopping_one);
        Shopping bookShopping_two = new Shopping();
        bookShopping_two.setCategory("书籍");
        bookShopping_two.setImageSrc(R.mipmap.book_two);
        bookShopping_two.setMessage("虚拟机:jvm高级特性与实践第3版计算机软件与程序设计华章原创精品周志明JDK新版本全面升级");
        bookShopping_two.setPrice("89");
        ShoppingList.add(bookShopping_two);
        Shopping bookShopping_three = new Shopping();
        bookShopping_three.setCategory("书籍");
        bookShopping_three.setImageSrc(R.mipmap.book_three);
        bookShopping_three.setMessage("核心技术卷II高级特性（原书第10版）卷12一二从入门到精通自学计算机程序开发教程编程思想书籍   ");
        bookShopping_three.setPrice("124");
        ShoppingList.add(bookShopping_three);
        Shopping bookShopping_four = new Shopping();
        bookShopping_four.setCategory("书籍");
        bookShopping_four.setImageSrc(R.mipmap.book_four);
        bookShopping_four.setMessage("从零基础到项目实践 程序开发设计网站编程    ");
        bookShopping_four.setPrice("40");
        ShoppingList.add(bookShopping_four);
        Shopping bookShopping_five = new Shopping();
        bookShopping_five.setCategory("书籍");
        bookShopping_five.setImageSrc(R.mipmap.book_five);
        bookShopping_five.setMessage("从入门到精通 第5版 明日科技 项目实战视频教程书Php      ");
        bookShopping_five.setPrice("55");
        ShoppingList.add(bookShopping_five);
        Shopping bookShopping_six = new Shopping();
        bookShopping_six.setCategory("书籍");
        bookShopping_six.setImageSrc(R.mipmap.book_six);
        bookShopping_six.setMessage("编程开发APP应用入门到精通基础教程从入门到精通   ");
        bookShopping_six.setPrice("299");
        ShoppingList.add(bookShopping_six);
        Shopping bookShopping_seven = new Shopping();
        bookShopping_seven.setCategory("书籍");
        bookShopping_seven.setImageSrc(R.mipmap.book_seven);
        bookShopping_seven.setMessage(" 从入门到项目实践（超值版）（软件开发魔典） 程序设计 计算机 ¥80  ");
        bookShopping_seven.setPrice("80");
        ShoppingList.add(bookShopping_seven);
        Shopping bookShopping_eig = new Shopping();
        bookShopping_eig.setCategory("书籍");
        bookShopping_eig.setImageSrc(R.mipmap.book_eig);
        bookShopping_eig.setMessage("第一行代码从入门到精通 安卓移动开发教程     ");
        bookShopping_eig.setPrice("40");
        ShoppingList.add(bookShopping_eig);
        Shopping bookShopping_nine = new Shopping();
        bookShopping_nine.setCategory("书籍");
        bookShopping_nine.setImageSrc(R.mipmap.book_nine);
        bookShopping_nine.setMessage("Studio开发实战 从零基础到App上线 第2版 编程   ");
        bookShopping_nine.setPrice("90");
        ShoppingList.add(bookShopping_nine);
        Shopping bookShopping_ten = new Shopping();
        bookShopping_ten.setCategory("书籍");
        bookShopping_ten.setImageSrc(R.mipmap.book_ten);
        bookShopping_ten.setMessage("从入门到精通 明日科技零基础学 项目开发实战入门精彩编程200例 studio开发安卓籍 APP开发视频教程   ");
        bookShopping_ten.setPrice("143");
        ShoppingList.add(bookShopping_ten);
    }
    //初始化外套商品信息
    private void initLoosecoatShoppingList(){
        Shopping loosecoatShopping_one = new Shopping();
        loosecoatShopping_one.setCategory("外套");
        loosecoatShopping_one.setImageSrc(R.mipmap.jacket_one);
        loosecoatShopping_one.setMessage("进口天鹅绒级貂皮大衣款整貂连帽海宁水貂皮草男  ");
        loosecoatShopping_one.setPrice("449");
        ShoppingList.add(loosecoatShopping_one);
        Shopping loosecoatShopping_two = new Shopping();
        loosecoatShopping_two.setCategory("外套");
        loosecoatShopping_two.setImageSrc(R.mipmap.jacket_two );
        loosecoatShopping_two.setMessage("中长款2019新款韩版修身帅气潮流爆款冬季加厚保暖男");
        loosecoatShopping_two.setPrice("1022");
        ShoppingList.add(loosecoatShopping_two);
        Shopping loosecoatShopping_three = new Shopping();
        loosecoatShopping_three.setCategory("外套");
        loosecoatShopping_three.setImageSrc(R.mipmap.jacket_three);
        loosecoatShopping_three.setMessage("大码加厚双面羊绒大衣士中长款羊驼绒阿尔巴卡羊毛呢子   ");
        loosecoatShopping_three.setPrice("394");
        ShoppingList.add(loosecoatShopping_three);
        Shopping loosecoatShopping_four = new Shopping();
        loosecoatShopping_four.setCategory("外套");
        loosecoatShopping_four.setImageSrc(R.mipmap.jacket_four);
        loosecoatShopping_four.setMessage("雅鹿羽绒服中长款冬季防寒保暖青年休闲毛领羽绒服潮    ");
        loosecoatShopping_four.setPrice("740");
        ShoppingList.add(loosecoatShopping_four);
        Shopping loosecoatShopping_five = new Shopping();
        loosecoatShopping_five.setCategory("外套");
        loosecoatShopping_five.setImageSrc(R.mipmap.jacket_five);
        loosecoatShopping_five.setMessage("吉普盾2019冬季新款士工装加厚情侣羽绒服中长款连帽外套男     ");
        loosecoatShopping_five.setPrice("906");
        ShoppingList.add(loosecoatShopping_five);
        Shopping loosecoatShopping_six = new Shopping();
        loosecoatShopping_six.setCategory("外套");
        loosecoatShopping_six.setImageSrc(R.mipmap.jacket_six);
        loosecoatShopping_six.setMessage("中长款修身帅气潮派克服羽绒服男   ");
        loosecoatShopping_six.setPrice("679");
        ShoppingList.add(loosecoatShopping_six);
        Shopping loosecoatShopping_seven = new Shopping();
        loosecoatShopping_seven.setCategory("外套");
        loosecoatShopping_seven.setImageSrc(R.mipmap.jacket_seven);
        loosecoatShopping_seven.setMessage("伯希和户外冲锋衣 女潮牌三合一秋冬加绒加  ");
        loosecoatShopping_seven.setPrice("672");
        ShoppingList.add(loosecoatShopping_seven);
        Shopping loosecoatShopping_eig = new Shopping();
        loosecoatShopping_eig.setCategory("外套");
        loosecoatShopping_eig.setImageSrc(R.mipmap.jacket_eig);
        loosecoatShopping_eig.setMessage("恒源祥2019新款羽绒服士中长款高品质白鸭绒保暖男     ");
        loosecoatShopping_eig.setPrice("288");
        ShoppingList.add(loosecoatShopping_eig);
        Shopping loosecoatShopping_nine = new Shopping();
        loosecoatShopping_nine.setCategory("外套");
        loosecoatShopping_nine.setImageSrc(R.mipmap.jacket_nine);
        loosecoatShopping_nine.setMessage("进口貂皮内胆派克服新款整貂原色貂皮草中长款连帽貂大衣 ");
        loosecoatShopping_nine.setPrice("262");
        ShoppingList.add(loosecoatShopping_nine);
        Shopping loosecoatShopping_ten = new Shopping();
        loosecoatShopping_ten.setCategory("外套");
        loosecoatShopping_ten.setImageSrc(R.mipmap.jacket_ten);
        loosecoatShopping_ten.setMessage("中老年人羽绒服加厚中长款防寒服脱卸内胆爸爸冬装新款大码  ");
        loosecoatShopping_ten.setPrice("900");
        ShoppingList.add(loosecoatShopping_ten);
    }
    //初始化鞋子商品信息
    private void initshoesShoppingList(){
        Shopping shoesShopping_one = new Shopping();
        shoesShopping_one.setCategory("鞋子");
        shoesShopping_one.setImageSrc(R.mipmap.shoes_one);
        shoesShopping_one.setMessage("正品匡1970S高低帮帆布鞋女鞋男经典款三星标  ");
        shoesShopping_one.setPrice("168");
        ShoppingList.add(shoesShopping_one);
        Shopping shoesShopping_two = new Shopping();
        shoesShopping_two.setCategory("鞋子");
        shoesShopping_two.setImageSrc(R.mipmap.shoes_two);
        shoesShopping_two.setMessage("匡威官方 经典款 休闲男女鞋 情侣鞋 101010");
        shoesShopping_two.setPrice("432");
        ShoppingList.add(shoesShopping_two);
        Shopping shoesShopping_three = new Shopping();
        shoesShopping_three.setCategory("鞋子");
        shoesShopping_three.setImageSrc(R.mipmap.shoes_three);
        shoesShopping_three.setMessage("高筒休闲板鞋 166516C/167278C   ");
        shoesShopping_three.setPrice("228");
        ShoppingList.add(shoesShopping_three);
        Shopping shoesShopping_four = new Shopping();
        shoesShopping_four.setCategory("鞋子");
        shoesShopping_four.setImageSrc(R.mipmap.shoes_four);
        shoesShopping_four.setMessage("官方 Converse x JWA Runstar Hybrid 高帮 164665C    ");
        shoesShopping_four.setPrice("338");
        ShoppingList.add(shoesShopping_four);
        Shopping shoesShopping_five = new Shopping();
        shoesShopping_five.setCategory("鞋子");
        shoesShopping_five.setImageSrc(R.mipmap.shoes_five);
        shoesShopping_five.setMessage("AllStar新款男女情侣百搭休闲帆布鞋    ");
        shoesShopping_five.setPrice("999");
        ShoppingList.add(shoesShopping_five);
        Shopping shoesShopping_six = new Shopping();
        shoesShopping_six.setCategory("鞋子");
        shoesShopping_six.setImageSrc(R.mipmap.shoes_six);
        shoesShopping_six.setMessage("官方NIKE AIR MAX 270 REACT男子运动鞋AO4971   ");
        shoesShopping_six.setPrice("1000");
        ShoppingList.add(shoesShopping_six);
        Shopping shoesShopping_seven = new Shopping();
        shoesShopping_seven.setCategory("鞋子");
        shoesShopping_seven.setImageSrc(R.mipmap.shoes_seven);
        shoesShopping_seven.setMessage("跑步鞋男鞋2020新款星耀减震回弹跑鞋男士低帮运动鞋 ");
        shoesShopping_seven.setPrice("899");
        ShoppingList.add(shoesShopping_seven);
        Shopping shoesShopping_eig = new Shopping();
        shoesShopping_eig.setCategory("鞋子");
        shoesShopping_eig.setImageSrc(R.mipmap.shoes_eig);
        shoesShopping_eig.setMessage("官方NIKE TANJUN男子运动鞋休闲鞋812654      ");
        shoesShopping_eig.setPrice("1018");
        ShoppingList.add(shoesShopping_eig);
        Shopping shoesShopping_nine = new Shopping();
        shoesShopping_nine.setCategory("鞋子");
        shoesShopping_nine.setImageSrc(R.mipmap.shoes_nine);
        shoesShopping_nine.setMessage("纽约时装周走秀同款重燃ACE 2020女鞋时尚中帮运动鞋888 ");
        shoesShopping_nine.setPrice("262");
        ShoppingList.add(shoesShopping_nine);
        Shopping shoesShopping_ten = new Shopping();
        shoesShopping_ten.setCategory("鞋子");
        shoesShopping_ten.setImageSrc(R.mipmap.shoes_nine);
        shoesShopping_ten.setMessage("男鞋跑步鞋2019冬季新款减震跑鞋秋季断码休闲鞋超轻运动鞋子  ");
        shoesShopping_ten.setPrice("520");
        ShoppingList.add(shoesShopping_ten);
    }
    //初始化上衣商品信息
    private void initJackrtShoppingList() {
        Shopping jackrtShopping_one = new Shopping();
        jackrtShopping_one.setCategory("上衣");
        jackrtShopping_one.setImageSrc(R.mipmap.jacket_one);
        jackrtShopping_one.setMessage("小熊卫衣女圆领原创印花可爱   ");
        jackrtShopping_one.setPrice("170");
        ShoppingList.add(jackrtShopping_one);
        Shopping jackrtShopping_two = new Shopping();
        jackrtShopping_two.setCategory("上衣");
        jackrtShopping_two.setImageSrc(R.mipmap.jacket_two );
        jackrtShopping_two.setMessage("森马外套男装秋季2019新款男青少年黑色夹克男士");
        jackrtShopping_two.setPrice("140");
        ShoppingList.add(jackrtShopping_two);
        Shopping shopping_three = new Shopping();
        shopping_three.setCategory("上衣");
        shopping_three.setImageSrc(R.mipmap.jacket_three);
        shopping_three.setMessage("宽松女士毛衣外套女装秋冬装2019新款潮针织开衫百搭   ");
        shopping_three.setPrice("89");
        ShoppingList.add(shopping_three);
        Shopping jackrtShopping_four = new Shopping();
        jackrtShopping_four.setCategory("上衣");
        jackrtShopping_four.setImageSrc(R.mipmap.jacket_four);
        jackrtShopping_four.setMessage("潮流ins复古纯色短袖t恤男宽松短袖oversize纯棉T恤学生    ");
        jackrtShopping_four.setPrice("30");
        ShoppingList.add(jackrtShopping_four);
        Shopping jackrtShopping_five = new Shopping();
        jackrtShopping_five.setCategory("上衣");
        jackrtShopping_five.setImageSrc(R.mipmap.jacket_five);
        jackrtShopping_five.setMessage("枫叶印花套头短袖T恤男士加大码宽松半袖胖子潮牌     ");
        jackrtShopping_five.setPrice("70");
        ShoppingList.add(jackrtShopping_five);
        Shopping jackrtShopping_six = new Shopping();
        jackrtShopping_six.setCategory("上衣");
        jackrtShopping_six.setImageSrc(R.mipmap.jacket_six);
        jackrtShopping_six.setMessage("个性百搭长袖t恤男潮ins学生宽松休闲港风情侣潮流纯色   ");
        jackrtShopping_six.setPrice("59");
        ShoppingList.add(jackrtShopping_six);
        Shopping jackrtShopping_seven = new Shopping();
        jackrtShopping_seven.setCategory("上衣");
        jackrtShopping_seven.setImageSrc(R.mipmap.jacket_seven);
        jackrtShopping_seven.setMessage("危机青年 简约基础款假两件卫衣男 女潮牌三合一秋冬加绒加  ");
        jackrtShopping_seven.setPrice("107");
        ShoppingList.add(jackrtShopping_seven);
        Shopping jackrtShopping_eig = new Shopping();
        jackrtShopping_eig.setCategory("上衣");
        jackrtShopping_eig.setImageSrc(R.mipmap.jacket_eig);
        jackrtShopping_eig.setMessage("复古加厚慵懒宽松针织打底衫百搭气质显瘦     ");
        jackrtShopping_eig.setPrice("89");
        ShoppingList.add(jackrtShopping_eig);
        Shopping jackrtShopping_nine = new Shopping();
        jackrtShopping_nine.setCategory("上衣");
        jackrtShopping_nine.setImageSrc(R.mipmap.jacket_nine);
        jackrtShopping_nine.setMessage("森马长袖衬衫男秋季纯棉条纹白衬衣男士商务休闲宽松港风 ");
        jackrtShopping_nine.setPrice("119");
        ShoppingList.add(jackrtShopping_nine);
        Shopping jackrtShopping_ten = new Shopping();
        jackrtShopping_ten.setCategory("上衣");
        jackrtShopping_ten.setImageSrc(R.mipmap.jacket_ten);
        jackrtShopping_ten.setMessage("摩调oversize卫衣男潮2019秋季连帽潮牌嘻哈外套印花宽松情侣  ");
        jackrtShopping_ten.setPrice("180");
        ShoppingList.add(jackrtShopping_ten);
    }


    //切换碎片
    public void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.LineLayout_ShowShoppingMessage,fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
    //从碎片1跳到碎片2
    private void showFragment(Fragment fragment1,Fragment fragment2){
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        //如果碎片2没有被添加
        if(!fragment2.isAdded()){
            //加入返回栈，点击返回就回到碎片1
            transaction.add(R.id.LineLayout_ShowShoppingMessage,fragment2)
                    .addToBackStack(null)
                    //提交事务
                    .commit();
        } else{
            //已经添加过的话就隐藏碎片1 显示碎片2  并加入返回栈 提交事务
            transaction.hide(fragment1).show(fragment2).addToBackStack(null).commit();
        }
        //开启碎片

    }
}
