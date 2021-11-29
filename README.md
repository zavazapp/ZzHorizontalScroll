# ZzHorizontalScroll
Horizontal scroll view with images and links as input values


Add it in your root build.gradle at the end of repositories:

    allprojects {
        repositories {
          ...
          maven { url 'https://jitpack.io' }
        }
      }
      
      
Add the dependency:

     dependencies {
	        implementation 'com.github.zavazapp:ZzHorizontalScroll:1.0.3'
	}
  
  

HorizontalScroll

    HorizontalScroll horizontalScroll =
                new HorizontalScroll.Builder()
                //required
                .withContext(this)
                .setRecyclerView(findViewById(R.id.weeks))
                .withData(data)

                //optionals

                .setDataOffset(1, new IOnDataSet() {
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
                .setScrollItemLayout(R.layout.scroll_item_layout) //library default = R.layout.scroll_item_layout

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
                .setLayoutManager(new YourLinearLayoutManager(this)) //library default = HorizontalLayoutManager.java

                        /**
                         * @param s (optional)
                         * @return Builder with custom SnapHelper
                         */
                .setSnapHelper(new LinearSnapHelper() || new PagerSnapHelper())

                .setBadgeTextSize(24f) //library default = 12f

                .setBadgeTextColor(Color.CYAN) //library default = Color.RED

                        /**
                         * @param imageSize (optional) - pixels - Sets the image text size of square shape. Sets the image radius.
                         * Size is auto reduced to screen width / items per screen
                         * @return Builder with custom badge text size.
                         */
                .setImageSize(450) //library default = 250 (in pixels)

                .setMaxImageSize(150) //library default = 250 (in pixels)

                .setTransform(ImageTransform.CIRCULAR || ImageTransform.SQUARE_CIRCULAR || ImageTransform.SQUARE)

                        /**
                         * @param angleRadius (optional)
                         * @return Builder with image with corner radius of chosen
                         * value if ImageTransform.SQUARE_CIRCULAR type is used
                         */
                .setAngleRadius(1)

                .itemsPerScreen(5) //library default = 5

                        /**
                         * @param onSetTitle (optional) - OnSetTitle interface for scroll item title callback
                         * @return Builder with custom OnSetTitle callback
                         * Your activity must implement OnTitleSet interface
                         */
                .setTitleListener(this)

                .build();

    }

Implement interfaces:

    YourClass implements OnScrollItemClickListener, OnSetTitle
  
  
Override methods:

    @Override
    public void onItemClick(int position) {
        Toast.makeText(this, "item " + position + " clicked", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setScrollItemTitle(int state) {
        //Optional TextView as per your implementation
        //that may take dsc from ScrollItemModel
    }
    
    
Use ScrollItemModel constructors to create your data:

      List<ScrollItemModel> data = new ArrayList<>();

    public ScrollItemModel(int imageRes, String dsc, String link, String badge) {
        this.imageRes = imageRes;
        this.dsc = dsc;
        this.link = link;
        this.badge = badge;
    }

    public ScrollItemModel(String imageUrl, String dsc, String link, String badge) {
        this.imageUrl = imageUrl;
        this.dsc = dsc;
        this.link = link;
        this.badge = badge;
    }
    
    public ScrollItemModel(URI uri, String dsc, String link, String badge) {
        this.uri = uri;
        this.dsc = dsc;
        this.link = link;
        this.badge = badge;
    }

Optionaly you can provide your layout for scroll item, but must have only two elements:


		<ImageView
			android:id="@+id/scrollItemView"
			android:layout_width="match_parent"
			android:layout_height="match_parent" />

		<TextView
			android:id="@+id/scrollBadge"
			android:layout_width="wrap_content"
			android:layout_height="wrap_content"/>
    
