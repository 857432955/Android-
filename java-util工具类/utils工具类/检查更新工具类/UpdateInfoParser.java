package com.lost.temple.transportation.entity;

import android.util.Xml;

import com.lost.temple.transportation.util.HttpUtil;

import org.xmlpull.v1.XmlPullParser;

import java.io.InputStream;
import java.lang.Exception;

/**
 * Created by ZhangRuxing on 2017-06-01.
 */

public class UpdateInfoParser {
     public static Update getUpdataInfo(InputStream is) throws Exception {
        XmlPullParser parser = Xml.newPullParser();
        parser.setInput(is, "utf-8" );
         int type = parser.getEventType();
        Update info = new Update();
         while (type != XmlPullParser.END_DOCUMENT) {
             switch (type) {
                 case XmlPullParser.START_TAG :
                     if ( "version" .equals(parser.getName())) {
                        info.setVersion(parser.nextText());
                    } else if ( "downloadURL" .equals(parser.getName())) {
                        info.setDownloadURL(HttpUtil.SERVER_URL + parser.nextText());
                    } else if ( "displayMessage" .equals(parser.getName())) {
                        info.setDisplayMessage(parser.nextText());
                    }
                     break ;
            }
            type = parser.next();
        }
         return info;
    }
}
