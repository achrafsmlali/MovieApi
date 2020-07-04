package com.decathlon.moviesAPI.payload;

import javax.validation.constraints.NotBlank;

public class MovieRequest {

    @NotBlank
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
