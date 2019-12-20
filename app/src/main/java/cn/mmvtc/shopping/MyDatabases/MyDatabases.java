package cn.mmvtc.shopping.MyDatabases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import cn.mmvtc.shopping.MyApplication.MyApplication;
import cn.mmvtc.shopping.bean.ShoppingCart;

public class MyDatabases extends SQLiteOpenHelper {
        private Context Context;
    private static final String TAG = "MyDatabases";
        public static  final String  CREATE_User="create table User("
                +"id integer primary key autoincrement,"
                +"AccountNumber integer,"
                +"password integer"
                + ")";
    private static final String CREATE_Shopping_list="create table ShoppingList("
            +"id integer primary key autoincrement,"
            +"category text,"
            +"image  integer,"
            +"ShoppingMessage text,"
            +"ShoppingPrice integer"+
            ")";
    private static final String CREATE_ShoppingCart_list="create table ShoppingCartList("
            +"id integer primary key autoincrement,"
            +"number integer,"
            +"message text,"
            +"price integer"
            + ")";
    public MyDatabases(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        Context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_User);
        db.execSQL(CREATE_Shopping_list);
        db.execSQL(CREATE_ShoppingCart_list);
       // ToaslUtils.getToast(MyApplication.getContext(),"用户表数据创建成功");
    }
    private static SQLiteDatabase getSQLiteDatabase(){
        MyDatabases myDatabases = new MyDatabases(MyApplication.getContext(), "Shopping.db", null, 2) ;
        SQLiteDatabase db = myDatabases.getWritableDatabase();
        return db;
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("drop table if exists User ");
        db.execSQL("drop table if exists ShoppingList");
        db.execSQL("drop table if exists ShoppingCartList");
        onCreate(db);
    }
    public static long registerUser(int AccountNumber,int password){
        SQLiteDatabase db = getSQLiteDatabase();
        ContentValues values = new ContentValues();
        values.put("AccountNumber",AccountNumber );
        values.put("password",password);
        long user = db.insert("User", null, values);
        return user;
    }
    //查询用户表单条数据  根据用户名   密码查找
    public static boolean selectFindUser(int AccountNumber){
        SQLiteDatabase db = getSQLiteDatabase();
        Cursor cursor = db.query("User", new String[]{"AccountNumber"}, "AccountNumber = ?", new String[]{String.valueOf(AccountNumber)}, null, null, null);
        if(cursor.moveToFirst()){
                return true;
        }
       return false;
    }
    //查询用户表单条数据  根据用户名 密码查找
    public static boolean selectFindUser(int number,int password){
        SQLiteDatabase db = getSQLiteDatabase();
        Cursor cursor = db.query("User", new String[]{"AccountNumber","password"}, "AccountNumber = ? and password=?", new String[]{String.valueOf(number),String.valueOf(password)}, null, null, null);
        if(cursor.moveToFirst()){
            return true;
        }
        return false;
    }
    //添加购物车数据
    public static long insertShoppingCartList(ContentValues values){
        SQLiteDatabase db = getSQLiteDatabase();
        long shoppingCartList = db.insert("ShoppingCartList", null, values);
        if(shoppingCartList>0){
            return shoppingCartList;
        }
        return 0;
    }
    //根据用户名查询用户的购物车记录
    public static List selectShoppingCartList(int number){
        SQLiteDatabase db = getSQLiteDatabase();
        List<ShoppingCart> sclist=new ArrayList<>();
        //  Cursor cursor = db.query("ShoppingCartList", new String[]{"number"}, "number = ?",new String[]{"123"}, null, null, null);
        Cursor cursor = db.query("ShoppingCartList",new String[]{"id,   number,price,message"}, "number like ?",new String[]{String.valueOf(number)}, null, null, null);
        if(cursor!=null){
            if(cursor.moveToFirst()){
                do{
                    ShoppingCart shoppingCart = new ShoppingCart();
                    String id=cursor.getString(cursor.getColumnIndex("id"));
                    String message=cursor.getString(cursor.getColumnIndex("message"));
                    int price=cursor.getInt(cursor.getColumnIndex("price"));
                    shoppingCart.setMessage(message);
                    shoppingCart.setPrice(String.valueOf(price));
                    shoppingCart.setId(id);
                    sclist.add(shoppingCart);
                }while(cursor.moveToNext());

            }
        }
        return sclist;
//        if(cursor!=null){
//            if(cursor.moveToFirst()){
//                do{
//
//                }while(cursor.moveToNext());
//            }
//        }

    }
    //根据用户名清除用户的购物车记录
    public static boolean clearUserShoppingCart(int number){
        SQLiteDatabase db = getSQLiteDatabase();
        int deleteShoppingCartyListItem = db.delete("ShoppingCartList", "number = ?", new String[]{String.valueOf(number)});
        if(deleteShoppingCartyListItem>=1){
            return true;
        }
        return false;
    }
    //根据用户名 和 id 清除用户的购物车记录
    public static boolean deleteSimpleUserShoppingCart_(int id){
        SQLiteDatabase db = getSQLiteDatabase();
        int shoppingCartList = db.delete("ShoppingCartList", "id=?", new String[]{String.valueOf(id)});
        if(shoppingCartList>=1){
            return true;
        }
        return false;
    }
}
