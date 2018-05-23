package com.letv.base;

import android.os.SystemClock;
import android.support.test.InstrumentationRegistry;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject2;
import android.support.test.uiautomator.UiWatcher;
import android.support.test.uiautomator.Until;
import android.util.Log;

import com.letv.base.LetvTestCase;

import junit.framework.Assert;

import java.io.File;
import java.security.Permission;
import java.util.regex.Pattern;

import javax.xml.transform.OutputKeys;

/**
 * Created by letv on 2016/1/2.
 * Last edit by letv on 15:02 2016/1/2.
 */
public class LetvWatcher extends com.letv.cases.common.LetvWatcher {
    private static final String TAG = com.letv.cases.common.LetvWatcher.class.getSimpleName();
    private static final UiDevice phone = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
    public static final UiWatcher unicomWelcomWatcher = new UiWatcher() {
        public boolean checkForCondition() {
            UiObject2 uniformWindows = phone.findObject(By.text(Pattern.compile(".*欢迎您使用中国联通.*", Pattern.DOTALL)));
            if (uniformWindows != null) {
                UiObject2 ok = phone.findObject(By.clazz(
                        "android.widget.Button").text("确定"));
                if (ok != null) {
                    ok.clickAndWait(Until.newWindow(), 2000);
                }
                return true;
            } else {
                return false;
            }
        }
    };

    public static final UiWatcher anrWatcher = new UiWatcher() {
        public boolean checkForCondition() {
            UiObject2 anrWindows = phone.findObject(By.text(Pattern.compile(".*无响应.*|.*no respon.*|.*not respon.*", Pattern.DOTALL)));
            if (anrWindows != null) {
                LetvTestCase.screenShot();
                try {
                    UiObject2 ok = phone.findObject(By.text(Pattern.compile("确定|关闭应用")));
                    if (ok != null) {
                        ok.clickAndWait(Until.newWindow(), 2000);
                    }
                    SystemClock.sleep(500);
                    UiObject2 feedback = phone.findObject(By.text("在线反馈"));
                    if (feedback != null) {
                        for (int i = 0; i < 3; i++) {
                            UiObject2 cancel = phone.findObject(By.text(Pattern.compile("取消|.*Cancel.*")));
                            if (cancel != null) {
                                cancel.click();
                                break;
                            }
                        }
                    }
                } finally {
                    LetvTestCase.send_status(LetvTestCase.STATUS_ANR, "ANR",
                            "Detected ANR when running case");
                    Assert.fail("ANR happened, the case is stoped!");
                }
                return true;
            } else {
                return false;
            }
        }
    };

    public static final UiWatcher fcWatcher = new UiWatcher() {
        public boolean checkForCondition() {
            UiObject2 fcWindows = phone.findObject(By.text(Pattern.compile(".*已停止运行.*|.*has stop.*", Pattern.DOTALL)));
            if (fcWindows != null) {
                LetvTestCase.screenShot();
                try {
                    UiObject2 cancel = phone.findObject(By.text(Pattern.compile("取消|关闭应用")));
                    if (cancel != null) {
                        cancel.clickAndWait(Until.newWindow(), 2000);
                    }

                } finally {
                    LetvTestCase.send_status(LetvTestCase.STATUS_FC, "FC",
                            "Detected FC when running case");
                    Assert.fail("FC happened, the case is stoped!");
                }
                return true;
            } else {
                return false;
            }
        }
    };

    public static final UiWatcher termsWatcher = new UiWatcher() {
        public boolean checkForCondition() {
            UiObject2 termsWindows = phone.findObject(By.clazz("android.widget.TextView")
                    .text(Pattern.compile("申明和条款|Terms & Conditions|Statements and terms")));
            if (termsWindows != null) {
                UiObject2 agreeAndGo = phone.findObject(By.clazz(
                        "android.widget.Button").text(Pattern.compile("同意并继续|Agree and continue")));
                if (agreeAndGo != null) {
                    agreeAndGo.click();
                    SystemClock.sleep(500);
                }
                return true;
            } else {
                return false;
            }
        }
    };
    public static final UiWatcher knowWatcher = new UiWatcher() {
        public boolean checkForCondition() {
            UiObject2 termsWindows = phone.findObject(By.clazz("android.widget.TextView")
                    .text(Pattern.compile("同步兼容性问题|Sync compatibility issue")));
            if (termsWindows != null) {
                UiObject2 agreeAndGo = phone.findObject(By.clazz(
                        "android.widget.Button").text(Pattern.compile("我知道了|Got it")));
                if (agreeAndGo != null) agreeAndGo.clickAndWait(Until.newWindow(), 2000);
                return true;
            } else {
                return false;
            }
        }
    };

    /**
     * 百度地图
     **/
    public static final UiWatcher confirmWatcher = new UiWatcher() {
        public boolean checkForCondition() {
            UiObject2 tipWindow = phone.findObject(By
                    .text(Pattern.compile("系统提示|连接问题")));
            if (tipWindow != null) {
                UiObject2 ok = phone.findObject(By.text(Pattern.compile("确定|OK")));
                if (ok != null) {
                    ok.clickAndWait(Until.newWindow(), 2000);
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }
    };

    public static final UiWatcher MapWatcher = new UiWatcher() {
        public boolean checkForCondition() {
            UiObject2 termsWindows = phone.findObject(By
                    .textContains("在位置设置中打开GPS"));
            if (termsWindows != null) {
                UiObject2 noDisplay = phone.findObject(By.text("不再显示此内容").checked(false));
                if (noDisplay != null) noDisplay.click();
                UiObject2 cancelButton = phone.findObject(By.text("取消"));
                cancelButton.click();
                return true;
            } else {
                return false;
            }
        }
    };



    /**
     * 系统升级
     **/
    public static final UiWatcher systemUpgradeWatcher = new UiWatcher() {
        public boolean checkForCondition() {

            UiObject2 upgradeWindows = phone.findObject(By.text(Pattern.compile(".*系统更新提示.*")));
            if (upgradeWindows != null) {
                UiObject2 option = phone.findObject(By
                        .text(Pattern.compile(".*一小时后提醒我.*")));
                if (option != null) {
                    option.click();
                    return true;
                } else {
                    return false;
                }
            }
            return false;
        }
    };


    public static final UiWatcher syncWatcher = new UiWatcher() {
        public boolean checkForCondition() {
            UiObject2 letvCountWindows = phone.findObject(By.text(Pattern.compile(".*开启同步.*", Pattern.DOTALL)));
            if (letvCountWindows != null) {
                UiObject2 notNow = phone.findObject(By.text("暂不"));
                if (notNow != null) {
                    notNow.clickAndWait(Until.newWindow(), 2000);
                    return true;
                } else {
                    return false;
                }
            }
            return false;
        }
    };

    public static final UiWatcher contactSaveWatcher = new UiWatcher() {
        public boolean checkForCondition() {
            UiObject2 tip = phone.findObject(By.text(Pattern.compile(".*联系人存储账号.*|.*Contact storage account.*")));
            if (tip != null) {
                UiObject2 notSync = phone.findObject(By.text(Pattern.compile(".*不同步.*|.*not synced.*")));
                if (notSync != null) {
                    notSync.clickAndWait(Until.newWindow(), 2000);
                    return true;
                } else {
                    return false;
                }
            }
            return false;
        }
    };

    //取消更新应用
    public static final UiWatcher upgradeWatcher = new UiWatcher() {
        public boolean checkForCondition() {
            UiObject2 LeSportsUpgrade = phone.findObject(By
                    .text(Pattern.compile(".*升级|Upgrade")));
            if (LeSportsUpgrade != null) {
                UiObject2 LeAppcancel = phone.findObject(By.text(Pattern.compile("暂不升级|忽略|以后再说|取消")));
                LeAppcancel.clickAndWait(Until.newWindow(), 30000);
                for (int i = 0; i < 300; i++) {  //升级结束后自动返回桌面
                    UiObject2 install = phone.findObject(By.text(Pattern.compile("安装")));
                    if (install!=null) {
                        break;
                    } else {
                        SystemClock.sleep(1000);
                    }
                }
                UiObject2 install = phone.findObject(By.text(Pattern.compile("Install|安装|升级")));
                if (install != null) {
                    install.click();
                    SystemClock.sleep(10000);
                    UiObject2 open = phone.findObject(By.text(Pattern.compile("Open|打开")));
                    if (open != null) {
                        open.click();
                        SystemClock.sleep(5000);
                    }
                }
                return true;
            } else {
                return false;
            }
        }
    };

    //关闭开通会员提示
    public static final UiWatcher SeelaterWatcher = new UiWatcher() {
        public boolean checkForCondition() {
            UiObject2 close = phone.findObject(By.text(Pattern.compile("稍后再看|忽略|等等再看|立马参与")));
            if (close != null) {
                close.click();
                SystemClock.sleep(2000);
                return true;
            } else {
                return false;
            }
        }
    };

    //领取金币
    public static final UiWatcher JinbiWatcher = new UiWatcher() {
        public boolean checkForCondition() {
            UiObject2 close = phone.findObject(By.text("领金币"));
            if (close != null) {
                close.click();
                SystemClock.sleep(2000);
                UiObject2 gotit = phone.findObject(By.text("知道了"));
                if (gotit != null) gotit.click();
                SystemClock.sleep(4000);
                for(int k=0;k<20;k++) {
                    UiObject2 wait = phone.findObject(By.text(Pattern.compile("距离下个宝藏出现还有.*")));
                    if (wait != null) {
                        for (int j = 0; j < 62; j++) {
                            UiObject2 next = phone.findObject(By.text("下个宝藏已经出现"));
                            if (next != null) {
                                UiObject2 read = phone.findObject(By.text("继续阅读"));
                                read.click();
                                SystemClock.sleep(4000);
                                phone.swipe((int) (phone.getDisplayWidth() * 0.5), (int) (phone.getDisplayHeight() * 0.2), (int) (phone.getDisplayWidth() * 0.5), (int) (phone.getDisplayHeight() * 0.8), 10);
                                SystemClock.sleep(4000);
                                close = phone.findObject(By.text("领金币"));
                                if (close != null) {
                                    close.click();
                                    SystemClock.sleep(2000);
                                    break;
                                }
                            }
                            SystemClock.sleep(2000);
                        }
                    }
                }
                return true;
            } else {
                return false;
            }
        }
    };
    public void baozang(){
        UiObject2 close = phone.findObject(By.text("领金币"));
        if(close!=null) {
            close.click();
            SystemClock.sleep(4000);
            UiObject2 gotit = phone.findObject(By.text("知道了"));
            if (gotit != null) gotit.click();
            SystemClock.sleep(4000);
            UiObject2 wait = phone.findObject(By.text(Pattern.compile("距离下个宝藏出现还有36秒")));
            if (wait != null) {
                for (int j = 0; j < 65; j++) {
                    UiObject2 next = phone.findObject(By.text("下个宝藏已经出现"));
                    if (next != null) {
                        UiObject2 read = phone.findObject(By.text("继续阅读"));
                        read.click();
                        SystemClock.sleep(4000);
                        phone.swipe((int) (phone.getDisplayWidth() * 0.5), (int) (phone.getDisplayHeight() * 0.2), (int) (phone.getDisplayWidth() * 0.5), (int) (phone.getDisplayHeight() * 0.8), 10);
                        SystemClock.sleep(4000);
                    }
                    SystemClock.sleep(2000);
                }
            }
        }
    }
    public static final UiWatcher LeEcoAccountWatcher = new UiWatcher() {
        public boolean checkForCondition() {
            UiObject2 Leaccount = phone.findObject(By
                    .text(Pattern.compile(".*登录乐视账号.*|.*Log in LeEco account before use.*|.*登录乐视帐号.*")));
            if (Leaccount != null) {
                UiObject2 cancel = phone.findObject(By.text(Pattern.compile("取消|Cancel")));
                if (cancel != null) {
                    cancel.clickAndWait(Until.newWindow(), 2000);
                }
                return true;
            } else {
                return false;
            }
        }
    };
    //更新乐视体育app
    public static final UiWatcher LeSportsUpgradeWatcher = new UiWatcher() {
        public boolean checkForCondition() {
            UiObject2 LeSportsUpgrade = phone.findObject(By
                    .text(Pattern.compile("升级|Upgrade")).pkg("com.lesports.glivesports"));
            if (LeSportsUpgrade != null) {
                LeSportsUpgrade.click();
                for (int i = 0; i < 300; i++) {  //升级结束后自动返回桌面
                    if (phone.getCurrentPackageName().equals("com.android.launcher3")) {
                        break;
                    } else {
                        SystemClock.sleep(1000);
                    }
                }
                UiObject2 install = phone.findObject(By.text(Pattern.compile("Install|安装|升级")));
                if (install != null) {
                    install.click();
                    SystemClock.sleep(10000);
                    UiObject2 open = phone.findObject(By.text(Pattern.compile("Open|打开")));
                    if (open != null) {
                        open.click();
                        SystemClock.sleep(5000);
                    }
                }
                return true;
            } else {
                return false;
            }
        }
    };

    //关闭开通会员提示
    public static final UiWatcher LeSportsVipWatcher = new UiWatcher() {
        public boolean checkForCondition() {
            UiObject2 close = phone.findObject(By
                    .res("com.lesports.glivesports:id/iv_vip_activity_close"));
            if (close != null) {
                close.click();
                SystemClock.sleep(2000);
                return true;
            } else {
                return false;
            }
        }
    };

    //滑动界面跳过初始化
    public static final UiWatcher LeSportsInitWatcher = new UiWatcher() {
        public boolean checkForCondition() {
            UiObject2 recycleView = phone.findObject(By
                    .res("com.lesports.glivesports:id/launcher_recyclerview"));
            if (recycleView != null) {
                UiObject2 start = null;
                for (int i = 0; i <= 6; i++) {
                    start = phone.findObject(By.res("com.lesports.glivesports:id/launcher_btn"));
                    if (start == null) {
                        phone.swipe((int) (phone.getDisplayWidth() * 0.98), (int) (phone.getDisplayHeight() * 0.5),
                                (int) (phone.getDisplayWidth() * 0.1), (int) (phone.getDisplayHeight() * 0.5), 25);
                        SystemClock.sleep(1000);
                    } else {
                        break;
                    }
                }
                start.click();
                SystemClock.sleep(3000);
                return true;
            } else {
                return false;
            }
        }
    };

    //关闭 选择你感兴趣的球队 提示
    public static final UiWatcher LeSportsInterestWatcher = new UiWatcher() {
        public boolean checkForCondition() {
            UiObject2 title = phone.findObject(By
                    .text(Pattern.compile(".*选择你感兴趣的球队.*|.*Select interesting team.*")));
            if (title != null) {
                UiObject2 done = phone.findObject(By.text(Pattern.compile("完成|Done")));
                if (done != null) {
                    done.click();
                    SystemClock.sleep(2000);
                }
                return true;
            } else {
                return false;
            }
        }
    };

    public static final UiWatcher LetvUpgradeWatcher = new UiWatcher() {
        public boolean checkForCondition() {
            UiObject2 LetvUpgrade = phone.findObject(By
                    .text(Pattern.compile("立即升级|立即更新|升级|安装|Install|install|立即升級|安装")).pkg(Pattern.compile("com.letv.android.client|com.letv.lesophoneclient")));
            if (LetvUpgrade != null) {
                LetvUpgrade.click();
                SystemClock.sleep(20000);
                UiObject2 install = phone.findObject(By.text("安装|Install"));
                if (install != null) {
                    install.click();
                    SystemClock.sleep(20000);
                    UiObject2 open = phone.findObject(By.text("打开|Open"));
                    if (open != null) {
                        open.click();
                        SystemClock.sleep(3000);
                    }
                }
                return true;
            } else {
                return false;
            }
        }
    };

    public static final UiWatcher LetvLoginWatcher = new UiWatcher() {
        public boolean checkForCondition() {
            UiObject2 noLogin = phone.findObject(By
                    .text(Pattern.compile("只想做个过客")).clazz("android.widget.TextView"));
            if (noLogin != null) {
                noLogin.click();
                SystemClock.sleep(1000);
                return true;
            } else {
                return false;
            }
        }
    };

    public static final UiWatcher LesoUpgradeWatcher = new UiWatcher() {
        public boolean checkForCondition() {
            UiObject2 LesoUpgradeView = phone.findObject(By
                    .res(Pattern.compile("com.letv.lesophoneclient:id/leso_bg")));
            if (LesoUpgradeView != null) {
                LesoUpgradeView.clickAndWait(Until.newWindow(), 3000);
            }
            UiObject2 LesoUpgrade = phone.findObject(By
                    .text(Pattern.compile("立即安装|立即更新|安装|Install|install")).res(Pattern.compile("com.letv.lesophoneclient:id/upgrade_confirm")));
            if (LesoUpgrade != null) {
                LesoUpgrade.click();
                SystemClock.sleep(5000);
                UiObject2 install = phone.findObject(By.text(Pattern.compile("Install|安装")));
                //UiObject2 install = phone.findObject(By.res("com.android.packageinstaller:id/ok_button"));
                if (install != null) {
                    install.click();
                    SystemClock.sleep(20000);
                    UiObject2 cancel = phone.findObject(By.text(Pattern.compile("取消|CANCEL")));  //该应用与已安装应用不兼容,取消安装
                    if (cancel != null) {
                        cancel.click();
                        SystemClock.sleep(1000);
                    }
                    UiObject2 open = phone.findObject(By.text(Pattern.compile("Open|打开|Done|完成")));
                    if (open != null) {
                        open.click();
                        SystemClock.sleep(5000);
                    }
                    UiObject2 leso = phone.findObject(By.res(Pattern.compile("com.letv.android.client:id/main_top_nav_search_home|com.letv.android.client:id/leso_icon|com.letv.android.client:id/searchContent|com.letv.android.client:id/fl_leso_logo_view")));
                    if (leso != null) {
                        leso.click();
                        SystemClock.sleep(5000);
                    }
                }
                return true;
            } else {
                return false;
            }
        }
    };
    public static final UiWatcher LeTvSytemUpgradeWatcher = new UiWatcher() {
        public boolean checkForCondition() {
            UiObject2 LeTvSytemUpgrade = phone.findObject(By
                    .text("继续使用").res("com.letv.android.client:id/update_keep_use"));
            if (LeTvSytemUpgrade != null) {
                LeTvSytemUpgrade.clickAndWait(Until.newWindow(), 2000);
                return true;
            } else {
                return false;
            }
        }
    };

    public static final UiWatcher LeshopUpgradeWatcher = new UiWatcher() {
        public boolean checkForCondition() {
            UiObject2 LeshopUpgrade = phone.findObject(By
                    .text(Pattern.compile("立即更新")).pkg(Pattern.compile("com.letv.letvshop")));
            if (LeshopUpgrade != null) {
                LeshopUpgrade.clickAndWait(Until.newWindow(), 10000);
                SystemClock.sleep(10000);
                UiObject2 install = phone.findObject(By.text(Pattern.compile("Install|安装")).res("com.android.packageinstaller:id/ok_button"));
                if (install != null) {
                    install.clickAndWait(Until.newWindow(), 10000);
                    SystemClock.sleep(10000);
                    UiObject2 open = phone.findObject(By.text(Pattern.compile("Open|打开")));
                    if (open != null) {
                        open.clickAndWait(Until.newWindow(), 2000);
                        SystemClock.sleep(500);
                    }
                }
                return true;
            } else {
                return false;
            }
        }
    };

    public static final UiWatcher AppstoreUpgradeWatcher = new UiWatcher() {
        public boolean checkForCondition() {
            UiObject2 AppstoreUpgrade = phone.findObject(By
                    .text(Pattern.compile(".*后台升级.*")).pkg("com.letv.app.appstore"));
            if (AppstoreUpgrade != null) {
                AppstoreUpgrade.click();
                for (int i = 0; i < 120; i++) {  //升级结束后自动返回桌面并重启
                    if (phone.getCurrentPackageName().equals("com.android.launcher3")) {
                        break;
                    } else {
                        SystemClock.sleep(1000);
                    }
                }
                SystemClock.sleep(3000);
                return true;
            } else {
                return false;
            }
        }
    };

    public static final UiWatcher tipWatcher = new UiWatcher() {
        public boolean checkForCondition() {
            UiObject2 tip = phone.findObject(By
                    .text(Pattern.compile("不再提示|Do not show again")));
            if (tip != null) {
                UiObject2 allow = phone.findObject(By.text(Pattern.compile("允许|Allow")));
                if (allow != null) allow.click();
                SystemClock.sleep(500);
                return true;
            } else {
                return false;
            }
        }
    };

    public static final UiWatcher GradeWatcher = new UiWatcher() {
        public boolean checkForCondition() {
            UiObject2 tip = phone.findObject(By
                    .text(Pattern.compile("给我们评分")).pkg("com.sina.weibo"));
            if (tip != null) {
                UiObject2 no = phone.findObject(By.text(Pattern.compile("不了，谢谢")));
                if (no != null) no.clickAndWait(Until.newWindow(), 2000);
                return true;
            } else {
                return false;
            }
        }
    };

    public static final UiWatcher MapUpdateWatcher = new UiWatcher() {
        public boolean checkForCondition() {
            UiObject2 updateTip = phone.findObject(By
                    .text(Pattern.compile("版本更新提示|直接下载")).pkg("com.autonavi.minimap"));
            if (updateTip != null) {
                UiObject2 no = phone.findObject(By.text(Pattern.compile("以后再说")));
                UiObject2 cancel = phone.findObject(By.res("com.autonavi.minimap:id/cancel_update"));
                if (no != null) {
                    no.clickAndWait(Until.newWindow(), 2000);
                    SystemClock.sleep(200);
                } else if (cancel != null) {
                    cancel.clickAndWait(Until.newWindow(), 2000);
                    SystemClock.sleep(200);
                } else {
                    return false;
                }
                return true;
            } else {
                return false;
            }
        }
    };

    public static final UiWatcher OpenAPMWatcher = new UiWatcher() {
        public boolean checkForCondition() {
            UiObject2 OpenAPMTip = phone.findObject(By
                    .text(Pattern.compile("启用飞行模式|Turn on Airplane mode")).pkg("com.android.settings"));
            if (OpenAPMTip != null) {
                UiObject2 OK = phone.findObject(By.text(Pattern.compile("确定|OK")));
                if (OK != null) {
                    OK.clickAndWait(Until.newWindow(), 2000);
                    SystemClock.sleep(200);
                }
                return true;
            } else {
                return false;
            }
        }
    };
    public static final UiWatcher PermissionWatcher = new UiWatcher() {
        public boolean checkForCondition() {
            UiObject2 PermissionTip = phone.findObject(By
                    .text(Pattern.compile(".*Allow.*to.*|权限申请.*|.*允许.*|Permission application.*")));
            if (PermissionTip != null) {
                UiObject2 Allow = phone.findObject(By.text(Pattern.compile("允许|Allow")));
                for (int i = 0; i <= 10; i++) {
                    if (Allow != null) {
                        Allow.clickAndWait(Until.newWindow(), 2000);
                        SystemClock.sleep(500);
                    }
                }
                return true;
            } else {
                return false;
            }
        }
    };

    public static final UiWatcher RequestPermissionWatcher = new UiWatcher() {
        public boolean checkForCondition() {
            UiObject2 RequestPermissionTip = phone.findObject(By
                    .text(Pattern.compile(".*请求.*权限.*|.*request.*permission.*")));
            if (RequestPermissionTip != null) {
                UiObject2 settings = phone.findObject(By.text(Pattern.compile("去设置|Settings|Go to Settings")));
                if (settings != null) {
                    settings.clickAndWait(Until.newWindow(), 2000);
                    SystemClock.sleep(200);
                    UiObject2 Permissions = phone.findObject(By.text(Pattern.compile("权限|Permissions")));
                    Permissions.clickAndWait(Until.newWindow(), 2000);
                    SystemClock.sleep(200);
                    UiObject2 PermissionsList = phone.findObject(By.res(Pattern.compile("android:id/list")));
                    int count = 0;
                    count = PermissionsList.getChildCount();
                    for (int i = 0; i <= count; i++) {
                        UiObject2 switchBtn = PermissionsList.findObject(By.res("android:id/switchWidget").checked(false));
                        if (switchBtn != null) {
                            switchBtn.clickAndWait(Until.newWindow(), 2000);
                            SystemClock.sleep(100);
                        }
                    }
                    phone.pressBack();
                    SystemClock.sleep(200);
                    phone.pressBack();
                    SystemClock.sleep(200);
                }

                return true;
            } else {
                return false;
            }
        }
    };
    public static final UiWatcher LeCloudWatcher = new UiWatcher() {
        public boolean checkForCondition() {
            UiObject2 LeCloudServers = phone.findObject(By
                    .text(Pattern.compile(".*LeCloud services.*|.*Enable sync now.*")));
            if (LeCloudServers != null) {
                UiObject2 cancel = phone.findObject(By.text(Pattern.compile(".*Cancel.*|.*cancel.*")));
                if (cancel != null) {
                    cancel.clickAndWait(Until.newWindow(), 2000);
                    SystemClock.sleep(200);
                }
                return true;
            } else {
                return false;
            }
        }
    };

    public static final UiWatcher guideWatcher = new UiWatcher() {
        public boolean checkForCondition() {
            UiObject2 guide = phone.findObject(By
                    .text(Pattern.compile(".*定制精彩生活.*")));
            if (guide != null) {
                UiObject2 checkMore = phone.findObject(By.text(Pattern.compile("查看更多|View more")));
                if (checkMore != null) {
                    checkMore.click();
                    SystemClock.sleep(3000);
                    phone.pressBack();
                    SystemClock.sleep(1000);
                }
                return true;
            } else {
                return false;
            }
        }
    };

    public static final UiWatcher editNoteWatcher = new UiWatcher() {
        public boolean checkForCondition() {
            UiObject2 note = phone.findObject(By
                    .text(Pattern.compile(".*放弃编辑.*")));
            if (note != null) {
                UiObject2 cancel = phone.findObject(By.text(Pattern.compile("放弃")));
                if (cancel != null) {
                    cancel.click();
                    SystemClock.sleep(1000);
                }
                return true;
            } else {
                return false;
            }
        }
    };

    public static final UiWatcher msgDiscardWatcher = new UiWatcher() {
        public boolean checkForCondition() {
            UiObject2 msgDiscard = phone.findObject(By
                    .text(Pattern.compile(".*discarded.*|.*被舍弃.*")));
            if (msgDiscard != null) {
                UiObject2 OK = phone.findObject(By.text(Pattern.compile("OK|确定")));
                if (OK != null) {
                    OK.clickAndWait(Until.newWindow(), 2000);
                    SystemClock.sleep(200);
                }
                return true;
            } else {
                return false;
            }
        }
    };
    //规避music界面CDLA无损音乐包免费领取...
    public static final UiWatcher tipCDLA = new UiWatcher() {
        public boolean checkForCondition() {
            UiObject2 tipWindow = phone.findObject(By
                    .text(Pattern.compile(".*免费领取.*")));
            if (tipWindow != null) {
                UiObject2 cancle = phone.findObject(By.text(Pattern.compile("取消|Cancel")));
                if (cancle != null) {
                    cancle.click();
                    SystemClock.sleep(500);
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }
    };

    //ignore weibo update tip
    public static final UiWatcher weiboUpdateWatcher = new UiWatcher() {
        public boolean checkForCondition() {
            UiObject2 updateTip = phone.findObject(By
                    .text(Pattern.compile("Update later|以后再说")).pkg("com.sina.weibo"));
            if (updateTip != null) {
                updateTip.click();
                return true;
            } else {
                return false;
            }
        }
    };

    //ignore weibo news  tip
    public static final UiWatcher weiboNewsTip = new UiWatcher() {
        public boolean checkForCondition() {
            UiObject2 Ignore = phone.findObject(By
                    .text(Pattern.compile("Ignore|忽略|进入微博|去开启")).pkg("com.sina.weibo"));
            if (Ignore != null) {
                Ignore.click();
                return true;
            } else {
                return false;
            }
        }
    };

    //ignore weibo login  tip
    public static final UiWatcher weiboLoginTip = new UiWatcher() {
        public boolean checkForCondition() {
            UiObject2 loginTip = phone.findObject(By
                    .text(Pattern.compile("注册登录后可发布微博")).pkg("com.sina.weibo"));
            UiObject2 cancel = phone.findObject(By.res(Pattern.compile("com.sina.weibo:id/iv_cancel")));
            UiObject2 navigater = phone.findObject(By.res(Pattern.compile("com.sina.weibo:id/iv_navigater_clickable")));
            if (cancel != null && loginTip != null) {
                cancel.click();
                return true;
            } else if (navigater != null) {
                navigater.click();
                return true;
            } else {
                return false;
            }
        }
    };


    //igore skip and go to fistpage
    public static final UiWatcher skipNavigationWatcher = new UiWatcher() {
        public boolean checkForCondition() {
            UiObject2 leMe = phone.findObject(By.pkg(Pattern.compile("com.letv.bbs|com.android.gallery3d")));
            if (leMe != null) {
                UiObject2 skip = phone.findObject(By.text(Pattern.compile("跳过|skip")));
                if (skip != null) {
                    skip.click();
                    SystemClock.sleep(2000);
                }
                UiObject2 gotoFistpage = phone.findObject(By.text("进入首页"));
                if (gotoFistpage != null) {
                    gotoFistpage.click();
                    SystemClock.sleep(2000);
                }
                return true;
            } else {
                return false;
            }
        }
    };

    //weather:choose postion
    public static final UiWatcher positioningTipWatcher = new UiWatcher() {
        public boolean checkForCondition() {
            UiObject2 positioningTip = phone.findObject(By
                    .text(Pattern.compile(".*定位提示.*|.*Locating reminder.*")));
            if (positioningTip != null) {
                UiObject2 ok = phone.findObject(By.text(Pattern.compile("确定|Confirm")));
                if (ok != null) {
                    ok.click();
                    SystemClock.sleep(5000);
                }
                return true;
            } else {
                return false;
            }
        }
    };

    //weather or weibo: Special tips
    public static final UiWatcher specialTipWatcher = new UiWatcher() {
        public boolean checkForCondition() {
            UiObject2 tipWindow = phone.findObject(By
                    .text(Pattern.compile("Special Tips|特别提示")));
            if (tipWindow != null) {
                UiObject2 ok = phone.findObject(By.text(Pattern.compile("Yes|确定")));
                if (ok != null) {
                    ok.click();
                    SystemClock.sleep(2000);
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }
    };

    //Gallery:skip Experience immediately
    public static final UiWatcher experienceTipWatcher = new UiWatcher() {
        public boolean checkForCondition() {
            UiObject2 experienceTipWatcher = phone.findObject(By
                    .text(Pattern.compile(".*立即体验.*|.*Start now.*")));
            if (experienceTipWatcher != null) {
                UiObject2 cancle = phone.findObject(By.text(Pattern.compile("取消|Cancel")));
                if (cancle != null) {
                    cancle.clickAndWait(Until.newWindow(), 2000);
                    SystemClock.sleep(3000);
                }
                return true;
            } else {
                return false;
            }
        }
    };

    //Gallery:Login in leEco account and sync photo----Chose not now
    public static final UiWatcher syncTipWatcher = new UiWatcher() {
        public boolean checkForCondition() {
            UiObject2 syncTipWatcher = phone.findObject(By
                    .text(Pattern.compile(".*Log in and enable.*|.*登录并开启.*")));
            if (syncTipWatcher != null) {
                UiObject2 cancle = phone.findObject(By.text(Pattern.compile("Not now|暂不")));
                if (cancle != null) {
                    cancle.clickAndWait(Until.newWindow(), 2000);
                    SystemClock.sleep(3000);
                }
                return true;
            } else {
                return false;
            }
        }
    };

    //Dial:chose keyboard
    public static final UiWatcher choseKeyboardWatcher = new UiWatcher() {
        public boolean checkForCondition() {
            UiObject2 choseKeyboard = phone.findObject(By
                    .text(Pattern.compile(".*Please select your preferred keyboard*|.*请选择常用的键盘形式.*")));
            if (choseKeyboard != null) {
                UiObject2 ok = phone.findObject(By.text(Pattern.compile("确定|OK")));
                if (ok != null) {
                    ok.clickAndWait(Until.newWindow(), 2000);
                    SystemClock.sleep(500);
                }
                return true;
            } else {
                return false;
            }
        }
    };
    //Note:enable Storage permission
    public static final UiWatcher enableStorageWatcher = new UiWatcher() {
        public boolean checkForCondition() {
            UiObject2 tip = phone.findObject(By
                    .text(Pattern.compile(".*requesting the Storage permission*|.*请求存储空间权限.*")));
            if (tip != null) {
                UiObject2 settings = phone.findObject(By.text(Pattern.compile("Go to Settings|去设置")));
                if (settings != null) {
                    settings.clickAndWait(Until.newWindow(), 2000);
                    SystemClock.sleep(500);
                    UiObject2 permissions = phone.findObject(By.text(Pattern.compile("Permissions|权限")));
                    if (permissions != null) {
                        permissions.click();
                    }
                    UiObject2 storageSwitch = phone.findObject(By.text(Pattern.compile("存储空间|Storage"))).getParent().getParent().getChildren().get(1).getChildren().get(0);
                    if (storageSwitch.isChecked()) {
                        storageSwitch.click();
                    }
                    SystemClock.sleep(1000);
                    phone.pressBack();
                    phone.pressBack();
                }
                return true;
            } else {
                return false;
            }
        }
    };

    /**
     * 微信
     **/
    public static final UiWatcher weChattipWatcher = new UiWatcher() {
        public boolean checkForCondition() {
            UiObject2 tipWindow = phone.findObject(By
                    .text(Pattern.compile(".*Record daily highlights.*|.*记录生活.*")));
            if (tipWindow != null) {
                UiObject2 ok = phone.findObject(By.text(Pattern.compile(".*OK.*|.*我知道了.*")));
                if (ok != null) {
                    ok.clickAndWait(Until.newWindow(), 2000);
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }
    };
    //update Lelive
    public static UiWatcher leLiveupdateWatcher = new UiWatcher() {
        public boolean checkForCondition() {
            UiObject2 tipWindow = phone.findObject(By
                    .text(Pattern.compile("暂不升级")));
            if (tipWindow != null) {
                tipWindow.click();
                SystemClock.sleep(30000);
                UiObject2 live = phone.findObject(By.res(Pattern.compile("com.android.launcher3:id/live_layout|com.android.launcher3:id/live")));
                if (live != null) {
                    live.click();
                    SystemClock.sleep(2000);
                }
                return true;
            } else {
                return false;
            }
        }
    };

    //当前使用的系统版本较低，导致Live无法正常播放，建议更新到最新系统--->忽略
    public static UiWatcher LiveIgnoreSystemUpdateWatcher = new UiWatcher() {
        public boolean checkForCondition() {
            UiObject2 tipWindow = phone.findObject(By
                    .text(Pattern.compile(".*系统版本较低.*")));
            if (tipWindow != null) {
                UiObject2 ignore = phone.findObject(By.text(Pattern.compile(".*继续使用.*")));
                if (ignore != null) {
                    ignore.click();
                    SystemClock.sleep(2000);
                }
                return true;
            } else {
                return false;
            }
        }
    };
    //Browser:Location  permissions
    public static UiWatcher locationAllowWatcher = new UiWatcher() {
        public boolean checkForCondition() {
            UiObject2 permission = phone.findObject(By.text("Location permissions"));
            UiObject2 synced = phone.findObject(By.textContains("Browser can be synced"));
            if (permission != null) {
                UiObject2 allow = phone.findObject(By.text("Allow"));
                if (allow != null) {
                    allow.click();
                    return true;
                } else {
                    return false;
                }
            } else if (synced != null) {
                UiObject2 cancel = phone.findObject(By.text("Cancel"));
                if (cancel != null) {
                    cancel.click();
                    return true;
                } else {
                    return false;
                }
            }
            return false;
        }
    };

    //Message:Network  permissions
    public static UiWatcher networkAllowWatcher = new UiWatcher() {
        public boolean checkForCondition() {
            UiObject2 tipWindow = phone.findObject(By
                    .text(Pattern.compile(".*免费网络信息.*|.*network messaging.*")));
            if (tipWindow != null) {
                UiObject2 cancel = phone.findObject(By.res(Pattern.compile("Cancel|取消")));
                if (cancel != null) {
                    cancel.click();
                    SystemClock.sleep(2000);
                }
                return true;
            } else {
                return false;
            }
        }
    };

    //RemoteControl:ignore update
    public static UiWatcher remoteControlUpdateWatcher = new UiWatcher() {
        public boolean checkForCondition() {
            UiObject2 updateTip = phone.findObject(By
                    .text(Pattern.compile(".*版本更新.*|.*Update.*")).pkg("com.letv.android.remotecontrol"));
            if (updateTip != null) {
                UiObject2 sure = phone.findObject(By.text(Pattern.compile("确定|OK")));
                if (sure != null) {
                    sure.clickAndWait(Until.newWindow(), 2000);
                    SystemClock.sleep(1000);
                    UiObject2 install = phone.findObject(By.text(Pattern.compile("安装|Install")));
                    if (install != null) {
                        install.clickAndWait(Until.newWindow(), 2000);
                        SystemClock.sleep(20000);
                        UiObject2 open = phone.findObject(By.text(Pattern.compile("OPEN|Open|打开")));
                        if (open != null) {
                            open.click();
                            SystemClock.sleep(8000);
                        }
                    }
                }
                return true;
            } else {
                return false;
            }
        }
    };

    //Canera:open Bright mode
    public static UiWatcher openBrightModeWatcher = new UiWatcher() {
        public boolean checkForCondition() {
            UiObject2 open = phone.findObject(By
                    .res(Pattern.compile("com.android.camera2:id/start_brightness")));
            if (open != null) {
                open.click();
                SystemClock.sleep(2000);
                return true;
            } else {
                return false;
            }
        }
    };
}