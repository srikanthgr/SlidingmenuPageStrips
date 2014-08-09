package com.srikanthgr.slidingmenupagestrips;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.srikanthgr.slidingmenupagestrips.adapters.SectionsPagerAdapter;

public class SlidingMenuPageStrips extends FragmentActivity {
	private DrawerLayout mDrawerLayout;
	private ListView drawerListLeft, drawerListRight;
	private ActionBarDrawerToggle mDrawerToggle;

	CharSequence mDrawerTitle;
	CharSequence mTitle;
	SlidingMenuPageStrips mContext;
	SectionsPagerAdapter mSectionsPagerAdapter;
	ViewPager mViewPager;
	ActionBar actionBar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sliding_menu_page_strips);
		mContext = this;
		actionBar = getActionBar();
		/*
		 * ViewPager vp = (ViewPager) findViewById(R.id.pager);
		 * CustomPagerAdapter adapter = new CustomPagerAdapter(mContext);
		 * vp.setAdapter(adapter);
		 */
		mSectionsPagerAdapter = new SectionsPagerAdapter(
				getSupportFragmentManager());

		// Set up the ViewPager with the sections adapter.
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mSectionsPagerAdapter);
		/*
		 * vp.setPageTransformer(false, new ViewPager.PageTransformer() {
		 * 
		 * @Override public void transformPage(View page, float position) {
		 * final float normalizedposition = Math.abs(Math.abs(position) - 1);
		 * page.setScaleX(normalizedposition / 2 + 0.5f);
		 * page.setScaleY(normalizedposition / 2 + 0.5f); } });
		 */
		mTitle = mDrawerTitle = getTitle();
		drawerListRight = (ListView) findViewById(R.id.right_drawer_5);

		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		drawerListLeft = (ListView) findViewById(R.id.left_drawer);

		// set a custom shadow that overlays the main content when the drawer
		// opens
		// mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow,
		// GravityCompat.START);
		// set up the drawer's list view with items and click listener
		mDrawerLayout.setDrawerListener(mDrawerToggle);

		drawerListLeft.setAdapter(new ArrayAdapter<String>(this,
				R.layout.drawer_list_item, getResources().getStringArray(
						R.array.left_menu)));

		drawerListRight.setAdapter(new ArrayAdapter<String>(this,
				R.layout.drawer_list_item, getResources().getStringArray(
						R.array.right_menu)));

		drawerListLeft.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				// Closing the drawer
				mDrawerLayout.closeDrawer(drawerListLeft);

			}
		});

		drawerListRight.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				// Closing the drawer
				mDrawerLayout.closeDrawer(drawerListRight);

			}
		});

		// enable ActionBar app icon to behave as action to toggle nav drawer
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeButtonEnabled(true);

		// ActionBarDrawerToggle ties together the the proper interactions
		// between the sliding drawer and the action bar app icon
		mDrawerToggle = new ActionBarDrawerToggle(this, /* host Activity */
		mDrawerLayout, /* DrawerLayout object */
		R.drawable.ic_drawer, /* nav drawer image to replace 'Up' caret */
		R.string.drawer_open, /*
							 * "open drawer" description for accessibility
							 */
		R.string.drawer_close /*
							 * "close drawer" description for accessibility
							 */
		) {
			@Override
			public void onDrawerClosed(View view) {
				// getActionBar().setTitle(mTitle);
				invalidateOptionsMenu(); // creates call to
				// onPrepareOptionsMenu();
				// Toast.makeText(getApplicationContext(),"Closed",Toast.LENGTH_SHORT).show();

			}

			@Override
			public void onDrawerOpened(View drawerView) {
				// getActionBar().setTitle(mDrawerTitle);
				invalidateOptionsMenu(); // creates call to
				// onPrepareOptionsMenu();
				// Toast.makeText(getApplicationContext(),"Opened",Toast.LENGTH_SHORT).show();

			}

		};

		if (savedInstanceState == null) {
			selectItem(0);
		}
	}

	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		return super.onPrepareOptionsMenu(menu);
	}

	/** Handling the touch event of app icon */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (mDrawerToggle.onOptionsItemSelected(item)) {
			boolean drawerOpenRight = mDrawerLayout
					.isDrawerOpen(drawerListRight);
			if (drawerOpenRight) {
				mDrawerLayout.closeDrawer(drawerListRight);
			}

			return true;
		} else {
			switch (item.getItemId()) {
			case R.id.action_settings:
				boolean drawerOpen = mDrawerLayout
						.isDrawerOpen(drawerListRight);
				if (drawerOpen) {
					mDrawerLayout.closeDrawer(drawerListRight);
				} else if (mDrawerLayout.isDrawerOpen(drawerListLeft)) {
					mDrawerLayout.closeDrawer(drawerListLeft);

				} else {
					mDrawerLayout.openDrawer(drawerListRight);
				}

			}
		}

		return super.onOptionsItemSelected(item);
	}

	private void selectItem(int position) {

		// update selected item and title, then close the drawer
		drawerListLeft.setItemChecked(position, true);
		mDrawerLayout.closeDrawer(drawerListLeft);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		// Sync the toggle state after onRestoreInstanceState has occurred.
		mDrawerToggle.syncState();
	}

}