package amruta.com.recipesonapprest.data;


import amruta.com.gallerydatalib.model.RecipeCategory;
import amruta.com.gallerydatalib.model.RecipeCategoryList;
import amruta.com.gallerydatalib.model.RecipeDetails;
import amruta.com.gallerydatalib.model.RecipeList;

/**
 * Created by AMRUTA on 8/17/16.
 * This class is used to casche the recipe data. It serializes data into the files for better performance.
 *
 */



public class DataCache
{

    public static RecipeCategoryList InstantiateRecipeObjectsForTesting()
    {
        RecipeCategoryList recipeList   =   new RecipeCategoryList();

        RecipeCategory[]recipeCategories   =   new RecipeCategory[3];

        RecipeCategory recipeCategory =   new RecipeCategory();
        recipeCategory.setCategory("Appetizers");
        recipeCategory.setImg_url("http://static.food2fork.com/42134272.jpg");

        RecipeCategory recipeCategory1 =   new RecipeCategory();
        recipeCategory1.setCategory("Soups");
        recipeCategory1.setImg_url("http://static.food2fork.com/potatosoup66f2.jpg");


        RecipeCategory recipeCategory2 =   new RecipeCategory();
        recipeCategory2.setCategory("Main course");
        recipeCategory2.setImg_url("http://static.food2fork.com/5337021451_e35711f343_o3593.jpg");

        recipeCategories[0]    =   recipeCategory;
        recipeCategories[1]    =   recipeCategory1;
        recipeCategories[2]    =   recipeCategory2;

        recipeList.setRecipeCategory(recipeCategories);
        recipeList.setCount(3);

        return recipeList;

    }


    public static RecipeList InstantiateRecipeDetailsForTesting()
    {
        RecipeList recipeList   =   new RecipeList();

        RecipeDetails recipeDetails[]   =   new RecipeDetails[1];

        RecipeDetails recipeDetails1    =   new RecipeDetails();
        recipeDetails1.setPublisher("Closet Cooking");
        recipeDetails1.setF2f_url("http://food2fork.com/view/35169");

        StringBuffer stringBuffer=  new StringBuffer();
        stringBuffer.append(
                "           2 tablespoons butter,\n" +
                "           1 pound chicken, \n" +
                "           1 onion, diced,\n" +
                "           2 carrots, diced,\n" +
                "           2 stalks celery, diced,\n" +
                "           2 cloves garlic, chopped,\n" +
                "           1/4 cup flour (rice flour for gluten free),\n" +
                "           3 cups chicken stock,\n" +
                "           hot sauce to taste,\n" +
                "           1 large yukon gold boiling potato,\n" +
                "           salt and pepper to taste,\n" +
                "           1 cup heavy cream,\n" +
                "           1/4 cup blue cheese, crumbled");



        recipeDetails1.setIngredients(stringBuffer.toString());

        recipeDetails1.setSource_url("http://www.closetcooking.com/2011/11/buffalo-chicken-chowder.html");
        recipeDetails1.setRecipe_id("35169");
        recipeDetails1.setImage_url("http://static.food2fork.com/Buffalo2BChicken2BChowder2B5002B0075c131caa8.jpg");
        recipeDetails1.setSocial_rank("100.0");
        recipeDetails1.setPublisher_url("http://closetcooking.com");
        recipeDetails1.setTitle("Buffalo Chicken Chowder");

        recipeDetails[0]    =   recipeDetails1;

        recipeList.setRecipes(recipeDetails);
        recipeList.setCount(1);

        return recipeList;

    }
}
