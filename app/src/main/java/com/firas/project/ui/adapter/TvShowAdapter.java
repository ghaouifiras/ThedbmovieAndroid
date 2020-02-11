package com.firas.project.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firas.project.R;
import com.firas.project.models.TvShow;
import com.firas.project.ui.Utility.TvShowClickListener;
import com.firas.project.ui.viewholder.TvShowViewHolder;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TvShowAdapter extends RecyclerView.Adapter <TvShowViewHolder> {
    private final TvShowClickListener tvshowClickListener;
    private final List<TvShow> tvshowList;

    public TvShowAdapter(TvShowClickListener tvshowClickListener, List<TvShow> tvshowList) {
        this.tvshowClickListener = tvshowClickListener;
        this.tvshowList = tvshowList;
    }

    @NonNull
    @Override
    public TvShowViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tvshow_card_view, parent, false);
        return new TvShowViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull TvShowViewHolder holder, int position) {
        TvShow tvshow = this.tvshowList.get(position);
        holder.bind(tvshow, tvshowClickListener);
    }

    @Override
    public int getItemCount() {
        return this.tvshowList.size();    }

    @Override
    public void onViewRecycled(@NonNull TvShowViewHolder holder) {
        super.onViewRecycled(holder);
    }
}
