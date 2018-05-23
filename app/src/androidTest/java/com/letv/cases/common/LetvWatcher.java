package com.letv.cases.common;

import android.os.SystemClock;
import android.support.test.InstrumentationRegistry;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject2;
import android.support.test.uiautomator.UiWatcher;
import android.support.test.uiautomator.Until;

import java.util.regex.Pattern;

/**
 * Created by letv on 2016/1/2.
 * Last edit by letv on 15:02 2016/1/2.
 */
public class LetvWatcher {
    private static final UiDevice phone = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
    public static final UiWatcher LeAppInstall = new UiWatcher() {
        @Override
        public boolean checkForCondition() {
            UiObject2 LeAppInstall = phone.findObject(By.text("立即安装"));
            if (LeAppInstall != null) {
                UiObject2 LeAppcancel = phone.findObject(By.text(Pattern.compile("忽略|取消")));
                LeAppcancel.clickAndWait(Until.newWindow(), 30000);
                SystemClock.sleep(20000);
                return true;
            } else {
                return false;
            }
        }
    };
}