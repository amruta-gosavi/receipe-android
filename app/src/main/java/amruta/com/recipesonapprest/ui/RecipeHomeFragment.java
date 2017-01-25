package amruta.com.recipesonapprest.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import amruta.com.gallerydatalib.model.RecipeDetails;
import amruta.com.gallerydatalib.model.RecipeList;
import amruta.com.recipesonapprest.R;

import static android.R.attr.data;

/**
 *
 * This fragment class inflates recyclerView and shows recipe data fetched from server.
 */

public class RecipeHomeFragment extends Fragment {
    public static final String ARG_PAGE = "ARG_PAGE";

    private int mPage;
    RecyclerView recyclerView;
    RecipeDetails recipeDetails;
    RecyclerView.LayoutManager mLayoutManager;
    RecipeDetails [] recipeDetailses;


    public static RecipeHomeFragment newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        RecipeHomeFragment fragment = new RecipeHomeFragment();
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

    public void UpdateAdapter(RecipeList recipeList) {
        if (recipeList != null) {
            recyclerView.setAdapter(new RecipeListAdapter(recipeList));
        }
    }

    public RecipeDetails getMyData()
    {
        return recipeDetails;
    }

    // Inflate the fragment layout we defined above for this fragment
    // Set the associated text for the title
    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.home_fragment, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recipe_list);

        //loading from server
        Log.d("LOG", "RecipeHomeFragment:onCreateView: Data from server :" + data);
        MainActivity activity = (MainActivity) getActivity();
        final RecipeList data = activity.getMyData();
        //changed layout to staggered grid layout. If you want to change to the vertical list view, uncomment above line
        //recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mLayoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(new RecipeListAdapter(data));
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(),
                recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                Log.d("LOG", "RecipeHomeFragment: onItemClick"+position);
                recipeDetailses    =   data.getRecipes();
                recipeDetails =   recipeDetailses[position];

                replaceGridFragment(position, recipeDetails.getRecipe_id());
            }

            @Override
            public void onItemLongClick(View view, int position) {
                // ...
            }
        }));
        return recyclerView;
    }

    private void replaceGridFragment(int pos, String id)
    {
        Bundle bundle=new Bundle();
        bundle.putInt("position",pos );
        bundle.putString("recipe_id", id);
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        NewRecipeFragment fragment = new NewRecipeFragment();
        fragment.setArguments(bundle);
        fragmentTransaction.add(R.id.placeholder_fragment, fragment, "Home");
        fragmentTransaction.addToBackStack("Home");
        fragmentTransaction.commit();

    }

    class RecipeListAdapter extends RecyclerView.Adapter<RecipeViewHolder> {
        private final RecipeList recipeDetailsList;

        RecipeListAdapter(RecipeList recipeDetailsList) {
            this.recipeDetailsList = recipeDetailsList;
        }

        @Override
        public RecipeViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            final LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
            final View v = layoutInflater.inflate(R.layout.recipe_card, viewGroup, false);
            return new RecipeViewHolder(v);
        }

        @Override
        public void onBindViewHolder(RecipeViewHolder recipeViewHolder, int i) {
            try {
                Log.d("LOG", "RecipeHomeFragment:onBindViewHolder");
                Picasso.with(getContext())
                        .load(recipeDetailsList.getRecipes()[i].getImage_url())
                        .resize(200, 200)
                        .into((ImageView) recipeViewHolder.itemView.findViewById(R.id.recipe_image));


            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public int getItemCount() {
            return recipeDetailsList.getCount();
        }


    }

    class RecipeViewHolder extends RecyclerView.ViewHolder  implements View.OnClickListener  {
        TextView recipeTitle;
        ImageView recipeImage;
        TextView recipeSource;
        TextView socialRank;

        RecipeViewHolder(View itemView) {
            super(itemView);
            recipeImage = (ImageView) itemView.findViewById(R.id.recipe_image);
        }

        @Override
        public void onClick(View view) {
            Toast.makeText(view.getContext(), "position = " + getPosition(), Toast.LENGTH_SHORT).show();

            if (view == view.findViewById(R.id.recipe_image)) {

            }
        }
    }
}