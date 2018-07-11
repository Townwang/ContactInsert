package com.townwang.contactinsertdemo;

import android.content.ContentResolver;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.townwang.contactinsertdemo.permission.RequsetPer;
import com.townwang.contactutils.ContactBean;
import com.townwang.contactutils.ContactUtils;
import com.townwang.contactutils.DeleteListener;
import com.townwang.contactutils.InsertListener;
import com.townwang.contactutils.QueryListener;
import com.yanzhenjie.permission.Permission;

import java.util.ArrayList;
import java.util.List;

import static android.provider.ContactsContract.Contacts.Entity.RAW_CONTACT_ID;

public class MainActivity extends AppCompatActivity implements QueryListener, InsertListener, DeleteListener {

    ContactUtils contactUtils;

    ContentResolver resolver;
     String contactName = "文科中的技术宅";
    ContactBean bean = null;
    List<String> contactNumber = new ArrayList<> ();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_main);
        contactUtils = new ContactUtils ();
        resolver = getContentResolver ();
        bean = new ContactBean ();
        init();
    }

    private void init() {
        contactNumber.add ("12345678901");
        contactNumber.add ("12345678902");
        contactNumber.add ("12345678903");
        contactNumber.add ("12345678904");
        bean.setName (contactName);
        bean.setNumbers (contactNumber);

        RequsetPer.getInstance ().getPermission (this, new RequsetPer.SuccessListener () {
            @Override
            public void success() {

                Toast.makeText (getBaseContext (),"成功",Toast.LENGTH_SHORT).show ();
            }
        }, Permission.Group.CONTACTS);
    }


    public void query(View view) {
        contactUtils.query (resolver,contactName,this);
    }

    public void insert(View view) {
        contactUtils.insert (resolver,bean,this);
    }

    public void delete(View view) {
        contactUtils.delete (resolver,contactName,RAW_CONTACT_ID,this);
    }

    @Override
    public void queryResult(boolean result) {
        Toast.makeText (this,"查询结果"+result,Toast.LENGTH_SHORT).show ();
    }

    @Override
    public void insertResult(boolean result) {
        Toast.makeText (this,"插入结果"+result,Toast.LENGTH_SHORT).show ();
    }

    @Override
    public void deleteResult(boolean result) {
        Toast.makeText (this,"删除结果"+result,Toast.LENGTH_SHORT).show ();
    }
}
