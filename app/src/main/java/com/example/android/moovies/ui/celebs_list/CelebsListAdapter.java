package com.example.android.moovies.ui.celebs_list;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.android.moovies.R;
import com.example.android.moovies.domain.models.mtv.Cast;
import com.squareup.picasso.Picasso;

import java.util.List;

class CelebsListAdapter extends RecyclerView.Adapter<CelebsListAdapter.CelebsHolder> {

    private List<Cast> cast;
    private Context context;
    private RecyclerViewInterface recyclerViewInterface;

    CelebsListAdapter(List<Cast> cast, Context context) {
        this.cast = cast;
        this.context = context;
    }

    @Override
    public CelebsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_celeb, parent, false);
        return new CelebsHolder(view);
    }

    @Override
    public void onBindViewHolder(CelebsHolder holder, int position) {
        holder.text_castName.setText(cast.get(position).getName());
        holder.text_castCharacter.setText("as " + cast.get(position).getCharacter());

        if (cast.get(position).getCharacter().isEmpty()) {
            holder.text_castCharacter.setText("-");
        }

        Picasso.with(context).load("https://image.tmdb.org/t/p/w185" + cast.get(position).getProfilePath()).into(holder.image_castPortrait);

        if (holder.image_castPortrait.getDrawable() == null) {
            holder.image_castPortrait.setMinimumHeight(278);
            holder.image_castPortrait.setMinimumWidth(185);
            holder.image_castPortrait.setScaleType(ImageView.ScaleType.CENTER_CROP);
        }
    }

    @Override
    public int getItemCount() {
        return cast.size();
    }

    public interface RecyclerViewInterface {

        void onCastClick(int position);

    }

    public void setRecyclerViewInterface(final CelebsListAdapter.RecyclerViewInterface recyclerViewInterface) {
        this.recyclerViewInterface = recyclerViewInterface;
    }

    class CelebsHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        LinearLayout list_item_cast;
        ImageView image_castPortrait;
        TextView text_castName;
        TextView text_castCharacter;

        CelebsHolder(View itemView) {
            super(itemView);
            list_item_cast = (LinearLayout) itemView.findViewById(R.id.list_item_cast);
            list_item_cast.setOnClickListener(this);
            image_castPortrait = (ImageView) itemView.findViewById(R.id.image_castPortrait);
            text_castName = (TextView) itemView.findViewById(R.id.text_castName);
            text_castCharacter = (TextView) itemView.findViewById(R.id.text_castCharacter);
        }

        @Override
        public void onClick(View view) {
            recyclerViewInterface.onCastClick(getAdapterPosition());
        }
    }
}