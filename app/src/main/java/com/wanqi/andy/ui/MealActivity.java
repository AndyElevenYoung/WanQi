package com.wanqi.andy.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.wanqi.andy.R;
import com.wanqi.andy.adapter.MealRecyclerViewAdapter;
import com.wanqi.andy.bean.Meal;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xc
 */
public class MealActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private MealRecyclerViewAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<Meal> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal);
        initData();
        initView();
    }

    private void initData() {
        list = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            int day = (i + 1) * 10;
            int cost = (i + 1) * 30;
            list.add(new Meal(day + "天", cost + "元/" + day + "羽毛"));
        }
    }

    private void initView() {
        mRecyclerView = findViewById(R.id.rv_meal);
        mLayoutManager = new GridLayoutManager(this, 3);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new MealRecyclerViewAdapter(list);
        mRecyclerView.setAdapter(mAdapter);

    }

    public void mealOnClick(View view) {
        switch (view.getId()) {
            case R.id.iv_meal_back:
                finish();
                break;
            default:
                break;
        }
    }
}
