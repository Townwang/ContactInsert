package com.townwang.contactutils;

import android.annotation.SuppressLint;
import android.content.ContentProviderOperation;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;

import java.util.ArrayList;
import java.util.List;


/**
 * @author Town
 * @created at 2016/12/16 16:24
 * @Last Modified by: Town
 * @Last Modified time: 2016/12/16 16:24
 * @Remarks 联系人帮助类
 */

public class ContactProviderHelper {

    /**
     * @param resolver    内容解析器
     * @param contactName 联系人名称
     * @return 是否查询到了联系人
     */
    @SuppressLint("NewApi")
    public static boolean queryTheContactName(ContentResolver resolver, String contactName) {
        try {
            Uri uri = ContactsContract.Data.CONTENT_URI;
            Cursor cursorUser = resolver.query (uri, new String[]{ContactsContract.CommonDataKinds.Phone._ID,
                    ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME, ContactsContract.CommonDataKinds.Phone.RAW_CONTACT_ID}, null, null, null);
            while (cursorUser.moveToNext ()) {
                String name = cursorUser.getString (1);
                if (name.equals (contactName)) {
                    return true;
                }
            }
        } catch (NullPointerException e) {
            return false;
        }
        return false;
    }

    @SuppressLint("NewApi")
    public static boolean delete(ContentResolver resolver, String name, String RAW_CONTACT_ID) {
        Cursor cursor = resolver.query (android.provider.ContactsContract.Data.CONTENT_URI, new String[]{RAW_CONTACT_ID}, ContactsContract.Contacts.DISPLAY_NAME +
                "=?", new String[]{name}, null);
        ArrayList<ContentProviderOperation> ops = new ArrayList<ContentProviderOperation> ();
        if (cursor.moveToFirst ()) {
            do {
                long Id = cursor.getLong (cursor.getColumnIndex (RAW_CONTACT_ID));
                ops.add (ContentProviderOperation.newDelete (ContentUris.withAppendedId (ContactsContract.RawContacts.CONTENT_URI, Id)).build ());
                try {
                    resolver.applyBatch (ContactsContract.AUTHORITY, ops);
                } catch (Exception e) {
                    return false;
                }
            } while (cursor.moveToNext ());
            cursor.close ();
        }
        return true;
    }

    @SuppressLint("NewApi")
    public static boolean insertContent(ContentResolver resolver, List<String> phoneNumber, String contactName) {

        try {
            ContentValues values = new ContentValues ();
            Uri rawContactUri = resolver.insert (
                    ContactsContract.RawContacts.CONTENT_URI, values);
            long rawContactId = ContentUris.parseId (rawContactUri);
            if (rawContactId != 0) {
                //插入或者更新联系人
                for (String str : phoneNumber) {
                    // 向data表插入数据
                    if (contactName != "") {
                        values.clear ();
                        values.put (ContactsContract.Contacts.Data.RAW_CONTACT_ID, rawContactId);
                        values.put (ContactsContract.Contacts.Data.MIMETYPE, ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE);
                        values.put (ContactsContract.CommonDataKinds.StructuredName.GIVEN_NAME, contactName);
                        resolver.insert (ContactsContract.Data.CONTENT_URI,
                                values);
                    }
                    // 向data表插入电话号码
                    if (str != "") {
                        values.clear ();
                        values.put (ContactsContract.Contacts.Data.RAW_CONTACT_ID, rawContactId);
                        values.put (ContactsContract.Contacts.Data.MIMETYPE, ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE);
                        values.put (ContactsContract.CommonDataKinds.Phone.NUMBER, str);
                        values.put (ContactsContract.CommonDataKinds.Phone.TYPE, ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE);
                        resolver.insert (ContactsContract.Data.CONTENT_URI,
                                values);
                    }
                }
                return true;
            }else {
                return false;
            }
        } catch (NullPointerException e) {
            e.printStackTrace ();
            return false;
        }
    }

}
