package com.suizexpressions.androidgithubquery;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.suizexpressions.androidgithubquery.data.model.Item;


public class RepoDetailActivity extends AppCompatActivity {
    private CollapsingToolbarLayout mCollapsingToolbarLayout;
    private TextView mUsernameDetails;
    private TextView mUserGitHubUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repo_detail);

         Item item = getIntent().getParcelableExtra("item");

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        mCollapsingToolbarLayout = findViewById(R.id.toolbar_layout);
        mCollapsingToolbarLayout.setTitle("");

        mUsernameDetails = findViewById(R.id.user_name_details);
        mUserGitHubUrl = findViewById(R.id.github_profile_url);

        mUsernameDetails.setText(getString(R.string.user_name_full, item.getUrl()));
        mUserGitHubUrl.setText(getString(R.string.user_url_full, item.getDescription()));

    }
}
