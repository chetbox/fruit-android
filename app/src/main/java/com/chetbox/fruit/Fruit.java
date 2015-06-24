package com.chetbox.fruit;

import android.net.Uri;

public class Fruit {

    private String name;
    private String description;
    private String image;

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Uri getImageUri() {
        return (image != null) ? Uri.parse(image) : null;
    }

}
