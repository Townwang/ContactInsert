package com.townwang.contactinsertdemo.permission;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;

import com.townwang.contactinsertdemo.R;
import com.yanzhenjie.permission.Permission;
import com.yanzhenjie.permission.Rationale;
import com.yanzhenjie.permission.RequestExecutor;

import java.util.List;

/**
 * @author Town
 * @created at 2018/3/26 12:01
 * @Last Modified by: Town
 * @Last Modified time: 2018/3/26 12:01
 * @Remarks 申请权限
 */
public final class DefaultRationale implements Rationale {

    @Override
    public void showRationale(Context context, List<String> permissions, final RequestExecutor executor) {
        List<String> permissionNames = Permission.transformText(context, permissions);
        @SuppressLint({"StringFormatInvalid", "LocalSuppress"}) String message = context.getString(R.string.permission_yes_content, TextUtils.join("\n", permissionNames));
        new AlertDialog.Builder (context)
                .setCancelable(false)
                .setTitle(R.string.permission_title)
                .setMessage(message)
                .setPositiveButton (context.getString (R.string.confirm), new DialogInterface.OnClickListener () {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        executor.execute();
                    }
                })
                .setNegativeButton (context.getString (R.string.clear), new DialogInterface.OnClickListener () {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        executor.cancel();
                    }
                })
                .show();
    }
}
