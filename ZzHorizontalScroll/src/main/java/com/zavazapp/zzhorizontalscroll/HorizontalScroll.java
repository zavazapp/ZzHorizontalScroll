package com.zavazapp.zzhorizontalscroll;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
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
    private LinearLayoutManager layoutManager;
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

    public LinearLayoutManager getLayoutManager() {
        return layoutManager;
    }

    public void setLayoutManager(LinearLayoutManager layoutManager) {
        this.layoutManager = layoutManager;
    }

    public void setSmoothScroller(RecyclerView.SmoothScroller smoothScroller) {
        this.smoothScroller = smoothScroller;
    }

    @Override
    public void setScrollItemTitle(int state) {
        TextView tw = itemView.findViewById(R.id.scrollBadge);
        if (tw != null && s != null && layoutManager != null) {
            View v = s.findSnapView(layoutManager);
            if (v != null) {
                tw.setText(data.get(layoutManager.getPosition(v)).getBadge());
            }
        }
    }

    public List<ScrollItemModel> getData() {
        return data;
    }

    public void setData(List<ScrollItemModel> data) {
        this.data = data;
    }

    public static class Builder{
        private Context context;
        private RecyclerView recyclerView;
        private SnapHelper s;
        private List<ScrollItemModel> data;
        private LinearLayoutManager layoutManager;
        private float badgeTextSize;
        private int badgeTextColor;
        private int imageSize;
        private int maxImageSize;
        private OnScrollItemClickListener clickListener;
        private OnSetTitle onSetTitle;
        private float shrinkAmount;
        private float shrinkDistance;
        private int offset;
        private int itemsPerScreen;
        private int scrollItemLayout;
        private ImageTransform transformType;
        private int angleRadius;
        private IOnDataSet iOnDataSet;

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
         * Calling activity must implement OnScrollItemClickListener
         * and override void onItemClick(int position);
         */
        public Builder setClickListener(OnScrollItemClickListener clickListener) {
            this.clickListener = clickListener;
            return this;
        }

        /**
         * @param layoutManager (Optional)
         * HorizontalLayoutManager extends LinearLayoutManager
         * @return Builder with custom HorizontalLayoutManager
         */
        public Builder setLayoutManager(LinearLayoutManager layoutManager) {
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
         * @param imageSize (optional) - pixels - Sets the image size of square shape. Sets the image radius.
         * Size is auto generated to max: screen width / items per screen
         * @return Builder with custom badge text size.
         */
        public Builder setImageSize(int imageSize) {
            this.imageSize = imageSize;
            return this;
        }

        /**
         * @param maxImageSize (optional) - pixels - Sets the image maximum size of square shape. Sets the image max radius.
         * Size is auto generated to max: screen width / items per screen
         * Useful if you want less items per screen with reduced size
         * @return Builder with custom badge text size.
         */
        public Builder setMaxImageSize(int maxImageSize) {
            this.maxImageSize = maxImageSize;
            return this;
        }

        /**
         * @param transformValue (optional)
         * @return Builder with custom image transform.
         */
        public Builder setTransform(ImageTransform transformValue) {
            this.transformType = transformValue;
            return this;
        }

        /**
         * @param angleRadius (optional)
         * @return Builder with image with corner radius of chosen
         * value if ImageTransform.SQUARE_CIRCULAR type is used
         */
        public Builder setAngleRadius(int angleRadius) {
            this.angleRadius = angleRadius;
            return this;
        }

        public Builder itemsPerScreen(int itemsPerScreen) {
            this.itemsPerScreen = itemsPerScreen;
            return this;
        }

        /**
         * @param onSetTitle (optional) - OnSetTitle interface for scroll item title callback
         * @return Builder with custom OnSetTitle callback
         * Your activity must implement OnTitleSet interface
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

        public Builder setDataOffset(int offset, IOnDataSet onDataSet) {
            this.offset = offset;
            this.iOnDataSet = onDataSet;
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
            bundle.putInt("max_image_size", maxImageSize == 0 ? 250 : maxImageSize);
            bundle.putInt("transform_code", transformType.getTransformCode() == 0 ? 3 : transformType.getTransformCode());
            bundle.putInt("curve_size", angleRadius);

            addOffset(data);

            if (itemsPerScreen == 0){
                itemsPerScreen = 5;
            }
            if (scrollItemLayout == 0){
                scrollItemLayout = R.layout.scroll_item_layout;
            }

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
            horizontalScroll.setData(data);
            iOnDataSet.onDataSet(data);

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
