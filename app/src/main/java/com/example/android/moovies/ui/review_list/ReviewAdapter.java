package com.example.android.moovies.ui.review_list;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.moovies.R;
import com.example.android.moovies.data.models.movie.Review;

import java.util.List;

class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ReviewViewHolder> {

    private List<Review> reviews;

    ReviewAdapter(List<Review> reviews) {
        this.reviews = reviews;
    }

    @Override
    public ReviewViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_review, parent, false);
        return new ReviewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ReviewViewHolder holder, final int position) {

        Review review = reviews.get(position);

        holder.reviewAuthor.setText(review.getAuthor());
        holder.reviewContent.setText(String.valueOf(review.getContent()));

    }

    @Override
    public int getItemCount() {
        return reviews.size();
    }

    public void add(Review movie) {
        reviews.add(movie);
        notifyItemInserted(reviews.size() - 1);
    }

    class ReviewViewHolder extends RecyclerView.ViewHolder {

        TextView reviewAuthor;
        TextView reviewContent;

        ReviewViewHolder(View v) {
            super(v);

            reviewAuthor = (TextView) v.findViewById(R.id.text_review_author);
            reviewContent = (TextView) v.findViewById(R.id.text_review_content);
        }

    }


}