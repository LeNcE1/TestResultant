package com.lence.testresultant;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lence.testresultant.model.StocksModel;


import butterknife.BindView;
import butterknife.ButterKnife;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    StocksModel mStocksModel;
    Presenter mPresenter;
    Context mContext;

    public Adapter(StocksModel stocksModel, Presenter presenter, Context context) {
        mStocksModel = stocksModel;
        mPresenter = presenter;
        mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item, parent, false);


        return new ViewHolder(itemView);
    }

    @SuppressLint("DefaultLocale")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.name.setText((mStocksModel.getStock().get(position).getName()+""));
        holder.volume.setText((mStocksModel.getStock().get(position).getVolume()+""));
        holder.amount.setText(String.format("%.2f", (mStocksModel.getStock().get(position).getPrice().getAmount())));
    }

    @Override
    public int getItemCount() {
        return mStocksModel.getStock().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.name)
        TextView name;
        @BindView(R.id.volume)
        TextView volume;
        @BindView(R.id.amount)
        TextView amount;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
