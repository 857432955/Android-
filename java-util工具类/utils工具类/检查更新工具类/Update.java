package com.lost.temple.transportation.entity;

/**
 * Created by ZhangRuXing on 2017/5/27.
 */

public class Update {

private String version,versionCode,updateTime,apkName,downloadURL,displayMessage;

public Update() {
    }

public Update(String version, String versionCode, String updateTime, String apkName, String downloadURL, String displayMessage) {
this .version = version;
this .versionCode = versionCode;
this .updateTime = updateTime;
this .apkName = apkName;
this .downloadURL = downloadURL;
this .displayMessage = displayMessage;
    }

public String getVersion() {
return version;
    }

public void setVersion(String version) {
this .version = version;
    }

public String getVersionCode() {
return versionCode;
    }

public void setVersionCode(String versionCode) {
this .versionCode = versionCode;
    }

public String getUpdateTime() {
return updateTime;
    }

public void setUpdateTime(String updateTime) {
this .updateTime = updateTime;
    }

public String getApkName() {
return apkName;
    }

public void setApkName(String apkName) {
this .apkName = apkName;
    }

public String getDownloadURL() {
return downloadURL;
    }

public void setDownloadURL(String downloadURL) {
this .downloadURL = downloadURL;
    }

public String getDisplayMessage() {
return displayMessage;
    }

public void setDisplayMessage(String displayMessage) {
this .displayMessage = displayMessage;
    }
}
