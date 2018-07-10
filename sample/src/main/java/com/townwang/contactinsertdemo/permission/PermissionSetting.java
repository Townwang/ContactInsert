package com.townwang.contactinsertdemo.permission;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;

import com.townwang.contactinsertdemo.R;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Permission;
import com.yanzhenjie.permission.SettingService;

import java.util.List;

/**
 * @author Town
 * @created at 2018/3/26 12:00
 * @Last Modified by: Town
 * @Last Modified time: 2018/3/26 12:00
 * @Remarks 权限被拒绝
 */

public final class PermissionSetting {
    /**
     * 上下文
     */
    private final Context mContext;

    /**
     * 构造方法
     *
     * @param context 上下文
     */
    PermissionSetting(Context context) {
        this.mContext = context;
    }

    /**
     * 弹出对话框 并提示用户手动授权
     *
     * @param permissions 权限
     */
    void showSetting(final List<String> permissions) {
        List<String> permissionNames = Permission.transformText (mContext, permissions);
        @SuppressLint({"StringFormatInvalid", "LocalSuppress"}) String message = mContext.getString (R.string.permission_deny_content, TextUtils.join ("\n", permissionNames));

        final SettingService settingService = AndPermission.permissionSetting (mContext);
        new AlertDialog.Builder (mContext)
                .setCancelable (false)
                .setTitle (R.string.permission_title)
                .setMessage (message)
                .setPositiveButton (mContext.getString (R.string.permission_yes), new DialogInterface.OnClickListener () {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        settingService.execute ();
                    }
                })
                .setNegativeButton (mContext.getString (R.string.permission_deny), new DialogInterface.OnClickListener () {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        settingService.cancel ();
                    }
                })
                .show ();
    }
}
