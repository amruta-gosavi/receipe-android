package amruta.com.gallerydatalib.proxy;

import android.util.Log;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import amruta.com.gallerydatalib.model.RecipeList;


/**
 *
 * This class is used to call rest api service
 */



public class RecipeHttpClient {
	private static final String API_KEY = "key";
	private static final String SEARCH_KEY = "q";
	private static final String URI_DELIMETER = "?";
	private static final String KEY_DELIMETER = "&";
	private static final String PAGE_COUNT_KEY = "page";
	private static final String EQUAL = "=";
	private static final String SORT_BY_KEY = " sort";
	private static final String RECIPE_ID = "rId";
	private static final int HTTP_STATUS_OK=200;
	private String baseURLWithAPIKey;

	
	public RecipeHttpClient(String baseURL, String apiKey) {
		// Need to add some validation of the input parameters -- TODO
		StringBuilder baseURIBuilder = new StringBuilder();
		baseURLWithAPIKey = baseURIBuilder.append(baseURL)
										  .append(URI_DELIMETER)
										  .append(API_KEY)
										  .append(EQUAL)
										  .append(apiKey)
										  .toString();
	}

	public RecipeList getRecipeList(String searchKey, int pageCount, String sortBy) throws JsonParseException, JsonMappingException, IOException {
		StringBuilder outputBuilder = new StringBuilder();
		try {
			URL recipeListURL = buildRecipeURL(searchKey, pageCount, sortBy);
			HttpURLConnection conn = (HttpURLConnection) recipeListURL.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");

			if (conn.getResponseCode() != HTTP_STATUS_OK) {
				throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

			String singleRead;
			System.out.println("Output from Server .... \n");
			while ((singleRead = br.readLine()) != null) {
				outputBuilder.append(singleRead);
				System.out.println(singleRead);
			}

			conn.disconnect();

		} catch (MalformedURLException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		}
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		RecipeList recipeList = objectMapper.readValue(outputBuilder.toString(), RecipeList.class);
		return recipeList;

	}

	
	private URL buildRecipeURL(String searchWord, int pageCount, String sortBy) throws MalformedURLException, IOException {
		StringBuilder urlBuilder = new StringBuilder();
		
			urlBuilder.append(baseURLWithAPIKey)
					  .append(KEY_DELIMETER)
					  .append(SEARCH_KEY)
					  .append(EQUAL)
					  .append(searchWord);
		
		if (pageCount > 0) {
			urlBuilder.append(KEY_DELIMETER)
					  .append(PAGE_COUNT_KEY)
					  .append(EQUAL)
					  .append(pageCount);
		}
		// Since sortBy is pre-define, need to use enum - TODO
		if (sortBy != null && sortBy.length() == 1) {
			urlBuilder.append(KEY_DELIMETER)
						.append(SORT_BY_KEY)
						.append(EQUAL)
						.append(sortBy);
		}
		URL url = new URL(urlBuilder.toString());
		System.out.println(url);
		return url;
	}

	
	public static void main(String args []){
		RecipeHttpClient recipeHttpClient = new RecipeHttpClient("http://food2fork.com/api/search", "51152b2219c8471c51efb231ff9c5fbd");
		try {
			recipeHttpClient.getRecipeList("potato", 1, "r");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	//The code for calling particular recipe corresponding to particular rid

	public RecipeList getRecipeDetails(String recipeId) throws JsonParseException, JsonMappingException, IOException {
		StringBuilder outputBuilder = new StringBuilder();
		try {
			URL recipeListURL = buildRecipeDetailsURL(recipeId);
			HttpURLConnection conn = (HttpURLConnection) recipeListURL.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");

			if (conn.getResponseCode() != HTTP_STATUS_OK) {
				throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

			String singleRead;
			System.out.println("Output from Server .... \n");
			while ((singleRead = br.readLine()) != null) {
				outputBuilder.append(singleRead);
				System.out.println(singleRead);
			}

			conn.disconnect();

		} catch (MalformedURLException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		}

		Log.d("HttpClient",""+outputBuilder.toString());
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		RecipeList recipeList = objectMapper.readValue(outputBuilder.toString(), RecipeList.class);
		return recipeList;

	}

	private URL buildRecipeDetailsURL(String recipeId) throws MalformedURLException, IOException{

		StringBuilder urlBuilder = new StringBuilder();

		urlBuilder.append("http://food2fork.com/api/get?key=bc2aee703ff9912daf07c1ae4dda2675").
				append(KEY_DELIMETER)
				.append(RECIPE_ID).append(EQUAL)
				.append(recipeId);


		URL url = new URL(urlBuilder.toString());
		System.out.println(url);
		return url;
	}


}