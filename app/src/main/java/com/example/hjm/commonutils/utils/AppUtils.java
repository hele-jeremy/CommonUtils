package com.example.hjm.commonutils.utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.provider.Settings;
import android.telephony.TelephonyManager;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.UUID;

/**
 * Created by hjm on 2017/1/19 13:08.
 * App相关工具类
 */

public class AppUtils {

    protected static final String PREFS_FILE = "device_id.xml";
    protected static final String PREFS_DEVICE_ID = "device_id";

    private AppUtils() {
        throw new UnsupportedOperationException("app工具类不能初始化对象");
    }

    /**
     * 获取应用程序名称
     */
    public static String getAppName(Context context) {
        if (context == null) {
            return null;
        }
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            int labelRes = packageInfo.applicationInfo.labelRes;
            return context.getResources().getString(labelRes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * [获取应用程序版本名称信息]
     *
     * @param context
     * @return 当前应用的版本名称
     */
    public static String getVersionName(Context context) {
        if (context == null) {
            return null;
        }
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            return packageInfo.versionName;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取当前app的版本
     */
    public static int getVersionCode(Context context) {
        if (context == null) return 1;
        PackageManager pm = context.getPackageManager();
        try {
            PackageInfo packageInfo = pm.getPackageInfo(context.getPackageName(), 0);
            return packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
        }
        return 1;
    }

    /**
     * 检查指定activity是否正显示在屏幕上
     *
     * @param activity
     * @return
     */
    public static boolean isActivityForeground(Activity activity) {
        if (activity == null) return false;
        ActivityManager am = (ActivityManager) activity.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> runTaskInfos = am.getRunningTasks(1);
        if (runTaskInfos != null && runTaskInfos.size() > 0) {
            return runTaskInfos.get(0).topActivity.getClass().getName().equals(activity.getClass().getName());
        }
        return false;
    }

    /**
     * 检查app是否运行在前台
     *
     * @param context
     * @return
     */
    public static boolean isForeground(Context context) {
        if (context == null) return false;
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> runTaskInfos = am.getRunningTasks(1);
        if (runTaskInfos != null && runTaskInfos.size() > 0) {
            return runTaskInfos.get(0).topActivity.getPackageName().equals(context.getPackageName());
        }
        return false;
    }

    public static String SpaceCardNumber(String cardNumber) {
        StringBuffer buf = new StringBuffer();
        for (int index = 0; index < cardNumber.length(); index++) {
            if (index >= cardNumber.length() - 4)
                buf.append(cardNumber.charAt(index));
            else
                buf.append("*");
            if ((index + 1) % 4 == 0)
                buf.append(" ");

        }
        return buf.toString();
    }

    public static String SpaceIdCardNumber(String cardNumber) {
        StringBuffer buf = new StringBuffer();
        for (int index = 0; index < cardNumber.length(); index++) {
            if (index >= cardNumber.length() - 4)
                buf.append(cardNumber.charAt(index));
            else
                buf.append("*");
            if ((index + 1) % 4 == 0)
                buf.append(" ");

        }
        return buf.toString();
    }

    public static void copyToClipBoard(Context context, String text) {
        ClipboardManager cm = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        cm.setPrimaryClip(ClipData.newPlainText(null, text));
    }

    public static String DeviceUuid(Context context) {
        String uuid;
        synchronized (AppUtils.class) {
            final SharedPreferences prefs = context.getSharedPreferences(PREFS_FILE, 0);
            uuid = prefs.getString(PREFS_DEVICE_ID, null);

            if (uuid != null) {
                return uuid;
            } else {
                final String androidId = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
                try {
                    //先检查设备的androidId
                    if (!"9774d56d682e549c".equals(androidId)) {
                        uuid = UUID.nameUUIDFromBytes(androidId.getBytes("utf8")).toString();
                    } else {
                        final String deviceId = ((TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE)).getDeviceId();
                        uuid = deviceId != null ? UUID.nameUUIDFromBytes(deviceId.getBytes("utf8")).toString() : UUID.randomUUID().toString();
                    }
                } catch (UnsupportedEncodingException e) {
                    throw new RuntimeException(e);
                }

                prefs.edit().putString(PREFS_DEVICE_ID, uuid.toString()).commit();

            }
        }
        return uuid;
    }
}
