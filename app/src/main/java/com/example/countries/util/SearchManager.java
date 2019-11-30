package com.example.countries.util;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.SearchView;

import java.util.Timer;
import java.util.TimerTask;

public class SearchManager {

    private static final String TAG = SearchView.class.getSimpleName();

    private static long lastUpdatedTimeMillis = 0;

    private static Timer timer = new Timer();

    private static QueryListener queryListener;

    public interface QueryListener {
        void onQueryChanged(String newQuery);
    }

    private SearchManager() {
    }

    public static void updateQuery(String newQuery) {
        long now = System.currentTimeMillis();

        if (lastUpdatedTimeMillis == 0) lastUpdatedTimeMillis = now;

        if (now - lastUpdatedTimeMillis < 2000) {
            timer.cancel();
            timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    Log.d(TAG, "updateQuery: New request started");
                    new Handler(Looper.getMainLooper()).post(() -> queryListener.onQueryChanged(newQuery));

                }
            }, 1000);
        }

        lastUpdatedTimeMillis = now;

    }

    public static void setQueryListener(QueryListener newQueryListener) {
        queryListener = newQueryListener;
    }
}
