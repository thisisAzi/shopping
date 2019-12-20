package cn.mmvtc.shopping.ActivityCollector;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

public class ActivityCollector {
    private  static List<Activity> activityList=new ArrayList<>();
    public static void addActivity(Activity activity){
        activityList.add(activity);
    }
    public static void removeActivity(Activity activity){
        activityList.remove(activity);
    }
    public static  void finishActivity(){
        for (Activity activity : activityList) {
            if(!activity.isFinishing()){
                activity.finish();
            }
        }
        activityList.clear();
    }
}
