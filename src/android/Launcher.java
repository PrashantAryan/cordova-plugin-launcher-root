package com.launcher.plugin;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import com.adobe.phonegap.push.PushConstants;
import com.root.cossbavatar.MainActivity;
import com.root.cossbavatar.SharepreferenceDatabase;
import java.util.ArrayList;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;

public class Launcher extends CordovaPlugin {
    public static String name;
    JSONArray jsonArray = new JSONArray();
    ArrayList<String> name1 = new ArrayList();
    public ArrayList<String> name3 = new ArrayList();
    String namePassword;
    String password;
    SharepreferenceDatabase sharepreferenceDatabase;

    public boolean execute(String action, JSONArray data, CallbackContext callbackContext) throws JSONException {
        if (!action.equals("launch")) {
            return false;
        }
        name = data.getString(0);
        String message = "Hello, " + name;
        System.out.println("back button Intent" + name);
        if (name.equals("com.skubbs.cossbapp")) {
            String password;
            String email;
            this.name1 = new ArrayList();
            try {
                Bundle extras = this.cordova.getActivity().getIntent().getExtras();
                if (MainActivity.isOnpausecalled) {
                    this.name1 = new ArrayList();
                    this.jsonArray = new JSONArray();
                    this.name1 = new ArrayList();
                    this.jsonArray.put(this.name1);
                    System.out.println("cordova1");
                    System.out.println("cordova1:-" + this.jsonArray);
                } else {
                    this.name1 = new ArrayList();
                    this.jsonArray = new JSONArray();
                    this.name1 = extras.getStringArrayList("loginArrayData");
                    this.jsonArray.put(this.name1);
                    System.out.println("cordova2");
                    System.out.println("cordova2:-" + this.jsonArray);
                }
                if (!this.name1.equals("null")) {
                    this.jsonArray = new JSONArray();
                    this.sharepreferenceDatabase = new SharepreferenceDatabase(this.cordova.getActivity());
                    password = this.sharepreferenceDatabase.getUserPassword();
                    email = this.sharepreferenceDatabase.getUserEmailId();
                    this.name1.add(this.sharepreferenceDatabase.getAppPackageName());
                    this.name1.add(email);
                    this.name1.add(password);
                    this.jsonArray = new JSONArray();
                    this.jsonArray.put(this.name1);
                    System.out.println("cordova3:-" + this.jsonArray);
                    callbackContext.success(this.jsonArray);
                    System.out.println("cordova3");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            if (this.name1.isEmpty()) {
                this.sharepreferenceDatabase = new SharepreferenceDatabase(this.cordova.getActivity());
                password = this.sharepreferenceDatabase.getUserPassword();
                email = this.sharepreferenceDatabase.getUserEmailId();
                this.name1.add(this.sharepreferenceDatabase.getAppPackageName());
                this.name1.add(email);
                this.name1.add(password);
                this.jsonArray = new JSONArray();
                this.jsonArray.put(this.name1);
                System.out.println("cordova4");
                System.out.println("cordova4:-" + this.jsonArray);
            }
            callbackContext.success(this.jsonArray);
            return true;
        }
        int i = 0;
        while (i < data.length()) {
            try {
                this.name3.add(data.get(i).toString());
                i++;
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(this.cordova.getActivity(), "App Not Found: ", 1).show();
            }
        }
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.HOME");
        intent.setFlags(536870912);
        intent = this.cordova.getActivity().getPackageManager().getLaunchIntentForPackage("com.skubbs.cossbapp");
        Bundle bundleApp = new Bundle();
        bundleApp.putStringArrayList(PushConstants.MESSAGE, this.name3);
        intent.putExtras(bundleApp);
        this.cordova.getActivity().startActivity(intent);
        this.name3.clear();
        callbackContext.success(this.jsonArray);
        return true;
    }
}
