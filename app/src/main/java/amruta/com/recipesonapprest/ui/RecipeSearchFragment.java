package amruta.com.recipesonapprest.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import amruta.com.gallerydatalib.model.RecipeCategoryList;
import amruta.com.recipesonapprest.R;
import amruta.com.recipesonapprest.data.DataCache;

import static android.R.attr.data;

/**
 * This fragment is responsible for showing search tab
 */

public class RecipeSearchFragment extends Fragment {
    public static final String ARG_PAGE = "ARG_PAGE";

    private int mPage;
    RecyclerView recyclerView;


    public static RecipeSearchFragment newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        RecipeSearchFragment fragment = new RecipeSearchFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage = getArguments().getInt(ARG_PAGE);
    }


    @Override
    public void onAttach(Activity a) {
        super.onAttach(a);
    }


    // Inflate the fragment layout we defined above for this fragment
    // Set the associated text for the title
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.search_fragment, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.search_list);


        //loading from server
        Log.d("LOG", "SearchFragment:onCreateView: Data from server :" + data);
       // MainActivity activity = (MainActivity) getActivity();
      //  RecipeList data = activity.getMyData();

        RecipeCategoryList recipeCategoryList = DataCache.InstantiateRecipeObjectsForTesting();

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(new RecipeSearchFragment.RecipeListAdapter(recipeCategoryList));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(),
                recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                //Log.d("LOG", "RecipeSearchFragment: onItemClick"+recyclerView.getAdapter().getItemId(position));

                replaceFragment();
          }

            @Override
            public void onItemLongClick(View view, int position) {
                // ...
            }
        }));

        //Log.d("LOG", "SearchFragment: Data from server :DataPasser" + data);


        return recyclerView;
    }

    private void replaceFragment()
    {

    }



    class RecipeListAdapter extends RecyclerView.Adapter<RecipeSearchFragment.SearchRecipeViewHolder> {
        private final RecipeCategoryList recipeDetailsList;

        //private final RecipeCategoryList recipeCategoryList = DataCache.InstantiateRecipeObjectsForTesting();

        RecipeListAdapter(RecipeCategoryList recipeDetailsList) {
            this.recipeDetailsList = recipeDetailsList;
        }

        @Override
        public SearchRecipeViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            final LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
            final View v = layoutInflater.inflate(R.layout.search_card, viewGroup, false);
            return new SearchRecipeViewHolder(v);
        }

        @Override
        public void onBindViewHolder(SearchRecipeViewHolder recipeViewHolder, int i) {
            recipeViewHolder.recipeTitle.setText(recipeDetailsList.getRecipeCategory()[i].getCategory());
            Picasso.with(getContext())
                    .load(recipeDetailsList.getRecipeCategory()[i].getImg_url())
                    .resize(0, 325)
                    .into((ImageView) recipeViewHolder.itemView.findViewById(R.id.recipe_search_image));


        }

        @Override
        public int getItemCount() {
            return recipeDetailsList.getCount();
        }
    }

    class SearchRecipeViewHolder extends RecyclerView.ViewHolder {
        TextView recipeTitle;
        ImageView recipeImage;
        TextView recipeSource;

        SearchRecipeViewHolder(View itemView) {
            super(itemView);
            recipeTitle = (TextView) itemView.findViewById(R.id.recipe_search_title);
            recipeImage = (ImageView) itemView.findViewById(R.id.recipe_search_image);
        }
    }


}
