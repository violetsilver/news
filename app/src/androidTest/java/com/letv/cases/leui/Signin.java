package com.letv.cases.leui;

import android.app.KeyguardManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Build;
import android.os.RemoteException;
import android.os.SystemClock;
import android.support.test.InstrumentationRegistry;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.StaleObjectException;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObject2;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiSelector;
import android.support.test.uiautomator.UiWatcher;
import android.util.Log;

import com.letv.base.CaseName;
import com.letv.base.LetvTestCase;
import com.letv.base.Utf7ImeHelper;
import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;

import static java.lang.Float.parseFloat;
import static java.lang.Integer.parseInt;

/**
 * Created by zhoujine on 2017/12/8.
 */

public class Signin extends LetvTestCase{

   /* @Before
    public void setUp() throws Exception {
        phone.registerWatcher("importantWatcher", importantWatcher);
        if(!isScreenLocked(InstrumentationRegistry.getContext())){
            callShell("input keyevent 26");
            sleepInt(1);
            phone.swipe((int) (phone.getDisplayWidth() * 0.5), (int) (phone.getDisplayHeight() * 0.7), (int) (phone.getDisplayWidth() * 0.5), (int) (phone.getDisplayHeight() * 0.2), 15);
            sleepInt(1);
            phone.swipe((int) (phone.getDisplayWidth() * 0.5), (int) (phone.getDisplayHeight() * 0.7), (int) (phone.getDisplayWidth() * 0.5), (int) (phone.getDisplayHeight() * 0.2), 15);
            sleepInt(1);
*//*                int x = phone.getDisplayWidth()/2;
                int height = phone.getDisplayHeight();
                phone.swipe(x, height/10*3, x, height/10*1, 10);
                phone.swipe(x, height/10*3, x, height/10*1, 10);*//*
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
    }*/

    @Test
    @CaseName("切换声音模式，进入首页播放视频")
    public void testSoundMode() throws UiObjectNotFoundException, RemoteException {
        press_back(3);
        phone.registerWatcher("importantWatcher", importantWatcher);
        if(!isScreenLocked(InstrumentationRegistry.getContext())){
            callShell("input keyevent 26");
            sleepInt(1);
            phone.swipe((int) (phone.getDisplayWidth() * 0.5), (int) (phone.getDisplayHeight() * 0.7), (int) (phone.getDisplayWidth() * 0.5), (int) (phone.getDisplayHeight() * 0.2), 15);
            sleepInt(1);
            phone.swipe((int) (phone.getDisplayWidth() * 0.5), (int) (phone.getDisplayHeight() * 0.7), (int) (phone.getDisplayWidth() * 0.5), (int) (phone.getDisplayHeight() * 0.2), 15);
            sleepInt(1);
/*                int x = phone.getDisplayWidth()/2;
                int height = phone.getDisplayHeight();
                phone.swipe(x, height/10*3, x, height/10*1, 10);
                phone.swipe(x, height/10*3, x, height/10*1, 10);*/
            sleepInt(1);
            UiObject2 p6=waitForObj(By.text("6"));
            p6.click();
            UiObject2 p9=waitForObj(By.text("9"));
            p9.click();
            UiObject2 p61=waitForObj(By.text("6"));
            p61.click();
            UiObject2 p91=waitForObj(By.text("9"));
            p91.click();
            sleepInt(1);
        };
        callShell("am start -n com.nuc.jct.ddd/io.dcloud.PandoraEntryActivity");
        UiObject2 pkg=waitForObj(By.pkg("com.nuc.jct.ddd"));
        verify("没有进入乐界面",pkg!=null);
        sleepInt(8);
        phone.click(540,1481);
        phone.click(540,1481);
        sleepInt(1);
        UiObject2 success=waitForObj(By.text("打卡成功!"));
        verify("打开失败！！",success!=null);
    }
    public final static boolean isScreenLocked(Context c) {

        KeyguardManager mKeyguardManager = (KeyguardManager) c.getSystemService(c.KEYGUARD_SERVICE);
        return !mKeyguardManager.inKeyguardRestrictedInputMode();

    }
    private BroadcastReceiver myReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            // TODO Auto-generated method stub
            boolean islockScreen=false;
            if (Intent.ACTION_SCREEN_ON.equals(intent.getAction()) ) {//当按下电源键，屏幕亮起的时候

            }
            if (Intent.ACTION_SCREEN_OFF.equals(intent.getAction()) ) {//当按下电源键，屏幕变黑的时候
                islockScreen = true;
            }
            if (Intent.ACTION_USER_PRESENT.equals(intent.getAction()) ) {//当解除锁屏的时候
                islockScreen = false;
            }
        }
    };

/*    @Test
    public void testQukan() throws UiObjectNotFoundException, RemoteException {
       // callShell("am start -n com.jifen.qukan/com.jifen.qukan.view.activity.MainActivity");
        UiObject2 newsTao = phone.findObject(By.text("趣头条"));
        if (newsTao != null) {
            newsTao.click();
        }
        sleepInt(5);
        UiObject2 touTiao = phone.findObject(By.text("头条"));
        if (touTiao != null) {
            touTiao.click();
            sleepInt(1);
        }
        phone.swipe((int)(phone.getDisplayWidth()*0.2),(int)(phone.getDisplayHeight()*0.12),(int)(phone.getDisplayWidth()*0.8),(int)(phone.getDisplayHeight()*0.12),30);
        sleepInt(1);
        String[] names={"推荐","热点","北京","娱乐","养生"};
        int name=getRandom1(names.length);
        Log.i(TAG, "testQukan: 1111111"+names[name]);
        for(int q=0;q<10;q++) {
            UiObject2 type = phone.findObject(By.text(names[name]));
            //UiObject2 type = phone.findObject(By.text("推荐"));
            if (type==null) {
                phone.swipe((int)(phone.getDisplayWidth()*0.72),(int)(phone.getDisplayHeight()*0.12),(int)(phone.getDisplayWidth()*0.57),(int)(phone.getDisplayHeight()*0.12),30);
                sleepInt(1);
            }else break;
        }
        sleepInt(1);
        UiObject2 type = phone.findObject(By.text(names[name]));
        verify("beijing not exists", type != null);
        type.click();
        sleepInt(1);
        phone.swipe((int)(phone.getDisplayWidth()*0.5),(int)(phone.getDisplayHeight()*0.2),(int)(phone.getDisplayWidth()*0.5),(int)(phone.getDisplayHeight()*0.8),15);
        sleepInt(5);
        for(int k=0;k<20;k++) {
            List<UiObject2> lists=phone.findObject(By.clazz("android.support.v7.widget.RecyclerView")).getChildren();
            for(int q=0;q<lists.size()-1;q++){
                UiObject2 a=lists.get(q).findObject(By.text(Pattern.compile("广告|视频|点击下载.*")));
                if(a==null){
                    lists.get(q).click();
                    sleepInt(2);
                    for (int j = 0; j < 5; j++) {
                        sleepInt(15);
                        phone.swipe(600 + getRandom(200), 1340 + getRandom(100), 600 + getRandom(200), 800 + getRandom(100), getRandom(150));
                        sleepInt(3);
                        verify("没有在悦头条界面",phone.getCurrentPackageName().equals("com.jifen.qukan"));
                        UiObject2 video=phone.findObject(By.clazz("baiduvr.ag"));
                        if(video!=null)break;
                        UiObject2 readAll = phone.findObject(By.desc(Pattern.compile(".*展开查看全文.*")));
                        if (readAll != null) {
                            phone.swipe(600 + getRandom(200), 1500 + getRandom(100), 600 + getRandom(200), 600 + getRandom(100), getRandom(150));
                            sleepInt(2);
                            phone.swipe(600 + getRandom(200), 1500 + getRandom(100), 600 + getRandom(200), 600 + getRandom(100), getRandom(150));
                            sleepInt(2);
                            readAll.click();
                            sleepInt(1);
                            phone.swipe((int)(phone.getDisplayWidth()*0.5),(int)(phone.getDisplayHeight()*0.8),(int)(phone.getDisplayWidth()*0.5),(int)(phone.getDisplayHeight()*0.2),15);
                            sleepInt(3);
                            phone.swipe((int)(phone.getDisplayWidth()*0.5),(int)(phone.getDisplayHeight()*0.8),(int)(phone.getDisplayWidth()*0.5),(int)(phone.getDisplayHeight()*0.2),15);
                            sleepInt(3);
                            phone.swipe((int)(phone.getDisplayWidth()*0.5),(int)(phone.getDisplayHeight()*0.8),(int)(phone.getDisplayWidth()*0.5),(int)(phone.getDisplayHeight()*0.2),15);
                            sleepInt(3);
                            phone.swipe((int)(phone.getDisplayWidth()*0.5),(int)(phone.getDisplayHeight()*0.8),(int)(phone.getDisplayWidth()*0.5),(int)(phone.getDisplayHeight()*0.2),15);
                            sleepInt(3);
                            phone.swipe((int)(phone.getDisplayWidth()*0.5),(int)(phone.getDisplayHeight()*0.8),(int)(phone.getDisplayWidth()*0.5),(int)(phone.getDisplayHeight()*0.2),10);
                            sleepInt(3);
                            break;
                        }
                    }
                }
                press_back(1);
            }
            phone.swipe((int)(phone.getDisplayWidth()*0.5),(int)(phone.getDisplayHeight()*0.85),(int)(phone.getDisplayWidth()*0.5),(int)(phone.getDisplayHeight()*0.15),30);
            sleepInt(1);
        }
    }*/

    @Test
    public void testSigninQukan() throws UiObjectNotFoundException, RemoteException {
        press_home(1);
        UiObject2 newsTao = phone.findObject(By.text("趣头条"));
        newsTao.click();
        sleepInt(10);
        UiObject2 touTiao = phone.findObject(By.text("推荐"));
        if (touTiao == null) {
            press_back(1);
        }
        UiObject2 fetch = phone.findObject(By.text("领取"));
        if (fetch != null) {
            fetch.click();
            sleepInt(1);
        }
        UiObject2 mine = phone.findObject(By.text("任务"));
        if (mine == null) {
            press_back(1);
        }
        mine = phone.findObject(By.text("任务"));
        verify("任务不存在", mine != null);
        mine.click();
        sleepInt(4);
    }

    @Test
    public void testQukan() throws UiObjectNotFoundException, RemoteException {
        press_home(1);
        UiObject2 newsTao = phone.findObject(By.text("趣头条"));
        newsTao.click();
        sleepInt(10);
        UiObject2 touTiao = phone.findObject(By.text("推荐"));
        if (touTiao == null) {
            press_back(1);
        }
        UiObject2 fetch = phone.findObject(By.text("领取"));
        if (fetch != null) {
            fetch.click();
            sleepInt(1);
        }
        UiObject2 mine = phone.findObject(By.text("任务"));
        if (mine == null) {
            press_back(1);
        }
        phone.swipe((int)(phone.getDisplayWidth()*0.2),(int)(phone.getDisplayHeight()*0.12),(int)(phone.getDisplayWidth()*0.8),(int)(phone.getDisplayHeight()*0.12),30);
        sleepInt(1);
        String[] names={"推荐","热点","北京","娱乐"};
        int name=getRandom1(names.length);
        Log.i(TAG, "testQukan: 1111111"+names[name]);
        for(int q=0;q<10;q++) {
            UiObject2 type = phone.findObject(By.text(names[name]));
            if (type==null) {
                phone.swipe((int)(phone.getDisplayWidth()*0.72),(int)(phone.getDisplayHeight()*0.12),(int)(phone.getDisplayWidth()*0.57),(int)(phone.getDisplayHeight()*0.12),30);
                sleepInt(1);
            }else break;
        }
        sleepInt(1);
        UiObject2 type = phone.findObject(By.text(names[name]));
        verify(names[name]+"beijing not exists", type != null);
        type.click();
        sleepInt(1);
        phone.swipe((int)(phone.getDisplayWidth()*0.5),(int)(phone.getDisplayHeight()*0.2),(int)(phone.getDisplayWidth()*0.5),(int)(phone.getDisplayHeight()*0.8),15);
        sleepInt(5);
        for(int k=0;k<8;k++) {
            phone.click((int)(phone.getDisplayWidth()*0.5),(int)(phone.getDisplayHeight()*0.5));
            sleepInt(2);
            for (int i = 0; i < 3; i++) {
                if (!phone.getCurrentPackageName().equals("com.jifen.qukan")) {
                    phone.pressBack();
                    sleepInt(1);
                } else break;
            }
            UiObject2 toutiao1 = phone.findObject(By.text("推荐"));
            if (toutiao1 != null) {
                phone.click((int) (phone.getDisplayWidth() * 0.7), (int) (phone.getDisplayHeight() * 0.7));
                sleepInt(1);
            }
            for (int j = 0; j < 13; j++) {
                UiObject2 video=waitForObj(By.clazz("baiduvr.ag"));
                if(video!=null)break;
                phone.swipe((int)(phone.getDisplayWidth() * 0.5),(int)(phone.getDisplayHeight() * 0.75),(int)(phone.getDisplayWidth() * 0.5),(int)(phone.getDisplayHeight() * 0.5),30);
                sleepInt(4);
                UiObject2 readAll = phone.findObject(By.desc(Pattern.compile(".*展开查看全文.*")));
                if (readAll != null) {
                    readAll.click();
                    sleepInt(1);
                }
                phone.swipe((int)(phone.getDisplayWidth() * 0.5),(int)(phone.getDisplayHeight() * 0.6),(int)(phone.getDisplayWidth() * 0.5),(int)(phone.getDisplayHeight() * 0.65),30);
                sleepInt(4);
                verify("没有在悦头条界面", phone.getCurrentPackageName().equals("com.jifen.qukan"));
                readAll = phone.findObject(By.desc(Pattern.compile(".*展开查看全文.*")));
                if (readAll != null) {
                    readAll.click();
                    sleepInt(1);
                }
                UiObject2 circle = phone.findObject(By.res("com.jifen.qukan:id/a85"));
                if (circle == null) {
                    break;
                }
            }
            press_back(1);
            phone.swipe((int)(phone.getDisplayWidth()*0.5),(int)(phone.getDisplayHeight()*0.67),(int)(phone.getDisplayWidth()*0.5),(int)(phone.getDisplayHeight()*0.4),30);
            sleepInt(1);
        }
    }
    @Test
    public void testKuaiMa() throws UiObjectNotFoundException, RemoteException {
        // callShell("am start -n com.jifen.qukan/com.jifen.qukan.view.activity.MainActivity");
        press_home(1);
        UiObject2 newsTao = phone.findObject(By.text("快马小报"));
        newsTao.click();
        sleepInt(5);
        for(int i=0;i<2;i++) {
            UiObject2 touTiao = phone.findObject(By.text("我的"));
            if (touTiao == null) {
                press_back(1);
                sleepInt(1);
            } else break;
        }
        UiObject2 touTiao = phone.findObject(By.text("小报"));
        if (touTiao != null) {
            touTiao.click();
            sleepInt(1);
        }
        sleepInt(1);
        String[] names={"推荐","美食","北京","社会"};
        int name=getRandom1(names.length);
        sleepInt(1);
        for(int i=0;i<5;i++){
            UiObject2 type = phone.findObject(By.text(names[name]));
            Log.i(TAG, "testKuaiMa: 1221");
            if(type==null) {
                if (Build.DEVICE.equals("ja3gduosctc")) {
                    phone.swipe((int) (phone.getDisplayWidth() * 0.72), (int) (phone.getDisplayHeight() * 0.15), (int) (phone.getDisplayWidth() * 0.3), (int) (phone.getDisplayHeight() * 0.15), 30);
                } else {
                    phone.swipe((int) (phone.getDisplayWidth() * 0.72), (int) (phone.getDisplayHeight() * 0.11), (int) (phone.getDisplayWidth() * 0.3), (int) (phone.getDisplayHeight() * 0.11), 30);
                    Log.i(TAG, "testKuaiMa: 111");
                }
            }else {
                break;
            }
            sleepInt(1);
        }

        UiObject2 type = phone.findObject(By.text(names[name]));
        if(type!=null) {
            type.click();
            sleepInt(1);
        }
        phone.swipe((int)(phone.getDisplayWidth()*0.5),(int)(phone.getDisplayHeight()*0.2),(int)(phone.getDisplayWidth()*0.5),(int)(phone.getDisplayHeight()*0.8),15);
        sleepInt(5);
        UiObject2 fetch=phone.findObject(By.text("领取金币"));
        if(fetch!=null){
            fetch.click();
            sleepInt(1);
            press_back(1);
        }
        int m=0;
        for(int k=0;k<10;k++) {
            List<UiObject2> lists=phone.findObject(By.clazz("android.support.v7.widget.RecyclerView")).getChildren();
            for(int q=0;q<lists.size()-1;q++){
                try {
                    UiObject2 c = lists.get(q).findObject(By.text(Pattern.compile("广告|热门精选|漫言情")));
                    if (c == null) {
                        lists.get(q).click();
                        sleepInt(5);
                        UiObject2 gress = phone.findObject(By.res("com.kuaima.browser:id/iv_progress"));
                        if (gress != null) {
                            for (int j = 0; j < 4; j++) {
                                sleepInt(1);
                                verify("没有在悦头条界面", phone.getCurrentPackageName().equals("com.kuaima.browser"));
                                phone.swipe((int) (phone.getDisplayWidth() * 0.5), (int) (phone.getDisplayHeight() * 0.8), (int) (phone.getDisplayWidth() * 0.5), (int) (phone.getDisplayHeight() * 0.2), 5);
                                sleepInt(2);
                                verify("没有在悦头条界面", phone.getCurrentPackageName().equals("com.kuaima.browser"));
                                phone.swipe((int) (phone.getDisplayWidth() * 0.5), (int) (phone.getDisplayHeight() * 0.55), (int) (phone.getDisplayWidth() * 0.5), (int) (phone.getDisplayHeight() * 0.6), 30);
                                sleepInt(1);
                                verify("没有在悦头条界面", phone.getCurrentPackageName().equals("com.kuaima.browser"));
                                phone.swipe((int) (phone.getDisplayWidth() * 0.5), (int) (phone.getDisplayHeight() * 0.8), (int) (phone.getDisplayWidth() * 0.5), (int) (phone.getDisplayHeight() * 0.2), 5);
                                sleepInt(2);
                            }
                            if (k % 3 == 0 && !(Build.DEVICE.equals("pisces"))) {
                                UiObject2 searchInput = waitForObj(By.clazz("android.widget.EditText"));
                                verify("No search field", searchInput != null);
                                sleepInt(2);
                                searchInput.setText("亲们互惠互赞哈哈\n亲测都能提现到账：" + "\n" + "快马小报邀请码：194404" + "\n" + "悦头条邀请码:23591804" + "\n" + "惠头条邀请码:27704076");
                                sleepInt(2);
                                UiObject2 send = phone.findObject(By.res("com.kuaima.browser:id/fl_comm"));
                                send.click();
                                sleepInt(3);
                            }
                        } else m++;
                        if (m >= 3) break;
                    }
                    press_back(1);
                }catch (StaleObjectException e){
                    phone.waitForWindowUpdate("com.kuaima.browser",10000);
                    continue;
                }
            }
            phone.swipe((int)(phone.getDisplayWidth()*0.5),(int)(phone.getDisplayHeight()*0.85),(int)(phone.getDisplayWidth()*0.5),(int)(phone.getDisplayHeight()*0.15),30);
            sleepInt(1);
        }
    }
    @Test
    public void testAiKan() throws UiObjectNotFoundException, RemoteException {
        // callShell("am start -n com.jifen.qukan/com.jifen.qukan.view.activity.MainActivity");
        UiObject2 newsTao = phone.findObject(By.text("爱看"));
        if (newsTao != null) {
            newsTao.click();
        }
        sleepInt(5);
        UiObject2 touTiao = phone.findObject(By.text("首页"));
        if (touTiao != null) {
            touTiao.click();
            sleepInt(1);
        }else press_back(1);
        touTiao = phone.findObject(By.text("首页"));
        touTiao.click();
        sleepInt(1);
/*        String[] names={"推荐","美食","北京","社会","推荐"};
        int name=getRandom1(names.length);
        Log.i(TAG, "testKuaiMa: "+names[name]+name);
        sleepInt(1);
        for(int i=0;i<5;i++){
            UiObject2 type = phone.findObject(By.text(names[name]));
            if(type==null){
                UiObject2 b=phone.findObject(By.clazz("android.support.v7.app.b"));
                int y=b.getVisibleBounds().centerY();
                phone.swipe((int)(phone.getDisplayWidth()*0.8),y,(int)(phone.getDisplayWidth()*0.2),y,30);
                sleepInt(1);
            }else break;
        }*/

        UiObject2 type = phone.findObject(By.text("娱乐"));
        verify("beijing not exists", type != null);
        type.click();
        sleepInt(1);
        phone.swipe((int)(phone.getDisplayWidth()*0.5),(int)(phone.getDisplayHeight()*0.2),(int)(phone.getDisplayWidth()*0.5),(int)(phone.getDisplayHeight()*0.8),15);
        sleepInt(5);
        UiObject2 fetch=phone.findObject(By.text("领取金币"));
        if(fetch!=null){
            fetch.click();
            sleepInt(1);
            press_back(1);
        }
        int m=0;
        for(int k=0;k<10;k++) {
            List<UiObject2> lists=phone.findObject(By.clazz(Pattern.compile("android.support.v7.widget.RecyclerView|android.widget.ListView"))).getChildren();
            for(int q=0;q<lists.size()-1;q++){
                UiObject2 c=lists.get(q).findObject(By.text(Pattern.compile("广告|立即下载")));
                if(c==null){
                    lists.get(q).click();
                    sleepInt(5);
                    UiObject2 gress=phone.findObject(By.res("com.kuaima.browser:id/iv_progress"));
                    if(gress!=null) {
                        for (int j = 0; j < 4; j++) {
                            sleepInt(1);
                            verify("没有在悦头条界面", phone.getCurrentPackageName().equals("com.kuaima.browser"));
                            phone.swipe((int) (phone.getDisplayWidth() * 0.5), (int) (phone.getDisplayHeight() * 0.8), (int) (phone.getDisplayWidth() * 0.5), (int) (phone.getDisplayHeight() * 0.2), 5);
                            sleepInt(2);
                            verify("没有在悦头条界面", phone.getCurrentPackageName().equals("com.kuaima.browser"));
                            phone.swipe((int) (phone.getDisplayWidth() * 0.5), (int) (phone.getDisplayHeight() * 0.55), (int) (phone.getDisplayWidth() * 0.5), (int) (phone.getDisplayHeight() * 0.6), 30);
                            sleepInt(1);
                            verify("没有在悦头条界面", phone.getCurrentPackageName().equals("com.kuaima.browser"));
                            phone.swipe((int) (phone.getDisplayWidth() * 0.5), (int) (phone.getDisplayHeight() * 0.8), (int) (phone.getDisplayWidth() * 0.5), (int) (phone.getDisplayHeight() * 0.2), 5);
                            sleepInt(2);
                        }
                        if(k%3==0&&!(Build.DEVICE.equals("pisces"))){
                            UiObject2 searchInput = waitForObj(By.clazz("android.widget.EditText"));
                            verify("No search field", searchInput != null);
                            sleepInt(2);
                            searchInput.setText("亲们互惠互赞哈哈\n亲测都能提现到账："+"\n"+"快马小报邀请码：194404"+"\n"+"悦头条邀请码:23591804"+"\n"+"惠头条邀请码:27704076");
                            sleepInt(2);
                            UiObject2 send=phone.findObject(By.res("com.kuaima.browser:id/fl_comm"));
                            send.click();
                            sleepInt(3);
                        }
                    }else m++;
                    if(m>=3)break;
                }
                press_back(1);
            }
            phone.swipe((int)(phone.getDisplayWidth()*0.5),(int)(phone.getDisplayHeight()*0.85),(int)(phone.getDisplayWidth()*0.5),(int)(phone.getDisplayHeight()*0.15),30);
            sleepInt(1);
        }
    }

    @Test
    public void testWeiLiSearch() throws UiObjectNotFoundException, RemoteException {
        phone.pressHome();
        sleepInt(1);
        UiObject2 huitoutiao = phone.findObject(By.text("微鲤头条"));
        huitoutiao.click();
        sleepInt(10);
        UiObject2 fresh = phone.findObject(By.text("头条"));
        if (fresh != null) {
            fresh.click();
            sleepInt(3);
        }
        UiObject2 box = phone.findObject(By.res("cn.weli.story:id/rl_treasure_box"));
        if (box != null) {
            box.click();
            sleepInt(2);
        }
        UiObject2 reading=phone.findObject(By.text("知道了"));
        if(reading!=null){
            reading.click();
            sleepInt(1);
        }
        UiObject2 mine = phone.findObject(By.text("我的"));
        if(mine==null){
            press_back(1);
            Log.i(TAG, "testWeiLiSearch: 111");
        }
        mine = waitForObj(By.text("我的"));
        mine.click();
        sleepInt(1);
        phone.click((int)(phone.getDisplayWidth() * 0.8),(int) (phone.getDisplayHeight() * 0.14));
        sleepInt(1);
        phone.swipe((int)(phone.getDisplayWidth() * 0.5),(int) (phone.getDisplayHeight() * 0.7),(int)(phone.getDisplayWidth() * 0.5),(int) (phone.getDisplayHeight() * 0.3), 10);
        sleepInt(3);
        UiObject2 searchLi = phone.findObject(By.text("立即搜索"));
        if(searchLi==null) {
            UiObject2 searchmy = phone.findObject(By.text("搜索关键词"));
            searchmy.click();
            sleepInt(1);
        }
        searchLi = phone.findObject(By.text("立即搜索"));
        searchLi.click();
        sleepInt(2);
        UiObject2 search = phone.findObject(By.res("cn.weli.story:id/edt_tool_search"));
        verify("adf", search != null);
        search.click();
        sleepInt(1);
        UiObject2 tosearch = phone.findObject(By.text(Pattern.compile("去搜索.*")));
        if(tosearch!=null) {
            for (int k = 0; k < 30; k++) {
                UiObject2 money = phone.findObject(By.text(Pattern.compile("领取.*")));
                if(money!=null){
                    money.click();
                    sleepInt(1);
                }
                UiObject2 done = phone.findObject(By.text(Pattern.compile("已领取")));
                if(done!=null&&done.getChildCount()==3)break;
                String[] names = {"黄豆", "蔷薇", "五十步笑百步", "马齿笕", "曲麻菜", "蒲公英", "秋葵", "鱼腥草", "板蓝根", "金银花", "黄芪", "山莓", "甘草", "当归", "葛根", "芡实", "杜仲", "蛇莓", "人参", "茯苓", "白术", "高圆圆", "赵又廷", "贾静雯", "王珞丹", "张钧甯", "杨幂", "景甜", "刘涛", "刘亦菲", "刘恺威", "唐嫣", "刘诗诗", "罗晋", "霍建华", "赵丽颖", "郑爽", "张檬", "刘亦菲", "孙俪", "姚笛", "张学友", "迪丽热巴", "马苏", "陈好", "赵立新", "许晴", "徐露", "马伊琍", "李小璐", "章子怡", "霍思燕", "赵雅芝", "罗晋", "鹿晗", "范冰冰", "周润发", "刘欢", "尤浩然", "李连杰", "陈赫", "李香凝", "杜旭东", "李小龙", "洪金宝", "董骠", "王宝强", "周星驰", "梅艳芳", "向佐", "黄家驹", "于承惠", "邱淑贞", "梁朝伟", "张国荣", "张碧晨", "迈克尔·杰克逊", "黄贯中", "富九", "黄家强", "释小龙", "赵文卓", "陈小春", "黄渤", "周星驰", "洪金宝", "吴彦祖", "郑伊健", "刘德华", "吴秀波", "胡歌", "段奕宏", "靳东", "迪丽热巴", "张铁林", "陈道明", "林永健", "韩红", "李幼斌", "李雪健", "王志文", "张国立", "唐国强", "左小青", "孙红雷", "杨紫", "谢孟伟", "郑爽", "杨洋","白敬亭","吴磊","李沁","陈学冬","黄子韬","井柏然","乔任梁","angelababy","付辛博","张翰","宋祖儿","行尸走肉","金蝉脱壳","百里挑一","金玉满堂","背水一战","霸王别姬","天上人间","不吐不快","海阔天空","情非得已","满腹经纶","兵临城下","春暖花开","插翅难逃","黄道吉日","天下无双","偷天换日","两小无猜","卧虎藏龙","珠光宝气","簪缨世族","花花公子","绘声绘影","国色天香","相亲相爱","八仙过海","金玉良缘","掌上明珠","皆大欢喜","逍遥法外","生财有道","极乐世界","情不自禁","愚公移山","魑魅魍魉","龙生九子","精卫填海","海市蜃楼","高山流水","卧薪尝胆","壮志凌云","金枝玉叶","四海一家","穿针引线","无忧无虑","无地自容","三位一体","落叶归根","相见恨晚","惊天动地","滔滔不绝","相濡以沫","长生不死","原来如此","女娲补天","三皇五帝","万箭穿心","水木清华","窈窕淑女","破釜沉舟","天涯海角","牛郎织女","倾国倾城","飘飘欲仙","福星高照","妄自菲薄","永无止境","学富五车","饮食男女","英雄豪杰","国士无双","塞翁失马","万家灯火","石破天惊","精忠报国","养生之道","覆雨翻云","六道轮回","鹰击长空","日日夜夜","厚德载物","亡羊补牢","万里长城","黄金时代","出生入死","一路顺风","随遇而安","千军万马","郑人买履","棋逢对手","叶公好龙","后会无期","守株待兔","凤凰于飞","一生一世","花好月圆","世外桃源","韬光养晦","画蛇添足","青梅竹马","风花雪月","滥竽充数","没完没了","总而言之","欣欣向荣","时光荏苒","差强人意","好好先生","无懈可击","随波逐流","袖手旁观","群雄逐鹿","血战到底","唯我独尊","买椟还珠","龙马精神","一见钟情","喜闻乐见","负荆请罪","三人成虎","河东狮吼","程门立雪","金戈铁马","笑逐颜开","千钧一发","纸上谈兵","风和日丽","邯郸学步","大器晚成","庖丁解牛","甜言蜜语","雷霆万钧","浮生若梦","大开眼界","汗牛充栋","百鸟朝凤","以德服人","白驹过隙","难兄难弟","鬼哭神嚎","声色犬马","指鹿为马","龙争虎斗","雾里看花","男大当婚","未雨绸缪","南辕北辙","三从四德","一丝不挂","高屋建瓴","阳春白雪","杯弓蛇影","闻鸡起舞","四面楚歌","登堂入室","张灯结彩","而立之年","饮鸩止渴","杏雨梨云","龙凤呈祥","勇往直前","左道旁门","莫衷一是","马踏飞燕","掩耳盗铃","大江东去","凿壁偷光","色厉内荏","花容月貌","越俎代庖","鳞次栉比","美轮美奂","缘木求鱼","再接再厉","马到成功","红颜知己","赤子之心","迫在眉睫","风流韵事","相形见绌","诸子百家","鬼迷心窍","星火燎原","画地为牢","岁寒三友","花花世界","纸醉金迷","狐假虎威","纵横捭阖","沧海桑田","不求甚解","暴殄天物","吃喝玩乐","乐不思蜀","身不由己","小家碧玉","文不加点","天马行空","人来人往","千方百计","天高地厚","万人空巷","争分夺秒","如火如荼","大智若愚","斗转星移","七情六欲","大禹治水","空穴来风","孟母三迁","绘声绘色","九五之尊","随心所欲","干将莫邪","相得益彰","借刀杀人","浪迹天涯","刚愎自用","镜花水月","黔驴技穷","肝胆相照","多多益善","叱咤风云","杞人忧天","作茧自缚","一飞冲天","殊途同归","风卷残云","因果报应","无可厚非","赶尽杀绝","天长地久","飞龙在天","桃之夭夭","南柯一梦","口是心非","江山如画","风华正茂","一帆风顺","一叶知秋","草船借箭","铁石心肠","望其项背","头晕目眩","大浪淘沙","纵横天下","有问必答","无为而治","釜底抽薪","吹毛求疵","好事多磨","空谷幽兰","悬梁刺股","白手起家","完璧归赵","忍俊不禁","沐猴而冠","白云苍狗","贼眉鼠眼","围魏救赵","烟雨蒙蒙","炙手可热","尸位素餐","出水芙蓉","礼仪之邦","一丘之貉","鹏程万里","叹为观止","韦编三绝","今生今世","草木皆兵","宁缺毋滥","回光返照","露水夫妻","讳莫如深","贻笑大方","紫气东来","万马奔腾","一诺千金","老马识途","五花大绑","捉襟见肘","瓜田李下","水漫金山","苦心孤诣","可见一斑","五湖四海","虚怀若谷","欲擒故纵","风声鹤唳","毛遂自荐","蛛丝马迹","中庸之道","迷途知返","自由自在","龙飞凤舞","树大根深","雨过天晴","乘风破浪","筚路蓝缕","朝三暮四","患得患失","君子好逑","鞭长莫及","竭泽而渔","飞黄腾达","囊萤映雪","飞蛾扑火","自怨自艾","风驰电掣","白马非马","退避三舍","三山五岳","称心如意","望梅止渴","茕茕孑立","振聋发聩","运筹帷幄","逃之夭夭","杯水车薪","有的放矢","矫枉过正","睚眦必报","姗姗来迟","一鸣惊人","孜孜不倦","一马平川","入木三分","沆瀣一气","天伦之乐","兄弟阋墙","藕断丝连","心猿意马","想入非非","盲人摸象","眉飞色舞","三教九流","高楼大厦","锲而不舍","过犹不及","狗尾续貂","斗酒学士","高山仰止","形影不离","小心翼翼","返璞归真","见贤思齐","按图索骥","枪林弹雨","桀骜不驯","遇人不淑","道貌岸然","名扬四海","虚与委蛇","门可罗雀","水落石出","不卑不亢","无法无天","拔苗助长","大快朵颐","因地制宜","单刀直入","时来运转","天方夜谭","一蹴而就","踌躇满志","战无不胜","插翅难飞","图穷匕见","鬼话连篇","亢龙有悔","望洋兴叹","爱屋及乌","惊鸿一瞥","风华绝代","名胜古迹","如履薄冰","持之以恒","潜移默化","昙花一现","巫山云雨","狡兔三窟","栉风沐雨","骇人听闻","断章取义","曲突徙薪","谢天谢地","脱颖而出","垂帘听政","一马当先","不耻下问","不以为然","春华秋实","欲盖弥彰","人琴俱亡","投鼠忌器","歧路亡羊","金风玉露","落花流水","春风化雨","心如刀割","锱铢必较","一叶障目","来历不明","名副其实","中流砥柱","绕梁三日","安步当车","放荡不羁","天衣无缝","自相矛盾","神机妙算","沧海一粟","冲锋陷阵","龙虎风云","言简意赅","九死一生","铁树开花","画龙点睛","风雨无阻","坐井观天","奇货可居","浮光掠影","牝鸡司晨","沽名钓誉","天作之合","甚嚣尘上","铩羽而归","劫后余生","泾渭分明","节哀顺变","有恃无恐","不绝如缕","马革裹尸","监守自盗","耳濡目染","金屋藏娇","不约而同","逐鹿中原","龙潭虎穴","江郎才尽","明日黄花","栩栩如生","人山人海","面面相觑","唇亡齿寒","相敬如宾","知法犯法","曾几何时","欢聚一堂","纷至沓来","李代桃僵","毛骨悚然","衣冠禽兽","有凤来仪","见微知著","旗鼓相当","无与伦比","摸金校尉","牛头马面","凤毛麟角","难得糊涂","衣香鬓影","马到功成","鸠占鹊巢","狭路相逢","春秋笔法","厉兵秣马","约法三章","豁然开朗","平步青云","步步为营","蝇营狗苟","心如止水","从善如流","殚精竭虑","十字路口","矢志不渝","九九归一","井底之蛙","居安思危","不一而足","周而复始","望穿秋水","秦晋之好","不落窠臼","司空见惯","怙恶不悛","百年好合","出神入化","身体力行","敬谢不敏","嗤之以鼻","天之骄子","贤妻良母","能说会道","进退维谷","甘之如饴","人心不古","颐指气使","墨守成规","左右逢源","回心转意","插科打诨","别来无恙","翩翩公子","穷兵黩武","舌战群儒","字字珠玑","义无反顾","举重若轻","钟灵毓秀","水滴石穿","防微杜渐","衣冠楚楚","卧冰求鲤","觥筹交错","络绎不绝","自强不息","秀色可餐","至理名言","分庭抗礼","萍水相逢","水性杨花","戛然而止","气喘吁吁","沉鱼落雁","望尘莫及","亦步亦趋","川流不息","千锤百炼","谈笑风生","高朋满座","丧心病狂","天下无敌","惊弓之鸟","耿耿于怀","心照不宣","荦荦大端","噤若寒蝉","上下其手","弄假成真","天网恢恢","夜郎自大","鞭辟入里","义薄云天","所向披靡","点石成金","回眸一笑","巴山夜雨","兢兢业业","克己复礼","风起云涌","不惑之年","义愤填膺","门当户对","声名狼藉","时过境迁","念念不忘","鞠躬尽瘁","不言而喻","人生如梦","琴棋书画","酸甜苦辣","走马观花","全力以赴","人面桃花","王侯将相","青山不老","朝令夕改","小时了了","玩世不恭","人情世故","聊胜于无","为虎作伥","休戚相关","三阳开泰","五子登科","熙熙攘攘","开源节流","绝处逢生","一石二鸟","鬼斧神工","青天白日","病入膏肓","横行霸道","对牛弹琴","诚惶诚恐","胡服骑射","虎视眈眈","十万火急","断袖之癖","得陇望蜀","分道扬镳","壮士断腕","自惭形秽","云淡风轻","巾帼英雄","眼花缭乱","不可一世","沁人心脾","侃侃而谈","闻过则喜","班门弄斧","舍我其谁","潸然泪下","肆无忌惮","心旷神怡","物竞天择","东山再起","丹凤朝阳","和光同尘","心力衰竭","事半功倍","阿鼻地狱","九关虎豹","劝百讽一","琳琅满目","一丝不苟","逝者如斯","同仇敌忾","朝秦暮楚","不亦乐乎","哭笑不得","重见天日","集腋成裘","风月无边","乐此不疲","咫尺天涯","宠辱不惊","安然无恙","一事无成","若即若离","本末倒置","秋风落叶","无价之宝","金刚怒目","以儆效尤","波涛汹涌","花团锦簇","海枯石烂","目无全牛","颠倒乾坤","当仁不让","车水马龙","天下为公","火中取栗","众矢之的","尽善尽美","欢天喜地","今非昔比","天府之国","不可名状","异想天开","粉墨登场","根深蒂固","钟鸣鼎食","历历在目","不法之徒","出人头地","以德报怨","梨花带雨","抛砖引玉","优柔寡断","开门见山","参差不齐","温文尔雅","甘心情愿","暗度陈仓","挑肥拣瘦","阿猫阿狗","心有余悸","数典忘祖","喜出望外","文过饰非","连锁反应","将心比心","无动于衷","鹤唳华亭","妙手空空","登峰造极","惊涛骇浪","自欺欺人","绿树成荫","岂有此理","万马齐喑","世态炎凉","冠冕堂皇","天罗地网","踽踽独行","兔死狐悲","众志成城","耳提面命","待字闺中","女扮男装","东张西望","马首是瞻","物极必反","蔚然成风","迫不及待","淋漓尽致","风尘仆仆","外强中干","求全责备","人浮于事","安居乐业","珠联璧合","一网打尽","任重道远","循循善诱","移花接木","不知所措","柳暗花明","白虹贯日","首鼠两端","前仆后继","醉生梦死","惺惺相惜","焚膏继晷","金童玉女","横扫千军","闭门造车","峰回路转","涸辙之鲋","锦上添花","亭亭玉立","干柴烈火","香草美人","新亭对泣","鹤立鸡群","一往无前","吴下阿蒙","草长莺飞","兔死狗烹","姹紫嫣红","因材施教","长生不老","爱莫能助","洗耳恭听","信手拈来","时不我待","举一反三","蠢蠢欲动","苟延残喘","正襟危坐","助人为乐","火树银花","齐大非偶","无影无踪","不胫而走","笨鸟先飞","精打细算","尾大不掉","词不达意","门庭若市","落英缤纷","戎马倥偬","上行下效","提纲挈领","蹉跎岁月"};
                String[] names1 = {"百度百科","搜狗百科","360百科","搜狗","百度",""};
                UiObject2 searchInput = waitForObj(By.clazz("android.widget.EditText"));
                verify("No search field", searchInput != null);
                int name = getRandom1(names.length);
                int name1 = getRandom1(names1.length);
                Log.i(TAG, "testWeiLiSearch: name1"+name1+"/"+names.length+name);
                searchInput.setText(names[name] + names1[name1]);
                sleepInt(3);
                UiObject2 searchth = phone.findObject(By.text("搜索"));
                searchth.click();
                sleepInt(10);
                verify("没有在悦头条界面", phone.getCurrentPackageName().equals("cn.weli.story"));
                phone.click((int) (phone.getDisplayWidth() * 0.5), (int) (phone.getDisplayHeight() * 0.5));
                sleepInt(5);
                for (int j = 0; j < 2; j++) {
                    sleepInt(3);
                    phone.swipe(600 + getRandom(200), 1340 + getRandom(100), 600 + getRandom(200), 800 + getRandom(100), getRandom(150));
                    sleepInt(3);
                    phone.swipe((int) (phone.getDisplayWidth() * 0.5), (int) (phone.getDisplayHeight() * 0.55), (int) (phone.getDisplayWidth() * 0.5), (int) (phone.getDisplayHeight() * 0.6), 30);
                    sleepInt(1);
                     reading=phone.findObject(By.text("知道了"));
                    if(reading!=null){
                        reading.click();
                        sleepInt(1);
                    }
                    verify("没有在悦头条界面", phone.getCurrentPackageName().equals("cn.weli.story"));
                    phone.swipe((int) (phone.getDisplayWidth() * 0.5), (int) (phone.getDisplayHeight() * 0.8), (int) (phone.getDisplayWidth() * 0.5), (int) (phone.getDisplayHeight() * 0.2), 10);
                    sleepInt(2);
                    UiObject2 progress = phone.findObject(By.pkg("cn.weli.story"));
                    verify("没有在悦头条界面", progress != null);
                }
                press_back(1);
                searchth = phone.findObject(By.text("搜索"));
                if(searchth==null){
                    press_back(1);
                }else break;

            }
        }
        UiObject2 fen100 = phone.findObject(By.text("+100"));
        if (fen100 != null) {
            fen100.click();
            sleepInt(1);
            UiObject2 share = phone.findObject(By.text("立即分享"));
            if(share!=null){share.click();
                sleepInt(4);
                UiObject2 lingqu = phone.findObject(By.text("领取奖励"));
                if (lingqu != null) {
                    lingqu.click();
                    sleepInt(1);
                } else {
                    press_back(1);
                    UiObject2 bubaoliu = phone.findObject(By.text("不保留"));
                    if (bubaoliu != null) bubaoliu.click();
                    sleepInt(1);
                }
            }else {
                UiObject2 lingqu = phone.findObject(By.text("领取奖励"));
                if (lingqu != null) {
                    lingqu.click();
                    sleepInt(1);}
            }
        }
    }


    @Test
    public void testKuaiMaSearch() throws UiObjectNotFoundException, RemoteException {
        // callShell("am start -n com.jifen.qukan/com.jifen.qukan.view.activity.MainActivity");
        press_home(1);
        UiObject2 newsTao = phone.findObject(By.text("快马小报"));
        newsTao.click();
        sleepInt(15);
        UiObject2 mine = waitForObj(By.text("我的"));
        if(mine==null){
            press_back(1);
        }
        mine = phone.findObject(By.text("我的"));
        mine.click();
        sleepInt(1);
        if(Build.DEVICE.equals("ja3gduosctc")){
            phone.click((int) (phone.getDisplayWidth() * 0.8), (int) (phone.getDisplayHeight() * 0.13));
        }else {
            phone.click((int) (phone.getDisplayWidth() * 0.9), (int) (phone.getDisplayHeight() * 0.215));
        }
        sleepInt(1);
        press_back(1);
       UiObject2 touTiao = phone.findObject(By.text("小报"));
        if (touTiao != null) {
            touTiao.click();
            sleepInt(1);
        }
        UiObject2 a=phone.findObject(By.res("com.kuaima.browser:id/iv_search_task"));
        a.click();
        sleepInt(1);
        for(int i=0;i<3;i++){
        UiObject2 lingqu = phone.findObject(By.desc("领取奖励"));
        if(lingqu!=null) {
            lingqu.click();
            sleepInt(2);
            press_back(1);
        }else break;
        }
        List<UiObject2> hongbao=phone.findObjects(By.desc("已经领取"));
        Log.i(TAG, "testKuaiMaSearch: honbao"+hongbao.size());
        if (hongbao.size()!=3) {
            for (int k = 0; k < 30; k++) {
                UiObject2 fetch = phone.findObject(By.text("领取金币"));
                if (fetch != null) {
                    fetch.click();
                    sleepInt(1);
                    press_back(1);
                }
                press_back(1);
                UiObject2 search = phone.findObject(By.res("com.kuaima.browser:id/tv_search"));
                verify("adf", search != null);
                search.click();
                sleepInt(1);
                String[] names = {"黄豆", "蔷薇", "五十步笑百步", "马齿笕", "曲麻菜", "蒲公英", "秋葵", "鱼腥草", "板蓝根", "金银花", "黄芪", "山莓", "甘草", "当归", "葛根", "芡实", "杜仲", "蛇莓", "人参", "茯苓", "白术", "高圆圆", "赵又廷", "贾静雯", "王珞丹", "张钧甯", "杨幂", "景甜", "刘涛", "刘亦菲", "刘恺威", "唐嫣", "刘诗诗", "罗晋", "霍建华", "赵丽颖", "郑爽", "张檬", "刘亦菲", "孙俪", "姚笛", "张学友", "迪丽热巴", "马苏", "陈好", "赵立新", "许晴", "徐露", "马伊琍", "李小璐", "章子怡", "霍思燕", "赵雅芝", "罗晋", "鹿晗", "范冰冰", "周润发", "刘欢", "尤浩然", "李连杰", "陈赫", "李香凝", "杜旭东", "李小龙", "洪金宝", "董骠", "王宝强", "周星驰", "梅艳芳", "向佐", "黄家驹", "于承惠", "邱淑贞", "梁朝伟", "张国荣", "张碧晨", "迈克尔·杰克逊", "黄贯中", "富九", "黄家强", "释小龙", "赵文卓", "陈小春", "黄渤", "周星驰", "洪金宝", "吴彦祖", "郑伊健", "刘德华", "吴秀波", "胡歌", "段奕宏", "靳东", "迪丽热巴", "张铁林", "陈道明", "林永健", "韩红", "李幼斌", "李雪健", "王志文", "张国立", "唐国强", "左小青", "孙红雷", "杨紫", "谢孟伟", "郑爽", "杨洋","白敬亭","吴磊","李沁","陈学冬","黄子韬","井柏然","乔任梁","angelababy","付辛博","张翰","宋祖儿","行尸走肉","金蝉脱壳","百里挑一","金玉满堂","背水一战","霸王别姬","天上人间","不吐不快","海阔天空","情非得已","满腹经纶","兵临城下","春暖花开","插翅难逃","黄道吉日","天下无双","偷天换日","两小无猜","卧虎藏龙","珠光宝气","簪缨世族","花花公子","绘声绘影","国色天香","相亲相爱","八仙过海","金玉良缘","掌上明珠","皆大欢喜","逍遥法外","生财有道","极乐世界","情不自禁","愚公移山","魑魅魍魉","龙生九子","精卫填海","海市蜃楼","高山流水","卧薪尝胆","壮志凌云","金枝玉叶","四海一家","穿针引线","无忧无虑","无地自容","三位一体","落叶归根","相见恨晚","惊天动地","滔滔不绝","相濡以沫","长生不死","原来如此","女娲补天","三皇五帝","万箭穿心","水木清华","窈窕淑女","破釜沉舟","天涯海角","牛郎织女","倾国倾城","飘飘欲仙","福星高照","妄自菲薄","永无止境","学富五车","饮食男女","英雄豪杰","国士无双","塞翁失马","万家灯火","石破天惊","精忠报国","养生之道","覆雨翻云","六道轮回","鹰击长空","日日夜夜","厚德载物","亡羊补牢","万里长城","黄金时代","出生入死","一路顺风","随遇而安","千军万马","郑人买履","棋逢对手","叶公好龙","后会无期","守株待兔","凤凰于飞","一生一世","花好月圆","世外桃源","韬光养晦","画蛇添足","青梅竹马","风花雪月","滥竽充数","没完没了","总而言之","欣欣向荣","时光荏苒","差强人意","好好先生","无懈可击","随波逐流","袖手旁观","群雄逐鹿","血战到底","唯我独尊","买椟还珠","龙马精神","一见钟情","喜闻乐见","负荆请罪","三人成虎","河东狮吼","程门立雪","金戈铁马","笑逐颜开","千钧一发","纸上谈兵","风和日丽","邯郸学步","大器晚成","庖丁解牛","甜言蜜语","雷霆万钧","浮生若梦","大开眼界","汗牛充栋","百鸟朝凤","以德服人","白驹过隙","难兄难弟","鬼哭神嚎","声色犬马","指鹿为马","龙争虎斗","雾里看花","男大当婚","未雨绸缪","南辕北辙","三从四德","一丝不挂","高屋建瓴","阳春白雪","杯弓蛇影","闻鸡起舞","四面楚歌","登堂入室","张灯结彩","而立之年","饮鸩止渴","杏雨梨云","龙凤呈祥","勇往直前","左道旁门","莫衷一是","马踏飞燕","掩耳盗铃","大江东去","凿壁偷光","色厉内荏","花容月貌","越俎代庖","鳞次栉比","美轮美奂","缘木求鱼","再接再厉","马到成功","红颜知己","赤子之心","迫在眉睫","风流韵事","相形见绌","诸子百家","鬼迷心窍","星火燎原","画地为牢","岁寒三友","花花世界","纸醉金迷","狐假虎威","纵横捭阖","沧海桑田","不求甚解","暴殄天物","吃喝玩乐","乐不思蜀","身不由己","小家碧玉","文不加点","天马行空","人来人往","千方百计","天高地厚","万人空巷","争分夺秒","如火如荼","大智若愚","斗转星移","七情六欲","大禹治水","空穴来风","孟母三迁","绘声绘色","九五之尊","随心所欲","干将莫邪","相得益彰","借刀杀人","浪迹天涯","刚愎自用","镜花水月","黔驴技穷","肝胆相照","多多益善","叱咤风云","杞人忧天","作茧自缚","一飞冲天","殊途同归","风卷残云","因果报应","无可厚非","赶尽杀绝","天长地久","飞龙在天","桃之夭夭","南柯一梦","口是心非","江山如画","风华正茂","一帆风顺","一叶知秋","草船借箭","铁石心肠","望其项背","头晕目眩","大浪淘沙","纵横天下","有问必答","无为而治","釜底抽薪","吹毛求疵","好事多磨","空谷幽兰","悬梁刺股","白手起家","完璧归赵","忍俊不禁","沐猴而冠","白云苍狗","贼眉鼠眼","围魏救赵","烟雨蒙蒙","炙手可热","尸位素餐","出水芙蓉","礼仪之邦","一丘之貉","鹏程万里","叹为观止","韦编三绝","今生今世","草木皆兵","宁缺毋滥","回光返照","露水夫妻","讳莫如深","贻笑大方","紫气东来","万马奔腾","一诺千金","老马识途","五花大绑","捉襟见肘","瓜田李下","水漫金山","苦心孤诣","可见一斑","五湖四海","虚怀若谷","欲擒故纵","风声鹤唳","毛遂自荐","蛛丝马迹","中庸之道","迷途知返","自由自在","龙飞凤舞","树大根深","雨过天晴","乘风破浪","筚路蓝缕","朝三暮四","患得患失","君子好逑","鞭长莫及","竭泽而渔","飞黄腾达","囊萤映雪","飞蛾扑火","自怨自艾","风驰电掣","白马非马","退避三舍","三山五岳","称心如意","望梅止渴","茕茕孑立","振聋发聩","运筹帷幄","逃之夭夭","杯水车薪","有的放矢","矫枉过正","睚眦必报","姗姗来迟","一鸣惊人","孜孜不倦","一马平川","入木三分","沆瀣一气","天伦之乐","兄弟阋墙","藕断丝连","心猿意马","想入非非","盲人摸象","眉飞色舞","三教九流","高楼大厦","锲而不舍","过犹不及","狗尾续貂","斗酒学士","高山仰止","形影不离","小心翼翼","返璞归真","见贤思齐","按图索骥","枪林弹雨","桀骜不驯","遇人不淑","道貌岸然","名扬四海","虚与委蛇","门可罗雀","水落石出","不卑不亢","无法无天","拔苗助长","大快朵颐","因地制宜","单刀直入","时来运转","天方夜谭","一蹴而就","踌躇满志","战无不胜","插翅难飞","图穷匕见","鬼话连篇","亢龙有悔","望洋兴叹","爱屋及乌","惊鸿一瞥","风华绝代","名胜古迹","如履薄冰","持之以恒","潜移默化","昙花一现","巫山云雨","狡兔三窟","栉风沐雨","骇人听闻","断章取义","曲突徙薪","谢天谢地","脱颖而出","垂帘听政","一马当先","不耻下问","不以为然","春华秋实","欲盖弥彰","人琴俱亡","投鼠忌器","歧路亡羊","金风玉露","落花流水","春风化雨","心如刀割","锱铢必较","一叶障目","来历不明","名副其实","中流砥柱","绕梁三日","安步当车","放荡不羁","天衣无缝","自相矛盾","神机妙算","沧海一粟","冲锋陷阵","龙虎风云","言简意赅","九死一生","铁树开花","画龙点睛","风雨无阻","坐井观天","奇货可居","浮光掠影","牝鸡司晨","沽名钓誉","天作之合","甚嚣尘上","铩羽而归","劫后余生","泾渭分明","节哀顺变","有恃无恐","不绝如缕","马革裹尸","监守自盗","耳濡目染","金屋藏娇","不约而同","逐鹿中原","龙潭虎穴","江郎才尽","明日黄花","栩栩如生","人山人海","面面相觑","唇亡齿寒","相敬如宾","知法犯法","曾几何时","欢聚一堂","纷至沓来","李代桃僵","毛骨悚然","衣冠禽兽","有凤来仪","见微知著","旗鼓相当","无与伦比","摸金校尉","牛头马面","凤毛麟角","难得糊涂","衣香鬓影","马到功成","鸠占鹊巢","狭路相逢","春秋笔法","厉兵秣马","约法三章","豁然开朗","平步青云","步步为营","蝇营狗苟","心如止水","从善如流","殚精竭虑","十字路口","矢志不渝","九九归一","井底之蛙","居安思危","不一而足","周而复始","望穿秋水","秦晋之好","不落窠臼","司空见惯","怙恶不悛","百年好合","出神入化","身体力行","敬谢不敏","嗤之以鼻","天之骄子","贤妻良母","能说会道","进退维谷","甘之如饴","人心不古","颐指气使","墨守成规","左右逢源","回心转意","插科打诨","别来无恙","翩翩公子","穷兵黩武","舌战群儒","字字珠玑","义无反顾","举重若轻","钟灵毓秀","水滴石穿","防微杜渐","衣冠楚楚","卧冰求鲤","觥筹交错","络绎不绝","自强不息","秀色可餐","至理名言","分庭抗礼","萍水相逢","水性杨花","戛然而止","气喘吁吁","沉鱼落雁","望尘莫及","亦步亦趋","川流不息","千锤百炼","谈笑风生","高朋满座","丧心病狂","天下无敌","惊弓之鸟","耿耿于怀","心照不宣","荦荦大端","噤若寒蝉","上下其手","弄假成真","天网恢恢","夜郎自大","鞭辟入里","义薄云天","所向披靡","点石成金","回眸一笑","巴山夜雨","兢兢业业","克己复礼","风起云涌","不惑之年","义愤填膺","门当户对","声名狼藉","时过境迁","念念不忘","鞠躬尽瘁","不言而喻","人生如梦","琴棋书画","酸甜苦辣","走马观花","全力以赴","人面桃花","王侯将相","青山不老","朝令夕改","小时了了","玩世不恭","人情世故","聊胜于无","为虎作伥","休戚相关","三阳开泰","五子登科","熙熙攘攘","开源节流","绝处逢生","一石二鸟","鬼斧神工","青天白日","病入膏肓","横行霸道","对牛弹琴","诚惶诚恐","胡服骑射","虎视眈眈","十万火急","断袖之癖","得陇望蜀","分道扬镳","壮士断腕","自惭形秽","云淡风轻","巾帼英雄","眼花缭乱","不可一世","沁人心脾","侃侃而谈","闻过则喜","班门弄斧","舍我其谁","潸然泪下","肆无忌惮","心旷神怡","物竞天择","东山再起","丹凤朝阳","和光同尘","心力衰竭","事半功倍","阿鼻地狱","九关虎豹","劝百讽一","琳琅满目","一丝不苟","逝者如斯","同仇敌忾","朝秦暮楚","不亦乐乎","哭笑不得","重见天日","集腋成裘","风月无边","乐此不疲","咫尺天涯","宠辱不惊","安然无恙","一事无成","若即若离","本末倒置","秋风落叶","无价之宝","金刚怒目","以儆效尤","波涛汹涌","花团锦簇","海枯石烂","目无全牛","颠倒乾坤","当仁不让","车水马龙","天下为公","火中取栗","众矢之的","尽善尽美","欢天喜地","今非昔比","天府之国","不可名状","异想天开","粉墨登场","根深蒂固","钟鸣鼎食","历历在目","不法之徒","出人头地","以德报怨","梨花带雨","抛砖引玉","优柔寡断","开门见山","参差不齐","温文尔雅","甘心情愿","暗度陈仓","挑肥拣瘦","阿猫阿狗","心有余悸","数典忘祖","喜出望外","文过饰非","连锁反应","将心比心","无动于衷","鹤唳华亭","妙手空空","登峰造极","惊涛骇浪","自欺欺人","绿树成荫","岂有此理","万马齐喑","世态炎凉","冠冕堂皇","天罗地网","踽踽独行","兔死狐悲","众志成城","耳提面命","待字闺中","女扮男装","东张西望","马首是瞻","物极必反","蔚然成风","迫不及待","淋漓尽致","风尘仆仆","外强中干","求全责备","人浮于事","安居乐业","珠联璧合","一网打尽","任重道远","循循善诱","移花接木","不知所措","柳暗花明","白虹贯日","首鼠两端","前仆后继","醉生梦死","惺惺相惜","焚膏继晷","金童玉女","横扫千军","闭门造车","峰回路转","涸辙之鲋","锦上添花","亭亭玉立","干柴烈火","香草美人","新亭对泣","鹤立鸡群","一往无前","吴下阿蒙","草长莺飞","兔死狗烹","姹紫嫣红","因材施教","长生不老","爱莫能助","洗耳恭听","信手拈来","时不我待","举一反三","蠢蠢欲动","苟延残喘","正襟危坐","助人为乐","火树银花","齐大非偶","无影无踪","不胫而走","笨鸟先飞","精打细算","尾大不掉","词不达意","门庭若市","落英缤纷","戎马倥偬","上行下效","提纲挈领","蹉跎岁月"};
                String[] names1 = {"百度百科","搜狗百科","360百科","搜狗","百度",""};
                UiObject2 searchInput = waitForObj(By.clazz("android.widget.EditText"));
                verify("No search field", searchInput != null);
                int name = getRandom1(names.length);
                int name1 = getRandom1(names1.length);
/*                if(Build.DEVICE.equals("ja3gduosctc")) {
                    String a="input text"+names[name] + names1[name1];
                    Log.i(TAG, "testKuaiMaSearch: ");
                    callShell("input text adb");
                    callShell(a);
                    callShell("adf");

                }*/

                searchInput = waitForObj(By.clazz("android.widget.EditText"));
                searchInput.setText(names[name] + names1[name1]);
                sleepInt(3);
                UiObject2 searchth = phone.findObject(By.text("搜索"));
                searchth.click();
                sleepInt(10);
                verify("没有在悦头条界面", phone.getCurrentPackageName().equals("com.kuaima.browser"));
                phone.click((int) (phone.getDisplayWidth() * 0.5), (int) (phone.getDisplayHeight() * 0.4));
                phone.click((int) (phone.getDisplayWidth() * 0.5), (int) (phone.getDisplayHeight() * 0.4));
                sleepInt(5);
                for (int j = 0; j < 3; j++) {
                    sleepInt(3);
                    phone.swipe(600 + getRandom(200), 1340 + getRandom(100), 600 + getRandom(200), 800 + getRandom(100), getRandom(150));
                    sleepInt(3);
                    phone.swipe((int) (phone.getDisplayWidth() * 0.5), (int) (phone.getDisplayHeight() * 0.55), (int) (phone.getDisplayWidth() * 0.5), (int) (phone.getDisplayHeight() * 0.6), 30);
                    sleepInt(1);
                    verify("没有在悦头条界面", phone.getCurrentPackageName().equals("com.kuaima.browser"));
                    phone.swipe((int) (phone.getDisplayWidth() * 0.5), (int) (phone.getDisplayHeight() * 0.8), (int) (phone.getDisplayWidth() * 0.5), (int) (phone.getDisplayHeight() * 0.2), 10);
                    sleepInt(2);
                    UiObject2 baoxiang = phone.findObject(By.text("开宝箱"));
                    if (baoxiang != null) {
                        baoxiang.click();
                        sleepInt(3);
                        phone.click((int) (phone.getDisplayWidth() * 0.5), (int) (phone.getDisplayHeight() * 0.35));
                        sleepInt(3);
                        phone.click((int) (phone.getDisplayWidth() * 0.5), (int) (phone.getDisplayHeight() * 0.35));
                        sleepInt(3);
                        press_back(2);
                        break;
                    }
                    UiObject2 progress = phone.findObject(By.pkg("com.kuaima.browser"));
                    verify("没有在悦头条界面", progress != null);
                }
                if(k%5==0) {
                    UiObject2 searchR = phone.findObject(By.res("com.kuaima.browser:id/iv_search_task"));
                    searchR.click();
                    sleepInt(1);
                    phone.swipe((int) (phone.getDisplayWidth() * 0.5), (int) (phone.getDisplayHeight() * 0.6), (int) (phone.getDisplayWidth() * 0.5), (int) (phone.getDisplayHeight() * 0.3), 30);
                    sleepInt(3);
                    for(int i=0;i<3;i++){
                        UiObject2 lingqu = phone.findObject(By.desc("领取奖励"));
                        if(lingqu!=null) {
                            lingqu.click();
                            sleepInt(3);
                            press_back(1);
                        }else break;
                    }
                    List<UiObject2> done=phone.findObjects(By.desc("已经领取"));
                    if (done.size()== 3) break;
                }
                press_back(1);
            }
            phone.swipe((int) (phone.getDisplayWidth() * 0.5), (int) (phone.getDisplayHeight() * 0.85), (int) (phone.getDisplayWidth() * 0.5), (int) (phone.getDisplayHeight() * 0.15), 30);
            sleepInt(1);
        }
    }

    @Test
    public void testTaoNews() throws UiObjectNotFoundException, RemoteException {
        press_home(1);
        //callShell("am start -n com.coohua.xinwenzhuan/.controller.MainActivity");
        UiObject2 newsTao = phone.findObject(By.text("淘新闻"));
        newsTao.click();
        sleepInt(10);
        String[] names={"推荐","情感","社会","情感","娱乐","军事","历史","健康"};
        for(int k=0;k<names.length;k++) {
            UiObject2 fresh = phone.findObject(By.text("新闻"));
            if (fresh != null) {
                fresh.click();
                sleepInt(3);
            }
            for(int i=0;i<4;i++){
                UiObject2 bar = phone.findObject(By.text(names[k]));
                if(bar==null){
                    phone.swipe((int)(phone.getDisplayWidth()*0.13),(int)(phone.getDisplayHeight()*0.7),(int)(phone.getDisplayWidth()*0.13),(int)(phone.getDisplayHeight()*0.5), 30);
                    sleepInt(1);
                }else break;
            }
            UiObject2 bar = phone.findObject(By.text(names[k]+"搜狗百科"));
            verify("beijing not exists", bar != null);
            bar.click();
            sleepInt(3);
            UiObject2 book=phone.findObject(By.res("com.coohua.xinwenzhuan:id/home_feed_recomend_tip_close"));
            if(book!=null)book.click();
            sleepInt(1);
            phone.swipe(600 + getRandom(200), 600 + getRandom(100), 600 + getRandom(200), 1300 + getRandom(100), 15);
            sleepInt(2);
            for (int i = 0; i < 5; i++) {
                phone.click((int)(phone.getDisplayWidth()*0.5),(int)(phone.getDisplayHeight()*0.3));
                sleepInt(3);
                if (!phone.getCurrentPackageName().equals("com.coohua.xinwenzhuan")) {
                    press_back(2);
                }
                for (int j = 0; j < 6; j++) {
                    UiObject2 count=phone.findObject(By.res("com.coohua.xinwenzhuan:id/news_detail_credit_count"));
                    if(count!=null) {
                        String numbers = count.getText();
                        if (parseInt(numbers) == 0) {
                            Assert.fail("阅读奖励已暂停");
                        }
                    }
                    phone.swipe(600 + getRandom(200), 1300 + getRandom(100), 600 + getRandom(200), 600 + getRandom(100), getRandom(150));
                    sleepInt(1);
                    UiObject2 readAll = phone.findObject(By.res("com.coohua.xinwenzhuan:id/news_detail_look_more"));
                    if (readAll != null) {
                        readAll.click();
                        sleepInt(1);
                        phone.swipe((int)(phone.getDisplayWidth()*0.5),(int)(phone.getDisplayHeight()*0.8),(int)(phone.getDisplayWidth()*0.5),(int)(phone.getDisplayHeight()*0.3), 10);
                        sleepInt(3);
                        phone.swipe((int)(phone.getDisplayWidth()*0.5),(int)(phone.getDisplayHeight()*0.8),(int)(phone.getDisplayWidth()*0.5),(int)(phone.getDisplayHeight()*0.3), 5);
                        sleepInt(2);
                        phone.swipe((int)(phone.getDisplayWidth()*0.5),(int)(phone.getDisplayHeight()*0.8),(int)(phone.getDisplayWidth()*0.5),(int)(phone.getDisplayHeight()*0.3), 2);
                        sleepInt(2);
                        break;
                    }
                    UiObject2 stop = phone.findObject(By.text("相关推荐"));
                    if (stop != null && j > 5||!phone.getCurrentPackageName().equals("com.coohua.xinwenzhuan")) {
                        break;
                    }
                }
                press_back(1);
                sleepInt(1);
                phone.swipe((int)(phone.getDisplayWidth()*0.5),(int)(phone.getDisplayHeight()*0.7),(int)(phone.getDisplayWidth()*0.5),(int)(phone.getDisplayHeight()*0.4), 30);
                sleepInt(1);
            }
        }
    }
    @Test
    public void testTaoNews2() throws UiObjectNotFoundException, RemoteException {
        press_home(1);
        //callShell("am start -n com.coohua.xinwenzhuan/.controller.MainActivity");
        UiObject2 newsTao = phone.findObject(By.text("淘新闻"));
        if (newsTao != null) {
            newsTao.click();
        }
        sleepInt(10);
            for (int i = 0; i < 10; i++) {
                UiObject2 beijing = waitForObj(By.res("com.coohua.xinwenzhuan:id/tab_search_gift_container"));
                verify("beijing not exists", beijing != null);
                beijing.click();
                sleepInt(3);
                double x=((double)(360+720*getRandom2(1)))/phone.getDisplayWidth();
                double y=((double)(950+getRandom2(8)*123))/phone.getDisplayHeight();
                Log.i(TAG, "testTaoNews2: "+getRandom2(1));
                Log.i(TAG, "testTaoNews2: "+getRandom2(8));
                Log.i(TAG, "testTaoNews2: "+x);
                Log.i(TAG, "testTaoNews2: "+y);
                Log.i(TAG, "testTaoNews2: "+(int)(phone.getDisplayWidth()*x));
                Log.i(TAG, "testTaoNews2: "+(int)(phone.getDisplayHeight()*y));
                phone.click((int)(phone.getDisplayWidth()*x),(int)(phone.getDisplayHeight()*y));
                sleepInt(5);
                phone.click((int)(phone.getDisplayWidth()*0.5),(int)(phone.getDisplayHeight()*0.5));
                sleepInt(10);
                for (int j = 0; j < 3; j++) {
                    UiObject2 count=phone.findObject(By.res("com.coohua.xinwenzhuan:id/news_detail_credit_count"));
                    if(count!=null) {
                        String numbers = count.getText();
                        if (parseInt(numbers) == 0) {
                            Assert.fail("阅读奖励已暂停");
                        }
                    }
                    phone.swipe(600 + getRandom(200), 1300 + getRandom(100), 600 + getRandom(200), 600 + getRandom(100), getRandom(150));
                    sleepInt(4);
                    // sleepInt(10);
                    UiObject2 readAll = phone.findObject(By.res("com.coohua.xinwenzhuan:id/news_detail_look_more"));
                    if (readAll != null) {
                        readAll.click();
                        sleepInt(1);
                    }

                    phone.swipe(600 + getRandom(200), 1200 + getRandom(100), 600 + getRandom(200), 700 + getRandom(100), getRandom(150));
                    //  sleepInt(10);
                    readAll = phone.findObject(By.res("com.coohua.xinwenzhuan:id/news_detail_look_more"));
                    if (readAll != null) {
                        readAll.click();
                        sleepInt(1);
                    }
                    verify("不在应用中",phone.getCurrentPackageName().equals("com.coohua.xinwenzhuan"));
                    UiObject2 stop = phone.findObject(By.text("相关推荐"));
                    if (stop != null && j > 5) {
                        phone.swipe(600 + getRandom(200), 800, 600 + getRandom(200), 850, getRandom(150));
                        phone.swipe(600 + getRandom(200), 800, 600 + getRandom(200), 830, getRandom(150));
                        break;
                    }
                }
                press_back(1);
            }
        }


    @Test
    public void testStatusBar() throws UiObjectNotFoundException {
        phone.swipe(500,25,500,1600,20);
        sleepInt(2);
        List<UiObject2> news=phone.findObjects(By.text(Pattern.compile(".*[阅读.*金币].*")).pkg(Pattern.compile("com.jifen.qukan")));
/*        List<UiObject2> newsin=phone.findObjects(By.pkg(Pattern.compile("com.ss.android.article.interesting")));
        List<UiObject2> newsian=phone.findObjects(By.text(Pattern.compile("开宝箱")));
        news.addAll(newsin);
        news.addAll(newsian);*/
        int n=news.size();
        for (int i=0;i<n;i++){
            sleepInt(2);
            try {
                UiObject2 newsi=news.get(i);
                verify("news not exits",newsi!=null);
                newsi.click();
                sleepInt(5);
                for (int j = 0; j < 3; j++) {
                    for(int k=0;k<3;k++){
                        if(phone.getCurrentPackageName().equals(Pattern.compile("com.jifen.qukan|com.ss.android.article.interesting"))){
                            sleepInt(15);
                            phone.click((int)(phone.getDisplayWidth()*0.9),(int)(phone.getDisplayHeight()*0.85));
                            sleepInt(1);
                        }else break;
                    }
                }
                press_back(1);
                sleepInt(2);
                phone.swipe(500,25,500,1600,20);
            }catch (StaleObjectException e){
                phone.waitForWindowUpdate("com.jifen.qukan",10000);
                continue;
            }

        }
    }

    @Test
    public void testSigninHui() throws UiObjectNotFoundException {
        phone.pressHome();
        sleepInt(1);
        UiObject2 huitoutiao=phone.findObject(By.text("惠头条"));
        huitoutiao.click();
        sleepInt(10);
        for(int i=0;i<3;i++) {
            if (!phone.getCurrentPackageName().equals("com.cashtoutiao")) {
                phone.pressBack();
                sleepInt(1);
            }else break;
        }
        UiObject2 task=waitForObj(By.text("任务中心"));
        verify("mine not exist",task!=null);
        task.click();
        sleepInt(5);
        phone.swipe((int)(phone.getDisplayWidth()*0.5),(int)(phone.getDisplayHeight()*0.3),(int)(phone.getDisplayWidth()*0.5),(int)(phone.getDisplayHeight()*0.6),30);
        sleepInt(3);
        UiObject2 sign=waitForObj(By.text("今日签到"));
       if(sign!=null)sign.click();
        sleepInt(1);
        phone.click((int)(phone.getDisplayWidth()*0.5),(int)(phone.getDisplayHeight()*0.19));
        sleepInt(3);
/*        UiObject2 mine=phone.findObject(By.text("我的"));
        verify("mine not exist",mine!=null);
        mine.click();
        sleepInt(5);
        phone.click((int)(phone.getDisplayWidth()*0.17),(int)(phone.getDisplayHeight()*0.45));
        sleepInt(8);
        phone.click((int)(phone.getDisplayWidth()*0.86),(int)(phone.getDisplayHeight()*0.95));
        sleepInt(6);
        phone.click((int)(phone.getDisplayWidth()*0.86),(int)(phone.getDisplayHeight()*0.95));
        sleepInt(6);
        phone.click((int)(phone.getDisplayWidth()*0.86),(int)(phone.getDisplayHeight()*0.95));
        sleepInt(6);
        phone.click((int)(phone.getDisplayWidth()*0.86),(int)(phone.getDisplayHeight()*0.95));
        sleepInt(6);
        phone.click((int)(phone.getDisplayWidth()*0.4),(int)(phone.getDisplayHeight()*0.16));
        sleepInt(8);
        phone.click((int)(phone.getDisplayWidth()*0.86),(int)(phone.getDisplayHeight()*0.95));
        sleepInt(6);
        phone.click((int)(phone.getDisplayWidth()*0.86),(int)(phone.getDisplayHeight()*0.95));
        sleepInt(6);
        phone.click((int)(phone.getDisplayWidth()*0.86),(int)(phone.getDisplayHeight()*0.95));
        sleepInt(6);
        phone.click((int)(phone.getDisplayWidth()*0.86),(int)(phone.getDisplayHeight()*0.95));
        sleepInt(6);
        press_back(1);*/
//        task=waitForObj(By.text("任务中心"));
//        verify("mine not exist",task!=null);
//        task.click();
//        sleepInt(5);
/*        phone.swipe((int)(phone.getDisplayWidth()*0.5),(int)(phone.getDisplayHeight()*0.3),(int)(phone.getDisplayWidth()*0.5),(int)(phone.getDisplayHeight()*0.6),30);
        sleepInt(3);
        phone.click((int)(phone.getDisplayWidth()*0.5),(int)(phone.getDisplayHeight()*0.19));
        sleepInt(5);*/
        phone.swipe((int)(phone.getDisplayWidth()*0.5),(int)(phone.getDisplayHeight()*0.67),(int)(phone.getDisplayWidth()*0.5),(int)(phone.getDisplayHeight()*0.3),30);
        sleepInt(2);
        phone.swipe((int)(phone.getDisplayWidth()*0.5),(int)(phone.getDisplayHeight()*0.67),(int)(phone.getDisplayWidth()*0.5),(int)(phone.getDisplayHeight()*0.3),30);
        sleepInt(2);
        phone.swipe((int)(phone.getDisplayWidth()*0.5),(int)(phone.getDisplayHeight()*0.67),(int)(phone.getDisplayWidth()*0.5),(int)(phone.getDisplayHeight()*0.3),30);
        sleepInt(2);
        UiObject2 accept=waitForObj(By.text("立即领取"));
        verify("mine not exist",accept!=null);
        accept.click();
        sleepInt(5);
        accept=waitForObj(By.text("立即领取"));
        verify("mine not exist",accept!=null);
        accept.click();
        sleepInt(5);
    }
    @Test
    public void testSearchHui() throws UiObjectNotFoundException {
        press_back(2);
        phone.pressHome();
        sleepInt(1);
        UiObject2 toutiao = phone.findObject(By.text("头条"));
        if (toutiao == null) {
            UiObject2 huitoutiao = phone.findObject(By.text("惠头条"));
            huitoutiao.click();
        }
        sleepInt(10);
        for (int i = 0; i < 3; i++) {
            if (!phone.getCurrentPackageName().equals("com.cashtoutiao")) {
                phone.pressBack();
                sleepInt(1);
            } else break;
        }
        UiObject2 task = waitForObj(By.text("任务中心"));
        verify("mine not exist", task != null);
        task.click();
        sleepInt(5);
        phone.swipe((int) (phone.getDisplayWidth() * 0.5), (int) (phone.getDisplayHeight() * 0.9), (int) (phone.getDisplayWidth() * 0.5), (int) (phone.getDisplayHeight() * 0.2), 25);
        sleepInt(3);
        phone.click((int) (phone.getDisplayWidth() * 0.5), (int) (phone.getDisplayHeight() * 0.7));
        sleepInt(1);
        UiObject2 search = waitForObj(By.text("立即搜索"));
        verify("mine not exist", search != null);
        search.click();
        sleepInt(3);
        verify("没有在惠头条界面",phone.getCurrentPackageName().equals("com.cashtoutiao"));
        for(int i=0;i<3;i++){
            verify("没有在惠头条界面",phone.getCurrentPackageName().equals("com.cashtoutiao"));
            phone.click((int) (phone.getDisplayWidth() * 0.93), (int) (phone.getDisplayHeight() * 0.36));
            sleepInt(3);
            verify("没有在惠头条界面",phone.getCurrentPackageName().equals("com.cashtoutiao"));
            phone.click((int) (phone.getDisplayWidth() * 0.35), (int) (phone.getDisplayHeight() * 0.49));
            sleepInt(8);
            verify("没有在惠头条界面",phone.getCurrentPackageName().equals("com.cashtoutiao"));
            phone.click((int) (phone.getDisplayWidth() * 0.5), (int) (phone.getDisplayHeight() * 0.6));
            sleepInt(60);
            press_back(2);
        }
    }
    @Test
    public void testExchangeYue() throws UiObjectNotFoundException {
        press_back(2);
        phone.pressHome();
        sleepInt(1);
        UiObject2 huitoutiao=phone.findObject(By.text("悦头条"));
        huitoutiao.click();
        sleepInt(15);
        for(int i=0;i<3;i++) {
            if (!phone.getCurrentPackageName().equals("com.expflow.reading")) {
                phone.pressBack();
                sleepInt(1);
            }else break;
        }
        verify("adf",phone.getCurrentPackageName().equals("com.expflow.reading"));
        UiObject2 mine=waitForObj(By.text("我的"));
        verify("mine not exist",mine!=null);
        mine.click();
        sleepInt(5);
        UiObject2 gde=phone.findObject(By.res("com.expflow.reading:id/txt_left_gold"));
        int gdenum=parseInt(gde.getText());
        if(gdenum>=20000) {
            if(!Build.DEVICE.equals("pisces")) {
                for (int i = 0; i < 2; i++) {
                    phone.click((int) (phone.getDisplayWidth() * 0.16), (int) (phone.getDisplayHeight() * 0.266));
                    sleepInt(8);
                    String[] a={"微信","支付宝"};
                    for(int j=0;j<a.length;j++) {
                        UiObject2 info = phone.findObject(By.text(a[j]));
                        info.click();
                        sleepInt(5);
                        verify("adf", phone.getCurrentPackageName().equals("com.expflow.reading"));
                        UiObject2 yiyuan = phone.findObject(By.text("1元"));
                        yiyuan.click();
                        sleepInt(2);
                        phone.click((int) (phone.getDisplayWidth() * 0.8), (int) (phone.getDisplayHeight() * 0.95));
                        sleepInt(3);
                        phone.click((int) (phone.getDisplayWidth() * 0.8), (int) (phone.getDisplayHeight() * 0.95));
                        sleepInt(2);
                    }
                    mine = phone.findObject(By.text("我的"));
                    if (mine == null) break;
                }
/*                for (int i = 0; i < 3; i++) {
                    phone.click((int) (phone.getDisplayWidth() * 0.16), (int) (phone.getDisplayHeight() * 0.266));
                    sleepInt(8);
                    UiObject2 num = phone.findObject(By.res("com.expflow.reading:id/tv2"));
                    UiObject2 info = phone.findObject(By.text(Pattern.compile("请填写个人信息")));
                    if (num == null || info != null) {
                        phone.click((int) (phone.getDisplayWidth() * 0.16), (int) (phone.getDisplayHeight() * 0.5));
                        sleepInt(8);
                    }
                    verify("adf", phone.getCurrentPackageName().equals("com.expflow.reading"));
                    UiObject2 yiyuan = phone.findObject(By.text("1元"));
*//*                int x=yiyuan.getVisibleBounds().centerX();
                int y=yiyuan.getVisibleBounds().centerY();*//*
                    yiyuan.click();
                    sleepInt(3);
*//*                verify("adf", phone.getCurrentPackageName().equals("com.expflow.reading"));
                phone.swipe((int) (phone.getDisplayWidth() * 0.5), (int) (phone.getDisplayHeight() * 0.6), (int) (phone.getDisplayWidth() * 0.5), (int) (phone.getDisplayHeight() * 0.3), 30);
                sleepInt(3)*//*
                    ;
                    phone.click((int) (phone.getDisplayWidth() * 0.8), (int) (phone.getDisplayHeight() * 0.95));
                    sleepInt(8);
                    phone.click((int) (phone.getDisplayWidth() * 0.8), (int) (phone.getDisplayHeight() * 0.95));
                    sleepInt(2);
                    mine = phone.findObject(By.text("我的"));
                    if (mine == null) break;
                }*/
            }else {
                for (int i = 0; i < 3; i++) {
                    phone.click((int) (phone.getDisplayWidth() * 0.16), (int) (phone.getDisplayHeight() * 0.266));
                    sleepInt(8);
                    UiObject2 num = phone.findObject(By.res("com.expflow.reading:id/tv2"));
                    UiObject2 info = phone.findObject(By.text(Pattern.compile("请填写个人信息")));
                    if (num == null || info != null) {
                        phone.click((int) (phone.getDisplayWidth() * 0.16), (int) (phone.getDisplayHeight() * 0.5));
                        sleepInt(8);
                    }
                    verify("adf", phone.getCurrentPackageName().equals("com.expflow.reading"));
                    phone.click((int) (phone.getDisplayWidth() * 0.5), (int) (phone.getDisplayHeight() * 0.35));
                verify("adf", phone.getCurrentPackageName().equals("com.expflow.reading"));
                phone.swipe((int) (phone.getDisplayWidth() * 0.5), (int) (phone.getDisplayHeight() * 0.6), (int) (phone.getDisplayWidth() * 0.5), (int) (phone.getDisplayHeight() * 0.3), 30);
                sleepInt(3);
                    phone.click((int) (phone.getDisplayWidth() * 0.8), (int) (phone.getDisplayHeight() * 0.95));
                    sleepInt(8);
                    mine = phone.findObject(By.text("我的"));
                    if (mine == null) break;
                }
            }
        }
    }

    @Test
    public void testExchangeHui() throws UiObjectNotFoundException {
        press_back(2);
        phone.pressHome();
        sleepInt(1);
        UiObject2 huitoutiao=phone.findObject(By.text("惠头条"));
        huitoutiao.click();
        sleepInt(15);
        for(int i=0;i<3;i++) {
            if (!phone.getCurrentPackageName().equals("com.cashtoutiao")) {
                phone.pressBack();
                sleepInt(1);
            }else break;
        }
        verify("adf",phone.getCurrentPackageName().equals("com.cashtoutiao"));
        UiObject2 mine=waitForObj(By.text("我的"));
        verify("mine not exist",mine!=null);
        mine.click();
        sleepInt(5);
        UiObject2 cash=phone.findObject(By.res("com.cashtoutiao:id/setting_surplus_gold"));
        int cashes=parseInt(cash.getText().replace(",",""));
        Log.i(TAG, "testExchangeHui: "+cashes);
        if(cashes>=10000) {
            phone.click((int) (phone.getDisplayWidth() * 0.16), (int) (phone.getDisplayHeight() * 0.34)); //点击兑换提现
            sleepInt(5);
            UiObject2 num = phone.findObject(By.res("com.cashtoutiao:id/text_cash_code"));
            if (num == null) {
                if (Build.DEVICE.equals("ja3gduosctc")) {
                    phone.click((int) (phone.getDisplayWidth() * 0.25), (int) (phone.getDisplayHeight() * 0.145));
                } else {
                    phone.click((int) (phone.getDisplayWidth() * 0.25), (int) (phone.getDisplayHeight() * 0.122));
                }
                sleepInt(5);
            }
            for (int i = 0; i < 2; i++) {
                verify("adf", phone.getCurrentPackageName().equals("com.cashtoutiao"));
                phone.click((int) (phone.getDisplayWidth() * 0.8), (int) (phone.getDisplayHeight() * 0.95)); //点击立即兑换
                sleepInt(8);
                phone.click((int) (phone.getDisplayWidth() * 0.95), (int) (phone.getDisplayHeight() * 0.08));
                sleepInt(1);
            }
        }
    }

    @Test
    public void testExchangeWeiLi() throws UiObjectNotFoundException {
        press_back(2);
        phone.pressHome();
        sleepInt(1);
        UiObject2 huitoutiao=phone.findObject(By.text("微鲤头条"));
        huitoutiao.click();
        sleepInt(15);
        for(int i=0;i<3;i++) {
            if (!phone.getCurrentPackageName().equals("cn.weli.story")) {
                phone.pressBack();
                sleepInt(1);
            }else break;
        }
        verify("adf",phone.getCurrentPackageName().equals("cn.weli.story"));
        UiObject2 mine=waitForObj(By.text("我的"));
        verify("mine not exist",mine!=null);
        mine.click();
        sleepInt(8);
        UiObject2 chang=phone.findObject(By.res("cn.weli.story:id/text_residue_change"));
        verify("adf",chang!=null);
        float residue=parseFloat(chang.getText());
        Log.i(TAG, "testExchangeWeiLi: residue"+residue);
        if(residue>=1) {
            UiObject2 tixian=phone.findObject(By.text("提现兑换")); //点击兑换提现
            tixian.click();
            sleepInt(2);
            UiObject2 renzheng=phone.findObject(By.text("去认证"));
            if(renzheng!=null) {
                UiObject2 zhifubao=phone.findObject(By.text("支付宝"));
                zhifubao.click();
                sleepInt(1);
            }
            verify("adf", phone.getCurrentPackageName().equals("cn.weli.story"));
            phone.click((int) (phone.getDisplayWidth() * 0.8), (int) (phone.getDisplayHeight() * 0.95)); //点击立即兑换
            sleepInt(1);

        }
    }

    @Test
    public void testExchangeKuaiMa() throws UiObjectNotFoundException {
        phone.pressHome();
        sleepInt(1);
        UiObject2 huitoutiao=phone.findObject(By.text("快马小报"));
        huitoutiao.click();
        sleepInt(15);
        press_back(1);
        sleepInt(1);
        verify("adf",phone.getCurrentPackageName().equals("com.kuaima.browser"));
        UiObject2 mine=waitForObj(By.text("我的"));
        verify("mine not exist",mine!=null);
        mine.click();
        sleepInt(5);
        UiObject2 chang=phone.findObject(By.res("com.kuaima.browser:id/tv_my_worthreading_count"));
        verify("adf",chang!=null);
        float residue=parseFloat(chang.getText());
        Log.i(TAG, "testExchangeWeiLi: residue"+residue);
        if(residue>=1.02) {
            UiObject2 tixian=phone.findObject(By.text(Pattern.compile("提现兑换.*|一元提现"))); //点击兑换提现
            tixian.click();
            sleepInt(3);
            UiObject2 renzheng=phone.findObject(By.text("去认证"));
            if(renzheng!=null) {
                UiObject2 zhifubao=phone.findObject(By.text("支付宝"));
                zhifubao.click();
                sleepInt(1);
            }
            verify("adf", phone.getCurrentPackageName().equals("com.kuaima.browser"));
            phone.click((int) (phone.getDisplayWidth() * 0.5), (int) (phone.getDisplayHeight() * 0.9)); //点击立即兑换
            sleepInt(1);
            UiObject2 shentixian=phone.findObject(By.text(Pattern.compile("申请提现"))); //点击兑换提现
            shentixian.click();
            sleepInt(3);
        }
    }

    @Test
    public void testSigninYue() throws UiObjectNotFoundException {
        press_back(2);
        phone.pressHome();
        sleepInt(1);
        UiObject2 toutiao = phone.findObject(By.text("头条"));
        if (toutiao == null) {
            UiObject2 huitoutiao = phone.findObject(By.text("悦头条"));
            huitoutiao.click();
        }
        sleepInt(10);
        for (int i = 0; i < 3; i++) {
            if (!phone.getCurrentPackageName().equals("com.expflow.reading")) {
                phone.pressBack();
                sleepInt(1);
            } else break;
        }
        verify("adf", phone.getCurrentPackageName().equals("com.expflow.reading"));
        UiObject2 task = waitForObj(By.text("任务"));
        verify("mine not exist", task != null);
        task.click();
        sleepInt(5);
        phone.swipe((int) (phone.getDisplayWidth() * 0.5), (int) (phone.getDisplayHeight() * 0.3), (int) (phone.getDisplayWidth() * 0.5), (int) (phone.getDisplayHeight() * 0.6), 30);
        sleepInt(3);
        verify("adf", phone.getCurrentPackageName().equals("com.expflow.reading"));
        if(Build.DEVICE.equals("ja3gduosctc")){
            phone.click((int) (phone.getDisplayWidth() * 0.5), (int) (phone.getDisplayHeight() * 0.32));
        }else {
            phone.click((int) (phone.getDisplayWidth() * 0.5), (int) (phone.getDisplayHeight() * 0.276));
        }
        sleepInt(5);
        press_back(1);
        verify("adf", phone.getCurrentPackageName().equals("com.expflow.reading"));
        phone.click((int) (phone.getDisplayWidth() * 0.88), (int) (phone.getDisplayHeight() * 0.67));
        sleepInt(2);
    }
    @Test
    public void testSigninQu() throws UiObjectNotFoundException {
        press_back(2);
        phone.pressHome();
        sleepInt(1);
        UiObject2 huitoutiao=phone.findObject(By.text("趣头条"));
        huitoutiao.click();
        sleepInt(10);
        for(int i=0;i<3;i++) {
            if (!phone.getCurrentPackageName().equals("com.jifen.qukan")) {
                phone.pressBack();
                sleepInt(1);
            }else break;
        }
        UiObject2 mine=waitForObj(By.text("我的"));
        verify("mine not exist",mine!=null);
        mine.click();
        sleepInt(2);
        verify("adf",phone.getCurrentPackageName().equals("com.jifen.qukan"));
        UiObject2 task=waitForObj(By.text("任务系统"));
        verify("mine not exist",task!=null);
        task.click();
        sleepInt(5);
        phone.click((int)(phone.getDisplayWidth()*0.5),(int)(phone.getDisplayHeight()*0.23));
        sleepInt(3);
        UiObject2 task1=waitForObj(By.text("任务"));
        if(task1==null)press_back(1);
        phone.click((int)(phone.getDisplayWidth()*0.5),(int)(phone.getDisplayHeight()*0.5));
        sleepInt(2);
        press_back(1);
       /* UiObject2 mine=waitForObj(By.text("我的"));
        verify("mine not exist",mine!=null);
        mine.click();
        sleepInt(5);
        for(int i=0;i<3;i++) {
                phone.click((int) (phone.getDisplayWidth() * 0.16), (int) (phone.getDisplayHeight() * 0.42));
                sleepInt(8);
                verify("adf", phone.getCurrentPackageName().equals("com.expflow.reading"));
                phone.click((int) (phone.getDisplayWidth() * 0.93), (int) (phone.getDisplayHeight() * 0.39));
                sleepInt(3);
                verify("adf", phone.getCurrentPackageName().equals("com.expflow.reading"));
                phone.swipe((int) (phone.getDisplayWidth() * 0.5), (int) (phone.getDisplayHeight() * 0.6), (int) (phone.getDisplayWidth() * 0.5), (int) (phone.getDisplayHeight() * 0.3), 30);
                sleepInt(3);
                phone.click((int) (phone.getDisplayWidth() * 0.8), (int) (phone.getDisplayHeight() * 0.95));
                sleepInt(8);
        }
        mine=phone.findObject(By.text("我的"));
        if(mine==null)press_back(1);
        for(int i=0;i<3;i++) {
                phone.click((int) (phone.getDisplayWidth() * 0.16), (int) (phone.getDisplayHeight() * 0.42));
                sleepInt(8);
                phone.click((int)(phone.getDisplayWidth()*0.5),(int)(phone.getDisplayHeight()*0.18));
                sleepInt(5);
                verify("adf", phone.getCurrentPackageName().equals("com.expflow.reading"));
                phone.click((int) (phone.getDisplayWidth() * 0.93), (int) (phone.getDisplayHeight() * 0.39));
                sleepInt(3);
                verify("adf", phone.getCurrentPackageName().equals("com.expflow.reading"));
                phone.click((int)(phone.getDisplayWidth()*0.8),(int)(phone.getDisplayHeight()*0.86));
                sleepInt(8);
        }
        verify("adf",phone.getCurrentPackageName().equals("com.expflow.reading"));
        mine=waitForObj(By.text("我的"));
        if(mine==null)press_back(1);
        UiObject2 task1=waitForObj(By.text("任务"));
        verify("mine not exist",task1!=null);
        task1.click();
        sleepInt(5);*/
    }


    @Test
    public void testHuiTouTiao() throws UiObjectNotFoundException, RemoteException {
        phone.pressHome();
        sleepInt(1);
//        callShell("am start -n com.cashtoutiao/com.cashtoutiao.account.ui.main.MainTabActivity");
        UiObject2 huitoutiao=phone.findObject(By.text("惠头条"));
        huitoutiao.click();
        sleepInt(15);
        for(int i=0;i<3;i++) {
            if (!phone.getCurrentPackageName().equals("com.cashtoutiao")) {
                phone.pressBack();
                sleepInt(1);
            }else break;
        }
        press_back(1);
        sleepInt(2);
        phone.click((int) (phone.getDisplayWidth() * 0.11), (int) (phone.getDisplayHeight() * 0.06));
        sleepInt(5);
        UiObject2 time = phone.findObject(By.text("时段奖励"));
        if(time!=null){
            press_back(1);
        }
        String[] names={"头条","头条","娱乐","八卦","社会","搞笑","健康","国内","军事","国际"};
        UiObject2 fresh = phone.findObject(By.text("头条"));
        if (fresh != null) {
            fresh.click();
            sleepInt(3);
        }else phone.click((int)(phone.getDisplayWidth()*0.7),(int)(phone.getDisplayHeight()*0.59)); //790 1128 1080 1812  1440 2580
        int name=getRandom1(names.length);
        Log.i(TAG, "testHuiTouTiao: name"+name+names[name]);
        phone.swipe((int)(phone.getDisplayWidth()*0.2),(int)(phone.getDisplayHeight()*0.16),(int)(phone.getDisplayWidth()*0.8),(int)(phone.getDisplayHeight()*0.16),15);
        for(int q=0;q<4;q++) {
            UiObject2 type = phone.findObject(By.text(names[name]));
            if (type==null) {
                phone.swipe((int)(phone.getDisplayWidth()*0.72),(int)(phone.getDisplayHeight()*0.16),(int)(phone.getDisplayWidth()*0.57),(int)(phone.getDisplayHeight()*0.16),30);
            }else break;
        }
        sleepInt(4);
        UiObject2 type = waitForObj(By.text(names[name]));
        verify("beijing not exists", type != null);
        type.click();
        sleepInt(1);
        phone.swipe((int)(phone.getDisplayWidth()*0.2),(int)(phone.getDisplayHeight()*0.25),(int)(phone.getDisplayWidth()*0.2),(int)(phone.getDisplayHeight()*0.8),15);
        sleepInt(1);
/*        for(int q=0;q<getRandom1(10);q++) {
            phone.swipe((int)(phone.getDisplayWidth()*0.5),(int)(phone.getDisplayHeight()*0.8),(int)(phone.getDisplayWidth()*0.5),(int)(phone.getDisplayHeight()*0.2),15);
        }*/
        sleepInt(5);
        for(int k=0;k<15;k++) {
            phone.click((int) (phone.getDisplayWidth() * 0.5), (int) (phone.getDisplayHeight() * 0.2));
                        sleepInt(2);
                        for (int i = 0; i < 3; i++) {
                            if (!phone.getCurrentPackageName().equals("com.cashtoutiao")) {
                                phone.pressBack();
                                sleepInt(1);
                            } else break;
                        }
                        UiObject2 toutiao1 = phone.findObject(By.text("头条"));
                        if (toutiao1 != null) {
                            phone.click((int) (phone.getDisplayWidth() * 0.7), (int) (phone.getDisplayHeight() * 0.7));
                            sleepInt(1);
                        }
                        sleepInt(5);
                        for (int j = 0; j < 20; j++) {
                            phone.swipe(600 + getRandom(200), 1300 + getRandom(100), 600 + getRandom(200), 600 + getRandom(100), getRandom(150));
                            sleepInt(3);
                            UiObject2 readAll = phone.findObject(By.desc("展开全文"));
                            if (readAll != null) {
                                readAll.click();
                                sleepInt(1);
                            }
                            phone.swipe(600 + getRandom(200), 600 + getRandom(50), 600 + getRandom(200), 800 + getRandom(50), getRandom(150));
                            sleepInt(3);
                            phone.swipe(600 + getRandom(200), 1200 + getRandom(100), 600 + getRandom(200), 700 + getRandom(100), getRandom(150));
                            sleepInt(3);
                            verify("没有在惠头条界面", phone.getCurrentPackageName().equals("com.cashtoutiao"));
                            readAll = phone.findObject(By.desc("展开全文"));
                            if (readAll != null) {
                                readAll.click();
                                sleepInt(1);
                            }
                            UiObject2 stop = phone.findObject(By.desc(Pattern.compile("热点新闻.*")));
                            UiObject2 circle = phone.findObject(By.res("com.cashtoutiao:id/circle_progress"));
                            if (circle == null || (stop != null && j > 5)) {
                                break;
                            }
                        }
                press_back(1);
            phone.swipe((int) (phone.getDisplayWidth() * 0.5), (int) (phone.getDisplayHeight() * 0.8), (int) (phone.getDisplayWidth() * 0.5), (int) (phone.getDisplayHeight() * 0.4), 30);
            sleepInt(1);
        }
        }
    @Test
    public void testZhongQing() throws UiObjectNotFoundException, RemoteException {
        phone.pressHome();
        sleepInt(1);
//        callShell("am start -n com.cashtoutiao/com.cashtoutiao.account.ui.main.MainTabActivity");
        UiObject2 huitoutiao = phone.findObject(By.text("中青看点"));
        huitoutiao.click();
        sleepInt(10);
        for (int i = 0; i < 3; i++) {
            UiObject2 tuijian = phone.findObject(By.text("推荐"));
            if (tuijian == null) {
                phone.pressBack();
                sleepInt(1);
            } else break;
        }
        UiObject2 fresh = phone.findObject(By.text("首页"));
        if (fresh != null) {
            fresh.click();
            sleepInt(3);
        }
        String[] names = {"推荐", "热点", "娱乐", "健康"};
        int name = getRandom1(names.length);
        Log.i(TAG, "testHuiTouTiao: name" + name + names[name]);
        phone.swipe((int) (phone.getDisplayWidth() * 0.2), (int) (phone.getDisplayHeight() * 0.11), (int) (phone.getDisplayWidth() * 0.8), (int) (phone.getDisplayHeight() * 0.11), 15);
/*        for(int q=0;q<4;q++) {
            UiObject2 type = phone.findObject(By.text(names[name]));
            if (type==null) {
                phone.swipe((int)(phone.getDisplayWidth()*0.72),(int)(phone.getDisplayHeight()*0.11),(int)(phone.getDisplayWidth()*0.57),(int)(phone.getDisplayHeight()*0.11),30);
            }else break;
        }*/
        sleepInt(1);
        UiObject2 type = waitForObj(By.text(names[name]));
        verify("beijing not exists", type != null);
        type.click();
        sleepInt(1);
        for (int k = 0; k < 15; k++) {
            phone.swipe((int) (phone.getDisplayWidth() * 0.2), (int) (phone.getDisplayHeight() * 0.25), (int) (phone.getDisplayWidth() * 0.2), (int) (phone.getDisplayHeight() * 0.8), 15);
            sleepInt(5);
            phone.click((int) (phone.getDisplayWidth() * 0.5), (int) (phone.getDisplayHeight() * 0.25));
            sleepInt(1);
            for (int i = 0; i < 3; i++) {
                if (!phone.getCurrentPackageName().equals("cn.youth.news")) {
                    phone.pressBack();
                    sleepInt(1);
                } else break;
            }
            phone.swipe((int) (phone.getDisplayWidth() * 0.5), (int) (phone.getDisplayHeight() * 0.92), (int) (phone.getDisplayWidth() * 0.5), (int) (phone.getDisplayHeight() * 0.06), 100);
            sleepInt(1);
            verify("adf", phone.getCurrentPackageName().equals("cn.youth.news"));
            phone.click((int) (phone.getDisplayWidth() * 0.5), (int) (phone.getDisplayHeight() * 0.728));
            sleepInt(1);
            for (int j = 0; j < 1; j++) {
                phone.swipe((int) (phone.getDisplayWidth() * 0.5), (int) (phone.getDisplayHeight() * 0.7), (int) (phone.getDisplayWidth() * 0.5), (int) (phone.getDisplayHeight() * 0.2), 2);
                sleepInt(1);
                phone.swipe((int) (phone.getDisplayWidth() * 0.5), (int) (phone.getDisplayHeight() * 0.7), (int) (phone.getDisplayWidth() * 0.5), (int) (phone.getDisplayHeight() * 0.2), 2);
                sleepInt(8);
            }
            sleepInt(4);
            press_back(1);
            UiObject2 tuijian = phone.findObject(By.text("我的"));
            if (tuijian == null) press_back(1);
            sleepInt(1);
        }
    }
    @Test
    public void testWeiLi() throws UiObjectNotFoundException, RemoteException {
        phone.pressHome();
        sleepInt(1);
        UiObject2 huitoutiao=phone.findObject(By.text("微鲤头条"));
        huitoutiao.click();
        sleepInt(10);
        for(int i=0;i<3;i++) {
            UiObject2 tuijian=phone.findObject(By.text("我的"));
            if (tuijian==null) {
                Log.i(TAG, "testWeiLi: 1111");
                phone.pressBack();
                sleepInt(1);
            }else break;
        }
        UiObject2 fresh = phone.findObject(By.text("头条"));
        if (fresh != null) {
            fresh.click();
            sleepInt(3);
        }
    UiObject2 reading=phone.findObject(By.text("知道了"));
    if(reading!=null){
        reading.click();
        sleepInt(1);
    }
        UiObject2 stop=phone.findObject(By.text(Pattern.compile(".*已停止.*")));
        if(stop!=null){
            UiObject2 sure=phone.findObject(By.text(Pattern.compile("确定")));
            sure.click();
            sleepInt(1);
        }
        press_back(1);
        sleepInt(2);
        phone.click((int) (phone.getDisplayWidth() * 0.11), (int) (phone.getDisplayHeight() * 0.06));
        sleepInt(5);
        press_back(1);
        String[] names={"推荐","社会","情感","搞笑","娱乐"};
        int name=getRandom1(names.length);
        UiObject2 type = waitForObj(By.text(names[name]));
        verify(names[name]+"not exists", type != null);
        type.click();
        sleepInt(1);
        phone.swipe((int)(phone.getDisplayWidth()*0.2),(int)(phone.getDisplayHeight()*0.25),(int)(phone.getDisplayWidth()*0.2),(int)(phone.getDisplayHeight()*0.8),15);
        sleepInt(5);
        lable:   for(int k=0;k<10;k++) {
            UiObject2 jinbi = phone.findObject(By.text("领金币"));
            if(jinbi!=null){
                jinbi.click();
                sleepInt(1);
                press_back(1);
            }
            List<UiObject2> lists = phone.findObject(By.clazz("android.support.v7.widget.RecyclerView")).getChildren();
            for (int q = 0; q < lists.size(); q++) {
                try {
                    UiObject2 a = lists.get(q).findObject(By.text(Pattern.compile("广告|立即下载")));
                    if (a == null) {
                        jinbi = phone.findObject(By.text("领金币"));
                        if (jinbi != null) {
                            jinbi.click();
                            sleepInt(1);
                            press_back(1);
                            break;
                        }
                        lists.get(q).click();
                        sleepInt(1);
                        for (int i = 0; i < 3; i++) {
                            if (!phone.getCurrentPackageName().equals("cn.weli.story")) {
                                phone.pressBack();
                                sleepInt(1);
                            } else break;
                        }
                        sleepInt(5);
                        UiObject2 cion = phone.findObject(By.res("cn.weli.story:id/rl_read_coin"));
                        if (cion != null && k==0&&q==0) {
                            cion.click();
                            UiObject2 today = waitForObj(By.text("今天"));
                            if (today != null && today.getParent().hasObject(By.text(Pattern.compile("阅读文章60分钟，获得3600金币.*")))) {
                                break lable;
                            } else press_back(1);
                        }
                        phone.swipe((int) (phone.getDisplayWidth() * 0.5), (int) (phone.getDisplayHeight() * 0.92), (int) (phone.getDisplayWidth() * 0.5), (int) (phone.getDisplayHeight() * 0.06), 100);
                        sleepInt(1);
                        verify("adf", phone.getCurrentPackageName().equals("cn.weli.story"));
                        sleepInt(3);
                        for (int j = 0; j < 17; j++) {
                            phone.swipe((int) (phone.getDisplayWidth() * 0.5), (int) (phone.getDisplayHeight() * 0.7), (int) (phone.getDisplayWidth() * 0.5), (int) (phone.getDisplayHeight() * 0.6), 30);
                            sleepInt(4);
                            reading = phone.findObject(By.text("知道了"));
                            if (reading != null) {
                                reading.click();
                                sleepInt(1);
                            }
                            reading = phone.findObject(By.text("本篇奖励已上限.*"));
                            if (reading != null)break;
                            verify("adf", phone.getCurrentPackageName().equals("cn.weli.story"));
                        }
                        press_back(1);
                        UiObject2 tuijian = waitForObj(By.text("我的"));
                    if (tuijian == null) press_back(1);
                    }
                }catch (StaleObjectException e){
                    phone.waitForWindowUpdate("cn.weli.story",10000);
                    //UiObject2 a = lists.get(q).findObject(By.text(Pattern.compile("广告|立即下载")));
                    continue;
                }
            }
            phone.swipe((int) (phone.getDisplayWidth() * 0.5), (int) (phone.getDisplayHeight() * 0.8), (int) (phone.getDisplayWidth() * 0.5), (int) (phone.getDisplayHeight() * 0.2), 30);
            sleepInt(3);
        }
    }

    @Test
    public void testWeiLiBao() throws UiObjectNotFoundException, RemoteException {
        phone.pressHome();
        sleepInt(1);
        UiObject2 huitoutiao = phone.findObject(By.text("微鲤头条"));
        huitoutiao.click();
        sleepInt(10);
        for (int i = 0; i < 3; i++) {
            UiObject2 tuijian = phone.findObject(By.text("推荐"));
            if (tuijian == null) {
                phone.pressBack();
                sleepInt(1);
            } else break;
        }
        UiObject2 fresh = phone.findObject(By.text("头条"));
        if (fresh != null) {
            fresh.click();
            sleepInt(3);
        }
        UiObject2 reading = phone.findObject(By.text("知道了"));
        if (reading != null) {
            reading.click();
            sleepInt(1);
        }
    }




    @Test
    public void testSouHu() throws UiObjectNotFoundException, RemoteException {
        phone.pressHome();
        sleepInt(1);
//        callShell("am start -n com.cashtoutiao/com.cashtoutiao.account.ui.main.MainTabActivity");
        UiObject2 huitoutiao=phone.findObject(By.text("搜狐新闻"));
        huitoutiao.click();
        sleepInt(10);
        for(int i=0;i<3;i++) {
            if (!phone.getCurrentPackageName().equals("com.sohu.infonews")) {
                phone.pressBack();
                sleepInt(1);
            }else break;
        }
        String[] names={"推荐","要闻","社会","娱乐","国际"};
        UiObject2 fresh = phone.findObject(By.text("刷新"));
        if (fresh != null) {
            fresh.click();
            sleepInt(3);
        }
        //else phone.click((int)(phone.getDisplayWidth()*0.7),(int)(phone.getDisplayHeight()*0.59)); //790 1128 1080 1812
/*        phone.swipe((int)(phone.getDisplayWidth()*0.2),(int)(phone.getDisplayHeight()*0.16),(int)(phone.getDisplayWidth()*0.2),(int)(phone.getDisplayHeight()*0.8),30);
        sleepInt(1);
        int name=getRandom1(names.length);
        Log.i(TAG, "testHuiTouTiao: name"+name+names[name]);
        for(int q=0;q<4;q++) {
            UiObject2 type = phone.findObject(By.text(names[name]));
            if (type==null) {
                phone.swipe((int)(phone.getDisplayWidth()*0.72),(int)(phone.getDisplayHeight()*0.16),(int)(phone.getDisplayWidth()*0.57),(int)(phone.getDisplayHeight()*0.16),30);
            }else break;
        }*/
        int name=getRandom1(names.length);
        sleepInt(4);
        Log.i(TAG, "testHuiTouTiao: name"+name+names[name]);
        UiObject2 type = waitForObj(By.text(names[name]));
        verify("beijing not exists", type != null);
        type.click();
        sleepInt(1);
        for(int q=0;q<getRandom1(10);q++) {
            phone.swipe((int)(phone.getDisplayWidth()*0.5),(int)(phone.getDisplayHeight()*0.8),(int)(phone.getDisplayWidth()*0.5),(int)(phone.getDisplayHeight()*0.2),15);
        }
        sleepInt(3);
        for(int k=0;k<20;k++) {
            phone.click((int)(phone.getDisplayWidth()*0.5),(int)(phone.getDisplayHeight()*0.5));
            //    List<UiObject2> lists = waitForObj(By.clazz("android.support.v7.widget.RecyclerView")).findObjects(By.clazz("android.widget.RelativeLayout"));
            //  for (int i = 0; i < lists.size(); i++) {
            //      phone.swipe(600 + getRandom(200), 1300 + getRandom(100), 600 + getRandom(200), 1000 + getRandom(100), getRandom(150));
            sleepInt(1);
            for(int i=0;i<3;i++) {
                if (!phone.getCurrentPackageName().equals("com.sohu.infonews")) {
                    phone.pressBack();
                    sleepInt(1);
                }else break;
            }
            UiObject2 toutiao1 = phone.findObject(By.text("推荐"));
            if(toutiao1!=null){
                phone.click((int)(phone.getDisplayWidth()*0.7),(int)(phone.getDisplayHeight()*0.7));
                sleepInt(1);
            }
            //         lists = waitForObj(By.clazz("android.support.v7.widget.RecyclerView")).findObjects(By.clazz("android.widget.RelativeLayout"));
            //         UiObject2 news = lists.get(0);
            //          verify("news not exists", news != null);
            //        news.click();
            sleepInt(5);
            for (int j = 0; j < 10; j++) {
                phone.swipe(600 + getRandom(200), 1300 + getRandom(100), 600 + getRandom(200), 400 + getRandom(100), getRandom(150));
                sleepInt(3);
/*                UiObject2   readAll = phone.findObject(By.desc("展开全文"));
                if (readAll != null) {
                    readAll.click();
                    sleepInt(1);
                }*/

/*                phone.swipe(600 + getRandom(200), 1200 + getRandom(100), 600 + getRandom(200), 700 + getRandom(100), getRandom(150));
                sleepInt(3);*/
                verify("没有在惠头条界面",phone.getCurrentPackageName().equals("com.sohu.infonews"));
/*                readAll = phone.findObject(By.desc("展开全文"));
                if (readAll != null) {
                    readAll.click();
                    sleepInt(1);
                }*/

                UiObject2 stop = phone.findObject(By.text(Pattern.compile("伤感|相关阅读|点赞")));
                UiObject2 circle=phone.findObject(By.res("com.cashtoutiao:id/circle_progress"));
                if (stop!=null) {
                    phone.swipe(600 + getRandom(200), 1300 + getRandom(100), 600 + getRandom(200), 400 + getRandom(100), getRandom(150));
                    sleepInt(5);
                    phone.swipe(600 + getRandom(200), 1300 + getRandom(100), 600 + getRandom(200), 400 + getRandom(100), getRandom(150));
                    sleepInt(3);
                    break;
                }
            }
            press_back(1);
            phone.swipe((int)(phone.getDisplayWidth()*0.5),(int)(phone.getDisplayHeight()*0.67),(int)(phone.getDisplayWidth()*0.5),(int)(phone.getDisplayHeight()*0.5),30);
            sleepInt(1);
        }
    }
    @Test
    public void testDongFang() throws UiObjectNotFoundException, RemoteException {
        UiObject2 huitoutiao=phone.findObject(By.text("东方头条"));
        huitoutiao.click();
        sleepInt(10);
        UiObject2 news=phone.findObject(By.text("新闻"));
        if(news!=null){
            news.click();
            sleepInt(2);
        }

        for(int i=0;i<3;i++) {
            UiObject2 tuijian=phone.findObject(By.text("推荐"));
            if (tuijian==null) {
                phone.pressBack();
                sleepInt(2);
            }else break;
        }
        String[] names={"推荐","热点","北京","娱乐"};
        int name=getRandom1(names.length);
        sleepInt(4);
        phone.swipe((int)(phone.getDisplayWidth()*0.2),(int)(phone.getDisplayHeight()*0.12),(int)(phone.getDisplayWidth()*0.5),(int)(phone.getDisplayHeight()*0.12),30);
        sleepInt(1);
        Log.i(TAG, "testHuiTouTiao: name"+name+names[name]);
        UiObject2 type = waitForObj(By.text(names[name]));
        if(type!=null) {
            type.click();
            sleepInt(1);
        }
        UiObject2 fresh = phone.findObject(By.text("刷新"));
        if (fresh != null) {
            fresh.click();
            sleepInt(3);
        }
        phone.swipe((int)(phone.getDisplayWidth()*0.5),(int)(phone.getDisplayHeight()*0.2),(int)(phone.getDisplayWidth()*0.5),(int)(phone.getDisplayHeight()*0.8),30);
        sleepInt(5);
        for(int k=0;k<5;k++) {
            UiObject2 ue = phone.findObject(By.res(Pattern.compile("com.songheng.eastnews:id/vw|com.songheng.eastnews:id/ue|com.songheng.eastnews:id/v4")));
            int id1 = parseInt(ue.getText());
            List<UiObject2> lists=phone.findObject(By.clazz(Pattern.compile("android.widget.ListView|android.support.v7.widget.RecyclerView"))).getChildren();
            for(int q=0;q<lists.size()-1;q++) {
                try {
                    UiObject2 a = lists.get(q).findObject(By.text(Pattern.compile("广告|视频|点击下载.*")));
                    if (a == null) {
                        if (!(k == 0 && q == 0)) {
                            lists.get(q).click();
                            sleepInt(5);
                        } else continue;
                        for (int j = 0; j < 5; j++) {
                            phone.swipe((int) (phone.getDisplayWidth() * 0.5), (int) (phone.getDisplayHeight() * 0.8), (int) (phone.getDisplayWidth() * 0.5), (int) (phone.getDisplayHeight() * 0.2), 30);
                            sleepInt(2);
                            phone.swipe((int) (phone.getDisplayWidth() * 0.5), (int) (phone.getDisplayHeight() * 0.8), (int) (phone.getDisplayWidth() * 0.5), (int) (phone.getDisplayHeight() * 0.2), 30);
                            sleepInt(2);
                            verify("adfasd", phone.getCurrentPackageName().equals("com.songheng.eastnews"));
                            UiObject2 readAll = phone.findObject(By.desc(Pattern.compile("展开全文.*")));
                            if (readAll != null) {
                                phone.swipe((int) (phone.getDisplayWidth() * 0.5), (int) (phone.getDisplayHeight() * 0.7), (int) (phone.getDisplayWidth() * 0.5), (int) (phone.getDisplayHeight() * 0.5), 30);
                                sleepInt(1);
                                readAll.click();
                                sleepInt(1);
                                for (int z = 0; z < 5; z++) {
                                    phone.swipe((int) (phone.getDisplayWidth() * 0.5), (int) (phone.getDisplayHeight() * 0.8), (int) (phone.getDisplayWidth() * 0.5), (int) (phone.getDisplayHeight() * 0.2), 10);
                                    sleepInt(1);
                                }
                                break;
                            }
                        }
                        press_back(1);
                    }
                }catch (StaleObjectException e){
                    phone.waitForWindowUpdate("com.songheng.eastnews",10000);
                    continue;
                }
            }
            phone.swipe((int)(phone.getDisplayWidth()*0.2),(int)(phone.getDisplayHeight()*0.12),(int)(phone.getDisplayWidth()*0.8),(int)(phone.getDisplayHeight()*0.12),30);
            sleepInt(1);
            ue = phone.findObject(By.res(Pattern.compile("com.songheng.eastnews:id/vw|com.songheng.eastnews:id/ue|com.songheng.eastnews:id/v4")));
            int id2 = parseInt(ue.getText());
            if(id2==id1) break;
            phone.swipe((int)(phone.getDisplayWidth()*0.5),(int)(phone.getDisplayHeight()*0.85),(int)(phone.getDisplayWidth()*0.5),(int)(phone.getDisplayHeight()*0.2),30);
            sleepInt(1);
        }
    }

    @Test
    public void testHongBao() throws UiObjectNotFoundException, RemoteException {
        phone.pressHome();
        sleepInt(1);
//        callShell("am start -n com.expflow.reading/com.expflow.reading.activity.MainActivity");
        UiObject2 huitoutiao=phone.findObject(By.text("红包头条"));
        huitoutiao.click();
        sleepInt(10);
        press_back(1);
        sleepInt(2);
        phone.click((int)(phone.getDisplayWidth()*0.14*(getRandom1(7))),(int)(phone.getDisplayHeight()*0.1));
        sleepInt(3);
        verify("没有在红包头条界面",phone.getCurrentPackageName().equals("com.martian.hbnews"));
        phone.swipe((int)(phone.getDisplayWidth()*0.5),(int)(phone.getDisplayHeight()*0.2),(int)(phone.getDisplayWidth()*0.5),(int)(phone.getDisplayHeight()*0.8),15);
        phone.swipe((int)(phone.getDisplayWidth()*0.5),(int)(phone.getDisplayHeight()*0.2),(int)(phone.getDisplayWidth()*0.5),(int)(phone.getDisplayHeight()*0.8),15);
        sleepInt(5);
        for(int k=0;k<10;k++) {
            for(int i=0;i<3;i++) {
                if (!phone.getCurrentPackageName().equals("com.martian.hbnews")) {
                    press_back(1);
                }else break;
            }
            phone.click((int)(phone.getDisplayWidth()*0.23),(int)(phone.getDisplayHeight()*0.5));
            sleepInt(3);
            for (int j = 0; j < 10; j++) {
                phone.swipe(600 + getRandom(200), 1000 + getRandom(100), 600 + getRandom(200), 800 + getRandom(100), getRandom(150));
                sleepInt(4);
                for(int i=0;i<3;i++) {
                    if (!phone.getCurrentPackageName().equals("com.martian.hbnews")) {
                        press_back(1);
                    }else break;
                }
                verify("没有在红包头条界面",phone.getCurrentPackageName().equals("com.martian.hbnews"));
/*                UiObject2 readAll = phone.findObject(By.desc("点击阅读全文"));
                if (readAll != null) {
                    readAll.click();
                    sleepInt(1);
                }*/
                UiObject2 circul = phone.findObject(By.res("com.martian.hbnews:id/circularProgressBar"));
                if(circul==null){
                    break;
                }
            }
            press_back(1);
            sleepInt(1);
            press_back(1);
            sleepInt(1);
            phone.swipe((int)(phone.getDisplayWidth()*0.5),(int)(phone.getDisplayHeight()*0.7),(int)(phone.getDisplayWidth()*0.5),(int)(phone.getDisplayHeight()*0.4),30);
            sleepInt(1);
        }
    }
    @Test
    public void testYueTouTiao() throws UiObjectNotFoundException, RemoteException {
        phone.pressHome();
        sleepInt(1);
//        callShell("am start -n com.expflow.reading/com.expflow.reading.activity.MainActivity");
            UiObject2 huitoutiao=phone.findObject(By.text("悦头条"));
            huitoutiao.click();
        sleepInt(10);
        for(int i=0;i<3;i++) {
            UiObject2 toutiao = phone.findObject(By.text("头条"));
            if (toutiao==null) {
                phone.pressBack();
                sleepInt(2);
            }else {
                toutiao.click();
                sleepInt(1);
                break;
            }
        }

        verify("没有在悦头条界面", phone.getCurrentPackageName().equals("com.expflow.reading"));
        phone.click((int) (phone.getDisplayWidth() * 0.11), (int) (phone.getDisplayHeight() * 0.06));
        sleepInt(5);
        UiObject2 toutiao=phone.findObject(By.text("头条"));
        if(toutiao==null) {
            press_back(1);
        }
        String[] names={"头条","社会","娱乐","体育","健康","财经","科技","文化","军事","国际","笑话","星座","体育","时尚"};
        int name=getRandom1(names.length);
        Log.i(TAG, "testHuiTouTiao: name"+name+names[name]);
        if(Build.DEVICE.equals("ja3gduosctc")){
            phone.swipe((int)(phone.getDisplayWidth()*0.2),(int)(phone.getDisplayHeight()*0.15),(int)(phone.getDisplayWidth()*0.8),(int)(phone.getDisplayHeight()*0.15),10);
        }else {
            phone.swipe((int)(phone.getDisplayWidth()*0.2),(int)(phone.getDisplayHeight()*0.11),(int)(phone.getDisplayWidth()*0.8),(int)(phone.getDisplayHeight()*0.11),10);
        }
        sleepInt(1);
        int slip=getRandomy(5);
        for(int q=0;q<slip;q++) {
                if(Build.DEVICE.equals("ja3gduosctc")){
                    phone.swipe((int) (phone.getDisplayWidth() * 0.72), (int) (phone.getDisplayHeight() * 0.15), (int) (phone.getDisplayWidth() * 0.3), (int) (phone.getDisplayHeight() * 0.15), 30);
                }else {
                    phone.swipe((int) (phone.getDisplayWidth() * 0.72), (int) (phone.getDisplayHeight() * 0.11), (int) (phone.getDisplayWidth() * 0.3), (int) (phone.getDisplayHeight() * 0.11), 30);
                }
                sleepInt(1);
        }
        sleepInt(4);
   /*     UiObject2 type = waitForObj(By.text(names[name]));
        verify(names[name]+" not exists", type != null);
        type.click();*/
        UiObject2 me = waitForObj(By.text("我的"));
        verify("没有在悦头条界面", me!=null);
        if(Build.DEVICE.equals("ja3gduosctc")) {
            phone.click(getRandomy(phone.getDisplayWidth() - 250) + 20, (int) (phone.getDisplayHeight() * 0.15));
        }else {
            phone.click(getRandomy(phone.getDisplayWidth() - 250) + 20, (int) (phone.getDisplayHeight() * 0.11));
        }
        sleepInt(2);
        phone.swipe((int)(phone.getDisplayWidth()*0.5),(int)(phone.getDisplayHeight()*0.2),(int)(phone.getDisplayWidth()*0.5),(int)(phone.getDisplayHeight()*0.8),15);
        sleepInt(10);
        for(int k=0;k<15;k++) {
            phone.click((int)(phone.getDisplayWidth()*0.5),(int)(phone.getDisplayHeight()*0.5));
             sleepInt(2);
                    for (int i = 0; i < 3; i++) {
                        if (!phone.getCurrentPackageName().equals("com.expflow.reading")) {
                            phone.pressBack();
                            sleepInt(1);
                        } else break;
                    }
                    UiObject2 toutiao1 = phone.findObject(By.text("社会"));
                    if (toutiao1 != null) {
                        phone.click((int) (phone.getDisplayWidth() * 0.7), (int) (phone.getDisplayHeight() * 0.7));
                        sleepInt(1);
                    }
                    sleepInt(5);
                    for (int j = 0; j < 15; j++) {
                        phone.swipe(600 + getRandom(200), 1000 + getRandom(100), 600 + getRandom(200), 800 + getRandom(100), getRandom(150));
                        sleepInt(4);
                        UiObject2 readAll = phone.findObject(By.desc("点击阅读全文"));
                        if (readAll != null) {
                            readAll.click();
                            sleepInt(1);
                        }
                        phone.swipe(600 + getRandom(200), 600 + getRandom(50), 600 + getRandom(200), 700 + getRandom(40), getRandom(100));
                        sleepInt(4);
                        phone.swipe(600 + getRandom(200), 1200 + getRandom(100), 600 + getRandom(200), 900 + getRandom(100), getRandom(150));
                        sleepInt(4);
                        verify("没有在悦头条界面", phone.getCurrentPackageName().equals("com.expflow.reading"));
                        readAll = phone.findObject(By.desc("点击阅读全文"));
                        if (readAll != null) {
                            readAll.click();
                            sleepInt(1);
                        }
                        UiObject2 circle = phone.findObject(By.res("com.expflow.reading:id/IvAwardAnim"));
                        if (circle == null) {
                            break;
                        }
                    }
                press_back(1);
            UiObject2 circle = phone.findObject(By.res("com.expflow.reading:id/IvAwardAnim"));
            if(circle!=null) {
                phone.swipe((int) (phone.getDisplayWidth() * 0.5), (int) (phone.getDisplayHeight() * 0.4), (int) (phone.getDisplayWidth() * 0.5), (int) (phone.getDisplayHeight() * 0.7), 30);
                sleepInt(1);
                phone.click((int) (phone.getDisplayWidth() * 0.05), (int) (phone.getDisplayHeight() * 0.05));
                sleepInt(1);
            }
            phone.swipe((int)(phone.getDisplayWidth()*0.5),(int)(phone.getDisplayHeight()*0.67),(int)(phone.getDisplayWidth()*0.5),(int)(phone.getDisplayHeight()*0.4),30);
            sleepInt(1);
        }
    }

    @Test
    public void testShiKuang() throws UiObjectNotFoundException, RemoteException {
        phone.pressHome();
        sleepInt(1);
        UiObject2 huitoutiao=phone.findObject(By.text("实况新闻"));
        huitoutiao.click();
        sleepInt(10);
        for(int i=0;i<3;i++) {
            UiObject2 toutiao = phone.findObject(By.text("首页"));
            if (toutiao==null) {
                phone.pressBack();
                sleepInt(2);
            }else {
                toutiao.click();
                sleepInt(1);
                break;
            }
        }
        for (int i=0;i<3;i++) {
            if (Build.DEVICE.equals("ja3gduosctc")) {
                //  phone.swipe((int)(phone.getDisplayWidth()*0.2),(int)(phone.getDisplayHeight()*0.15),(int)(phone.getDisplayWidth()*0.8),(int)(phone.getDisplayHeight()*0.15),10);
                phone.click(getRandom(phone.getDisplayWidth() - 200), (int)(phone.getDisplayHeight() * 0.15));
            }else if(Build.DEVICE.contains("pisces")){
                phone.click(getRandom(phone.getDisplayWidth() - 200), (int)(phone.getDisplayHeight() * 0.15));
            }
            else {
                // phone.swipe((int)(phone.getDisplayWidth()*0.2),(int)(phone.getDisplayHeight()*0.11),(int)(phone.getDisplayWidth()*0.8),(int)(phone.getDisplayHeight()*0.11),10);
                phone.click(getRandom(phone.getDisplayWidth() - 200), (int) (phone.getDisplayHeight() * 0.11));
            }
            UiObject2 video=phone.findObject(By.res(Pattern.compile("com.weikuai.wknews:id/video_time|com.weikuai.wknews:id/start")));
            if(video==null)break;
        }

        sleepInt(1);
/*        for(int q=0;q<10;q++) {
            UiObject2 type = phone.findObject(By.text(names[name]));
            if (type==null) {
                if(Build.DEVICE.equals("ja3gduosctc")){
                    phone.swipe((int) (phone.getDisplayWidth() * 0.72), (int) (phone.getDisplayHeight() * 0.15), (int) (phone.getDisplayWidth() * 0.57), (int) (phone.getDisplayHeight() * 0.15), 30);
                }else {
                    phone.swipe((int) (phone.getDisplayWidth() * 0.72), (int) (phone.getDisplayHeight() * 0.11), (int) (phone.getDisplayWidth() * 0.57), (int) (phone.getDisplayHeight() * 0.11), 30);
                }
                sleepInt(1);
            }else break;
        }*/

        sleepInt(1);
        phone.swipe((int)(phone.getDisplayWidth()*0.5),(int)(phone.getDisplayHeight()*0.2),(int)(phone.getDisplayWidth()*0.5),(int)(phone.getDisplayHeight()*0.8),15);
        sleepInt(10);
        for(int k=0;k<15;k++) {
            List<UiObject2> lists=phone.findObject(By.clazz("android.widget.ListView")).getChildren();
            for(int q=0;q<lists.size()-1;q++) {
                try {
                    UiObject2 a = lists.get(q).findObject(By.text(Pattern.compile(".*广告.*")));
                    if (a == null) {
                        lists.get(q).click();
                        sleepInt(2);
                        for (int i = 0; i < 3; i++) {
                            if (!phone.getCurrentPackageName().equals("com.weikuai.wknews")) {
                                phone.pressBack();
                                sleepInt(1);
                            } else break;
                        }
                        sleepInt(5);
                        for (int j = 0; j < 15; j++) {
                            phone.swipe(600 + getRandom(200), 1000 + getRandom(100), 600 + getRandom(200), 800 + getRandom(100), getRandom(150));
                            sleepInt(3);
                            UiObject2 readAll = phone.findObject(By.desc("查看全文"));
                            if (readAll != null) {
                                readAll.click();
                                sleepInt(1);
                            }
                            phone.swipe(600 + getRandom(200), 600 + getRandom(50), 600 + getRandom(200), 700 + getRandom(40), getRandom(100));
                            sleepInt(3);
                            phone.swipe(600 + getRandom(200), 1200 + getRandom(100), 600 + getRandom(200), 900 + getRandom(100), getRandom(150));
                            sleepInt(3);
                            UiObject2 circle = phone.findObject(By.res("com.weikuai.wknews:id/superview"));
                            if (circle == null) {
                                press_back(1);
                            }
                            verify("没有在悦头条界面", circle != null);
                            readAll = phone.findObject(By.desc("查看全文"));
                            if (readAll != null) {
                                readAll.click();
                                sleepInt(1);
                            }

                            UiObject2 stop = phone.findObject(By.desc(Pattern.compile("热点新闻.*")));
                            circle = phone.findObject(By.res("com.weikuai.wknews:id/superview"));
                            if (circle == null || (stop != null && j > 5)) {
                                break;
                            }
                        }

                    }
                    press_back(1);
                }catch (Exception StaleObjectException){
                    phone.waitForWindowUpdate("com.weikuai.wknews",10000);
                    continue;
                }
            }
            phone.swipe((int) (phone.getDisplayWidth() * 0.5), (int) (phone.getDisplayHeight() * 0.8), (int) (phone.getDisplayWidth() * 0.5), (int) (phone.getDisplayHeight() * 0.2), 30);
            sleepInt(1);
        }
    }

    @Test
    public void testHuiTouTiaoVideo() throws UiObjectNotFoundException, RemoteException {
        phone.pressHome();
        sleepInt(1);
/*        phone.swipe(900,1026,200,1026,20);
        sleepInt(1);
        phone.swipe(900,1026,200,1026,20);
        sleepInt(1);
        phone.swipe(900,1026,200,1026,20);
        sleepInt(1);*/
        //callShell("am start -n com.cashtoutiao/com.cashtoutiao.account.ui.main.MainTabActivity");
        UiObject2 toutiao=phone.findObject(By.text("头条"));
        if(toutiao==null) {
            UiObject2 huitoutiao=phone.findObject(By.text("惠头条"));
            huitoutiao.click();
        }
        sleepInt(10);
        UiObject2 fresh = phone.findObject(By.text("视频"));
        fresh.click();
        sleepInt(3);
        String[] names={"推荐","推荐","娱乐","搞笑","社会","生活","军事"};
        for(int k=0;k<names.length;k++) {
            UiObject2 beijing = waitForObj(By.text(names[getRandom1(k)]));
            verify("beijing not exists"+names[k], beijing != null);
            beijing.click();
            sleepInt(1);
            for(int i=0;i<2;i++) {
                phone.swipe(500, 880, 500, 210, 38);
                sleepInt(2);
                phone.click(500, 880);
                UiObject2 circle=waitForObj(By.res("com.cashtoutiao:id/circle_progress"));
                verify("adf",circle!=null);
                sleepInt(15*60);
                press_back(1);
                beijing = waitForObj(By.text(names[k]));
                verify("beijing not exists"+names[k], beijing != null);
            }
                }
            }


    public int getRandom(int k) {
        int i=80+(int)(Math.random()*(k-2));//获取1~(k-1)之间的随机整数
        return i;
    }
    public int getRandom1(int k) {
       int i=(int)(Math.random()*(k-1));//获取1~(k-1)之间的随机整数
/*        int max=k-1;
        int min=0;//要生成在[min,max]之间的随机整数
        Random random = new Random();
        int i = random.nextInt(max)%(max-min+1) + min;*/
        return i;
    }
    public int getRandomy(int k) {
        int i=(int)(Math.random()*(k));//获取0~(k)之间的随机整数
/*        int max=k-1;
        int min=0;//要生成在[min,max]之间的随机整数
        Random random = new Random();
        int i = random.nextInt(max)%(max-min+1) + min;*/
        return i;
    }
    public int getRandom2(int k) {
        int i=(int)(Math.random()*(k-1));//获取0~(k-1)之间的随机整数
        return i;
    }
    public static final UiWatcher importantWatcher = new UiWatcher() {
        public boolean checkForCondition() {

            UiObject2 upgradeWindows = phone.findObject(By.text(Pattern.compile(".*(要闻推送).*", Pattern.DOTALL)));
            if (upgradeWindows != null) {
                UiObject2 details=phone.findObject(By.text("查看详情"));
                details.click();
                for(int j=0;j<10;j++) {
                    phone.swipe(540, 1400, 540, 700, 1000);
                    phone.swipe(540, 1400, 540, 500, 500);
                    phone.swipe(540, 1200, 540, 800, 500);
                    UiObject2 stop=phone.findObject(By.desc("本文及配图均为趣头条自媒体用户上传，不代表平台观点。"));
                    if (stop!=null)break;
                }
                return true;
            }
            return false;
        }
    };

}
