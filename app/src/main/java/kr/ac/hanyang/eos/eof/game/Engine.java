package kr.ac.hanyang.eos.eof.game;

import android.content.Context;
import android.content.res.Resources;

/**
 * Created by space on 2017-09-07.
 */

public class Engine {
    private static Context context;
    private static Resources resources;

    public static void init(Context context) {
        Engine.context = context;
        Engine.resources = context.getResources();
    }

    public static Context getContext() {
        return context;
    }

    public static Resources getResources() {
        return resources;
    }
}
