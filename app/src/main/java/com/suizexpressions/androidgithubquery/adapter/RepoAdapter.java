package com.suizexpressions.androidgithubquery.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.suizexpressions.androidgithubquery.R;
import com.suizexpressions.androidgithubquery.RepoDetailActivity;
import com.suizexpressions.androidgithubquery.data.model.Item;

import java.util.List;

/**
 * Created by Eresuyi on 07/06/2018.
 */

public class RepoAdapter extends RecyclerView.Adapter<RepoAdapter.RepoViewHolder> {

    private List<Item> mItems;
    private Context mContext;
    private RepoItemListener mItemListener;

    public class RepoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView mTitleTv;
        public TextView mDescTextView;
        private ImageView mImageView;

        RepoItemListener mItemListener;

        public RepoViewHolder(View itemView, RepoItemListener itemListener) {
            super(itemView);
            mTitleTv = itemView.findViewById(R.id.tv_title);
            mDescTextView = itemView.findViewById(R.id.tv_user_id);
            mImageView = itemView.findViewById(R.id.user_image);

            this.mItemListener = itemListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            final Item item = getItem(getAdapterPosition());
            Intent intent = new Intent(mContext, RepoDetailActivity.class);
            intent.putExtra("item", item);
            mContext.startActivity(intent);
            this.mItemListener.onRepoClick(item.getId());

            notifyDataSetChanged();

        }

    }

    public RepoAdapter(Context context, List<Item> posts, RepoItemListener itemListener) {
        mItems = posts;
        mContext = context;
        mItemListener = itemListener;
    }

    @Override
    public RepoAdapter.RepoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View postView = inflater.inflate(R.layout.repo_item_rows, parent, false);

        RepoViewHolder viewHolder = new RepoViewHolder(postView, this.mItemListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RepoAdapter.RepoViewHolder holder, int position) {

        Item item = mItems.get(position);
        holder.mTitleTv.setText("Repo Name: " + item.getName());
        holder.mDescTextView.setText("User ID: " + item.getId());
        Picasso.with(mContext)
                .load(item.getOwner().getAvatarUrl())
                .placeholder(R.drawable.placeholder)
                .resize(175, 175)
                .centerCrop()
                .into(holder.mImageView);
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public void updateAnswers(List<Item> items) {
        mItems = items;
        notifyDataSetChanged();
    }

    private Item getItem(int adapterPosition) {
        return mItems.get(adapterPosition);
    }

    public interface RepoItemListener {
        void onRepoClick(long id);
    }

}


