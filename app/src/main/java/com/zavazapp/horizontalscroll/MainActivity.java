package com.zavazapp.horizontalscroll;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.zavazapp.zzhorizontalscroll.HorizontalScroll;
import com.zavazapp.zzhorizontalscroll.IOnDataSet;
import com.zavazapp.zzhorizontalscroll.ImageTransform;
import com.zavazapp.zzhorizontalscroll.OnScrollItemClickListener;
import com.zavazapp.zzhorizontalscroll.OnSetTitle;
import com.zavazapp.zzhorizontalscroll.ScrollItemModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnScrollItemClickListener, OnSetTitle{

    public List<ScrollItemModel> data = new ArrayList<>();
    private final int BABY_SCROLL_DATA_OFFSET = 3;
    public HorizontalScroll horizontalScroll;
    private RecyclerView recyclerView;
    private SnapHelper s = new LinearSnapHelper();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.weeks);

        data.add(new ScrollItemModel(0,
                "Title1",
                "link",
                null));
        data.add(new ScrollItemModel(0,
                "Title2",
                "link",
                ""));
//        data.add(new ScrollItemModel(R.drawable.kolica_120,
//                "Title3",
//                "link",
//                ""));
//        data.add(new ScrollItemModel(R.drawable.kolica_120,
//                "Title4",
//                "link",
//                ""));
//        data.add(new ScrollItemModel("https://www.bebac.com/uploads/porodiljsko-bolovanje-vizual(1).png",
//                "Title5",
//                "link",
//                ""));
//        data.add(new ScrollItemModel(android.R.drawable.ic_menu_camera,
//                "Title6",
//                "link",
//                "1"));
//        data.add(new ScrollItemModel(android.R.drawable.ic_menu_camera,
//                "Title7",
//                "link",
//                "2"));
//        data.add(new ScrollItemModel(android.R.drawable.ic_menu_camera,
//                "Title8",
//                "link",
//                "3"));
//        data.add(new ScrollItemModel(android.R.drawable.ic_menu_camera,
//                "Title9",
//                "link",
//                "4"));
//        data.add(new ScrollItemModel(android.R.drawable.ic_menu_camera,
//                "Title10",
//                "link",
//                "5"));



                horizontalScroll = new HorizontalScroll.Builder()
                //required
                .withContext(this)
                .setRecyclerView(recyclerView)
                .withData(data)

                //optionals

                .setDataOffset(BABY_SCROLL_DATA_OFFSET, new IOnDataSet() {
                    /**
                     * @param data - If offset is > 0 data must be changed for offset items on start and end.
                     *             So original data must be refreshed.
                     */
                    @Override
                    public void onDataSet(List<ScrollItemModel> data) {
                        refreshData(data);
                    }
                }) //library default = 0

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
//                .setScrollItemLayout(R.layout.scroll_item_layout) //library default = R.layout.scroll_item_layout

                        /**
                         * @param clickListener (Optional)
                         * @return Builder with clickListener
                         * Calling activity must implement OnScrollItemClickListener
                         * and override void onItemClick(int position);
                         */
                .setClickListener(this)

                        /**
                         * @param layoutManager (Optional)
                         * HorizontalLayoutManager extends LinearLayoutManager
                         * @return Builder with custom HorizontalLayoutManager
                         */
                //.setLayoutManager(new YourLinearLayoutManager(this)) //library default = HorizontalLayoutManager.java

                        /**
                         * @param s (optional)
                         * @return Builder with custom SnapHelper
                         */
                //.setSnapHelper(new LinearSnapHelper() || new PagerSnapHelper())

                .setBadgeTextSize(24f) //library default = 12f

                .setBadgeTextColor(Color.CYAN) //library default = Color.RED

                        /**
                         * @param imageSize (optional) - pixels - Sets the image text size of square shape. Sets the image radius.
                         * Size is auto reduced to screen width / items per screen
                         * @return Builder with custom badge text size.
                         */
                .setImageSize(450) //library default = 250 (in pixels)

                .setMaxImageSize(150) //library default = 250 (in pixels)

                //.setTransform(ImageTransform.CIRCULAR || ImageTransform.SQUARE_CIRCULAR || ImageTransform.SQUARE)
                .setTransform(ImageTransform.SQUARE_CIRCULAR)

                        /**
                         * @param angleRadius (optional)
                         * @return Builder with image with corner radius of chosen
                         * value if ImageTransform.SQUARE_CIRCULAR type is used
                         */
                .setAngleRadius(1)

                .itemsPerScreen(7) //library default = 5

                        /**
                         * @param onSetTitle (optional) - OnSetTitle interface for scroll item title callback
                         * @return Builder with custom OnSetTitle callback
                         * Your activity must implement OnTitleSet interface
                         */
                .setTitleListener(this)
                .setPlaceholderImage(R.drawable.ic_baseline_do_not_disturb_24)
                .build();




                new HorizontalScroll.Builder()
                        //required
                        .withContext(this)
                        .setRecyclerView(findViewById(R.id.weeks2))
                        .withData(data)

                        //optionals

                        .setDataOffset(BABY_SCROLL_DATA_OFFSET, new IOnDataSet() {
                            /**
                             * @param data - If offset is > 0 data must be changed for offset items on start and end.
                             *             So original data must be refreshed.
                             */
                            @Override
                            public void onDataSet(List<ScrollItemModel> data) {
                                refreshData(data);
                            }
                        }) //library default = 0

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
//                .setScrollItemLayout(R.layout.scroll_item_layout) //library default = R.layout.scroll_item_layout

                        /**
                         * @param clickListener (Optional)
                         * @return Builder with clickListener
                         * Calling activity must implement OnScrollItemClickListener
                         * and override void onItemClick(int position);
                         */
                        .setClickListener(this)

                        /**
                         * @param layoutManager (Optional)
                         * HorizontalLayoutManager extends LinearLayoutManager
                         * @return Builder with custom HorizontalLayoutManager
                         */
                        //.setLayoutManager(new YourLinearLayoutManager(this)) //library default = HorizontalLayoutManager.java

                        /**
                         * @param s (optional)
                         * @return Builder with custom SnapHelper
                         */
                        //.setSnapHelper(new LinearSnapHelper() || new PagerSnapHelper())

                        .setBadgeTextSize(24f) //library default = 12f

                        .setBadgeTextColor(Color.RED) //library default = Color.RED

                        /**
                         * @param imageSize (optional) - pixels - Sets the image text size of square shape. Sets the image radius.
                         * Size is auto reduced to screen width / items per screen
                         * @return Builder with custom badge text size.
                         */
                        .setImageSize(450) //library default = 250 (in pixels)

                        .setMaxImageSize(150) //library default = 250 (in pixels)

                        //.setTransform(ImageTransform.CIRCULAR || ImageTransform.SQUARE_CIRCULAR || ImageTransform.SQUARE)
                        .setTransform(ImageTransform.SQUARE_CIRCULAR)

                        /**
                         * @param angleRadius (optional)
                         * @return Builder with image with corner radius of chosen
                         * value if ImageTransform.SQUARE_CIRCULAR type is used
                         */
                        .setAngleRadius(1)

                        .itemsPerScreen(3) //library default = 5

                        /**
                         * @param onSetTitle (optional) - OnSetTitle interface for scroll item title callback
                         * @return Builder with custom OnSetTitle callback
                         * Your activity must implement OnTitleSet interface
                         */
                        .setTitleListener(this)
                        .setSnapHelper(s)
                        .setPlaceholderImage(R.drawable.ic_code)
                        .build();


    }

    private void refreshData(List<ScrollItemModel> data) {
        this.data = data;
    }


    @Override
    public void onItemClick(int position, int recyclerId) {

    }

    @Override
    public void setScrollItemTitle(int position, int recyclerId) {
        //Optional TextView as per your implementation
        //that may take dsc from ScrollItemModel
        TextView tw = findViewById(R.id.dsc);
        View v = s.findSnapView(horizontalScroll.getLayoutManager());
        if (v != null) {
            tw.setText(data.get(recyclerView.getChildLayoutPosition(v) + BABY_SCROLL_DATA_OFFSET).getDsc());
        }
    }
}