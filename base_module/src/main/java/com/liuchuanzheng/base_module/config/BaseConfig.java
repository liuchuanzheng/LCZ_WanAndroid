package com.liuchuanzheng.base_module.config;

/**
 * @author 刘传政
 * @date 2020/8/3 19:49
 * QQ:1052374416
 * 电话:18501231486
 * 作用:
 * 注意事项:
 */
public class BaseConfig {
    private static volatile BaseConfig instance;
    private  BaseConfigBean baseConfigBean;

    private BaseConfig() {
    }

    public static BaseConfig getInstance() {
        if (instance == null) {
            synchronized (BaseConfig.class) {

                if (instance == null) {
                    instance = new BaseConfig();
                }
            }
        }
        return instance;
    }
    public void init(BaseConfigBean baseConfigBean){
        this.baseConfigBean = baseConfigBean;
    }
    public  BaseConfigBean getBaseConfigBean() {
        //默认没初始化也返回一个默认的配置类
        if (baseConfigBean == null) {
            baseConfigBean = new BaseConfigBean();
        }
        return baseConfigBean;
    }

}
