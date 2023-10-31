package com.raghav.simpledragswiperv

import android.content.Context
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.raghav.library.DragSwipeAdapter

class RVAdapter(
    context: Context,
    datas: List<UserModel?>?,
    itemLayoutId: Int,
    recyclerView: RecyclerView?,
    isCanDrag: Boolean,
    isCanSwipe: Boolean,
    displayMode: Int,
    itemSpacing: Int,
    onItemClickListener: OnItemClick<*>?
) : DragSwipeAdapter<UserModel?>(
    context,
    datas?.toMutableList(),
    itemLayoutId,
    recyclerView!!,
    isCanDrag,
    isCanSwipe,
    displayMode,
    itemSpacing,
    onItemClickListener
) {
    override fun bindView(item: UserModel?, viewHolder: ViewHolder?) {
        if (item != null) {
            val name = viewHolder?.getView(R.id.textViewName) as TextView
            val phone = viewHolder.getView(R.id.textViewPhone) as TextView
            val country = viewHolder.getView(R.id.textViewCountry) as TextView
            name.text = item.name
            phone.text = item.phone
            country.text = item.country
        }
    }

    override fun onItemSwiped() {}
    override fun onItemMoved() {}

    companion object {
        const val HORIZONTAL = 0
        const val VERTICAL = 1
        const val GRID = 2
    }
}