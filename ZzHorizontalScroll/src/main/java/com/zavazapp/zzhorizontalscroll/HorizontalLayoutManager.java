package com.zavazapp.zzhorizontalscroll;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class HorizontalLayoutManager extends LinearLayoutManager {

    private float mShrinkAmount;
    private float mShrinkDistance;
    public OnSetTitle onSetTitle;


    /**
     * @param context
     * @param mShrinkAmount
     * @param mShrinkDistance
     * @param onSetTitle
     */
    public HorizontalLayoutManager(Context context, float mShrinkAmount, float mShrinkDistance, OnSetTitle onSetTitle) {
        super(context);
        this.mShrinkAmount = mShrinkAmount;
        this.mShrinkDistance = mShrinkDistance;
        this.onSetTitle = onSetTitle;
    }

    public HorizontalLayoutManager(Context context, int orientation, boolean reverseLayout) {
        super(context, orientation, reverseLayout);
    }

    public HorizontalLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public int scrollHorizontallyBy(int dx, RecyclerView.Recycler recycler, RecyclerView.State state) {
        int scrolled =  super.scrollHorizontallyBy(dx, recycler, state);

        float midpoint = getWidth() / 2.f;
        float d0 = 0.f;
        float d1 = mShrinkDistance * midpoint;
        float s0 = 1.f;
        float s1 = 1.f - mShrinkAmount;


        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            float childMidpoint = 0;
            if (child != null) {
                childMidpoint = (getDecoratedRight(child) + getDecoratedLeft(child)) / 2.f;
                float d = Math.min(d1, Math.abs(midpoint - childMidpoint));
                float scale = s0 + (s1 - s0) * (d - d0) / (d1 - d0);
                child.setScaleX(scale);
                child.setScaleY(scale);
            }
        }

        return scrolled;
    }

    @Override
    public void onScrollStateChanged(int state) {
        super.onScrollStateChanged(state);
        if (onSetTitle != null) {
            onSetTitle.setTitle(findFirstCompletelyVisibleItemPosition());
        }
    }

    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        super.onLayoutChildren(recycler, state);
        scrollHorizontallyBy(0, recycler, state);
    }
}
