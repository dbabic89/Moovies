package com.example.android.moovies.domain.models.search;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class SearchResults {

    @SerializedName("page")
    @Expose
    private int page;
    @SerializedName("total_results")
    @Expose
    private int totalResults;
    @SerializedName("total_pages")
    @Expose
    private int totalPages;
    @SerializedName("results")
    @Expose
    private List<SearchListItem> results = null;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public List<SearchListItem> getResults() {
        return results;
    }

    private void setResults(List<SearchListItem> results) {
        this.results = results;
    }

    public static class SearchResultsDeserializer implements JsonDeserializer<SearchResults> {

        @Override
        public SearchResults deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            SearchResults searchResults = new Gson().fromJson(json, SearchResults.class);
            List<SearchListItem> searchListItems = new ArrayList<>();

            JsonObject jsonObject = json.getAsJsonObject();
            JsonArray searchResult = jsonObject.getAsJsonArray("results");
            for (int i = 0; i < searchResult.size(); i++) {
                JsonObject result = searchResult.get(i).getAsJsonObject();
                String type = result.getAsJsonPrimitive("media_type").getAsString();

                switch (type) {
                    case "movie": {

                        String poster = "";

                        try {
                            poster = result.getAsJsonPrimitive("poster_path").getAsString();
                        } catch (ClassCastException e) {
                            Log.i("TAGA", e.getMessage());
                        }
                        SearchListItem searchListItem = new SearchListItem(result.getAsJsonPrimitive("id").getAsInt(),
                                result.getAsJsonPrimitive("title").getAsString(),
                                poster,
                                result.getAsJsonPrimitive("media_type").getAsString());

                        searchListItems.add(searchListItem);
                        break;
                    }
                    case "tv": {

                        String poster = "";

                        try {
                            poster = result.getAsJsonPrimitive("poster_path").getAsString();
                        } catch (ClassCastException e) {
                            Log.i("TAGA", e.getMessage());
                        }

                        SearchListItem searchListItem = new SearchListItem(result.getAsJsonPrimitive("id").getAsInt(),
                                result.getAsJsonPrimitive("name").getAsString(),
                                poster,
                                result.getAsJsonPrimitive("media_type").getAsString());

                        searchListItems.add(searchListItem);
                        break;
                    }
                    default: {


                        String poster = "";

                        try {
                            poster = result.getAsJsonPrimitive("profile_path").getAsString();
                        } catch (ClassCastException e) {
                            Log.i("TAGA", e.getMessage());
                        }

                        SearchListItem searchListItem = new SearchListItem(result.getAsJsonPrimitive("id").getAsInt(),
                                result.getAsJsonPrimitive("name").getAsString(),
                                poster,
                                result.getAsJsonPrimitive("media_type").getAsString());

                        searchListItems.add(searchListItem);
                        break;
                    }
                }
            }
            searchResults.setPage(jsonObject.getAsJsonPrimitive("page").getAsInt());
            searchResults.setResults(searchListItems);
            searchResults.setTotalResults(jsonObject.getAsJsonPrimitive("total_results").getAsInt());
            searchResults.setTotalPages(jsonObject.getAsJsonPrimitive("total_pages").getAsInt());

            return searchResults;
        }
    }

}