package com.sureit.mymovies;

import android.app.ProgressDialog;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String BASE_URL_MOVIE = "https://api.themoviedb.org/3/discover/movie?";
    private static final String API_KEY = "d030f9aa381a82c96ecda4ad31fb6af5";
    private static final String POPULARITY = "popularity.desc";
    private static final String RATING = "vote_average.desc";


    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<MovieList> movieLists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewMovie);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        movieLists = new ArrayList<>();

        try {
            loadUrlData(null);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.sort_settings,menu);
    return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.allmovies:
                item.setChecked(true);
                try {
                    movieLists.clear();
                    loadUrlData(null);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                return true;

            case R.id.popular:
                item.setChecked(true);
                try {
                    movieLists.clear();
                    loadUrlData(POPULARITY);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                return true;

            case R.id.rated:
                item.setChecked(true);
                try {
                    movieLists.clear();
                    loadUrlData(RATING);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void loadUrlData(String orderBy) throws MalformedURLException {

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                buildUri(orderBy), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                progressDialog.dismiss();

                try {

                    JSONObject jsonObject = new JSONObject(response);

                    JSONArray array = jsonObject.getJSONArray("results");

                    for (int i = 0; i < array.length(); i++){

                        JSONObject jo = array.getJSONObject(i);

                        MovieList developers = new MovieList(jo.getString("title"), jo.getString("overview"),
                                jo.getString("poster_path"),jo.getString("vote_average"),jo.getString("release_date"));
                        movieLists.add(developers);

                    }

                    adapter = new MovieAdapter(movieLists, getApplicationContext());
                    recyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                } catch (JSONException e) {

                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(MainActivity.this, "Error" + error.toString(), Toast.LENGTH_SHORT).show();

            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    public String buildUri(String sortby){

        final String QUERY_SORT_BY = "sort_by";
        final String QUERY_APPKEY = "api_key";
        final String QUERY_VOTE_COUNT = "vote_count.gte";
        final String PARAM_MIN_VOTES = "50";

        Uri builtUri = Uri.parse(BASE_URL_MOVIE)
                .buildUpon()
                .appendQueryParameter(QUERY_SORT_BY, sortby)
                .appendQueryParameter(QUERY_VOTE_COUNT, PARAM_MIN_VOTES)
                .appendQueryParameter(QUERY_APPKEY,API_KEY)
                .build();
        return builtUri.toString();

    }
}
