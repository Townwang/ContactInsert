package com.townwang.contactinsertdemo.permission;

import android.content.Context;

import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;

import java.util.List;

/**
 * @author Town
 * @created at 2018/3/31 10:28
 * @Last Modified by: Town
 * @Last Modified time: 2018/3/31 10:28
 * @Remarks 请求权限封装
 */

public class RequsetPer {
    //成功弹出框
    private DefaultRationale mRationale;
    //失败弹出框
    private PermissionSetting permissionSetting;

    /**
     * 构造方法
     */
    RequsetPer() {
    }

    /***********************************************************************************************
     * 单例 采用  饿汉式
     */
    private static class RequsetPerHolder {
        private static final RequsetPer INSTANCE = new RequsetPer ();
    }

    public static RequsetPer getInstance() {
        return RequsetPer.RequsetPerHolder.INSTANCE;
    }

    /**********************************************************************************************/
    /**
     * 请求权限
     *
     * @param context         上下文
     * @param permissionArray 权限
     * @param listener        成功回调
     */
    public void getPermission(final Context context, final SuccessListener listener, String... permissionArray) {

        if (mRationale == null) {
            mRationale = new DefaultRationale ();
        }
        if (permissionSetting == null) {
            permissionSetting = new PermissionSetting (context);
        }
        AndPermission.with (context)
                .permission (permissionArray)
                .rationale (mRationale)
                .onGranted (new Action () {
                    @Override
                    public void onAction(List<String> data) {
                        listener.success ();
                    }
                }).onDenied (new Action () {
            @Override
            public void onAction(List<String> data) {
                if (AndPermission.hasAlwaysDeniedPermission (context, data)) {
                    permissionSetting.showSetting (data);
                }
            }
        }).start ();
    }


    /**
     * 请求成功回调
     */
    public interface SuccessListener {
        //成功
        void success();
    }

}
