package com.wanqi.andy.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wanqi.andy.R;
import com.wanqi.andy.bean.Meal;

import java.util.List;

/**
 * @author xc
 * @date 2018/3/29
 */

public class MealRecyclerViewAdapter extends RecyclerView.Adapter<MealRecyclerViewAdapter.ViewHolder> {

    private List<Meal> mealList;

    public MealRecyclerViewAdapter(List<Meal> mealList) {
        this.mealList = mealList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_meal, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        //将数据填充到具体的view中
        holder.tvValue.setText(mealList.get(position).getValue());
        holder.tvCost.setText(mealList.get(position).getCost());
    }

    @Override
    public int getItemCount() {
        return mealList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvValue;
        private TextView tvCost;
        ViewHolder(View itemView) {
            super(itemView);
            tvValue = itemView.findViewById(R.id.tv_item_meal_value);
            tvCost = itemView.findViewById(R.id.tv_item_meal_cost);
        }
    }
}

