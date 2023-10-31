package com.raghav.library

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ItemTouchHelperCallback : ItemTouchHelper.Callback {
    private val mAdapter: ItemTouchHelperAdapter

    /**
     * Can you drag and drop?
     */
    private var isCanDrag = false

    /**
     * Can it be slid
     */
    private var isCanSwipe = false

    constructor(adapter: ItemTouchHelperAdapter) {
        mAdapter = adapter
    }

    constructor(adapter: ItemTouchHelperAdapter, canDrag: Boolean, canSwipe: Boolean) {
        mAdapter = adapter
        isCanDrag = canDrag
        isCanSwipe = canSwipe
    }

    /**
     * Can the settings be dragged and dropped?
     *
     * @param canDrag is true, no false
     */
    fun setDragEnable(canDrag: Boolean) {
        isCanDrag = canDrag
    }

    /**
     * Set whether it can be swiped
     *
     * @param canSwipe is true, no false
     */
    fun setSwipeEnable(canSwipe: Boolean) {
        isCanSwipe = canSwipe
    }

    /**
     * Can the item be dragged when the item is long pressed?
     * @return
     */
    override fun isLongPressDragEnabled(): Boolean {
        return isCanDrag
    }

    /**
     * Whether the Item can be slid (H: slide left and right, V: slide up and down)
     * @return
     */
    override fun isItemViewSwipeEnabled(): Boolean {
        return isCanSwipe
    }

    /**
     * When the user drags or slides the Item, we need to tell the system to slide or drag the direction
     * @param recyclerView
     * @param viewHolder
     * @return
     */
    override fun getMovementFlags(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int {
        val layoutManager = recyclerView.layoutManager
        if (layoutManager is GridLayoutManager) { // GridLayoutManager
            // flag If the value is 0, it is equivalent to this function being turned off.
            val dragFlag =
                ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT or ItemTouchHelper.UP or ItemTouchHelper.DOWN
            val swipeFlag = 0
            // create make
            return makeMovementFlags(dragFlag, swipeFlag)
        } else if (layoutManager is LinearLayoutManager) { // linearLayoutManager
            val orientation = layoutManager.orientation
            var dragFlag = 0
            var swipeFlag = 0

            // For the sake of easy understanding, it is equivalent to a horizontal ListView and a vertical ListView.
            if (orientation == LinearLayoutManager.HORIZONTAL) { // If it is a horizontal layout
                swipeFlag = ItemTouchHelper.UP or ItemTouchHelper.DOWN
                dragFlag = ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
            } else if (orientation == LinearLayoutManager.VERTICAL) { // If it is a vertical layout, equivalent to ListView
                dragFlag = ItemTouchHelper.UP or ItemTouchHelper.DOWN
                swipeFlag = ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
            }
            return makeMovementFlags(dragFlag, swipeFlag)
        }
        return 0
    }

    /**
     * Called back when Item is dragged
     * @param recyclerView
     * @param viewHolder
     * @param target
     * @return
     */
    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        /**
         * Callback
         */
        mAdapter.onMove(viewHolder.adapterPosition, target.adapterPosition)
        return true
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, i: Int) {
        /**
         * Callback
         */
        mAdapter.onSwipe(viewHolder.adapterPosition)
    }
}