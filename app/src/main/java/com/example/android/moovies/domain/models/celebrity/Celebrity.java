package com.example.android.moovies.domain.models.celebrity;

import com.example.android.moovies.domain.models.movie.Images;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Celebrity {

    @SerializedName("birthday")
    @Expose
    private String birthday;
    @SerializedName("tagged_images")
    @Expose
    private TaggedImages taggedImages;
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("place_of_birth")
    @Expose
    private String placeOfBirth;
    @SerializedName("homepage")
    @Expose
    private Object homepage;
    @SerializedName("profile_path")
    @Expose
    private String profilePath;
    @SerializedName("imdb_id")
    @Expose
    private String imdbId;
    @SerializedName("deathday")
    @Expose
    private String deathday;
    @SerializedName("images")
    @Expose
    private Images images;
    @SerializedName("external_ids")
    @Expose
    private ExternalIds externalIds;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("also_known_as")
    @Expose
    private List<String> alsoKnownAs = null;
    @SerializedName("biography")
    @Expose
    private String biography;
    @SerializedName("tv_credits")
    @Expose
    private TvCredits tvCredits;
    @SerializedName("movie_credits")
    @Expose
    private MovieCredits movieCredits;
    @SerializedName("gender")
    @Expose
    private int gender;
    @SerializedName("adult")
    @Expose
    private boolean adult;
    @SerializedName("popularity")
    @Expose
    private float popularity;

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public TaggedImages getTaggedImages() {
        return taggedImages;
    }

    public void setTaggedImages(TaggedImages taggedImages) {
        this.taggedImages = taggedImages;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlaceOfBirth() {
        return placeOfBirth;
    }

    public void setPlaceOfBirth(String placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
    }

    public Object getHomepage() {
        return homepage;
    }

    public void setHomepage(Object homepage) {
        this.homepage = homepage;
    }

    public String getProfilePath() {
        return profilePath;
    }

    public void setProfilePath(String profilePath) {
        this.profilePath = profilePath;
    }

    public String getImdbId() {
        return imdbId;
    }

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    public String getDeathday() {
        return deathday;
    }

    public void setDeathday(String deathday) {
        this.deathday = deathday;
    }

    public Images getImages() {
        return images;
    }

    public void setImages(Images images) {
        this.images = images;
    }

    public ExternalIds getExternalIds() {
        return externalIds;
    }

    public void setExternalIds(ExternalIds externalIds) {
        this.externalIds = externalIds;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getAlsoKnownAs() {
        return alsoKnownAs;
    }

    public void setAlsoKnownAs(List<String> alsoKnownAs) {
        this.alsoKnownAs = alsoKnownAs;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public TvCredits getTvCredits() {
        return tvCredits;
    }

    public void setTvCredits(TvCredits tvCredits) {
        this.tvCredits = tvCredits;
    }

    public MovieCredits getMovieCredits() {
        return movieCredits;
    }

    public void setMovieCredits(MovieCredits movieCredits) {
        this.movieCredits = movieCredits;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public boolean isAdult() {
        return adult;
    }

    public void setAdult(boolean adult) {
        this.adult = adult;
    }

    public float getPopularity() {
        return popularity;
    }

    public void setPopularity(float popularity) {
        this.popularity = popularity;
    }

}