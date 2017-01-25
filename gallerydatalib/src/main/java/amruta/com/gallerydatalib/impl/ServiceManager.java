package amruta.com.gallerydatalib.impl;

import java.io.IOException;

import amruta.com.gallerydatalib.model.RecipeList;
import amruta.com.gallerydatalib.proxy.RecipeHttpClient;

/**
 * Created by AMRUTA on 1/10/17.
 */

public class ServiceManager
{


    RecipeList recipeDetailsArrayList = null;
    String BASE_URI = "http://food2fork.com/api/search";
    String API_KEY = "51152b2219c8471c51efb231ff9c5fbd";
    String SEARCH_KEY = "";
    int PAGE_CNT  =   1;





    public ServiceManager()
    {

    }

    public void init()
    {


    }

    public RecipeList getPhotoImagesFromServer(int page, String search_key, String sortBy)
    {

        RecipeHttpClient recipeHttpClient = new RecipeHttpClient(BASE_URI, API_KEY);
        try {
            recipeDetailsArrayList = recipeHttpClient.getRecipeList(search_key, page, sortBy);


        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return recipeDetailsArrayList;
    }

    public RecipeList getPhotoDetails(String recipe_id)
    {
        RecipeHttpClient recipeHttpClient = new RecipeHttpClient(BASE_URI, API_KEY);
        try {
            recipeDetailsArrayList = recipeHttpClient.getRecipeDetails(recipe_id);


        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return recipeDetailsArrayList;
    }

}
