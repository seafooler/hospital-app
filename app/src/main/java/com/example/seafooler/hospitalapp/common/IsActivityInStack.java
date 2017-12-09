package com.example.seafooler.hospitalapp.common;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;

import java.util.List;

import static android.content.Context.ACTIVITY_SERVICE;

/**
 * Created by seafooler on 17-12-9.
 */

public class IsActivityInStack {
    /**
     * 判断某一个类是否存在任务栈里面
     * @param context
     * @param cls
     * @return
     *
     */
    public static boolean isActivityExsit(Context context, Class<?> cls){
        Intent intent = new Intent(context, cls);
        ComponentName cmpName = intent.resolveActivity(context.getPackageManager());
        boolean flag = false;
        if (cmpName != null) { // 说明系统中存在这个activity
            ActivityManager am = (ActivityManager) context.getSystemService(ACTIVITY_SERVICE);
            List<ActivityManager.RunningTaskInfo> taskInfoList = am.getRunningTasks(10);
            for (ActivityManager.RunningTaskInfo taskInfo : taskInfoList) {
                if (taskInfo.baseActivity.equals(cmpName)) { // 说明它已经启动了
                    flag = true;
                    break;  //跳出循环，优化效率
                }
            }
        }
        return flag;
    }
}
