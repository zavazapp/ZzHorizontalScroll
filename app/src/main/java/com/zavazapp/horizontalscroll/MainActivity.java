package com.zavazapp.horizontalscroll;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;

import com.zavazapp.zzhorizontalscroll.HorizontalScroll;
import com.zavazapp.zzhorizontalscroll.OnScrollItemClickListener;
import com.zavazapp.zzhorizontalscroll.OnSetTitle;
import com.zavazapp.zzhorizontalscroll.ScrollItemModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnSetTitle, OnScrollItemClickListener {

    List<ScrollItemModel> data = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        data.add(new ScrollItemModel(R.drawable.kolica_120,
                "",
                "link",
                "1"));
        data.add(new ScrollItemModel(android.R.drawable.ic_menu_camera,
                "",
                "link",
                "2"));
        data.add(new ScrollItemModel(R.drawable.kolica_120,
                "",
                "link",
                "3"));
        data.add(new ScrollItemModel(R.drawable.kolica_120,
                "",
                "link",
                "4"));
        data.add(new ScrollItemModel("https://www.bebac.com/uploads/porodiljsko-bolovanje-vizual(1).png",
                "",
                "link",
                "5"));
        data.add(new ScrollItemModel(android.R.drawable.ic_menu_camera,
                "",
                "link",
                "1"));
        data.add(new ScrollItemModel(android.R.drawable.ic_menu_camera,
                "",
                "link",
                "2"));
        data.add(new ScrollItemModel(android.R.drawable.ic_menu_camera,
                "",
                "link",
                "3"));
        data.add(new ScrollItemModel(android.R.drawable.ic_menu_camera,
                "",
                "link",
                "4"));
        data.add(new ScrollItemModel(android.R.drawable.ic_menu_camera,
                "asdfsaf",
                "link",
                "5"));

        HorizontalScroll horizontalScroll =
                new HorizontalScroll.Builder()
                .withContext(this)
                .withData(data)
                .setRecyclerView(findViewById(R.id.weeks))
                .setBadgeTextColor(Color.WHITE)
                .setBadgeTextSize(14f)
                .setDataOffset(2)
                .setScrollItemLayout(R.layout.scroll_item_layout2)
                .setTitleListener(this)
                .setClickListener(this)
                .itemsPerScreen(8)
                .build();
    }


    @Override
    public void onItemClick(int position) {

    }

    @Override
    public void setTitle(int position) {

    }
}