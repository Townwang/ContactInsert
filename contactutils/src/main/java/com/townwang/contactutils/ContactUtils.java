package com.townwang.contactutils;

import android.app.Activity;
import android.content.ContentResolver;

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
    public void query(final ContentResolver resolver, final String contactName, final Activity activity, final QueryListener listener) {
        new Thread () {
            @Override
            public void run() {
                super.run ();
                final boolean result = ContactProviderHelper.queryTheContactName (resolver, contactName);
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
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
    public void insert(final ContentResolver resolver,  final ContactBean bean, final Activity activity, final InsertListener listener) {
        new Thread () {
            @Override
            public void run() {
                super.run ();
                final boolean result = ContactProviderHelper.insertContent (resolver, bean.getNumbers (), bean.getName ());
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
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
    public void delete(final ContentResolver resolver, final String contactName, final String RAW_CONTACT_ID ,final Activity activity, final DeleteListener listener){
        new Thread () {
            @Override
            public void run() {
                super.run ();
                final boolean result = ContactProviderHelper.delete (resolver,contactName,RAW_CONTACT_ID);
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        listener.deleteResult (result);
                    }
                });
            }
        }.start ();
    }


}
