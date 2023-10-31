package com.raghav.simpledragswiperv;

import android.content.Context;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.raghav.library.DragSwipeAdapter;

import java.util.List;


public class RVAdapter extends DragSwipeAdapter<UserModel> {


    public RVAdapter(@NonNull Context context, List<UserModel> datas, int itemLayoutId, RecyclerView recyclerView, boolean isCanDrag, boolean isCanSwipe, int displayMode, int itemSpacing, OnItemClick onItemClickListener) {
        super(context, datas, itemLayoutId, recyclerView, isCanDrag, isCanSwipe, displayMode, itemSpacing, onItemClickListener);
    }

    @Override
    protected void bindView(UserModel item, DragSwipeAdapter.ViewHolder viewHolder) {
        if(item!=null){
            TextView name=(TextView)viewHolder.getView(R.id.textViewName);
            TextView phone=(TextView)viewHolder.getView(R.id.textViewPhone);
            TextView country=(TextView)viewHolder.getView(R.id.textViewCountry);
            name.setText(item.getName());
            phone.setText(item.getPhone());
            country.setText(item.getCountry());
        }
    }

    @Override
    public void onItemSwiped() {

    }

    @Override
    public void onItemMoved() {

    }
}
