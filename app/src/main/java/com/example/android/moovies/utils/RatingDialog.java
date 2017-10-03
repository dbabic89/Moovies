package com.example.android.moovies.utils;

import android.app.Dialog;
import android.content.Context;
import android.widget.Button;
import android.widget.TextView;

import com.example.android.moovies.R;

public class RatingDialog extends Dialog {

    private int current = 0;
    private Button b1, b2;

    public RatingDialog(final Context context, TextView textUserRating, String title, String rateIt) {
        super(context);

        this.setContentView(R.layout.dialog__rating);
        this.setTitle(title);
        com.hedgehog.ratingbar.RatingBar ratingBar = (com.hedgehog.ratingbar.RatingBar) this.findViewById(R.id.rating_bar);
        ratingBar.setOnRatingChangeListener(new com.hedgehog.ratingbar.RatingBar.OnRatingChangeListener() {
            @Override
            public void onRatingChange(float RatingCount) {

                current = (int) RatingCount;
            }
        });

        b1 = (Button) this.findViewById(R.id.button3);
        b2 = (Button) this.findViewById(R.id.button4);

        if (textUserRating.getText().toString().equals(rateIt)) ratingBar.setStar(0);
        else ratingBar.setStar(Integer.parseInt(textUserRating.getText().toString()));

        this.show();
    }
    public int getCurrentRating(){
        return current;
    }


    public Button getPositiveButton(){
        return b1;
    }


    public Button getNegativeButton(){
        return b2;
    }

}
