package com.example.android.moovies.utils;

import com.example.android.moovies.domain.models.movie.Keyword;
import com.example.android.moovies.domain.models.movie.ProductionCountry;
import com.example.android.moovies.domain.models.movie.SpokenLanguage;
import com.example.android.moovies.domain.models.mtv.Crew;
import com.example.android.moovies.domain.models.mtv.Genre;
import com.example.android.moovies.domain.models.mtv.Image;
import com.example.android.moovies.domain.models.mtv.Images;
import com.example.android.moovies.domain.models.mtv.ProductionCompany;
import com.example.android.moovies.domain.models.tv.KeywordsResults;
import com.example.android.moovies.domain.models.tv.Network;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StringFormating {

    public static List<String> getGenres(List<Genre> genres) {

        ArrayList<String> allGenres = new ArrayList<>();

        for (int i = 0; i < genres.size(); i++) {

            Genre genre = genres.get(i);
            String currentGenre = genre.getName();

            if (currentGenre.equals("Science Fiction")) currentGenre = "Sci-Fi";
            allGenres.add(i, currentGenre);
        }

        return allGenres;

    }

    public static String getNetworks(List<Network> networks) {

        String network = "";

        for (int i = 0; i < networks.size(); i++) {

            String currentNetwork = networks.get(i).getName();

            if (i == 0) network = network + currentNetwork;
            else network = network + ", " + currentNetwork;
        }

        return network;

    }
    public static List<String> getKeywords(List<Keyword> keywordsList) {

        ArrayList<String> allKeywords = new ArrayList<>();

        for (int i = 0; i < keywordsList.size(); i++) {

            Keyword keyword = keywordsList.get(i);
            String currentKeyword = keyword.getName();
            allKeywords.add(i, currentKeyword);

        }

        return allKeywords;

    }


    public static List<String> getKeywordResults(List<KeywordsResults> keywordsList) {

        ArrayList<String> allKeywords = new ArrayList<>();

        for (int i = 0; i < keywordsList.size(); i++) {

            KeywordsResults keyword = keywordsList.get(i);
            String currentKeyword = keyword.getName();
            allKeywords.add(i, currentKeyword);

        }

        return allKeywords;

    }

    public static String getDirectors(List<Crew> crewList) {

        String directors = "";

        for (Crew crew : crewList) {

            if (crew.getJob().equals("Director")) {
                if (directors.equals("")) directors = " " + crew.getName();
                else directors = directors + "\n " + crew.getName();
            }
        }

        return directors;

    }

    public static String currencyFormating(long value) {

        DecimalFormat decimalFormat = new DecimalFormat("#,###,###");
        return decimalFormat.format(value);

    }

    public static String timeFormating(int duration) {

        int hours = duration / 60;
        int minutes = duration % 60;

        return " " + hours + "h " + minutes + "m";

    }

    public static String dateFormating(String date) {

        DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat outputFormat = new SimpleDateFormat("d.M.yyyy");
        Date currentDate = null;

        try {
            currentDate = inputFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return " " + outputFormat.format(currentDate);

    }

    public static String countriesFormating(List<ProductionCountry> countryList) {

        String countries = "";

        for (int i = 0; i < countryList.size(); i++) {

            String country = countryList.get(i).getName();

            if (i == 0) countries = countries + country;
            else countries = countries + ", " + country;
        }

        return countries;
    }

    public static String companyFormating(List<ProductionCompany> companyList) {

        String companies = "";

        for (int i = 0; i < companyList.size(); i++) {

            String company = companyList.get(i).getName();

            if (i == 0) companies = companies + company;
            else companies = companies + ", " + company;
        }

        return companies;
    }

    public static String getSpokenLanguage(List<SpokenLanguage> spokenLanguages) {

        String languages = "";

        for (int i = 0; i < spokenLanguages.size(); i++) {

            String language = spokenLanguages.get(i).getName();

            if (i == 0) languages = languages + language;
            else languages = languages + ", " + language;
        }

        return languages;
    }

    public static List<Image> getImageList(Images images){
        final List<Image> imageList = new ArrayList<>();

        for (int i = 0; i < images.getBackdrops().size(); i++) {

            imageList.add(images.getBackdrops().get(i));
        }

        for (int i = 0; i < images.getPosters().size(); i++) {

            imageList.add(images.getPosters().get(i));
        }

        return imageList;
    }


    public static List<String> getImageListtoString(Images images){
        final List<String> imageList = new ArrayList<>();

        for (int i = 0; i < images.getBackdrops().size(); i++) {

            imageList.add(images.getBackdrops().get(i).getFilePath());
        }

        for (int i = 0; i < images.getPosters().size(); i++) {

            imageList.add(images.getPosters().get(i).getFilePath());
        }

        return imageList;
    }
}
