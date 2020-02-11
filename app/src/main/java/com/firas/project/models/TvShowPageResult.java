package com.firas.project.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class TvShowPageResult implements Serializable {
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
    private ArrayList<TvShow> tvshowResult;

    public TvShowPageResult(Integer page, Integer totalResults, Integer totalPages, ArrayList<TvShow> tvshowResult) {
        this.page = page;
        this.totalResults = totalResults;
        this.totalPages = totalPages;
        this.tvshowResult = tvshowResult;
    }

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

    public ArrayList<TvShow> getTvshowResult() {
        return tvshowResult;
    }

    public void setTvshowResult(ArrayList<TvShow> tvshowResult) {
        this.tvshowResult = tvshowResult;
    }
}
