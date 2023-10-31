package com.raghav.library;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class DragSwipeAdapter<T>
        extends RecyclerView.Adapter<DragSwipeAdapter.ViewHolder>
        implements ItemTouchHelperAdapter{

    public static final int HORIZONTAL = 0;
    public static final int VERTICAL = 1;
    public static final int GRID = 2;

    private List<T> items;
    private Context context;
    private OnItemClick<T> listener;
    private int layoutId;

    public interface OnItemClick<T> {
        void onClick(View view, int position, T item);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private Map<Integer, View> views;

        public ViewHolder(View view, OnItemClick listener) {
            super(view);
            views = new HashMap<>();
            views.put(0, view);

            if (listener != null)
                view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (listener != null)
                listener.onClick(view, getAdapterPosition(), getItem(getAdapterPosition()));
        }

        public void initViewList(int[] idList) {
            for (int id : idList)
                initViewById(id);
        }

        public void initViewById(int id) {
            View view = (getView() != null ? getView().findViewById(id) : null);

            if (view != null)
                views.put(id, view);
        }

        public View getView() {
            return getView(0);
        }

        public View getView(int id) {
            if (views.containsKey(id))
                return views.get(id);
            else
                initViewById(id);

            return views.get(id);
        }
    }

//    protected abstract View createView(Context context, ViewGroup viewGroup, int viewType);

    protected abstract void bindView(T item, DragSwipeAdapter.ViewHolder viewHolder);


    public DragSwipeAdapter(@NonNull Context context, List<T> datas, int itemLayoutId,
                            RecyclerView recyclerView,
                            boolean isCanDrag, boolean isCanSwipe, int displayMode,
                            int itemSpacing, OnItemClick onItemClickListener) {
        this.context=context;
        this.items =datas;
        this.listener=onItemClickListener;
        this.layoutId=itemLayoutId;

        recyclerView.setAdapter(this);

        ItemTouchHelperCallback helperCallback=new ItemTouchHelperCallback(this);
        helperCallback.setSwipeEnable(isCanSwipe);
        helperCallback.setDragEnable(isCanDrag);
        ItemTouchHelper itemTouchHelper=new ItemTouchHelper(helperCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
        recyclerView.addItemDecoration(new EqualSpacingItemDecoration(itemSpacing, displayMode)); // 16px. In practice, you'll want to use getDimensionPixelSize
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(layoutId, viewGroup, false);
        ViewHolder holder = new ViewHolder(view,listener);
        return holder;
    }

    @Override
    public void onBindViewHolder(DragSwipeAdapter.ViewHolder holder, int position) {
        bindView(getItem(position), holder);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public T getItem(int index) {
        return ((items != null && index < items.size()) ? items.get(index) : null);
    }

    public Context getContext() {
        return context;
    }

    public void setList(List<T> list) {
        items = list;
    }

    public List<T> getList() {
        return items;
    }

    public void setClickListener(OnItemClick listener) {
        this.listener = listener;
    }

    public void addAll(List<T> list) {
        items.addAll(list);
        notifyDataSetChanged();
    }

    public void reset() {
        items.clear();
        notifyDataSetChanged();
    }

    @Override
    public void onMove(int fromPosition, int toPosition) {
        /**
         * Move to the original array data here
         */
        Collections.swap(items, fromPosition, toPosition);
        /**
         * Notification data movement
         */
        notifyItemMoved(fromPosition, toPosition);

        onItemMoved();
    }
    @Override
    public void onSwipe(int position) {
        /**
         * Original data removal data
         */
        items.remove(position);
        /**
         * Notification removal
         */
        notifyItemRemoved(position);

        onItemSwiped();
    }
    public abstract void onItemSwiped();
    public abstract void onItemMoved();
}