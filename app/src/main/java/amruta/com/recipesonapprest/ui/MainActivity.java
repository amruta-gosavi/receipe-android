package amruta.com.recipesonapprest.ui;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.astuetz.PagerSlidingTabStrip;

import amruta.com.gallerydatalib.impl.ServiceManager;
import amruta.com.gallerydatalib.model.RecipeList;
import amruta.com.recipesonapprest.R;


/**
 * This activity is the container for the viewpager and fragments. and responsible for getting data from server.1
 */

public class MainActivity extends AppCompatActivity {

    PagerSlidingTabStrip tabsStrip;
    RecipeList recipeList;
    RecipeList recipeDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get the ViewPager and set it's PagerAdapter so that it can display items
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(new SampleFragmentPagerAdapter(getSupportFragmentManager()));
        viewPager.setOffscreenPageLimit(3);

        // Give the PagerSlidingTabStrip the ViewPager
        tabsStrip = (PagerSlidingTabStrip) findViewById(R.id.tabs);
        // Attach the view pager to the tab strip
        tabsStrip.setViewPager(viewPager);
        // Attach the page change listener to tab strip and **not** the view pager inside the activity
        tabsStrip.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            // This method will be invoked when a new page becomes selected.
            @Override
            public void onPageSelected(int position) {

            }

            // This method will be invoked when the current page is scrolled
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                // Code goes here
            }

            // Called when the scroll state changes:
            // SCROLL_STATE_IDLE, SCROLL_STATE_DRAGGING, SCROLL_STATE_SETTLING
            @Override
            public void onPageScrollStateChanged(int state) {
                // Code goes here
            }
        });
        try {recipeList = new DownloadImagesTask().execute("").get();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public RecipeList getMyData() {

        return recipeList;
    }

    public void setMyRecipeDetailsData(RecipeList recipeDetails) {
        this.recipeDetails = recipeDetails;
    }

    public RecipeList getMyRecipeDetailsData() {

        return recipeDetails;
    }

    private class DownloadImagesTask extends AsyncTask<String, Void, RecipeList> {

        @Override
        protected RecipeList doInBackground(String... params) {

            RecipeList recipeDetailsArrayList = null;
            try {
                Thread.sleep(1000);
                try {
                    recipeDetailsArrayList = new ServiceManager().getPhotoImagesFromServer(1, "", "r");

                } catch (Exception e) {

                    e.printStackTrace();
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return recipeDetailsArrayList;
        }

        @Override
        protected void onPostExecute(RecipeList result) {
        }

        @Override
        protected void onPreExecute() {
        }

        @Override
        protected void onProgressUpdate(Void... values) {
        }

    }

}
