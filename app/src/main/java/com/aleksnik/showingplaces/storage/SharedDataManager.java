package com.aleksnik.showingplaces.storage;

import android.content.Context;
import android.content.SharedPreferences;



public class SharedDataManager {

    private static final int DEFAULT_COVER_AREA = 1000;


    private static final String NAME = "Showing-places";
    private static final String COVER_AREA = "coverArea";


    private final SharedPreferences mSharedPreferences;

    public SharedDataManager (Context mContext) {
        mSharedPreferences = mContext.getSharedPreferences(NAME, Context.MODE_PRIVATE);
        mSharedPreferences.edit().putInt(COVER_AREA, DEFAULT_COVER_AREA).apply();
    }

    public void setCoverArea (int coverArea) {
        mSharedPreferences.edit().putInt(COVER_AREA,coverArea).apply();
    }

    public int getCoverArea() {
            return mSharedPreferences.getInt(COVER_AREA, 0);
        }


    public void sharedHelperClear() {
            mSharedPreferences.edit().clear().apply();
        }
}
