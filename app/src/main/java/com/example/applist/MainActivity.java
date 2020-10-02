package com.example.applist;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<ApplicationInfo> appList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.list_item);
        appList = (ArrayList<ApplicationInfo>) listOfApps();
        CustomAdapter adapter = new CustomAdapter(this, R.layout.list_items, appList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                            @Override
                                            public void onItemClick(AdapterView<?> adapter, View view, int position, long arg) {
                                                // TODO Auto-generated method stub
                                                ApplicationInfo app = appList.get(position);
                                                try {
                                                    PackageInfo packageInfo = getPackageManager().getPackageInfo(app.packageName, 0);
                                                    Toast.makeText(getApplicationContext(), "Version : " + packageInfo.versionName, Toast.LENGTH_LONG).show();
                                                } catch (PackageManager.NameNotFoundException e) {
                                                    e.printStackTrace();
                                                }
                                            }
                                        }
        );
    }

    private List<ApplicationInfo> listOfApps() {
        List<ApplicationInfo> apps = getPackageManager().getInstalledApplications(0);
        List<ApplicationInfo> installedApps = new ArrayList<>();
        for (ApplicationInfo app : apps) {
            if ((app.flags & ApplicationInfo.FLAG_UPDATED_SYSTEM_APP) != 0) {
                installedApps.add(app);
                //it's a system app, not interested
            } else if ((app.flags & ApplicationInfo.FLAG_SYSTEM) != 0) {
//                installedApps.add(app);
            } else {
                installedApps.add(app);
            }
        }
        return installedApps;
    }
}