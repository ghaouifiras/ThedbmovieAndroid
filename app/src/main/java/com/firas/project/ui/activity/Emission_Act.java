package com.firas.project.ui.activity;

import android.content.Intent;
import android.os.Bundle;

import android.widget.Toast;

import com.firas.project.R;
import com.firas.project.models.TvShow;
import com.firas.project.models.TvShowPageResult;
import com.firas.project.network.GetTvShowDataService;
import com.firas.project.network.RetrofitInstance;
import com.firas.project.ui.Utility.EndlessRecyclerViewScrollListener;

import com.firas.project.ui.Utility.TvShowClickListener;
import com.firas.project.ui.adapter.TvShowAdapter;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Emission_Act extends AppCompatActivity {
    public static final String API_KEY = "api_key";
    private static int totalPages;
    private Call<TvShowPageResult> call;
    private RecyclerView recyclerView;
    private List<TvShow> tvshowResults;
    private TvShowAdapter tvshowAdapter;
    int page=1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.emissionlayout);

        recyclerView = findViewById(R.id.rv_tvshow);
        GridLayoutManager manager = new GridLayoutManager(this, 2);
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return 1;
            }
        });
        recyclerView.setLayoutManager(manager);

        loadPage(1);
        EndlessRecyclerViewScrollListener scrollListener = new EndlessRecyclerViewScrollListener(manager) {

             @Override
            public void onLoadMore( int page, int totalItemsCount, RecyclerView view) {
                if ((page + 1) <= totalPages) {
                    loadPage(page + 1);
                }

            }
        };

        recyclerView.addOnScrollListener(scrollListener);

        call.enqueue(new Callback<TvShowPageResult>() {
            @Override
            public void onResponse(Call<TvShowPageResult> call, Response<TvShowPageResult> response) {

                if( page == 1) {
                    tvshowResults = response.body().getTvshowResult();
                    totalPages = response.body().getTotalPages();

                    tvshowAdapter= new TvShowAdapter(new TvShowClickListener() {
                        @Override
                        public void onTvShowClick(TvShow tvshow) {

                            Intent intent = new Intent(Emission_Act.this, DetailActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putSerializable("tvshow",tvshow);
                            intent.putExtras(bundle);
                            startActivity(intent);


                        }
                    }, tvshowResults);

                    recyclerView.setAdapter(tvshowAdapter);
                } else {
                    List<TvShow> tvshows = response.body().getTvshowResult();
                    for(TvShow tvshow : tvshows){
                        tvshowResults.add(tvshow);
                        tvshowAdapter.notifyItemInserted(tvshowResults.size() - 1);
                    }
                }

            }

            @Override
            public void onFailure(Call<TvShowPageResult> call, Throwable t) {
                Toast.makeText(Emission_Act.this, t.getMessage(), Toast.LENGTH_LONG).show();


            }




        });





    }

    private void loadPage(final int page) {
        GetTvShowDataService tvshowDataService = RetrofitInstance.getRetrofitInstance().create(GetTvShowDataService.class);


        call = tvshowDataService.getPopularTvShow(page,API_KEY);

    }

    public static String tvshowImagePathBuilder(String imagePath) {
        return "https://image.tmdb.org/t/p/" +
                "w500" +
                imagePath;
    }
}
