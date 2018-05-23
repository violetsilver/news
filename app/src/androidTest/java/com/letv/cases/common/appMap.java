package com.letv.cases.common;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by shaoxue on 2016/6/22.
 */
public class appMap {
    public Map<String ,String> osApps(){
        Map<String,String> appMapsOS = new HashMap<>();
        appMapsOS.put(AppName.YOUTUBE, PkgName.YOUTUBE);
        appMapsOS.put(AppName.GMAIL, PkgName.GMAIL);
        appMapsOS.put(AppName.CHROME, PkgName.CHROME);
        appMapsOS.put(AppName.PLAYMUSIC, PkgName.PLAYMUSIC);
        appMapsOS.put(AppName.PLAYMOVIE, PkgName.PLAYMOVIE);
        appMapsOS.put(AppName.GOOGLEPICTURE, PkgName.GOOGLEPICTURE);
        appMapsOS.put(AppName.GOOGLESETTING, PkgName.GOOGLESETTING);
        appMapsOS.put(AppName.GOOGLE, PkgName.GOOGLE);
        appMapsOS.put(AppName.GOOGLEDRIVE, PkgName.GOOGLEDRIVE);
        appMapsOS.put(AppName.VOICESEARCH, PkgName.VOICESEARCH);
        appMapsOS.put(AppName.GOOGLESTORE, PkgName.GOOGLESTORE);
        appMapsOS.put(AppName.GoogleMap, PkgName.GoogleMap);
        appMapsOS.put(AppName.Hangouts, PkgName.Hangouts);
        appMapsOS.put(AppName.WHATSAPP, PkgName.WhatsApp);
        appMapsOS.put(AppName.FACEBOOK, PkgName.Facebook);
        appMapsOS.put(AppName.FacebookMessenger, PkgName.FacebookMessenger);
        appMapsOS.put(AppName.TWITTER, PkgName.Twitter);
        appMapsOS.put(AppName.LEVIDI, PkgName.LEVIDI);
        appMapsOS.put(AppName.HOTSTAR, PkgName.HOTSTAR);
        appMapsOS.put(AppName.SHAREIT, PkgName.SHAREIT);
        appMapsOS.put(AppName.SKYPE, PkgName.SKYPE);
        appMapsOS.put(AppName.GOOGLECLOCK, PkgName.GOOGLECLOCK);
        appMapsOS.put(AppName.GOOGLECALENDAR, PkgName.GOOGLECALENDAR);
        appMapsOS.put(AppName.TVCONTROLLER, PkgName.TVCONTROLLER);
        return appMapsOS;
    }
}