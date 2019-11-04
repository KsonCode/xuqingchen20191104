package com.bwie.xuqingchen20191104.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bwie.xuqingchen20191104.R;
import com.bwie.xuqingchen20191104.ScanActivity;
import com.bwie.xuqingchen20191104.bean.HomeBean;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.VH> {

    private Context context;
    private List<HomeBean.ResultBean.RxxpBean.ListBean> list;
    public HomeAdapter(Context context, List<HomeBean.ResultBean.RxxpBean.ListBean> list){
        this.context = context;
        this.list = list;
    }
    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.home_item_layout,null);
        VH vh = new VH(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, final int position) {

        holder.textView.setText(list.get(position).commodityName);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EventBus.getDefault().postSticky(list.get(position));

               context.startActivity(new Intent(context, ScanActivity.class));

            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }



    class VH extends RecyclerView.ViewHolder{

        @BindView(R.id.title)
        TextView textView;
        public VH(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

//    interface Click
}
