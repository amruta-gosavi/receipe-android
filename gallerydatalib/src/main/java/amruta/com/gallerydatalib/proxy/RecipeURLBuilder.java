package amruta.com.gallerydatalib.proxy;


/**
 * Created by AMRUTA on 1/10/17.
 */


public class RecipeURLBuilder {
	private RecipeURLDefinition recipeURLDefinition;
	// Forcing singleton
	public RecipeURLBuilder(){
		recipeURLDefinition = new RecipeURLDefinition();
	}
	
	public RecipeURLDefinition addHost(){
		
		return recipeURLDefinition;
	}
	private static class RecipeURLDefinition{
		
		private String hostName;
		private String scheme;
		private int port;
		private String methodName;
		private String apiKey;
		private String searchKey;
		private String sortBy;
		private int pageNo;
		
	
		public RecipeURLDefinition setHostName(String hostName) {
			this.hostName = hostName;
			return this;
		}
		
		public RecipeURLDefinition setScheme(String scheme) {
			this.scheme = scheme;
			return this;
		}
		
		public RecipeURLDefinition setPort(int port) {
			this.port = port;
			return this;
		}
		
		public RecipeURLDefinition setMethodName(String methodName) {
			this.methodName = methodName;
			return this;
		}
		
		public RecipeURLDefinition setApiKey(String apiKey) {
			this.apiKey = apiKey;
			return this;
		}
	
		public RecipeURLDefinition setSearchKey(String searchKey) {
			this.searchKey = searchKey;
			return this;
		}
		
		public RecipeURLDefinition setSortBy(String sortBy) {
			this.sortBy = sortBy;
			return this;
		}
		
		public RecipeURLDefinition setPageNo(int pageNo) {
			this.pageNo = pageNo;
			return this;
		}
		
		
	}
}
