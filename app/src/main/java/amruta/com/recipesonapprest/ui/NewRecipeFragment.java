package amruta.com.recipesonapprest.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import amruta.com.gallerydatalib.model.RecipeDetails;
import amruta.com.gallerydatalib.model.RecipeList;
import amruta.com.recipesonapprest.R;


/**
 * This class handle the recipe details tab.
 */

public class NewRecipeFragment extends Fragment
{
    private int mPage=3;
    public static final String ARG_PAGE = "ARG_PAGE";
    RecipeList recipeDetails =   null;
    int pos = 0;
    String recipeId;

    public static NewRecipeFragment newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        NewRecipeFragment fragment = new NewRecipeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle args = getArguments();
        if (args != null) {
            pos =   args.getInt("position");
            recipeId    =   args.getString("recipe_id");
        }
       // mPage = getArguments().getInt(ARG_PAGE);
    }

    @Override
    public void onAttach(Activity a) {
        super.onAttach(a);

    }

    // Inflate the fragment layout we defined above for this fragment
    // Set the associated text for the title
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.new_recipe_fragment, container, false);

        Log.d("TAG", "onViewCreated");

         MainActivity activity = (MainActivity) getActivity();
        final RecipeList data   =   activity.getMyData();
        recipeDetails   =   data;
        TextView titleView  =   (TextView) view.findViewById(R.id.recipe_details_title);
        TextView publisherView  =   (TextView) view.findViewById(R.id.recipe_details_publisher);
        TextView ingredientsView =  (TextView) view.findViewById(R.id.recipe_details_ingredients);

        if(recipeDetails!=null  )
        {
            if(recipeDetails.getRecipes()!=null)
            {
                RecipeDetails objRecipe =  recipeDetails.getRecipes()[pos] ;
                titleView.setText(objRecipe.getTitle());
                publisherView.setText(objRecipe.getPublisher());
                ingredientsView.setText(recipeDetails.getRecipes()[pos].getIngredients());

                try {
                    Log.d("LOG", "NewRecipeFragment:onBindViewHolder");
                    Picasso.with(getContext())
                            .load(objRecipe.getImage_url())
                            .resize(0, 325)
                            .into((ImageView) view.findViewById(R.id.recipe_detail_img));


                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

    }
}
