package com.xianggao.healthassistant.utils;

import android.util.Log;

/**
 * 项目名：  HealthAssistant
 * 包名：    com.xianggao.healthassistant.utils
 * 文件名：  L
 * 创建者：  Shawn Gao
 * 创建时间：2017/2/18 - 22:17
 * 描述：    Log类
 */

public class L {
    private static boolean debug = true;
    private static String TAG = "my_test";

    public static void e(String msg) {
        if (debug)
            Log.e(TAG, msg);
    }
}
