package com.suizexpressions.androidgithubquery;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.suizexpressions.androidgithubquery.adapter.RepoAdapter;
import com.suizexpressions.androidgithubquery.data.model.GitHubResponse;
import com.suizexpressions.androidgithubquery.data.model.Item;
import com.suizexpressions.androidgithubquery.data.remote.GitHubApiUtils;
import com.suizexpressions.androidgithubquery.data.remote.RepoService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private final static String TAG = MainActivity.class.getSimpleName();

    private RepoAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private RepoService mService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mService = GitHubApiUtils.getRepoService();

        mRecyclerView = findViewById(R.id.rv_repos);
        mAdapter = new RepoAdapter(this, new ArrayList<Item>(0), new RepoAdapter.RepoItemListener() {

            @Override
            public void onRepoClick(long id) {

            }
        });

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setHasFixedSize(true);
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST);
        mRecyclerView.addItemDecoration(itemDecoration);

        fetchRepos();
    }

    public void fetchRepos() {

        mService.getRepos().enqueue(new Callback<GitHubResponse>() {
            @Override
            public void onResponse(Call<GitHubResponse> call, Response<GitHubResponse> response) {

                if(response.isSuccessful()) {
                    mAdapter.updateAnswers(response.body().getItems());
                    Log.d(TAG, "Repos loaded from API");
                }else {
                    // handle request errors depending on status code
                    int statusCode  = response.code();

                }
            }

            @Override
            public void onFailure(Call<GitHubResponse> call, Throwable t) {
                showErrorMessage();
                Log.d(TAG, "Error loading repos from API");

            }
        });
    }
    public void showErrorMessage() {
        Toast.makeText(this, "Error loading repos", Toast.LENGTH_SHORT).show();
    }
}
