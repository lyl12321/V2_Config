package com.lqwq.v2_config.ui.slideshow;

import android.graphics.drawable.Drawable;

public class AppList {
    private Drawable drawable;
    private String appName;
    private String appUID;

    public AppList(Drawable drawable,String appName,String  appUID){
        this.drawable = drawable;
        this.appName = appName;
        this.appUID = appUID;
    }

    public Drawable getDrawable() {
        return drawable;
    }

    public void setDrawable(Drawable drawable) {
        this.drawable = drawable;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getAppUID() {
        return appUID;
    }

    public void setAppUID(String appUID) {
        this.appUID = appUID;
    }
}
