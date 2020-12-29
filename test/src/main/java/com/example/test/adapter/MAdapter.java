package com.example.test.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test.R;
import com.example.test.api.App;
import com.example.test.bean.InfoBean;

import java.util.ArrayList;

public class MAdapter extends RecyclerView.Adapter<MAdapter.VH> {
    private Context context;
    private ArrayList<InfoBean.DataBean> lists;
    private MyLis myLis;

    public void setMyLis(MyLis myLis) {
        this.myLis = myLis;
    }

    public MAdapter(Context context, ArrayList<InfoBean.DataBean> lists) {
        this.context = context;
        this.lists = lists;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_rlv, null);
        return new VH(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull final VH holder, final int position) {
        holder.name.setText(lists.get(position).getName());
        holder.cb.setChecked(lists.get(position).isSelect());
        //在监听中，如果点击了则更改值为true
        holder.cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    lists.get(position).setSel(true);
                }else{
                    lists.get(position).setSel(false);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return lists.size();
    }

    public class VH extends RecyclerView.ViewHolder {

        private final TextView name;
        private final CheckBox cb;

        public VH(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            cb = itemView.findViewById(R.id.cb);
        }
    }
    public interface MyLis{
        void setOnClick(int position);
    }
}
