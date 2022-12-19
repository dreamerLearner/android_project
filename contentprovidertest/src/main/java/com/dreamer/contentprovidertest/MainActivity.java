package com.dreamer.contentprovidertest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.content.ContentProviderOperation;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.OperationApplicationException;
import android.content.UriMatcher;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.RemoteException;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashSet;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {"android.permission.WRITE_CONTACTS","android.permission.WRITE_PROFILE"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        verifyStoragePermissions(this);
//        ActivityCompat.requestPermissions(this, PERMISSIONS_STORAGE,REQUEST_EXTERNAL_STORAGE);
//        getMsgs();
//        try {
//            addContact();
//        } catch (RemoteException e) {
//            e.printStackTrace();
//        } catch (OperationApplicationException e) {
//            e.printStackTrace();
//        }
    }
    private void getMsgs(){
        Uri uri = Uri.parse("content://sms/");
        ContentResolver contentResolver = getContentResolver();
        Cursor query = contentResolver.query(uri, new String[]{"address", "date", "type", "body"}, null, null, "date");
//        Log.i("query:",query.getString(0));
        while (query.moveToNext()){
            String address = query.getString(0);
            String date = query.getString(1);
            String type = query.getString(2);
            String body = query.getString(3);
            System.out.println("地址:" + address);
            System.out.println("时间:" + date);
            System.out.println("类型:" + type);
            System.out.println("内容:" + body);
            System.out.println("======================");
        }
        query.close();
    }
    private void addContact() throws RemoteException, OperationApplicationException {
        //使用事务添加联系人
        Uri uri = Uri.parse("content://com.android.contacts/raw_contacts");
        Uri dataUri =  Uri.parse("content://com.android.contacts/data");

        ContentResolver resolver = getContentResolver();
        ArrayList<ContentProviderOperation> operations = new ArrayList<ContentProviderOperation>();
        ContentProviderOperation op1 = ContentProviderOperation.newInsert(uri)
                .withValue("account_name", null)
                .build();
        operations.add(op1);

        //依次是姓名，号码，邮编
        ContentProviderOperation op2 = ContentProviderOperation.newInsert(dataUri)
                .withValueBackReference("raw_contact_id", 0)
                .withValue("mimetype", "vnd.android.cursor.item/name")
                .withValue("data2", "Coder-pig")
                .build();
        operations.add(op2);

        ContentProviderOperation op3 = ContentProviderOperation.newInsert(dataUri)
                .withValueBackReference("raw_contact_id", 0)
                .withValue("mimetype", "vnd.android.cursor.item/phone_v2")
                .withValue("data1", "13798988888")
                .withValue("data2", "2")
                .build();
        operations.add(op3);

        ContentProviderOperation op4 = ContentProviderOperation.newInsert(dataUri)
                .withValueBackReference("raw_contact_id", 0)
                .withValue("mimetype", "vnd.android.cursor.item/email_v2")
                .withValue("data1", "779878443@qq.com")
                .withValue("data2", "2")
                .build();
        operations.add(op4);
        //将上述内容添加到手机联系人中~
        resolver.applyBatch("com.android.contacts", operations);
        Toast.makeText(getApplicationContext(), "添加成功", Toast.LENGTH_SHORT).show();
    }


    public void verifyStoragePermissions(Activity activity) {
        try {
            //检测是否有全部权限
            HashSet<Object> rs = new HashSet<>();
            for(int i = 0;i<PERMISSIONS_STORAGE.length;i++){
                int permission = ActivityCompat.checkSelfPermission(activity, PERMISSIONS_STORAGE[i]);
                rs.add(permission);
            }

            if (rs.contains(PackageManager.PERMISSION_DENIED)) {
                // 没有写的权限，去申请写的权限，会弹出对话框
                ActivityCompat.requestPermissions(activity, PERMISSIONS_STORAGE,REQUEST_EXTERNAL_STORAGE);
                System.out.println("here1 ");
            }else {
                System.out.println("here2 ");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}