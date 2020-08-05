package com.liuchuanzheng.base_module.config.inter;

import android.content.Context;

public interface IApp {

    Context applicationContext();

    String applicationId();

    int versionCode();

    String versionName();

    Boolean isDebug();

    String buildType();

    String baseUrl();
}
