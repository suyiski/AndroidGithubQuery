package com.suizexpressions.androidgithubquery.data.remote;

import com.suizexpressions.androidgithubquery.data.model.GitHubResponse;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Eresuyi on 07/06/2018.
 */

public interface RepoService {

    @GET("/search/repositories?q=topic:android")
    Call<GitHubResponse> getRepos();

}
