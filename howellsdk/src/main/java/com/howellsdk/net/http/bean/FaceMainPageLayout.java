package com.howellsdk.net.http.bean;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class FaceMainPageLayout {
    @SerializedName("LayoutItem") ArrayList<MainPageLayoutItem> layoutItems;

    @Override
    public String toString() {
        return "FaceMainPageLayout{" +
                "layoutItems=" + layoutItems +
                '}';
    }

    public ArrayList<MainPageLayoutItem> getLayoutItems() {
        return layoutItems;
    }

    public void setLayoutItems(ArrayList<MainPageLayoutItem> layoutItems) {
        this.layoutItems = layoutItems;
    }

    public FaceMainPageLayout() {

    }

    public FaceMainPageLayout(ArrayList<MainPageLayoutItem> layoutItems) {

        this.layoutItems = layoutItems;
    }
}
