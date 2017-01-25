package amruta.com.gallerydatalib.model;

public class RecipeList {
	private int count;
	private RecipeDetails[] recipes;

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public RecipeDetails[] getRecipes() {
		return recipes;
	}

	public void setRecipes(RecipeDetails[] recipes) {
		this.recipes = recipes;
	}

	@Override
	public String toString() {
		return "ClassPojo [count = " + count + ", recipes = " + recipes + "]";
	}
}