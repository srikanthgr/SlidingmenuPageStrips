package com.srikanthgr.slidingmenupagestrips.adapters;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.srikanthgr.slidingmenupagestrips.fragments.DummySectionFragment;

/**
 * A FragmentPagerAdapter that returns a fragment corresponding to one of
 * the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

	public SectionsPagerAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getItem(int position) {
		// getItem is called to instantiate the fragment for the given page.
		// Return a DummySectionFragment (defined as a static inner class
		// below) with the page number as its lone argument.
		Fragment fragment = new DummySectionFragment();
		Bundle args = new Bundle();
		args.putInt(DummySectionFragment.ARG_SECTION_NUMBER, position + 1);
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public int getCount() {
		// Show 5 total pages.
		return 5;
	}

	@Override
	public CharSequence getPageTitle(int position) {
		switch (position) {
		case 0:
			return "Section 1";
		case 1:
			return "Section 2";
		case 2:
			return "Section 3";
		case 3:
			return "Section 4";
		case 4:
			return "Section 5";
		}
		return null;
	}
}