package com.raghav.library

interface ItemTouchHelperAdapter {
    /**
     * @param fromPosition starting position
     * @param toPosition The location of the move
     */
    fun onMove(fromPosition: Int, toPosition: Int)
    fun onSwipe(position: Int)
}