package com.zavazapp.zzhorizontalscroll;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import java.util.ArrayList;
import java.util.List;

public class HorizontalScroll implements OnSetTitle{

    private RecyclerView recyclerView;
    private SnapHelper s;
    private List<ScrollItemModel> data;
    private HorizontalLayoutManager layoutManager;
    private ConstraintLayout itemView;
    private RecyclerView.SmoothScroller smoothScroller;

    private HorizontalScroll() {
    }

    public RecyclerView getRecyclerView() {
        return recyclerView;
    }

    public void setRecyclerView(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
    }

    public HorizontalLayoutManager getLayoutManager() {
        return layoutManager;
    }

    public void setLayoutManager(HorizontalLayoutManager layoutManager) {
        this.layoutManager = layoutManager;
    }

    public void setSmoothScroller(RecyclerView.SmoothScroller smoothScroller) {
        this.smoothScroller = smoothScroller;
    }

    @Override
    public void setTitle(int state) {
        TextView tw = itemView.findViewById(R.id.scrollBadge);
        if (tw != null && s != null && layoutManager != null) {
            View v = s.findSnapView(layoutManager);
            if (v != null) {
                tw.setText(data.get(layoutManager.getPosition(v)).getBadge());
            }
        }
    }



    public static class Builder{
        private Context context;
        private RecyclerView recyclerView;
        private SnapHelper s;
        private List<ScrollItemModel> data;
        private HorizontalLayoutManager layoutManager;
        private float badgeTextSize;
        private int badgeTextColor;
        private int imageSize;
        private OnScrollItemClickListener clickListener;
        private OnSetTitle onSetTitle;
        private float shrinkAmount;
        private float shrinkDistance;
        private int offset;
        private int itemsPerScreen;
        private int scrollItemLayout;

        /**
         * Context, data and recyclerView required
         */
        public Builder() {
        }

        /**
         * @param context (Required)
         * @return Builder
         */
        public Builder withContext(@NonNull Context context) {
            this.context = context;
            return this;
        }

        /**
         * @param data (Required)
         * @return Builder
         */
        public Builder withData(@NonNull List<ScrollItemModel> data) {
            this.data = data;
            return this;
        }

        /**
         * @param recyclerView (Required)
         * @return Builder
         */
        public Builder setRecyclerView(@NonNull RecyclerView recyclerView) {
            this.recyclerView = recyclerView;
            return this;
        }

        /**
         * @param scrollItemLayout
         * must be of ViewGroup type and include
             * <ImageView
             * ndroid:id="@+id/weeksImageScroll"/>
         * and
             * <TextView
             * android:id="@+id/scrollBadge"/>
         * @return Builder with custom item layout
         */
        public Builder setScrollItemLayout(int scrollItemLayout) {
            this.scrollItemLayout = scrollItemLayout;
            return this;
        }

        /**
         * @param clickListener (Optional)
         * @return Builder with clickListener
         */
        public Builder setClickListener(OnScrollItemClickListener clickListener) {
            this.clickListener = clickListener;
            return this;
        }

        /**
         * @param layoutManager (Optional)
         * @return Builder with custom HorizontalLayoutManager
         */
        public Builder setLayoutManager(HorizontalLayoutManager layoutManager) {
            this.layoutManager = layoutManager;
            return this;
        }

        /**
         * @param s (optional)
         * @return Builder with custom SnapHelper
         */
        public Builder setSnapHelper(SnapHelper s) {
            this.s = s;
            return this;
        }

        /**
         * @param badgeTextSize (optional)- Sets the badge text size.
         * @return Builder with custom badge text size.
         */
        public Builder setBadgeTextSize(float badgeTextSize) {
            this.badgeTextSize = badgeTextSize;
            return this;
        }

        /**
         * @param badgeTextColor (optional)- Sets the badge text color.
         * @return Builder with custom badge text color. Default = Color.RED
         */
        public Builder setBadgeTextColor(int badgeTextColor) {
            this.badgeTextColor = badgeTextColor;
            return this;
        }

        /**
         * @param imageSize (optional) - Sets the image text size of square shape. Sets the image radius.
         * @return Builder with custom badge text size.
         */
        public Builder setImageSize(int imageSize) {
            this.imageSize = imageSize;
            return this;
        }

        public Builder itemsPerScreen(int itemsPerScreen) {
            this.itemsPerScreen = itemsPerScreen;
            return this;
        }

        /**
         * @param onSetTitle (optional) - OnSetTitle interface for title set callback
         * @return Builder with custom OnSetTitle callback
         */
        public Builder setTitleListener(OnSetTitle onSetTitle) {
            this.onSetTitle = onSetTitle;
            return this;
        }

        /**
         * @param shrinkAmount (optional) TODO
         * @param shrinkDistance (optional) TODO
         * @return
         */
        public Builder setLayoutShrinkAndDistance(float shrinkAmount, float shrinkDistance) {
            this.shrinkAmount = shrinkAmount;
            this.shrinkDistance = shrinkDistance;
            return this;
        }

        public Builder setDataOffset(int offset) {
            this.offset = offset;
            return this;
        }

        public HorizontalScroll build(){
            validate();
            HorizontalScroll horizontalScroll = new HorizontalScroll();

            shrinkAmount = shrinkAmount == 0 ? 0.5f : shrinkAmount;
            shrinkDistance = shrinkDistance == 0 ? 0.78f : shrinkDistance;

            if (layoutManager == null){
                layoutManager = new HorizontalLayoutManager(context, shrinkAmount, shrinkDistance, onSetTitle);
            }

            layoutManager.setOrientation(RecyclerView.HORIZONTAL);
            recyclerView.setLayoutManager(layoutManager);

            horizontalScroll.setLayoutManager(layoutManager);
            horizontalScroll.setRecyclerView(recyclerView);

            //Bundle
            Bundle bundle = new Bundle();
            bundle.putFloat("text_size", badgeTextSize == 0 ? 12 : badgeTextSize);
            bundle.putInt("text_color", badgeTextColor == 0 ? Color.RED : badgeTextColor);
            bundle.putInt("image_size", imageSize == 0 ? 250 : imageSize);

            addOffset(data);

            ScrollAdapter adapter = new ScrollAdapter(context, data, bundle, scrollItemLayout, itemsPerScreen, clickListener);

            if (s == null){
                s = new LinearSnapHelper();
            }
            s.attachToRecyclerView(recyclerView);
            s.findSnapView(layoutManager);
            recyclerView.setAdapter(adapter);

            /*Scroll weeks to current*/
            RecyclerView.SmoothScroller smoothScroller = new LinearSmoothScroller(context) {
                @Override
                protected int getVerticalSnapPreference() {
                    return LinearSmoothScroller.SNAP_TO_START;
                }
            };
            smoothScroller.setTargetPosition(0);
            layoutManager.startSmoothScroll(smoothScroller);

            horizontalScroll.setSmoothScroller(smoothScroller);

            return horizontalScroll;
        }

        private void addOffset(List<ScrollItemModel> data) {
            if (offset != 0){
                List<ScrollItemModel> temp = new ArrayList<>();
                for (int f = 0; f < offset; f++) {
                    temp.add(new ScrollItemModel(R.drawable.ic_twotone_fast_forward_24,
                            "",
                            "",
                            ""));
                }

                temp.addAll(data);

                for (int f = 0; f < offset; f++) {
                    temp.add(new ScrollItemModel(R.drawable.ic_twotone_fast_rewind_24,
                            "",
                            "",
                            ""));
                }
                this.data = temp;
            }
        }

        private void validate()  {
            if (context == null){
                throw new ScrollViewExceptions("Context can not be null.");
            }
            if (recyclerView == null){
                throw new ScrollViewExceptions("RecyclerView can not be null.");
            }
            if (data == null){
                throw new ScrollViewExceptions("Data can not be null.");
            }
        }
    }

}
