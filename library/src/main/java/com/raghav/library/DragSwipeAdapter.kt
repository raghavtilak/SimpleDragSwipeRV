package com.raghav.library

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import java.util.Collections

abstract class DragSwipeAdapter<T>(
    val context: Context, datas: MutableList<T?>?, itemLayoutId: Int,
    recyclerView: RecyclerView,
    isCanDrag: Boolean, isCanSwipe: Boolean, displayMode: Int,
    itemSpacing: Int, onItemClickListener: OnItemClick<*>?
) : RecyclerView.Adapter<DragSwipeAdapter<T>.ViewHolder>(), ItemTouchHelperAdapter {
    private var items: MutableList<T?>?
    private var listener: OnItemClick<T?>?
    private val layoutId: Int

    interface OnItemClick<T> {
        fun onClick(view: View?, position: Int, item: T)
    }

    inner class ViewHolder(view: View, listener: OnItemClick<*>?) : RecyclerView.ViewHolder(view),
        View.OnClickListener {
        private val views: MutableMap<Int, View>

        init {
            views = HashMap()
            views[0] = view
            if (listener != null) view.setOnClickListener(this)
        }

        override fun onClick(view: View) {
            if (listener != null) listener!!.onClick(
                view,
                adapterPosition,
                getItem(adapterPosition)
            )
        }

        fun initViewList(idList: IntArray) {
            for (id in idList) initViewById(id)
        }

        fun initViewById(id: Int) {
            val view = if (view != null) view!!.findViewById<View>(id) else null
            if (view != null) views[id] = view
        }

        val view: View?
            get() = getView(0)

        fun getView(id: Int): View? {
            if (views.containsKey(id)) return views[id] else initViewById(id)
            return views[id]
        }
    }

    //    protected abstract View createView(Context context, ViewGroup viewGroup, int viewType);
    protected abstract fun bindView(item: T?, viewHolder: ViewHolder?)

    init {
        items = datas
        listener = onItemClickListener as OnItemClick<T?>?
        layoutId = itemLayoutId
        recyclerView.adapter = this
        val helperCallback = ItemTouchHelperCallback(this)
        helperCallback.setSwipeEnable(isCanSwipe)
        helperCallback.setDragEnable(isCanDrag)
        val itemTouchHelper = ItemTouchHelper(helperCallback)
        itemTouchHelper.attachToRecyclerView(recyclerView)
        recyclerView.addItemDecoration(
            EqualSpacingItemDecoration(
                itemSpacing,
                displayMode
            )
        ) // 16px. In practice, you'll want to use getDimensionPixelSize
    }

    override fun onCreateViewHolder(
        viewGroup: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view =
            LayoutInflater.from(viewGroup.context).inflate(layoutId, viewGroup, false)
        return ViewHolder(view, listener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        bindView(getItem(position), holder)
    }

    override fun getItemCount(): Int {
        return items!!.size
    }

    fun getItem(index: Int): T? {
        return if (items != null && index < items!!.size) items!![index] else null
    }

    fun setList(list: MutableList<T?>?) {
        items = list
    }

    val list: List<T?>?
        get() = items

    fun setClickListener(listener: OnItemClick<*>?) {
        this.listener = listener as OnItemClick<T?>?
    }

    fun addAll(list: List<T?>?) {
        items!!.addAll(list!!)
        notifyDataSetChanged()
    }

    fun reset() {
        items!!.clear()
        notifyDataSetChanged()
    }

    override fun onMove(fromPosition: Int, toPosition: Int) {
        /**
         * Move to the original array data here
         */
        Collections.swap(items, fromPosition, toPosition)
        /**
         * Notification data movement
         */
        notifyItemMoved(fromPosition, toPosition)
        onItemMoved()
    }

    override fun onSwipe(position: Int) {
        /**
         * Original data removal data
         */
        items!!.removeAt(position)
        /**
         * Notification removal
         */
        notifyItemRemoved(position)
        onItemSwiped()
    }

    abstract fun onItemSwiped()
    abstract fun onItemMoved()
}