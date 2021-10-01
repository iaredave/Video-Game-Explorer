package com.example.videogameexplorer;

import com.google.gson.annotations.Expose;

public class Results {
    @Expose
    private String name;
    @Expose
    private String background_image;

    public Results(String name, String background_image) {
        this.name = name;
        this.background_image = background_image;
    }

    public String getName() {
        return name;
    }

    public String getBackground_image() {
        return background_image;
    }
}
