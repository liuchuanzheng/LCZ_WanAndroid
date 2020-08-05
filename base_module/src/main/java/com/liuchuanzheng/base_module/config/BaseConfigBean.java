package com.liuchuanzheng.base_module.config;

import android.content.Context;

import com.liuchuanzheng.tools_module.ApplicationUtil;

/**
 * 默认的配置类
 * 配置整个base module的一些特性.采用builder模式创建
 * 所有属性都有默认值,所以使用者可以根据自己的需要选择性的改变一些属性
 */
public class BaseConfigBean{
    private String logTag;
    private Context applicationContext;

    public BaseConfigBean() {
        this(new Builder());
    }

    public String getLogTag() {
        return logTag;
    }

    public BaseConfigBean(Builder builder) {
       logTag = builder.logTag;
    }

    public static class Builder {
        String logTag = "base";
        Context applicationContext = ApplicationUtil.getCurApplication().getApplicationContext();

        public Builder() {
        }

        public Builder setLogTag(String logTag) {
            this.logTag = logTag;
            return this;
        }
        public Builder setApplicationContext(Context applicationContext) {
            this.applicationContext = applicationContext;
            return this;
        }

        public BaseConfigBean build() {
            return new BaseConfigBean(this);
        }
    }
}