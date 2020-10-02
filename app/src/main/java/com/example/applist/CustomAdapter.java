package com.example.applist;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter<ApplicationInfo> {

    private Context context;
    private ArrayList<ApplicationInfo> appList;
    private PackageManager packageManager;

    public CustomAdapter(Context context, int resource, ArrayList<ApplicationInfo> apps) {
        super(context,resource, apps);
        this.context = context;
        this.appList = apps;
        this.packageManager = context.getPackageManager();
    }

    @Override
    public int getCount() {
        return ((null != appList) ? appList.size() : 0);
    }

    @Nullable
    @Override
    public ApplicationInfo getItem(int position) {
        return ((null != appList) ? appList.get(position) : null);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.list_items, null);
        }
        ApplicationInfo data = appList.get(position);
        if (data != null) {
            ImageView img = (ImageView) view.findViewById(R.id.imageView);
            TextView appName = (TextView) view.findViewById(R.id.textView);
            img.setImageDrawable(data.loadIcon(packageManager));
            appName.setText(data.loadLabel(packageManager));
        }
        return view;
    }
}
