package com.raghav.library;

public interface ItemTouchHelperAdapter {

    /**
     * @param fromPosition starting position
     * @param toPosition The location of the move
     */
    void onMove(int fromPosition, int toPosition);
    void onSwipe(int position);
}