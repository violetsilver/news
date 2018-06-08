package com.letv.base;

import android.app.Instrumentation;
import android.app.UiAutomation;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.os.RemoteException;
import android.os.SystemClock;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.BySelector;
import android.support.test.uiautomator.Configurator;
import android.support.test.uiautomator.Direction;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject2;
import android.support.test.uiautomator.Until;
import android.util.Log;
import android.view.InputDevice;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import static com.letv.cases.leui.Signin.isScreenLocked;

/**
 * Created by letv on 2015/12/10.
 * Last edit by letv on 15:02 2015/12/10.
 */
@RunWith(AndroidJUnit4.class)
public class LetvTestCase {
    appMap appmap = new appMap();
    public static Instrumentation instrumentation;
    public static UiDevice phone;
    public static Context context;
    public static String folderName;
    public static final int KEY_POWER = 26;
    public static final int KEY_BACK = 4;
    public static final int KEY_CENTER = 23;
    public static final int KEY_DEL = 67;
    public static final int KEY_ENTER = 66;
    public static final int KEY_HOME = 3;
    public static final int KEY_MENU = 82;
    public static final int KEY_APP_SWITCH = 187;
    public static final int KEY_SETTING = 176;
    // This key is used to exempt online tracing
    public static final String KEY_TRACE = "trace";
    public static final int KEY_VOLUME_DOWN = 25;
    public static final int KEY_VOLUME_MUTE = 164;
    // define static keyEvent
    public static final int KEY_VOLUME_UP = 24;
    public static final int KEYCODE_APP_SWITCH = 83;

    // Define status code
    public static final int STATUS_ANR = 10;
    public static final int STATUS_FC = 11;
    public static final int STATUS_SCREENSHOT = 17;
    public static final int STATUS_STEP = 15;
    public static final int STATUS_TITLE = 16;
    public static final int STATUS_TIME = 13;
    public static final String TAG = com.letv.base.LetvTestCase.class.getSimpleName();
    UiAutomation uiAutomation = InstrumentationRegistry.getInstrumentation().getUiAutomation();
    public Map<String,String> listApps = new HashMap<>();
    public static final String PACKAGE_HOME = "com.android.launcher3";

    // Define sdcard root directory
    public static final String ROOT_DIR = "/sdcard";
    private Bundle params;
    public static final int SIM1 = 1;
    public static final int SIM2 = 2;


    public static Long WAIT_TIME_OUT = 5000L;
    @Rule public final TestName name = new TestName();
    @Before
    public void letvTestCaseBaseSetUp() throws Exception {
        instrumentation = InstrumentationRegistry.getInstrumentation();
        phone = UiDevice.getInstance(instrumentation);
        params = InstrumentationRegistry.getArguments();
        context = InstrumentationRegistry.getContext();

        Configurator.getInstance().setWaitForSelectorTimeout(20000);
        Log.i(TAG, "=====================case start time is :"
                + getCurrentTime());
//        String delAnr = "rm -rf /data/anr";
//        callShell(delAnr);
//        String delTombstones = "rm -rf /data/tombstones";
//        callShell(delTombstones);
        Method currentTestMethod = getClass().getDeclaredMethod(name.getMethodName()
        );
        if (currentTestMethod.isAnnotationPresent(com.letv.base.CaseName.class)) {
            String cnName = currentTestMethod.getAnnotation(com.letv.base.CaseName.class)
                    .value();
            send_status(STATUS_TITLE, "title", cnName);
        }
        // get current case name and create a folder
        folderName = getStrParams("caseFolder");
        // create root folder for every case
        createDir(ROOT_DIR + File.separator + "AutoSmoke_UI30" + File.separator
                + folderName);

        listApps = initMapApps();
        registerCommonWatcher();
        if(Build.DEVICE.equals("pisces")||Build.DEVICE.equals("HWPRA-H")){
            if(!isScreenLocked(InstrumentationRegistry.getContext())){
                callShell("input keyevent 26");
                sleepInt(1);
                phone.swipe((int) (phone.getDisplayWidth() * 0.5), (int) (phone.getDisplayHeight() * 0.7), (int) (phone.getDisplayWidth() * 0.5), (int) (phone.getDisplayHeight() * 0.2), 15);
                sleepInt(1);
                phone.swipe((int) (phone.getDisplayWidth() * 0.5), (int) (phone.getDisplayHeight() * 0.7), (int) (phone.getDisplayWidth() * 0.5), (int) (phone.getDisplayHeight() * 0.2), 15);
                sleepInt(1);
                UiObject2 p6=waitForObj(By.text("6"));
                if(p6!=null) {
                    p6.click();
                    UiObject2 p9 = waitForObj(By.text("9"));
                    p9.click();
                    UiObject2 p61 = waitForObj(By.text("6"));
                    p61.click();
                    UiObject2 p91 = waitForObj(By.text("9"));
                    p91.click();
                    sleepInt(1);
                }
            };
            press_back(4);
            press_home(1);
        }else gotoHomeScreen();
    }

    @After
    public void letvTestCaseBasetearDown() throws Exception {
        System.out.println("The phone type is: " + android.os.Build.MODEL);
//        boolean ANRFlag = false;
//        boolean TombstoneFlag = false;
//        // errorTypeCount的作用是统计发生了几种错误类型
//        int errorTypeCount = 0;
//        String path = ROOT_DIR + File.separator + "AutoSmoke_UI30"
//                + File.separator + folderName + File.separator;
//        callShell("/system/bin/sh /data/local/tmp/PullLog.sh " + folderName);
//        callShell("mv /data/tombstones " + path);
//        File f = new File(path + "tombstones/tombstone_00");
//        File f1 = new File(path + "anr");
//        // 检测ANR是否存在
//        if (f1.exists()) {
//            ANRFlag = true;
//            errorTypeCount = errorTypeCount + 1;
//
//            if (phone.hasObject(By.clazz("android.widget.TextView")
//                    .text(Pattern.compile(".*(isn't responding|无响应).*")))) {
//                screenShot();
//
//                UiObject2 ok = phone.findObject(By.clazz(
//                        "android.widget.Button").text(Pattern.compile("OK|确定")));
//                if (ok != null) {
//                    ok.click();
//                    ok.recycle();
//                }
//                System.out.println("ANR has been catched and TV recovery normally!!!");
//            } else {
//                screenShot();
//                System.out.println("Can not find ANR note!!!");
//            }
//        }
//        // 检测TOMBSTONES是否存在
//        if (f.exists()) {
//            TombstoneFlag = true;
//            errorTypeCount = errorTypeCount + 1;
//            send_status(STATUS_TOMBSTONES, "TOMBSTONES",
//                    "Detected TOMBSTONES when running case");
//            screenShot();
//        }
//		/*
//		 * 讨论error发生的几种情况： 1.只有ANR发生或者之后TOMBSTONES发生 2.ANR和TOMBSTONES都发生
//		 */
//        Log.i(TAG, "=========================errorTypeCount" + errorTypeCount);
        unregisterCommonWatcher();
//        switch (errorTypeCount) {
//            case 1:
//                Log.i(TAG, "==========================case end time is :"
//                        + getCurrentTime());
//                if (ANRFlag) {
//                    Assert.fail("ANR occurred");
//                }
//                if (TombstoneFlag) {
//                    Assert.fail("TOMBSTONES occurred ,the case is stopped!");
//                }
//                break;
//            case 2:
//                Log.i(TAG, "==========================case end time is :"
//                        + getCurrentTime());
//                if (ANRFlag && TombstoneFlag) {
//                    Assert.fail("TOMBSTONES and ANR occurred ,the case is stopped!");
//                }
//                break;
//            default:
//                Log.i(TAG, "==========================case end time is :"
//                        + getCurrentTime());
//                break;
//        }
////        openWifiTeardown();
        if(Build.DEVICE.equals("pisces")||Build.DEVICE.equals("HWPRA-H")){
            if(!isScreenLocked(InstrumentationRegistry.getContext())&&Build.DEVICE.equals("HWPRA-H")){
                callShell("input keyevent 26");
                sleepInt(1);
                phone.swipe((int) (phone.getDisplayWidth() * 0.5), (int) (phone.getDisplayHeight() * 0.7), (int) (phone.getDisplayWidth() * 0.5), (int) (phone.getDisplayHeight() * 0.2), 15);
                sleepInt(1);
                phone.swipe((int) (phone.getDisplayWidth() * 0.5), (int) (phone.getDisplayHeight() * 0.7), (int) (phone.getDisplayWidth() * 0.5), (int) (phone.getDisplayHeight() * 0.2), 15);
                sleepInt(1);
                UiObject2 p6=waitForObj(By.text("6"));
                if(p6!=null) {
                    p6.click();
                    UiObject2 p9 = waitForObj(By.text("9"));
                    p9.click();
                    UiObject2 p61 = waitForObj(By.text("6"));
                    p61.click();
                    UiObject2 p91 = waitForObj(By.text("9"));
                    p91.click();
                    sleepInt(1);
                }
            };
            for(int i=0;i<4;i++){
                press_back(1);
                UiObject2 exit=phone.findObject(By.text("退出"));
                if(exit!=null){
                    exit.click();
                    sleepInt(1);
                }
            };
            press_home(1);
        }else gotoHomeScreen();
}

    public String getStrEngCh(int resourceId){
        return InstrumentationRegistry.getTargetContext().getString(resourceId);
    }




    public void openWifiTeardown() {
        try{
            launchApp(AppName.SETTING);
            UiObject2 wlan = null;
            for (int i = 0; i < 5; i++) {
                wlan = phone.findObject(By.clazz("android.widget.TextView").text("WLAN"));
                if (wlan == null) {
                    swipe_screen(phone.getDisplayWidth()*0.5, phone.getDisplayHeight()*0.2,
                            phone.getDisplayWidth()*0.5, phone.getDisplayHeight()*0.8, 20);
                } else {
                    break;
                }
            }
            //verify("在设置里没能找到WIFI选项", wlan != null);
            clickAndWaitForNewWindow(wlan);
            sleep(500);
            if (phone.hasObject(By.text("飞行模式"))) {
                return;
            }
            UiObject2 switchWidget = phone.findObject(By.clazz(Pattern.compile(
                    "com.letv.leui.widget.LeSwitch|android.widget.Switch")).res(Pattern.compile(
                    "com.android.settings:id/switch_widget|com.android.settings:id/switchWidget")));
            //verify("在WIFI信息界面没有找到WIFIkaig", switchWidget != null);
            if (!switchWidget.isChecked()) {
                switchWidget.click();
                //verify("设置里打开WIFI不成功", switchWidget.wait(Until.checked(true), WAIT_TIME_OUT));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void usPopUps(){
        UiObject2 report_issue = phone.findObject(By.text(Pattern.compile(".*Report this issue.*")));
        if (report_issue != null) phone.findObject(By.text("OK")).clickAndWait(Until.newWindow(),3000);
        UiObject2 fb_session = phone.findObject(By.text("Session Expired"));
        if (fb_session != null){
            phone.findObject(By.text("OK")).clickAndWait(Until.newWindow(), 7000);
            press_back(4);
        }
    }

    public void gotoHomeScreen() {
        addStep("Goto HomeScreen");
        try {
            if (callShell("dumpsys display").contains("mPowerRequest=policy=OFF")){
//                addStep("屏幕黑屏,需要通过点击一次电源键打量屏幕");
                press_power(1);
                sleepInt(1);
                if (callShell("dumpsys display").contains("mPowerRequest=policy=BRIGHT")){
//                    addStep("屏幕已经被打亮");
                }else if(callShell("dumpsys display").contains("mPowerRequest=policy=OFF")){
//                    addStep("屏幕未被打亮，再次点击power");
                    press_power(1);
                    sleepInt(1);
                }
            }else if (callShell("dumpsys display").contains("mPowerRequest=policy=DIM")){
//                addStep("屏幕休眠,需要通过点击一次电源键进入黑屏，再点击一次电源键打量屏幕");
                press_power(1);
                sleepInt(1);
                if (callShell("dumpsys display").contains("mPowerRequest=policy=OFF")){
//                    addStep("屏幕已经黑屏再次点击power");
                    phone.wakeUp();
                    sleepInt(1);
                    if (callShell("dumpsys display").contains("mPowerRequest=policy=BRIGHT")){
//                        addStep("屏幕已经被打亮");
                    }
                }
	/*		press_power(1);	*/
            }else if (callShell("dumpsys display").contains("mPowerRequest=policy=BRIGHT")){
//                addStep("屏幕亮屏,不需要做任何操作");
            }
            usPopUps();
            UiObject2 lock1 = phone.findObject(By.pkg("com.android.systemui")
                    .res("com.android.systemui:id/keyguardNotificationView"));
            UiObject2 lock2 = phone.findObject(By.pkg("com.android.systemui")
                    .res("com.android.systemui:id/scrim_behind"));
            UiObject2 lock3 = phone.findObject(By.pkg("com.android.systemui")
                    .res("com.android.systemui:id/panel_holder"));
            if((lock3 != null||lock1 != null||lock2 != null)&&(callShell("dumpsys display").contains("mPowerRequest=policy=BRIGHT")||callShell("dumpsys display").contains("mPowerRequest=policy=DIM"))){
                UiObject2 musicControls = phone.findObject(By.res("com.android.music:id/controls"));
                if(musicControls != null){
                    phone.swipe((int) (phone.getDisplayWidth() * 0.1),
                            (int) (phone.getDisplayHeight() * 0.45),
                            (int) (phone.getDisplayWidth() * 0.9),
                            (int) (phone.getDisplayHeight() * 0.45), 50);
                    phone.swipe((int) (phone.getDisplayWidth() * 0.1),
                            (int) (phone.getDisplayHeight() * 0.45),
                            (int) (phone.getDisplayWidth() * 0.9),
                            (int) (phone.getDisplayHeight() * 0.45), 50);
                }
                phone.swipe((int) (phone.getDisplayWidth() * 0.5),
                        (int) (phone.getDisplayHeight() * 0.45),
                        (int) (phone.getDisplayWidth() * 0.5),
                        (int) (phone.getDisplayHeight() * 0.05), 50);
                phone.swipe((int) (phone.getDisplayWidth() * 0.5),
                        (int) (phone.getDisplayHeight() * 0.45),
                        (int) (phone.getDisplayWidth() * 0.5),
                        (int) (phone.getDisplayHeight() * 0.05), 50);
            }
            /*if (!phone.isScreenOn()) {
                System.out.println("检测到屏幕处于关闭状态");
                phone.wakeUp();
                sleepInt(1);
                verify("屏幕在锁屏状态下没有被解锁", phone.isScreenOn());
                unLockDevice();
            }*/
        } catch (RemoteException e) {
            screenShot();
            Assert.fail(e.getMessage());
        }

        for (int i = 0; i < 2; i++) {
            if(phone.hasObject(By.pkg(PACKAGE_HOME))){
                break;
            }
            callShell("input keyevent 4");
            sleep(50);
        }

        for (int i = 0; i < 3; i++) {
            if(phone.hasObject(By.desc("主屏幕："))){
                press_home(1);
                break;
            }
            for(int j=0;j<2;j++){
                press_back(1);
                UiObject2 exit=phone.findObject(By.text("退出"));
                if(exit!=null){
                    exit.click();
                    sleepInt(1);
                }
            }
            press_home(1);
        }
    }

    public void unLockDevice() {
        addStep("unLockDevice");
        phone.swipe(538, 1847, 538, 300, 30);
        phone.swipe(538, 1847, 538, 300, 30);
    }

    public void sleepInt(int sc) {
        if (sc <= 0) {
            return;
        }
        int tenNum = sc/10;
        int oneNum = sc%10;
        if (tenNum > 0) {
            for (int i = 0; i < tenNum; i++) {
                sleep(10 * 1000);
                phone.runWatchers();
            }
            sleep(oneNum * 1000);
        }else {
            sleep(oneNum * 1000);
        }
    }

    public void verify(boolean b) {
        if (!b) {
            screenShot();
            Assert.fail();
        }
    }

    public void verify(String msg, boolean b) {
        if (msg.equals("")) {
            verify(b);
        } else if (!b) {
            screenShot();
            Assert.fail(msg);
        }
    }

    public void check(String msg, boolean b) {
        if (msg.equals("")) {
            check(b);
        } else if (!b) {
            throw new RuntimeException(msg);
        }
    }
    public void check(boolean b) {
        if (!b) {
            throw new RuntimeException();
        }
    }

    public void failCount (int count,int loop,String msg) {
        if(count > loop/5) {
            screenShot();
            Assert.fail(msg);
        }
    }

    public static void screenShot() {
        String filename = System.currentTimeMillis() + ".png";
        String command = "screencap " + ROOT_DIR + File.separator
                + "AutoSmoke_UI30" + File.separator + folderName
                + File.separator + filename;
        Bundle b = new Bundle();
        b.putString("screencap", filename);
        send_status(STATUS_SCREENSHOT, "screencap", filename);
//        callShell(command);
    }

    public static String callShell(String shellString) {
        try {
            return phone.executeShellCommand(shellString);
        } catch (IOException e) {
            System.out.println("call shell failed!!");
            e.printStackTrace();
            return null;
        }
    }

    public boolean longPressKey(int keyCode, int millisecond) {

        final long eventTime = SystemClock.uptimeMillis();
        KeyEvent downEvent = new KeyEvent(eventTime, eventTime, KeyEvent.ACTION_DOWN,
                keyCode, 0, 0, KeyCharacterMap.VIRTUAL_KEYBOARD, 0, 0,
                InputDevice.SOURCE_KEYBOARD);
        if (uiAutomation.injectInputEvent(downEvent,true)) {
            KeyEvent upEvent = new KeyEvent(eventTime, eventTime, KeyEvent.ACTION_UP,
                    keyCode, 0, 0, KeyCharacterMap.VIRTUAL_KEYBOARD, 0, 0,
                    InputDevice.SOURCE_KEYBOARD);
            SystemClock.sleep(millisecond);
            if(uiAutomation.injectInputEvent(upEvent,true)) {
                return true;
            }
        }
        return false;
    }

    public void sleep(int time){SystemClock.sleep(time);}

    public String getStrParams(String key){return params.getString(key);}

   // public int getIntParams(String key){return params.getInt(key);}
   public int getIntParams(String key){return Integer.parseInt(params.getString(key));}

    public static String getCurrentTime() {
        Calendar ca = Calendar.getInstance();
        int year = ca.get(Calendar.YEAR);
        int month = ca.get(Calendar.MONTH);
        int day = ca.get(Calendar.DATE);
        int minute = ca.get(Calendar.MINUTE);
        int hour = ca.get(Calendar.HOUR);
        int second = ca.get(Calendar.SECOND);
        return (String.valueOf(year) + "-"
                + String.valueOf(month + 1) + "-" + String.valueOf(day) + "-"
                + String.valueOf(hour) + "-" + String.valueOf(minute) + "-" + String
                .valueOf(second));
    }

    public void addStep(String comment){
        Bundle b = new Bundle();
        b.putString("casestep", comment);
        send_status(STATUS_STEP, "caseStep", comment);
        send_status(STATUS_TIME, "CurrentTime", getCurrentTime());
    }

    // create case folder to save logs and screenshot
    public void createDir(String dir) {
        File folder = new File(dir);
        if (folder.exists()) {
            System.out.println("The folder " + dir + " has existed!");
            System.out.println("current_case_root_folder=" + folderName);
            // createLogFile(dir);
            return;
        }
        if (!dir.endsWith(File.separator)) {
            dir = dir + File.separator;
        }
        // create folder
        if (folder.mkdirs()) {
            System.out.println("Create folder " + dir + " success!");
            System.out.println("current_case_root_folder=" + folderName);
            // createLogFile(dir);
        } else {
            System.out.println("Create folder " + dir + " fail!");
        }
    }

    public void press_keyevent(int times, int keycode) {
        if (times < 1)
            return;
        for (int i = times; i > 0; i--) {
            phone.pressKeyCode(keycode);
            sleep(500);
        }
    }

    public void press_menu(int times) {
        press_keyevent(times, KEY_APP_SWITCH);
    }

    public void press_back(int times) {
        press_keyevent(times, KEY_BACK);
    }
    public void press_power(int times) {
        press_keyevent(times, KEY_POWER);
    }
    public void press_center(int times) {
        press_keyevent(times, KEY_CENTER);
    }

    public void press_home(int times) {
        press_keyevent(times, KEY_HOME);
    }

    public void pressDialPad(String number){
        UiObject2 num1 = waitForObj(By.res("com.android.dialer:id/one"));
        if (num1 == null) {
            for (int i = 0; i < number.length(); i++) {
                UiObject2 numBtn = phone.findObject(By.clazz("android.widget.TextView")
                        .res("com.android.dialer:id/dialpad_key_number")
                        .text(String.valueOf(number.charAt(i))));
                verify("numBtn is null",numBtn!=null);
                numBtn.click();
                sleep(500);
            }
        }else{
            for (int i = 0; i < number.length(); i++) {
                String num = String.valueOf(number.charAt(i));
                Map<String,String> map = new HashMap<>();
                map.put("1","one");
                map.put("2","two");
                map.put("3","three");
                map.put("4","four");
                map.put("5","five");
                map.put("6","six");
                map.put("7","seven");
                map.put("8","eight");
                map.put("9","nine");
                map.put("*","star");
                map.put("0","zero");
                map.put("#","pound");
                UiObject2 numBtn = phone.findObject(By.res("com.android.dialer:id/"+map.get(num)));
                verify("Failed to find number "+String.valueOf(number.charAt(i))+" in dial pad "+map.get(num),numBtn != null);//不能找到数字
                numBtn.click();
                sleep(500);
            }
        }
    }

    public void swipe_screen(double startX, double startY, double endX, double endY,
                             int swipeSpeed) {
        phone.swipe((int) startX, (int) startY, (int) endX, (int) endY, swipeSpeed);
        sleep(500);
    }

    public static void send_status(int code, String key, String value) {
        Bundle b = new Bundle();
        b.putString(key, value);
        instrumentation.sendStatus(code, b);
    }

    public boolean waitForExist(BySelector selector){
        return phone.wait(Until.hasObject(selector),WAIT_TIME_OUT);
    }

    public boolean waitForExist(BySelector selector,long timeout){
        return phone.wait(Until.hasObject(selector),timeout);
    }

    public boolean waitUntilGone(BySelector selector){
        return phone.wait(Until.gone(selector), WAIT_TIME_OUT);
    }

    public boolean waitUntilGone(BySelector selector,long timeout){
        return phone.wait(Until.gone(selector),timeout);
    }

    public UiObject2 waitForObj(BySelector selector){
        return phone.wait(Until.findObject(selector), WAIT_TIME_OUT);
    }

    public UiObject2 waitForObj(BySelector selector,long timeout){
        return phone.wait(Until.findObject(selector),timeout);
    }

    public List<UiObject2> waitForObjs(BySelector selector){
        return phone.wait(Until.findObjects(selector), WAIT_TIME_OUT);
    }

    public List<UiObject2> waitForObjs(BySelector selector,long timeout){
        return phone.wait(Until.findObjects(selector), timeout);
    }

    public boolean clickAndWaitForNewWindow(UiObject2 obj){
        return obj.clickAndWait(Until.newWindow(), WAIT_TIME_OUT);
    }

    public boolean clickAndWaitForNewWindow(UiObject2 obj,long timeout){
        return obj.clickAndWait(Until.newWindow(), timeout);
    }

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

    public boolean launchApp(String appName){
        return launchApp(appName, true);
    }

    public boolean launchApp(String appName, boolean flag) {
        // verify the app is not null
        if (appName.equals("") || appName == null) {
            System.out.println("the App name can't be null!!!");
            return false;
        }
        // verify if the app is a valid App
        if (!(listApps.containsKey(appName))) {
            screenShot();
            if (flag) {
                Assert.fail("The app " + appName
                        + "is not a valid App, please check the App name.");
            }
            System.out.println("The app " + appName
                    + "is not a valid App, please check the App name.");
            return false;
        }

        for (int i = 0; i < 5; i++) {
            if(phone.hasObject(By.pkg(PACKAGE_HOME))) {
                break;
            }
            press_back(1);
        }
        for (int i = 0; i < 2; i++) {
            if(phone.hasObject(By.desc(Pattern.compile("主屏幕：第2屏，共3屏, 设置.*|Home screen: Screen 2 of 3, Settings.*")))){
                sleepInt(1);
                press_home(1);
                break;
            }else if(phone.hasObject(By.desc(Pattern.compile("主屏幕：第3屏，共3屏, 设置.*|Home screen: Screen 3 of 3, Settings*")))){
                phone.swipe(20, 500, (int) (phone.getDisplayWidth() * 0.8), 500, 30);
                press_home(1);
                break;
            }
            press_home(1);
        }
        //BySelector appS = By.text(Pattern.compile(appName)).clazz("android.widget.TextView").res(Pattern.compile("^(?!.*folder_icon_name.*)"));
        BySelector appS = By.text(Pattern.compile(appName)).clazz("android.widget.TextView");
//        BySelector appS = By.text(Pattern.compile(appName)).depth(8);
        for (int i = 0; i < 3; i++) {
            if(findAppInDesktop(appS)){
                if (appName != AppName.APPSTORE && waitForExist(By.pkg(PkgName.APPSTORE))){
                    //乐迷提示安装界面
                    throw new RuntimeException("App not installed");
                }
                System.out.print(waitForExist(By.pkg(Pattern.compile(listApps.get(appName)))));
                verify("Can not found " + appName, waitForExist(By.pkg(Pattern.compile(listApps.get(appName)))));
                sleep(500);
                break;
            }else if(i==2){
                screenShot();
                Assert.fail(appName + "can't found");
                return false;
            }
            phone.swipe((int) (phone.getDisplayWidth() * 0.8), 500, 20, 500, 30);
            sleep(500);
        }
        return true;
    }

    public boolean findAppInDesktop(BySelector appS) {
        UiObject2 app = phone.findObject(appS);
        if (app != null) {
            clickAndWaitForNewWindow(app);
            return true;
        }
        UiObject2 appIcon = phone.findObject(By.depth(8));
        verify("Desktop icon was not found", appIcon != null);
        List<UiObject2> dirList = appIcon.getParent().findObjects(By.depth(1).clazz("android.widget.FrameLayout").desc(Pattern.compile(".*文件夹.*|.*Folder.*")));
       // List<UiObject2> dirList = appIcon.getParent().findObjects(By.depth(1).clazz("android.widget.FrameLayout"));
        if (dirList.size() == 0){
            return false;
        }
        for (int i = 0; i < dirList.size(); i++) {
            if(null != dirList.get(i)){
                clickAndWaitForNewWindow(dirList.get(i));
                sleep(500);
                app = phone.findObject(appS);
                if (app != null) {
                    clickAndWaitForNewWindow(app);
                    return true;
                } else{
                    press_back(1);
                }
            }
        }
        return false;
    }

    public boolean verifyByImg(String target, int x, int y, int x1, int y1,
                            double percent) {
        int width = x1 - x;
        int height = y1 - y;
        String p1 = target;
        String p2 = "/sdcard/current.png";
        File f1 = new File(p1);
        File f2 = new File(p2);
        phone.takeScreenshot(f2);
        Bitmap sub1 = null;
        try {
            sub1 = com.letv.base.Util.getSubImage(p1, x, y, width, height);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Bitmap sub2 = null;
        try {
            sub2 = com.letv.base.Util.getSubImage(p2, x, y, width, height);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        boolean same = com.letv.base.Util.sameAs(sub1, sub2, percent);
//        if (!same) {
//            Assert.fail("图片对比失败,target:" + target + ", dst:" + p2);
//        }
        return same;
    }

    public void verifyByImg(String target, double percent) {
        String p1 = target;
        String p2 = "/sdcard/2.png";
        File f1 = new File(p1);
        File f2 = new File(p2);
        phone.takeScreenshot(f2);
        boolean same = false;
        try {
            same = Util.sameAs(p1, p2, 0.9);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if (!same) {
            Assert.fail("图片对比失败,target:" + target + ", dst:" + p2);
        }
    }

    private void registerCommonWatcher() {
        phone.registerWatcher("anrWatcher", LetvWatcher.anrWatcher);
        phone.registerWatcher("fcWatcher", LetvWatcher.fcWatcher);
        phone.registerWatcher("termsWatcher", LetvWatcher.termsWatcher);
        phone.registerWatcher("upgradeWatcher", LetvWatcher.upgradeWatcher);
       // phone.registerWatcher("systemUpgradeWatcher", LetvWatcher.systemUpgradeWatcher);
        //phone.registerWatcher("syncWatcher", LetvWatcher.syncWatcher);
       // phone.registerWatcher("unicomWelcomWatcher", LetvWatcher.unicomWelcomWatcher);
       // phone.registerWatcher("tipWatcher", LetvWatcher.tipWatcher);
       // phone.registerWatcher("PermissionWatcher", LetvWatcher.PermissionWatcher);
       // phone.registerWatcher("RequestPermissionWatcher", LetvWatcher.RequestPermissionWatcher);
        phone.registerWatcher("RequestPermissionWatcher", LetvWatcher.SeelaterWatcher);
        phone.registerWatcher("JinbiWatcher", LetvWatcher.JinbiWatcher);
    }

    private void unregisterCommonWatcher() {
        phone.removeWatcher("anrWatcher");
        phone.removeWatcher("fcWatcher");
        phone.removeWatcher("termsWatcher");
        phone.removeWatcher("upgradeWatcher");
       // phone.removeWatcher("systemUpgradeWatcher");
       // phone.removeWatcher("syncWatcher");
       // phone.removeWatcher("unicomWelcomWatcher");
      //  phone.removeWatcher("tipWatcher");
      //  phone.removeWatcher("PermissionWatcher");
     //   phone.removeWatcher("RequestPermissionWatcher");
        phone.removeWatcher("SeelaterWatcher");
        phone.removeWatcher("JinbiWatcher");
    }

    private Map<String ,String> initMapApps(){
        Map<String,String> map = new HashMap<>();
        map.putAll(appmap.cnApps());
        map.putAll(appmap.osApps());
        return map;
    }


    public void openAPM() {
        addStep("Open APM");
        changeAPM(true);
    }

    public void closeAPM() {
        addStep("Close APM");
        changeAPM(false);
    }

    private void changeAPM(boolean tag) {
        launchApp(AppName.SETTING);
        for (int i = 0; i < 3; i++) {
            UiObject2 scrollableView;
            if (!phone.hasObject(By.text("飞行模式"))) {
                scrollableView = phone.findObject(By.scrollable(true));
                verify("未找到可滚动VIEW",scrollableView != null);
                scrollableView.swipe(Direction.DOWN,1f,3000);
                sleepInt(1);
            } else {
                break;
            }
        }
        /**
         * tag=true 打开飞行模式
         * tag=false 关闭飞行模式
         **/
        UiObject2 APM = phone.findObject(By.text("飞行模式")).getParent().getParent().findObject(By.res(phone.getCurrentPackageName(),"switchWidget"));
        verify("不能够找到飞行模式开关", APM != null);
        if (APM.isChecked() != tag){
            APM.click();
            if (tag){
                verify("飞行模式开启失败", APM.wait(Until.checked(tag),WAIT_TIME_OUT));
            }else {
                verify("飞行模式关闭失败", APM.wait(Until.checked(tag),WAIT_TIME_OUT));
            }
            sleepInt(1);
        }
    }

    public void SIM1dataUse() {
        addStep("在设置（双卡）里打开移动数据网络，切换SIM卡到卡1");
        changeSIM(SIM1);
    }

    public void SIM2dataUse() {
        addStep("在设置（双卡）里打开移动数据网络，切换SIM卡到卡2");
        changeSIM(SIM2);
    }

    private void changeSIM(int tag) {
        launchApp(AppName.SETTING);
        UiObject2 SIMCards = null;
        for (int i = 0; i < 3; i++) {
            SIMCards = phone.findObject(By.clazz("android.widget.TextView").textContains("双卡"));
            if (SIMCards == null) {
                UiObject2 scrollableView = phone.findObject(By.scrollable(true));
                verify("未找到可滚动VIEW",scrollableView != null);
                scrollableView.swipe(Direction.DOWN,1f,3000);
                sleepInt(1);
            } else {
                break;
            }
        }
        verify("未发现双卡管理", SIMCards != null);
        clickAndWaitForNewWindow(SIMCards);
        UiObject2 cellularData = waitForObj(By.res("android:id/title").text("移动数据网络"));
        verify("未发现移动数据网络", cellularData != null);
        clickAndWaitForNewWindow(cellularData);
        sleepInt(1);
        UiObject2 choseSim = null;
        switch (tag){
            case SIM1:
                choseSim = waitForObj(By.res(phone.getCurrentPackageName(), "subscriber_item"),WAIT_TIME_OUT);
                verify("选择上网的SIM1卡不存在", choseSim != null);
                if (choseSim.getParent().findObject(By.clazz("com.letv.leui.widget.LeCheckBox")).isChecked()) return;
                break;
            case SIM2:
                List<UiObject2> choseSimList = waitForObjs(By.res(phone.getCurrentPackageName(), "subscriber_item"),WAIT_TIME_OUT);
                verify("选择上网的SIM2卡不存在", choseSimList.size() >= 2);
                choseSim = choseSimList.get(1);
                if (choseSim.getParent().findObject(By.clazz("com.letv.leui.widget.LeCheckBox")).isChecked()) return;
                break;
        }
        verify("选择上网的SIM卡不存在",choseSim != null);
        choseSim.click();
        sleepInt(20);
        cellularData = waitForObj(By.res("android:id/title").text("移动数据网络"));
        verify("未发现移动数据网络", cellularData != null);
        UiObject2 CMCC01 = cellularData.getParent().findObject(By.res("android:id/summary"));
        verify("验证SIM" + tag + "卡是否切换成功失败", CMCC01.wait(Until.textMatches("中国.*"), WAIT_TIME_OUT));
        press_back(3);
    }

    public void switch3G() {
        changeNetMode("3G");
    }

    public void switch4G() {
        changeNetMode("4G");
    }

    private void changeNetMode(String tag) {
        launchApp(AppName.SETTING);
        UiObject2 cellularNetworks = null;
        for (int i = 0; i < 3; i++) {
            cellularNetworks = phone.findObject(By.clazz("android.widget.TextView").text(Pattern.compile(".*Mobile.*|.*移动网络.*|.*mobile networks.*")));
            if (cellularNetworks == null) {
                UiObject2 scrollableView = phone.findObject(By.scrollable(true));
                verify("No scrollableView",scrollableView != null);
                scrollableView.swipe(Direction.DOWN,1f,3000);
                sleepInt(1);
            } else {
                break;
            }
        }
        verify("No cellularNetworks button", cellularNetworks != null);
        clickAndWaitForNewWindow(cellularNetworks);
        sleep(500);
        UiObject2 networkMode = waitForObj(By.text(Pattern.compile("网络模式|Preferred network.*")),WAIT_TIME_OUT);
        verify("No networkMode button", networkMode != null);
        clickAndWaitForNewWindow(networkMode);
        sleep(500);
        UiObject2 network = waitForObj(By.textContains(tag),WAIT_TIME_OUT);
        verify("No " + tag, network != null);
        if (network.getParent().findObject(By.clazz("com.letv.leui.widget.LeCheckBox")).isChecked()) return;
        clickAndWaitForNewWindow(network);
        sleepInt(10);
        UiObject2 makeSure = phone.findObject(By.res("android:id/message").textContains(tag));
//        verify(""+tag+"是否切换成功失败", makeSure != null);
        press_back(3);
    }

    public void connectData() {
        changeSimDataStatus(true);
    }

    public void disconnectData() {
        changeSimDataStatus(false);
    }

    private void changeSimDataStatus(boolean tag) {
        launchApp(AppName.SETTING);
        UiObject2 cellularNetworks = null;
        for (int i = 0; i < 3; i++) {
            cellularNetworks = phone.findObject(By.text(Pattern.compile(".*Mobile.*|.*移动网络.*|.*mobile networks.*")));
            if (cellularNetworks == null) {
                UiObject2 scrollableView = phone.findObject(By.scrollable(true));
                verify("No scrollable View",scrollableView != null);
                scrollableView.swipe(Direction.DOWN,1f,3000);
                sleepInt(1);
            } else {
                break;
            }
        }
        verify("No mobile data ", cellularNetworks != null);
        clickAndWaitForNewWindow(cellularNetworks);
        sleep(500);
        UiObject2 simData = waitForObj(By.text(Pattern.compile("Mobile data|移动数据|Data enabled|mobile data")));
        verify("No Mobile data", simData != null);
        /**
         * tag=true 打开数据网络
         * tag=false 关闭数据网络
         **/
        UiObject2 dataSwitch = simData.getParent().getParent().getParent().findObject(By.clazz(Pattern.compile("com.letv.leui.widget.LeSwitch|android.widget.Switch")));
        verify("No switch button", dataSwitch != null);
        if (dataSwitch.isChecked() == !tag){
            dataSwitch.click();
            if (tag){
                verify("Open mobile data fail", dataSwitch.wait(Until.checked(tag), WAIT_TIME_OUT));
            }else {
                verify("Close mobile data fail", dataSwitch.wait(Until.checked(tag),WAIT_TIME_OUT));
            }
            sleepInt(1);
        }
    }

    public void openGPS() {
        changeGPS(true);
    }

    public void closeGPS() {
        changeGPS(false);
    }

    private void changeGPS(boolean tag) {
        launchApp(AppName.SETTING);
        for(int i=0;i<2;i++){                 //保证从settings最顶部开始向下查找
            phone.swipe((int) (phone.getDisplayWidth() * 0.5),
                    (int) (phone.getDisplayHeight() * 0.2),
                    (int) (phone.getDisplayWidth() * 0.5),
                    (int) (phone.getDisplayHeight() * 0.8), 50);
        }
        UiObject2 location = null;
        for (int i = 0; i < 3; i++) {
            location = phone.findObject(By.text(Pattern.compile("Location|位置信息")));
            if (location == null) {
                UiObject2 scrollableView = phone.findObject(By.scrollable(true));
                verify("No scrollable view",scrollableView != null);
                scrollableView.scroll(Direction.DOWN,1f,3000);
                sleepInt(1);
            } else {
                break;
            }
        }
        verify("No location button", location != null);
        clickAndWaitForNewWindow(location);
        sleep(500);
        /**
         * tag=true 打开位置
         * tag=false 关闭位置
         **/
        UiObject2 switchWidget = waitForObj(By.res(phone.getCurrentPackageName(),"switch_widget"),WAIT_TIME_OUT);
        verify("No switch button", switchWidget != null);
        // close location
        if (switchWidget.isChecked() == true) {
            switchWidget.click();
        }
        sleepInt(1);
        if (switchWidget.isChecked() == !tag){
            switchWidget.click();
            if (tag){
                verify("Open location failed", switchWidget.wait(Until.checked(tag), WAIT_TIME_OUT));
            }else {
                verify("Close location failed", switchWidget.wait(Until.checked(tag),WAIT_TIME_OUT));
            }
            sleepInt(1);
        }
    }

    public void openWifi() {
        addStep("在设置里开启WIFI");
        changeWifi(true);
    }

    public void closeWifi() {
        addStep("在设置里关闭WIFI");
        changeWifi(false);
    }

    private void changeWifi(boolean tag) {
/*        launchApp(AppName.SETTING);
        UiObject2 wlan = null;
        for (int i = 0; i < 5; i++) {
            wlan = phone.findObject(By.clazz("android.widget.TextView").text(Pattern.compile("WLAN|Wi.*Fi")));
            if (wlan == null) {
                swipe_screen(phone.getDisplayWidth() * 0.5, phone.getDisplayHeight() * 0.2,
                        phone.getDisplayWidth() * 0.5, phone.getDisplayHeight() * 0.8, 20);
            } else {
                break;
            }
        }
        verify("在设置里没能找到WIFI选项", wlan != null);
        clickAndWaitForNewWindow(wlan);
        sleep(500);*/
        launchSettings("WLAN","verify");
        if (phone.hasObject(By.text("飞行模式"))) {
            verify("没有进入WIFI信息界面",false);
        }

        /**
         * tag=true 打开位置
         * tag=false 关闭位置
         **/
        UiObject2 switchWidget = waitForObj(By.res(phone.getCurrentPackageName(), "switch_widget"));
        verify("在位置里没有找到位置信息开关", switchWidget != null);
        if (switchWidget.isChecked() == !tag){
            switchWidget.click();
            if (tag){
                verify("设置里打开WIFI不成功", switchWidget.wait(Until.checked(tag), WAIT_TIME_OUT));
            }else {
                verify("位置开关关闭失败", switchWidget.wait(Until.checked(tag), WAIT_TIME_OUT));
            }
            sleepInt(1);
        }

    }

    public void connectWifi() {
        openWifi();
        addStep("添加网络" + getStrParams("WifiAP"));
        UiObject2 add = waitForObj(By.res("android:id/title").text("添加网络"),WAIT_TIME_OUT);
        verify("添加网络的按键不存在", add != null);
        add.wait(Until.enabled(true),WAIT_TIME_OUT);
        sleep(500);
        clickAndWaitForNewWindow(add);
        addStep("填写详细信息，包括网络名称，如果WIFI有密码，安全性选择WPA/WPA2 PSK，并确定添加");
        UiObject2 APname = waitForObj(By.clazz("android.widget.EditText")
                .res("android:id/edit").text("网络名称"),WAIT_TIME_OUT);
        verify("网络名称不存在", APname != null);
        APname.setText(getStrParams("WifiAP"));
        sleep(500);
        String pwd = getStrParams("WifiPWD");
        if (!pwd.equals("noPassword")) {
            UiObject2 APsecurity = phone.findObject(By.clazz("android.widget.TextView")
                    .res("android:id/title").text("安全性"));
            verify("安全性选项不存在", APsecurity != null);
            clickAndWaitForNewWindow(APsecurity);
            sleep(500);
            UiObject2 WPA = phone.findObject(By.res("android:id/le_bottomsheet_text")
                    .text("WPA/WPA2 PSK"));
            verify("WPA/WPA2 PSK不存在", WPA != null);
            clickAndWaitForNewWindow(WPA);
            sleep(500);
            UiObject2 APpw = phone.findObject(By.clazz("android.widget.TextView")
                    .res("android:id/title").text("密码"));
            verify("密码不存在", APpw != null);
            UiObject2 APpwEdit = APpw.getParent().findObject(By.res("android:id/edit"));
            APpwEdit.setText(getStrParams("WifiPWD"));
        }
        addStep("信息填写完毕，点击确定键提交提交");
        UiObject2 OK = waitForObj(By.clazz(
                "android.widget.TextView").text("确定"));
        verify("ok的button不存在", OK != null);
        sleepInt(1);
        clickAndWaitForNewWindow(OK);
        sleepInt(2);
    }

    public void connectDataAndSwitchSimAndSwitchNetMode(int SimTag,String NetMode) {
        launchApp(AppName.SETTING);
        addStep("在设置（移动网络）里确保移动数据是开启的");
        UiObject2 cellularNetworks = null;
        for (int i = 0; i < 3; i++) {
            cellularNetworks = phone.findObject(By.clazz("android.widget.TextView").textContains("移动网络"));
            if (cellularNetworks == null) {
                UiObject2 scrollableView = phone.findObject(By.scrollable(true));
                verify("未找到可滚动视图",scrollableView != null);
                scrollableView.swipe(Direction.DOWN,1f,3000);
                sleepInt(1);
            } else {
                break;
            }
        }
        verify("未发现移动网络", cellularNetworks != null);
        clickAndWaitForNewWindow(cellularNetworks);
        sleep(500);
        UiObject2 simData = waitForObj(By.text("移动数据"),WAIT_TIME_OUT);
        verify("未发现移动数据", simData != null);
        /**
         * 确保数据网络打开
         **/
        UiObject2 dataSwitch = simData.getParent().getParent().getParent().findObject(By.clazz(Pattern.compile("com.letv.leui.widget.LeSwitch|android.widget.Switch")));
        verify("未发现移动数据开关", dataSwitch != null);
        if (!dataSwitch.isChecked()){
            dataSwitch.click();
            verify("移动数据开关打开失败", dataSwitch.wait(Until.checked(true), WAIT_TIME_OUT));
            sleepInt(1);
        }

        /**
         * 切换网络模式，NetMode为“3G”或者“4G”，会根据此字符串寻找UI
         **/
        addStep("在设置（移动网络）里切换网络模式到" + NetMode + "网络");
        step2: {
            UiObject2 networkMode = waitForObj(By.text("网络模式"),WAIT_TIME_OUT);
            verify("未发现网络模式", networkMode != null);
            networkMode.wait(Until.enabled(true), WAIT_TIME_OUT*2);
            clickAndWaitForNewWindow(networkMode);
            sleep(500);
            UiObject2 network = waitForObj(By.textContains(NetMode),WAIT_TIME_OUT);
            verify("未找到网络模式中的" + NetMode, network != null);
            if (network.getParent().findObject(By.clazz("com.letv.leui.widget.LeCheckBox")).isChecked()){
                press_back(1);
                break step2;
            }
            clickAndWaitForNewWindow(network);
            sleepInt(5);
            UiObject2 makeSure = waitForObj(By.res("android:id/message").textContains(NetMode));
            verify("验证"+NetMode+"是否切换成功失败", makeSure != null);
        }

        /**
         * 切换SIM卡，SimTag只接受LetvTestCase.SIM1和LetvTestCase.SIM2
         **/
        addStep("在设置（双卡）里打开移动数据网络，切换SIM卡到卡" + SimTag);
        step3: {
            UiObject2 cellularData = waitForObj(By.res("android:id/title").text("移动数据网络"));
            verify("未发现移动数据网络", cellularData != null);
            clickAndWaitForNewWindow(cellularData);
            sleepInt(1);
            UiObject2 choseSim = null;
            switch (SimTag){
                case SIM1:
                    choseSim = waitForObj(By.res(phone.getCurrentPackageName(), "subscriber_item"),WAIT_TIME_OUT);
                    verify("选择上网的SIM1卡不成功", choseSim != null);
                    if (choseSim.getParent().findObject(By.clazz("com.letv.leui.widget.LeCheckBox")).isChecked()) break step3;
                    break;
                case SIM2:
                    List<UiObject2> choseSimList = waitForObjs(By.res(phone.getCurrentPackageName(), "subscriber_item"),WAIT_TIME_OUT);
                    verify("选择上网的SIM2卡不成功", choseSimList != null);
                    choseSim = choseSimList.get(1);
                    if (choseSim.getParent().findObject(By.clazz("com.letv.leui.widget.LeCheckBox")).isChecked()) break step3;
                    break;
            }
            choseSim.click();
            sleepInt(25);
            cellularData = waitForObj(By.res("android:id/title").text("移动数据网络"),20000);
            verify("未发现移动数据网络", cellularData != null);
            UiObject2 CMCC01 = null;
            for (int i = 0; i < 30; i++) {
                cellularData = waitForObj(By.text("移动数据网络"));
                verify("未发现移动数据网络", cellularData != null);
                CMCC01 = cellularData.getParent().findObject(By.res("android:id/summary"));
                if (CMCC01 != null){
                    break;
                }
                sleepInt(2);
            }
            verify("验证SIM" + SimTag + "卡是否切换成功失败", CMCC01 != null);
        }

    }

    public void sendMsg(boolean isMMS,int SimTag,String receiver,String content){
        String simCardRes = "";
        if (SimTag == SIM1){
            simCardRes = "com.android.mms:id/sim1View";
        }else if (SimTag == SIM2){
            simCardRes = "com.android.mms:id/sim2View";
        }else {
            Assert.fail("错误的SIMCard参数");
        }
        /**确保进入后应用在初始界面**/
        gotoHomeScreen();
        press_menu(1);
        UiObject2 clear =waitForObj(By.res("com.android.systemui:id/leui_recent_clear_all_btn"));
        verify("清理按钮不存在", clear != null);
        clickAndWaitForNewWindow(clear,2000);

        addStep("打开信息程序");
        launchApp(AppName.MESSAGE);
        verify("信息应用不在主界面", waitForExist(By.res("com.android.mms:id/action_compose_new")));
        /**清除信息列表**/
        if (!phone.hasObject(By.clazz("android.widget.TextView")
                .textContains("无会话"))) {
            UiObject2 msgList = phone.findObject(By.res("android:id/list"));
            verify("彩信列表不存在", msgList != null);
            List<UiObject2> msg = msgList.getChildren();
            verify("彩信不存在", msg.size() != 0);
            int dx = msg.get(0).getVisibleBounds().centerX();
            int dy = msg.get(0).getVisibleBounds().centerY();
            phone.swipe(dx, dy, dx, dy, 100);
            UiObject2 selectAll = phone.findObject(By.clazz(
                    "android.widget.TextView").text(Pattern.compile("全选|全不选")));
            verify("全选或者全不选不存在", selectAll != null);
            if (selectAll.getText().equals("全选")) {
                selectAll.click();
            }
            sleepInt(1);
            UiObject2 del = phone.findObject(By.res("android:id/title").text("删除"));
            verify("删除按钮不存在", del != null);
            clickAndWaitForNewWindow(del);
            UiObject2 confirm = waitForObj(By.textContains("删除"));
            verify("删除按钮不存在", confirm != null);
            clickAndWaitForNewWindow(confirm);
            sleepInt(2);
        }
        sleepInt(1);
        verify("信息列表不为空", phone.hasObject(By.clazz("android.widget.TextView")
                .textContains("无会话")));
        addStep("编写新信息");
        UiObject2 newMsgBtn = waitForObj(By.res("com.android.mms:id/action_compose_new"));
        verify("新建信息的按键不存在", newMsgBtn != null);
        clickAndWaitForNewWindow(newMsgBtn);
        /**填写收件人和信息内容**/
        UiObject2 receiverEdit = waitForObj(By.res("com.android.mms:id/recipients_editor"));
        verify("在编写信息界面未发现填写收件人的地方", receiverEdit != null);
        receiverEdit.findObject(By.clazz("android.widget.EditText")).setText(receiver);
        sleepInt(1);
        UiObject2 contentEdit = waitForObj(By.clazz("android.widget.EditText")
                .res("com.android.mms:id/embedded_text_editor"));
        verify("在编写信息界面未发现输入内容的区域", contentEdit != null);
        contentEdit.setText(content);
        sleepInt(1);
        /**如果是彩信，添加附件**/
        if (isMMS){
            UiObject2 attach = waitForObj(By.clazz("android.widget.ImageButton")
                    .res("com.android.mms:id/share_button"));
            verify("添加附件的按钮不存在", attach != null);
            clickAndWaitForNewWindow(attach);
            UiObject2 pic = waitForObj(By.clazz("android.widget.TextView")
                    .res("com.android.mms:id/tv_share_name").text("照片"));
            verify("点击附件后未找到照片选项", pic != null);
            clickAndWaitForNewWindow(pic);

            UiObject2 picChoose = waitForObj(By.clazz("android.widget.TextView").text("Resource"));
            verify("进入图库后未找到已经上传到手机上的资源文件", picChoose != null);
            clickAndWaitForNewWindow(picChoose);
            sleepInt(2);
            phone.click(135, 363);
            sleepInt(3);
        }
        UiObject2 sim1card = waitForObj(By.res(simCardRes));
        verify("在编写信息界面未发现sim1卡", sim1card != null);
        sim1card.click();
        sleepInt(1);
        addStep("发送新信息");
        UiObject2 sendBtn = waitForObj(By.res(Pattern.compile(
                "com.android.mms:id/send_button_mms|com.android.mms:id/send_button_sms")));
        verify("在编写信息界面未发现发送按键", sendBtn != null);
        sendBtn.click();
        sleepInt(5);
        /**确认信息发送成功**/
        verify("短信发送成功后未找到发送时间", phone.hasObject(By.res("com.android.mms:id/date_view")));
        addStep("查看SIM1收到的彩信");
        addStep("等待收到信息");
        verify("未收到彩信", waitForExist(By.res("com.android.mms:id/msg_list_item_recv"), 90 * 1000));
    }

    public void callNum(int SimTag,String callNum){
        String simCardRes = "";
        if (SimTag == SIM1){
            simCardRes = "com.android.dialer:id/show_call_1";
        }else if (SimTag == SIM2){
            simCardRes = "com.android.dialer:id/show_call_2";
        }else {
            Assert.fail("错误的SIMCard参数");
        }

        addStep("使用SIM"+SimTag+"卡打电话");
        launchApp(AppName.PHONE);
        verify("未能找到拨号界面", waitForExist(By.text("拨号").selected(true)));
        sleepInt(1);
        UiObject2 deleteBtn = phone.findObject(By.clazz("android.widget.ImageView")
                .res("com.android.dialer:id/deleteButton"));
        if (deleteBtn != null) {
            deleteBtn.longClick();
        }
        sleepInt(1);
        addStep("拨打号码" + callNum);
        pressDialPad(callNum);
        addStep("用SIM" + SimTag + "卡拨出电话");
        UiObject2 simCard = phone.findObject(By.res(simCardRes));
        verify("在拨号界面没有找到sim"+SimTag+"卡", simCard != null);
        clickAndWaitForNewWindow(simCard);
        sleepInt(10);
        UiObject2 callState = waitForObj(By.res("com.android.dialer:id/callStateLabel"), 10000);
        verify("拨号状态未找到", callState != null);
        verify("号码未拨通", callState.wait(Until.textNotEquals("正在拨号"), 15000));
        sleepInt(5);
        UiObject2 endBtn = waitForObj(By.clazz(
                "android.widget.ImageButton").res(
                "com.android.dialer:id/end_call_button"));
        verify("未能成功接通", endBtn != null);
        addStep("挂断电话");
        clickAndWaitForNewWindow(endBtn);
        press_back(1);
    }

    public void openPage(String url,String verifyTag) {
        addStep("启动浏览器，检测主页键和网址输入框是否存在");
        launchApp(AppName.BROWSER);
        UiObject2 home = waitForObj(By.res("com.android.browser:id/menu_bar_home"));
        verify("启动浏览器后未能发现主页键", home != null);
        home.click();
        sleepInt(1);
        UiObject2 urlEdit = waitForObj(By.clazz("android.widget.EditText")
                .res("com.android.browser:id/url_input_view"));
        verify("启动浏览器后未能发现网址输入框", urlEdit != null);
        addStep("输入特定网址" + url + "并且等待10秒钟看网页是否被打开成功");
        urlEdit.click();
        urlEdit.setText(url);
        sleepInt(1);
        phone.pressEnter();
        sleepInt(10);
        UiObject2 sina = waitForObj(By.textContains(verifyTag)
                .res("com.android.browser:id/url_input_view"), 15 * 1000);
        verify(url + "打开失败", sina != null);
    }

    public void openAndCloseApp(String appName){
        addStep("启动" + appName);
        launchApp(appName);
        UiObject2 agreeAndGo = waitForObj(By.text("同意并继续"),2000);
        if (agreeAndGo != null) clickAndWaitForNewWindow(agreeAndGo);
        phone.runWatchers();
        addStep("home键后台" + appName + "，再次点击" + appName + "应用");
        press_home(1);
        launchApp(appName);
        phone.runWatchers();
        addStep("home键后台" + appName + "。调用多任务，多任务中点击"+appName);
        press_home(1);
        verify("未能返回桌面", waitForExist(By.pkg(PACKAGE_HOME)));
        press_menu(1);
        List<UiObject2> recentApp = waitForObjs(By.res(Pattern.compile(".*leui_recent_thumbnail")));
        verify("未进入多任务界面", recentApp != null && recentApp.size() >=2);
        recentApp.get(1).click();
        verify("多任务中未能进入" + appName, waitForExist(By.pkg(listApps.get(appName))));
        sleep(700);
        phone.runWatchers();
        sleep(1300);
        press_back(2);
        verify("未能退出应用", waitForExist(By.pkg(PACKAGE_HOME)));
    }

    public void addBookmark(boolean delExist){
//      打开Browser，进入书签页
//      检查Download SoGou书签是否存在，delExist = true，删除所有书签，重新添加书签
//                                      delExist = false，书签不存在则添加，存在直接退出Browser
        launchApp(AppName.BROWSER);
        if (Build.VERSION.SDK_INT >22) {
            UiObject2 home = waitForObj(By.res("com.android.browser:id/menu_bar_home"));
            check("can't find home page", home != null);
            home.click();
            sleepInt(2);
            UiObject2 menu = waitForObj(By.res("com.android.browser:id/menu_bar_more"));
            check("can't find more", menu != null);
            menu.click();
            sleepInt(2);
        }else{
            UiObject2 home = waitForObj(By.clazz("android.widget.LinearLayout").res(Pattern.compile("com.android.browser:id/menu_bar_layout|com.android.browser:id/menu_bar_layout_first")));
            UiObject2 share = waitForObj(By.res("com.android.browser:id/menu_bar_share_or_go_forward"));
            //  List<UiObject2> homeButton = home.getChildren();
            UiObject2 menu = waitForObj(By.res("com.android.browser:id/menu_bar_more"));
            check("can't find more",menu != null);
            menu.click();
            sleepInt(2);
        }
        UiObject2 Bookmark = waitForObj(By.text(Pattern.compile("书签.*|Favorites|Bookmarks")));
        check("can't find Favorites", Bookmark != null);
        Bookmark.click();
        sleepInt(2);
        UiObject2 Download = waitForObj(By.text("DOWNLOAD"));
        UiObject2 Sougou = waitForObj(By.text("SOGOU"));
        if(Download==null||Sougou==null){  //书签不存在，直接添加
            newBookMark();
        }
        else{
            if(delExist==true){         //delExist = true，删除所有书签，重新添加书签
                addStep("delete existing bookmark");
                UiObject2 FirstBookmark = waitForObj(By.res("com.android.browser:id/front").clazz("android.widget.LinearLayout"));
                check(FirstBookmark != null);
                int x = FirstBookmark.getVisibleBounds().centerY();
                int y = FirstBookmark.getVisibleBounds().centerY();
                phone.swipe(x, y, x, y, 50);
                sleepInt(2);
                UiObject2 Allselect = waitForObj(By.text(Pattern.compile("全选|Select all")));
                if (Allselect != null) {
                    check("can't find Select all", Allselect != null);
                    Allselect.click();
                    sleepInt(2);
                }
                UiObject2 Delete = waitForObj(By.text(Pattern.compile(".*删除.*|.*Delete.*")));
                check("can't find Delete", Delete != null);
                Delete.click();
                sleepInt(2);
                UiObject2 confirm = waitForObj(By.text(Pattern.compile(".*删除.*|.*Delete.*")));
                check(confirm != null);
                confirm.click();
                sleepInt(2);
                UiObject2 NoBookmark = waitForObj(By.text(Pattern.compile("没有书签|No bookmarks")));
                check(NoBookmark != null);
                newBookMark();
            }
        }
    }

    public void newBookMark(){
//      Browser->书签界面，添加书签
        addStep("AddBookMark");
        press_back(1);
        sleepInt(2);
        UiObject2 url = waitForObj(By.res(Pattern.compile("com.android.browser:id/title_display|com.android.browser:id/home_bg_address_bar_title")));
        if (url != null) {
            url.click();
            sleepInt(2);
        }
        UiObject2 url1 = waitForObj(By.res(Pattern.compile("com.android.browser:id/url_input_view|com.android.browser:id/url_bar")).clazz("android.widget.EditText"));
        check("Can't find url input", url1 != null);
        url1.click();
        sleepInt(2);
//            url1.setText("http://10.57.9.203/");
        url1.setText("http://42.96.171.2/");

        phone.pressEnter();
        sleepInt(3);
        if (Build.VERSION.SDK_INT >22) {
            UiObject2 menu = waitForObj(By.res("com.android.browser:id/menu_bar_more"));
            check("can't find more", menu != null);
            menu.click();
            sleepInt(2);
            UiObject2 addBookmark = waitForObj(By.text(Pattern.compile("添加书签|.*add.*|.*Add.*")));
            check("can't find Add to Favorites", addBookmark != null);
            addBookmark.click();
            sleepInt(2);
            UiObject2 clearBMName = waitForObj(By.res("com.android.browser:id/bookmark_name"));
            UiObject2 delete = waitForObj(By.clazz("android.widget.ImageView"));
            check("No delete button",delete != null);
            delete.click();
            sleepInt(2);
            check("No clear bookmark name field",clearBMName != null);
            clearBMName.setText("DOWNLOAD");
            sleepInt(2);
            UiObject2 save = waitForObj(By.text(Pattern.compile("保存|Save")));
            save = waitForObj(By.text(Pattern.compile("保存|Save")));
            save.click();
            sleepInt(2);
            press_back(1);
            UiObject2 home = waitForObj(By.res("com.android.browser:id/menu_bar_home"));
            check("can't find home page", home != null);
            home.click();
            sleepInt(2);
            sleepInt(2);
            UiObject2 url3 = waitForObj(By.res(Pattern.compile("com.android.browser:id/title_display|com.android.browser:id/home_bg_address_bar_title")));
            if (url3 != null) {
                url3.click();
                sleepInt(2);
            }
            UiObject2 url2 = waitForObj(By.res(Pattern.compile("com.android.browser:id/url_input_view|com.android.browser:id/url_bar")).clazz("android.widget.EditText"));
            check("Can't find url input", url2 != null);
            url2.click();
            sleepInt(2);
            url1.setText("http://www.sogou.com/");
            sleepInt(2);
            phone.pressEnter();
            sleepInt(2);
            UiObject2 menu1 = waitForObj(By.res("com.android.browser:id/menu_bar_more"));
            check("can't find more", menu1 != null);
            menu1.click();
            sleepInt(2);
            UiObject2 addBookmark1 = waitForObj(By.text(Pattern.compile("添加书签|.*add.*|.*Add.*")));
            check("can't find Add to Favorites", addBookmark1 != null);
            addBookmark1.click();
            sleepInt(2);
            delete = waitForObj(By.clazz("android.widget.ImageView"));
            check("No delete button",delete != null);
            delete.click();
            sleepInt(5);
            clearBMName = waitForObj(By.res("com.android.browser:id/bookmark_name"));
            check("No clear bookmark name field",clearBMName != null);
            clearBMName.setText("SOGOU");
            sleepInt(5);
            save = waitForObj(By.text(Pattern.compile("保存|Save")));
            check("No save button",save != null);
            save.click();
            sleepInt(5);
        }else{

            UiObject2 share = waitForObj(By.res("com.android.browser:id/menu_bar_share_or_go_forward"));
            if(share != null){
                share.click();
                sleepInt(1);
            }else {
                UiObject2 menu = waitForObj(By.res("com.android.browser:id/menu_bar_more"));
                check("can't find more", menu != null);
                menu.click();
                sleepInt(2);
            }
            UiObject2 addBookmark = waitForObj(By.text(Pattern.compile(".*书签|Add to Favorites")));
            check("can't find Add to Favorites", addBookmark != null);
            addBookmark.click();
            sleepInt(2);
            UiObject2 clearBMName = waitForObj(By.res("com.android.browser:id/bookmark_name"));
            UiObject2 delete = waitForObj(By.clazz("android.widget.ImageView"));
            check("No delete button",delete != null);
            delete.click();
            sleepInt(2);
            check("No clear bookmark name field",clearBMName != null);
            clearBMName.setText("DOWNLOAD");
            sleepInt(2);
            UiObject2 save = waitForObj(By.text(Pattern.compile("保存|Save")));
            check("No save button",save != null);
            save.click();
            sleepInt(2);
            press_back(1);
            UiObject2 home1 = waitForObj(By.clazz("android.widget.LinearLayout").res(Pattern.compile("com.android.browser:id/menu_bar_layout|com.android.browser:id/menu_bar_layout_first")));
            List<UiObject2> homeButton = home1.getChildren();
            //   homeButton.get(2).click();
            sleepInt(2);
            if (url != null) {
                url.click();
                sleepInt(2);
            }
            url1.click();
            sleepInt(2);
            url1.setText("http://www.sogou.com/");
            sleepInt(2);
            phone.pressEnter();
            sleepInt(2);
            UiObject2 share1 = waitForObj(By.res("com.android.browser:id/menu_bar_share_or_go_forward"));
            if(share1 != null){
                share1.click();
            }else {
                UiObject2 menu = waitForObj(By.res("com.android.browser:id/menu_bar_more"));
                check("can't find more", menu != null);
                menu.click();
            }
            sleepInt(2);
            UiObject2 addBookmark1 = waitForObj(By.text(Pattern.compile(".*书签|Add to Favorites")));
            check("can't find Add to Favorites", addBookmark1 != null);
            addBookmark1.click();
            sleepInt(5);
            delete = waitForObj(By.clazz("android.widget.ImageView"));
            check("No delete button",delete != null);
            delete.click();
            sleepInt(5);
            clearBMName = waitForObj(By.res("com.android.browser:id/bookmark_name"));
            check("No clear bookmark name field", clearBMName != null);
            clearBMName.setText("SOGOU");
            sleepInt(5);
            save = waitForObj(By.text(Pattern.compile("保存|Save")));
            check("No save button",save != null);
            save.click();
            sleepInt(5);
        }
        press_back(4);
        press_back(3);
        press_home(1);
    }

//  Open subSetting
    public void launchSettings(String subSettingsText,String checkMethod){
//   subSettings : WLAN Bluetoot.etc
//   checkMethod : verify  or  check
//   eg: launchSettings("WLAN","check");
        Map<String,String> map = new HashMap<>();
        map.put("WLAN","WLAN|Wi-Fi");
        map.put("Bluetooth","Bluetooth|蓝牙");
        map.put("Personal hotspot","Personal hotspot|个人热点");
        map.put("Dual SIM & mobile networks","双卡和移动网络|Dual SIM & mobile networks");
        map.put("Aeroplane mode","Aeroplane mode|Aeroplane mode|飞行模式");
        map.put("Notifications","通知管理|Notification management|通知|Notifications");
        map.put("Location","位置信息|Location");
        map.put("About phone","关于手机|About phone");
        map.put("Display","显示|Display");
        map.put("Do not disturb","勿扰|Do not disturb|勿扰模式");
        map.put("Personalize","个性化|.*Personalize.*");
        map.put("Control center","控制中心|Control center");
        map.put("Accessibility","Accessibility|辅助功能");
        if(!map.containsKey(subSettingsText)){
            if(checkMethod=="check"){
                check("subSetting "+subSettingsText + "not in the maplist",false);
            }else{
                verify("subSetting "+subSettingsText + "not in the maplist",false);
            }
            return;
        }
        addStep("Launch settings app");
        launchApp(AppName.SETTING);
        UiObject2 subSettings = null;
        //Scroll down to find
        for (int i = 0; i < 3; i++) {
            subSettings = waitForObj(By.text(Pattern.compile(map.get(subSettingsText))));
            if (subSettings == null) {
                UiObject2 scrollableView = waitForObj(By.scrollable(true));
                check("No scrollable view", scrollableView != null);
                scrollableView.scroll(Direction.DOWN,1f,3000);
                sleepInt(1);
            } else {
                break;
            }
        }
        //if scroll down to find failed,then scroll up to find again
        if (subSettings == null){
            for (int i = 0; i < 4; i++) {
                subSettings = waitForObj(By.text(Pattern.compile(map.get(subSettingsText))));
                if (subSettings == null) {
                    UiObject2 scrollableView = waitForObj(By.scrollable(true));
                    check("No scrollable view", scrollableView != null);
                    scrollableView.scroll(Direction.UP,1f,3000);
                    sleepInt(1);
                } else {
                    break;
                }
            }
        }

        if(checkMethod=="check"){
            check("do not find subSetting "+subSettingsText ,subSettings != null);
        }else{
            verify("do not find subSetting "+subSettingsText ,subSettings != null);
        }
        addStep("Open subSetting "+ subSettingsText);
        subSettings.click();
        sleepInt(2);
    }


    /**
     * 结束应用进程(Kill app progress)
     */
    public void killProgress(){
        press_menu(1);
        sleep(500);
        if(!(callShell("getprop").contains("zl1"))){
            phone.swipe((int)(phone.getDisplayWidth()*0.75),(int)(phone.getDisplayHeight()*0.5),
                    (int)(phone.getDisplayWidth()*0.25),(int)(phone.getDisplayHeight()*0.5), 20);
        }else{
            //ZL1
            phone.swipe((int)(phone.getDisplayWidth()*0.5),(int)(phone.getDisplayHeight()*0.75),
                    (int)(phone.getDisplayWidth()*0.5),(int)(phone.getDisplayHeight()*0.25), 20);
        }

    }
}