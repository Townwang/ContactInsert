/*
 * Copyright © 文科中的技术宅
 * blog:https://www.townwang.com
 */
package com.townwang.contactutils;

import android.content.ContentResolver;
import android.os.Handler;
import android.os.Looper;

/**
 * @author Town
 * @created at 2018/7/9 17:10
 * @Last Modified by: Town
 * @Last Modified time: 2018/7/9 17:10
 * @Remarks 联系人操作类
 */

public class ContactUtils {


    /**
     *  查询联系人
     * @param resolver 内容解析器
     * @param contactName 联系人姓名
     * @param listener  查询回调
     */
    public void query(final ContentResolver resolver, final String contactName, final QueryListener listener) {
        new Thread () {
            @Override
            public void run() {
                super.run ();
                final boolean result = ContactProviderHelper.queryTheContactName (resolver, contactName);

                Handler mainHandler = new Handler(Looper.getMainLooper());
                mainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        //已在主线程中，可以更新UI
                        listener.queryResult (result);
                    }
                });
            }
        }.start ();
    }

    /**
     *  插入联系人
     * @param resolver 内容解析器
     * @param bean 联系人
     */
    public void insert(final ContentResolver resolver, final ContactBean bean) {
        new Thread () {
            @Override
            public void run() {
                super.run ();
                ContactProviderHelper.insertContent (resolver, bean.getNumbers (), bean.getName ());
            }
        }.start ();
    }
    /**
     *  插入联系人
     * @param resolver 内容解析器
     * @param bean 联系人
     * @param listener 插入结果回调
     */
    public void insert(final ContentResolver resolver,  final ContactBean bean, final InsertListener listener) {
        new Thread () {
            @Override
            public void run() {
                super.run ();
                final boolean result = ContactProviderHelper.insertContent (resolver, bean.getNumbers (), bean.getName ());

                Handler mainHandler = new Handler(Looper.getMainLooper());
                mainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        //已在主线程中，可以更新UI
                        listener.insertResult (result);
                    }
                });
            }
        }.start ();
    }

    /**
     * 删除联系人
     * @param resolver 内容解析器
     * @param contactName 联系人姓名
     * @param RAW_CONTACT_ID 联系人ID
     */
    public void delete(final ContentResolver resolver, final String contactName, final String RAW_CONTACT_ID ){
        new Thread () {
            @Override
            public void run() {
                super.run ();
                ContactProviderHelper.delete (resolver,contactName,RAW_CONTACT_ID);

            }
        }.start ();
    }
    /**
     * 删除联系人
     * @param resolver 内容解析器
     * @param contactName 联系人姓名
     * @param RAW_CONTACT_ID 联系人ID
     * @param listener 删除结果回调
     */
    public void delete(final ContentResolver resolver, final String contactName, final String RAW_CONTACT_ID, final DeleteListener listener){
        new Thread () {
            @Override
            public void run() {
                super.run ();
                final boolean result = ContactProviderHelper.delete (resolver,contactName,RAW_CONTACT_ID);
                Handler mainHandler = new Handler(Looper.getMainLooper());
                mainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        //已在主线程中，可以更新UI
                        listener.deleteResult (result);
                    }
                });
            }
        }.start ();
    }


}
