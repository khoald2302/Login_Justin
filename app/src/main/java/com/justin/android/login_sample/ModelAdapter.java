package com.justin.android.login_sample;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by PC on 7/18/2016.
 */
public class ModelAdapter  extends RecyclerView.Adapter<ModelAdapter.MyViewHolder>{

    private Context mContext;
    private List<Model> modelList;

    public ModelAdapter(Context mContext, List<Model> modelList) {
        this.mContext = mContext;
        this.modelList = modelList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list,parent,false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Model model = modelList.get(position);
         holder.Hoten.setText(model.getHoten());
         holder.Diachi.setText(model.getDiachi());
         holder.Sdt.setText(model.getSdt());
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView Hoten,Sdt,Diachi;

        public MyViewHolder(View itemView) {
            super(itemView);
            Hoten=(TextView)itemView.findViewById(R.id.tv_name);
            Sdt=(TextView)itemView.findViewById(R.id.tv_sdt);
            Diachi=(TextView)itemView.findViewById(R.id.tv_diachi);
        }
    }
}
