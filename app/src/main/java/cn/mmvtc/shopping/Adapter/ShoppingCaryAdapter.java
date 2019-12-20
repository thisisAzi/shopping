package cn.mmvtc.shopping.Adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import cn.mmvtc.shopping.Fragment.frag_Shopping_ShowCart;
import cn.mmvtc.shopping.MyDatabases.MyDatabases;
import cn.mmvtc.shopping.OnRecyclerItemClickListener.OnRecycleritemClickListener_ShoppingCart;
import cn.mmvtc.shopping.R;
import cn.mmvtc.shopping.ToastUtils.ToaslUtils;
import cn.mmvtc.shopping.bean.Shopping;
import cn.mmvtc.shopping.bean.ShoppingCart;

public class ShoppingCaryAdapter extends RecyclerView.Adapter<ShoppingCaryAdapter.ViewHolder> {
    private List<ShoppingCart>  msCart;
    private static final String TAG = "ShoppingCaryAdapter";
    //声明自定义接口
    private OnRecycleritemClickListener_ShoppingCart sclistener;
    //提供set方法供Activity 或 Fragment使用
    public void setRecyclerItemClickListener(OnRecycleritemClickListener_ShoppingCart onRecycleritemClickListener_shoppingCart){
        sclistener=onRecycleritemClickListener_shoppingCart;
    }
     class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tvItemShoppingCartMessage;
        private TextView tvItemShoppingCartPirce;
         private Button btnDeleteOneShopping;


         public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvItemShoppingCartMessage=(TextView)itemView.findViewById(R.id.tv_item_ShoppingCartMessage);
            tvItemShoppingCartPirce=(TextView)itemView.findViewById(R.id.tv_item_ShoppingCartPirce);
             btnDeleteOneShopping = (Button)itemView. findViewById(R.id.btn_deleteOneShopping);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(sclistener!=null){
                        sclistener.OnRecyclerItemClickListener_shoppingCart(getAdapterPosition(),msCart);
                    }
                }
            });
             btnDeleteOneShopping.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View view) {
                     int position = getAdapterPosition();
                     ShoppingCart sc=msCart.get(position);
                     boolean b = MyDatabases.deleteSimpleUserShoppingCart_(Integer.parseInt(sc.getId()));
                    if(b){
                        msCart.remove(position);
                        frag_Shopping_ShowCart frag_shopping_showCart = new frag_Shopping_ShowCart();
                        frag_shopping_showCart.setShoppingCartNumber(msCart.size());
                        notifyDataSetChanged();

                    }

                 }
             });
        }
    }
    public ShoppingCaryAdapter(List<ShoppingCart> scList){
        msCart=scList;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_item_shoppingcart,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ShoppingCart scCart=msCart.get(position);
        holder.tvItemShoppingCartMessage.setText(scCart.getMessage());
        holder.tvItemShoppingCartPirce.setText(scCart.getPrice());
    }

    @Override
    public int getItemCount() {
        return msCart.size();
    }


}
