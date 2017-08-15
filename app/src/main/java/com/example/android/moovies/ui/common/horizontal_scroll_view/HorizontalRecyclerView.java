package com.example.android.moovies.ui.common.horizontal_scroll_view;

public class HorizontalRecyclerView {

    public HorizontalRecyclerView() {
    }

//    public HorizontalRecyclerView(Context context, int i, LinearLayout linearLayout, String string) {
//
//        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        CardView cardView = (CardView) layoutInflater.inflate(R.layout.horizontal_list, null);
//
//        final TextView textView = (TextView) cardView.findViewById(R.id.text_movie_list);
//        final RecyclerView recyclerView = (RecyclerView) cardView.findViewById(R.id.recycler_view_horizontal);
//        final Button button = (Button) cardView.findViewById(R.id.button_see_more);
//
//        final TmdbApiData tmdbApiData = new TmdbApiData(context);
//        List<MovieListResult> movies = null;
//
//        switch (i){
//            case 0:
//                movies = tmdbApiData.getNowPlayingMovies(recyclerView);
//                break;
//            case 1:
//                movies = tmdbApiData.getUpcomingMovies();
//                break;
//            case 2:
//                movies = tmdbApiData.getPopularMovies();
//                break;
//            case 3:
//                movies = tmdbApiData.getTopRatedMovies();
//                break;
//        }
//
//        textView.setText(string);
//        textView.setTextSize(20);
//
//        button.setVisibility(View.INVISIBLE);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                fragmentCommunication.movieList(i, 0);
//            }
//        });
//
//        IconAdapter movieListAdapter = new IconAdapter(movies, R.layout.icon_movie, context);
//        recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
//        recyclerView.setAdapter(movieListAdapter);
//        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
//
//            @Override
//            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
//                super.onScrollStateChanged(recyclerView, newState);
//            }
//        });
//
//        for (MovieListResult movie : movies) {
//            Log.i("TAG", "adapter" + movie.getTitle());
//        }
//
//        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
//                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//        layoutParams.setMargins(0, 0, 0, 16);
//
//        linearLayout.addView(cardView, layoutParams);
//    }

}
