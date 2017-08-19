package com.example.android.moovies.data.models.movie;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Changes {

    @SerializedName("changes")
    @Expose
    private List<Object> changes = null;

    public List<Object> getChanges() {
        return changes;
    }

    public void setChanges(List<Object> changes) {
        this.changes = changes;
    }

}