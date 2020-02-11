package com.firas.project.ui.viewholder;

import android.content.res.Resources;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.firas.project.R;
import com.firas.project.models.TvShow;
import com.firas.project.ui.Utility.TvShowClickListener;
import com.squareup.picasso.Picasso;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

import static com.firas.project.ui.activity.Emission_Act.tvshowImagePathBuilder;

public class TvShowViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.iv_tvshow_poster) ImageView mTvShowPoster;
    @BindView(R.id.cv_tvshow_card) CardView mTvShowCard;

    public TvShowViewHolder(final View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(final TvShow tvshow, final TvShowClickListener tvshowClickListener) {

        mTvShowCard.setLayoutParams(new ViewGroup.LayoutParams(getScreenWidth()/2, getMeasuredPosterHeight(getScreenWidth()/2)));

        Picasso.with(mTvShowPoster.getContext()).load(tvshowImagePathBuilder(tvshow.getPosterPath())).placeholder(R.drawable.placeholder).fit().centerCrop().into(mTvShowPoster);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvshowClickListener.onTvShowClick(tvshow);
            }
        });



    }

    private int getScreenWidth() {
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }
    private int getMeasuredPosterHeight(int width) {
        return (int) (width * 1.5f);
    }

}
