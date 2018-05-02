package app.comps456f;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by meiyuk on 18/4/2018.
 */

public class ViewPagerAdaptor extends FragmentPagerAdapter {

    ArrayList<Fragment> fragments = new ArrayList<>();
    //ArrayList<String> tabTitles = new ArrayList<>();


    public ViewPagerAdaptor(FragmentManager fm){
        super(fm);
    }

    // This determines the fragment for each tab
    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    // This determines the number of tabs
    @Override
    public int getCount() {
        return fragments.size();
    }


    // This determines the title for each tab
    @Override
    public CharSequence getPageTitle(int position) {
        return fragments.get(position).toString();
    }


    public void addFragments(Fragment f){
        fragments.add(f);
        //this.tabTitles.add(tabTitles);
    }

    @Override
    public int getItemPosition(Object object) {
        // POSITION_NONE makes it possible to reload the PagerAdapter
        return POSITION_NONE;
    }

}






