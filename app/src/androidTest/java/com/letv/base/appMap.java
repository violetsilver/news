package com.letv.base;

import java.util.HashMap;
import java.util.Map;

import com.letv.cases.common.*;
import com.letv.base.AppName;
import com.letv.base.PkgName;
import com.letv.base.appMap;

/**
 * Created by shaoxue on 2016/6/22.
 */
public class appMap extends com.letv.cases.common.appMap{
    public Map<String ,String> cnApps(){
        Map<String,String> appMaps = new HashMap<>();
        appMaps.put(AppName.PHONE, PkgName.PHONE);
        appMaps.put(AppName.MESSAGE, PkgName.MESSAGE);
        appMaps.put(AppName.BROWSER, PkgName.BROWSER);
        appMaps.put(AppName.CAMERA, PkgName.CAMERA);
        appMaps.put(AppName.CLOCK, PkgName.CLOCK);
        appMaps.put(AppName.CALENDAR, PkgName.CALENDAR);
        appMaps.put(AppName.WEATHER, PkgName.WEATHER);
        appMaps.put(AppName.MUSIC, PkgName.MUSIC);
        appMaps.put(AppName.TVCONTROLLER, PkgName.TVCONTROLLER);
        appMaps.put(AppName.APPSTORE, PkgName.APPSTORE);
        appMaps.put(AppName.LETVVIDEO, PkgName.LETVVIDEO);
        appMaps.put(AppName.SETTING, PkgName.SETTING);
        appMaps.put(AppName.FILEMANAGER, PkgName.FILEMANAGER);
        appMaps.put(AppName.WALLPAPER, PkgName.WALLPAPER);
        appMaps.put(AppName.GALLERY, PkgName.GALLERY);
        appMaps.put(AppName.EMAIL, PkgName.EMAIL);
        appMaps.put(AppName.CONTACT, PkgName.CONTACT);
        appMaps.put(AppName.UPGRADE, PkgName.UPGRADE);
        appMaps.put(AppName.DOWNLOAD, PkgName.DOWNLOAD);
        appMaps.put(AppName.RECORDER, PkgName.RECORDER);
        appMaps.put(AppName.MAP, PkgName.MAP);
        appMaps.put(AppName.NOTE, PkgName.NOTE);
        appMaps.put(AppName.WPS, PkgName.WPS);
        appMaps.put(AppName.LESTORE, PkgName.LESTORE);
        appMaps.put(AppName.LEACCOUNT, PkgName.LEACCOUNT);
        appMaps.put(AppName.CALCULATOR, PkgName.CALCULATOR);
        appMaps.put(AppName.VIDEOPLAYER, PkgName.VIDEOPLAYER);
        appMaps.put(AppName.SIMCARD, PkgName.SIMCARD);
        appMaps.put(AppName.LESPORT, PkgName.LESPORT);
        appMaps.put(AppName.SUPERMANAGER, PkgName.SUPERMANAGER);
        appMaps.put(AppName.SUPERSEARCH, PkgName.SUPERSEARCH);
        appMaps.put(AppName.GAMECENTER, PkgName.GAMECENTER);
        appMaps.put(AppName.LEAUTO, PkgName.LEAUTO);
        appMaps.put(AppName.LEFANBBS, PkgName.LEFANBBS);
        appMaps.put(AppName.WEIBO, PkgName.WEIBO);
        appMaps.put(AppName.TODAYNEWS, PkgName.TodayNews);
        appMaps.put(AppName.WECHAT, PkgName.WeChat);
        appMaps.put(AppName.READER, PkgName.iReader);
        appMaps.put(AppName.FANNATION, PkgName.FANNATION);
        appMaps.put(AppName.EDAO, PkgName.EDAO);
        appMaps.put(AppName.LianMeng, PkgName.LianMeng);
        appMaps.put(AppName.Webchat, PkgName.Webchat);
        return appMaps;
    }
}