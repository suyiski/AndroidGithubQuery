package com.suizexpressions.androidgithubquery.data.remote;

/**
 * Created by Eresuyi on 07/06/2018.
 */

public class GitHubApiUtils {

    private GitHubApiUtils() {}

    public static final String BASE_URL = "https://api.github.com";

    public static RepoService getRepoService() {

        return RetrofitClient.getClient(BASE_URL).create(RepoService.class);
    }


}
