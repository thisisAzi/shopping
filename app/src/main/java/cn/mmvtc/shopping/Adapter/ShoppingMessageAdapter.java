package cn.mmvtc.shopping.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


import cn.mmvtc.shopping.OnRecyclerItemClickListener.OnRecycleritemClickListener_Shopping;
import cn.mmvtc.shopping.R;
import cn.mmvtc.shopping.bean.Shopping;

public class ShoppingMessageAdapter  extends RecyclerView.Adapter<ShoppingMessageAdapter.ViewHolder> {
    private List<Shopping> mshoppinglist;
    //声明自定义的监听接口
    private OnRecycleritemClickListener_Shopping onRecyclerItemClickListener;
    //提供set 方法供Activity或Fragment调用
    public void setRecyclerItemClickListener(OnRecycleritemClickListener_Shopping listener){
        onRecyclerItemClickListener=listener;

    }

    public ShoppingMessageAdapter(List<Shopping> shoppingList){
        mshoppinglist=shoppingList;
    }
         class ViewHolder extends  RecyclerView.ViewHolder{
            ImageView mShoppingImage;
            TextView mShoppingMessage;
            TextView mShoppingprice;
            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                mShoppingImage=(ImageView)itemView.findViewById(R.id.iv_item_ShoppingImage);
                mShoppingMessage=(TextView)itemView.findViewById(R.id.tv_item_ShoppingMessage);
                mShoppingprice=(TextView)itemView.findViewById(R.id.tv_item_ShoppingPrice);
                //将监听传递给自定义接口
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(onRecyclerItemClickListener!=null){
                            onRecyclerItemClickListener.OnRecyclerItemClickListener_shopping(getAdapterPosition(),mshoppinglist);
                        }
                    }
                });
            }
        }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_item_shopping,parent,false);
            final ViewHolder viewHolder = new ViewHolder(view);
            return viewHolder;
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Shopping shopping=mshoppinglist.get(position);
        holder.mShoppingImage.setImageResource(shopping.getImageSrc());
        holder.mShoppingMessage.setText(shopping.getMessage());
        holder.mShoppingprice.setText(shopping.getPrice());
    }

    @Override
    public int getItemCount() {
        return mshoppinglist.size();
    }
}
