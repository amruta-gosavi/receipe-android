package amruta.com.recipesonapprest.ui;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.ViewGroup;

import com.astuetz.PagerSlidingTabStrip;

import amruta.com.recipesonapprest.R;

/**
 * This class is the holder for fragment pages.
 */


public class SampleFragmentPagerAdapter extends FragmentPagerAdapter implements PagerSlidingTabStrip.IconTabProvider {

    final int PAGE_COUNT = 3;
    FragmentManager fragmentManager;
    private int tabIcons[] = {R.drawable.ic_home24, R.drawable.ic_search24, R.drawable.ic_add24};

    public SampleFragmentPagerAdapter(FragmentManager fm)
    {
        super(fm);
        this.fragmentManager = fm;
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return RecipeHomeFragment.newInstance(position + 1);
            case 1:
                return RecipeSearchFragment.newInstance(position + 1);
            case 2:
                return new NewRecipeFragment().newInstance(position +1);
            default:
                return new Fragment();
        }
    }


    @Override
    public int getPageIconResId(int position) {
        return tabIcons[position];
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        Log.d("LOG", "SampleFragmentPagerAdapter: destroyItem::"+object);
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.remove((Fragment) object);
        ft.commit();
    }
}



