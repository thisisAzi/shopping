package cn.mmvtc.shopping.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import cn.mmvtc.shopping.MyApplication.MyApplication;
import cn.mmvtc.shopping.OnRecyclerItemClickListener.OnRecyclerItemClickListener_shoppingCategory;
import cn.mmvtc.shopping.R;
import cn.mmvtc.shopping.bean.ShoppingCategory;

public class ShoppingCategoryAdapter extends RecyclerView.Adapter<ShoppingCategoryAdapter.ViewHolder> {
    private List<ShoppingCategory> mscList;
    //声明自定义的监听接口
    private OnRecyclerItemClickListener_shoppingCategory onRecyclerItemClickListener;
    //提供set方法供Activity或Fragment调用
    public void setRecyclerItemClickListener(OnRecyclerItemClickListener_shoppingCategory listener){
        onRecyclerItemClickListener=listener;
    }
    public ShoppingCategoryAdapter(List<ShoppingCategory> sclist){
        mscList=sclist;
    }
    class ViewHolder extends RecyclerView.ViewHolder{
        TextView shoppingCategory;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            shoppingCategory=(TextView)itemView.findViewById(R.id.rv_item_showCategory);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(onRecyclerItemClickListener!=null){
                        onRecyclerItemClickListener.OnRecyclerItemClickListener_shoppingCategory(getAdapterPosition(),mscList);
                    }
                }
            });

        }
    }


    @NonNull
    @Override
    public ShoppingCategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_item_category,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ShoppingCategoryAdapter.ViewHolder holder, int position) {
        ShoppingCategory sc= mscList.get(position);
        holder.shoppingCategory.setText(sc.getName());
    }

    @Override
    public int getItemCount() {
        return mscList.size();
    }
}
