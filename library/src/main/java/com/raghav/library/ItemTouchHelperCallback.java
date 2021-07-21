package com.raghav.library;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ItemTouchHelperCallback extends ItemTouchHelper.Callback {

    private final ItemTouchHelperAdapter mAdapter;
    /**
     * Can you drag and drop?
     */
    private boolean isCanDrag = false;
    /**
     * Can it be slid
     */
    private boolean isCanSwipe = false;

    public ItemTouchHelperCallback(ItemTouchHelperAdapter adapter) {
        mAdapter = adapter;
    }

    public ItemTouchHelperCallback(ItemTouchHelperAdapter adapter, boolean canDrag, boolean canSwipe) {
        mAdapter = adapter;
        this.isCanDrag = canDrag;
        this.isCanSwipe = canSwipe;
    }

    /**
     * Can the settings be dragged and dropped?
     *
     * @param canDrag is true, no false
     */
    public void setDragEnable(boolean canDrag) {
        isCanDrag = canDrag;
    }

    /**
     * Set whether it can be swiped
     *
     * @param canSwipe is true, no false
     */
    public void setSwipeEnable(boolean canSwipe) {
        isCanSwipe = canSwipe;
    }

    /**
     * Can the item be dragged when the item is long pressed?
     * @return
     */
    @Override
    public boolean isLongPressDragEnabled() {
        return isCanDrag;
    }

    /**
     * Whether the Item can be slid (H: slide left and right, V: slide up and down)
     * @return
     */
    @Override
    public boolean isItemViewSwipeEnabled() {
        return isCanSwipe;
    }

    /**
     * When the user drags or slides the Item, we need to tell the system to slide or drag the direction
     * @param recyclerView
     * @param viewHolder
     * @return
     */
    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {// GridLayoutManager
            // flag If the value is 0, it is equivalent to this function being turned off.
            int dragFlag = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT | ItemTouchHelper.UP | ItemTouchHelper.DOWN;
            int swipeFlag = 0;
            // create make
            return makeMovementFlags(dragFlag, swipeFlag);
        } else if (layoutManager instanceof LinearLayoutManager) {// linearLayoutManager
            LinearLayoutManager linearLayoutManager = (LinearLayoutManager) layoutManager;
            int orientation = linearLayoutManager.getOrientation();

            int dragFlag = 0;
            int swipeFlag = 0;

            // For the sake of easy understanding, it is equivalent to a horizontal ListView and a vertical ListView.
            if (orientation == LinearLayoutManager.HORIZONTAL) {// If it is a horizontal layout
                swipeFlag = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
                dragFlag = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
            } else if (orientation == LinearLayoutManager.VERTICAL) {// If it is a vertical layout, equivalent to ListView
                dragFlag = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
                swipeFlag = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
            }
            return makeMovementFlags(dragFlag, swipeFlag);
        }
        return 0;
    }

    /**
     * Called back when Item is dragged
     * @param recyclerView
     * @param viewHolder
     * @param target
     * @return
     */
    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        /**
         * Callback
         */
        mAdapter.onMove(viewHolder.getAdapterPosition(), target.getAdapterPosition());
        return true;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int i) {
        /**
         * Callback
         */
        mAdapter.onSwipe(viewHolder.getAdapterPosition());
    }


}