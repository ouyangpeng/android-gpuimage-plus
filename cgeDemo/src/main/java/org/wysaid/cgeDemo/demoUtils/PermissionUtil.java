package org.wysaid.cgeDemo.demoUtils;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;

import org.wysaid.common.Common;
import org.wysaid.myUtils.MsgUtil;

public class PermissionUtil {
    private static final int REQUEST_PERMISSION = 0;
    private static final String[] PERMISSIONS_STORAGE = {
            Manifest.permission.CAMERA,
            Manifest.permission.RECORD_AUDIO,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE
    };

    public static void verifyPermissions(Activity activity) {
        try {

            for (int i = 0; i != PERMISSIONS_STORAGE.length; ++i) {
                int reqCode = ActivityCompat.checkSelfPermission(activity, PERMISSIONS_STORAGE[i]);
                if (reqCode != PackageManager.PERMISSION_GRANTED) {
                    // No write permission, try request. A pop-up window may be shown to the user.
                    ActivityCompat.requestPermissions(activity, new String[]{PERMISSIONS_STORAGE[i]}, REQUEST_PERMISSION);
                    Log.i(Common.LOG_TAG, "request permission " + PERMISSIONS_STORAGE[i] + " ...");
                }
            }
        } catch (Exception e) {
            Log.e(Common.LOG_TAG, "Error: " + e.getMessage());
            e.printStackTrace();
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            // Request storage manage permission in order to write videos to the sd card.
            if (!Environment.isExternalStorageManager()) {
                Log.i(Common.LOG_TAG, "request permission ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION ...");
                Intent intent = new Intent();
                intent.setAction(Settings.ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION);
                activity.startActivity(intent);
                if (!Environment.isExternalStorageManager()) {
                    Log.i(Common.LOG_TAG, "request permission ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION failed!\n");
                    MsgUtil.toastMsg(activity, "Write permission not allowed, video recording may fail (to write).", Toast.LENGTH_LONG);
                }
            }
        }
    }
}
