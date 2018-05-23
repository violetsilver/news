package com.letv.cases.common;

import android.os.RemoteException;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObject2;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiScrollable;
import android.support.test.uiautomator.UiSelector;
import com.letv.base.LetvTestCase;

/**
 * Created by kevin.caasi on 2/7/2017.
 */

public class Utils extends LetvTestCase{
    public void launchAppByPackage(String packageName)
            throws RemoteException {
        gotoHomeScreen();
        if (packageName.equals("") || packageName == null) {
            System.out.println("the App name can't be null!!!");
        } else {
            callShell("am start -n " + packageName);
        }
        sleepInt(2);
    }

    public void searchScreenandClick(String appName){
        UiScrollable settingsItem = new UiScrollable(new UiSelector()
                .className("android.support.v7.widget.RecyclerView"));
        UiObject searchApp = null;
        try {
            searchApp = settingsItem.getChildByText(new UiSelector()
                    .className("android.widget.TextView"), appName);
            searchApp.click();
            sleepInt(3);
        } catch (UiObjectNotFoundException e) {
            e.printStackTrace();
        }
        sleepInt(3);
    }

    public boolean launchAppNew(String appName){
        press_home(1);
        sleep(2);
        UiObject2 menu = phone.findObject(By.pkg("com.android.launcher3").descContains("Apply"));
        verify("App drawer button not found", menu != null);
        if(menu != null){
            menu.click();
        }
        sleep(2);
        searchScreenandClick(appName);
        return true;
    }
}
