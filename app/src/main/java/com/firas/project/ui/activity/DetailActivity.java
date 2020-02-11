package com.firas.project.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.firas.project.R;
import com.firas.project.models.TvDescription;
import com.firas.project.models.TvShow;
import com.firas.project.network.GetTvDescriptionDataService;
import com.firas.project.network.RetrofitInstance;
import com.squareup.picasso.Picasso;

import static com.firas.project.ui.activity.Emission_Act.API_KEY;
import static com.firas.project.ui.activity.Emission_Act.tvshowImagePathBuilder;

public class DetailActivity extends AppCompatActivity {
   //* @BindView(R.id.movie_activity_poster) ImageView mMoviePoster;
   @BindView(R.id.tv) TextView tv;
   @BindView(R.id.img)ImageView imgv;
@BindView(R.id.overview)TextView overview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        TvShow tvshow = (TvShow) bundle.getSerializable("tvshow");
        Toast.makeText(DetailActivity.this,tvshow.getId()+"", Toast.LENGTH_SHORT).show();
        populateActivity(tvshow);
       getTvshow(tvshow.getId());


    }

    private void getTvshow(int tvshowid) {
        GetTvDescriptionDataService tvdescriptionrService = RetrofitInstance.getRetrofitInstance().create(GetTvDescriptionDataService.class);
        Call<TvDescription> call = tvdescriptionrService.getTvDescription(tvshowid, API_KEY);


        call.enqueue(new Callback<TvDescription>() {
            @Override
            public void onResponse(Call<TvDescription> call, Response<TvDescription> response) {
                String ch1 = response.body().getName();
                String ch =response.body().getOverview();
                tv.setText(ch1);
                overview.setText(ch);

            }

            @Override
            public void onFailure(Call<TvDescription> call, Throwable t) {
                Toast.makeText(DetailActivity.this, "something_went_wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void populateActivity(TvShow tvshow){
        Picasso.with(this).load(tvshowImagePathBuilder(tvshow.getPosterPath())).into(imgv);
}


}
