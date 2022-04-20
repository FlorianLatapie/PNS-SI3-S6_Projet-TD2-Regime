package fr.univ.cotedazur.polytech.projet_td2_regime.create_meal;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.util.Log;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class PermissionFactory {
    public static final int CAMERA_REQUEST_CODE = 100;
    public static final int IMAGE_PICK_GALLERY_CODE = 200;
    public static final int REQUEST_ID_IMAGE_CAPTURE = 300;
    public static final int PERMISSIONS_REQUEST_READ_MEDIA = 400;
    private static final String TAG = "PermissionFactory";


    /**
     * check if permission is still granted
     * if it is not, request permissions and the result will come back to callbackActivity
     *
     * @param callbackActivity is the Activity where result is sent
     * @param permission is the permission we would like to be granted
     * @param arg is an argument... the arg can be use to remember the action witch involved to ask the permission
     * @return true if permission is still granted, false else
     */
    public static boolean buildAndCheck(Activity callbackActivity, String permission, String arg){
        int permissionCheck = ContextCompat.checkSelfPermission(callbackActivity, permission);
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            Log.d(TAG,permission + " => permission NOT granted");
            ActivityCompat.requestPermissions(callbackActivity, new String[]{permission,arg}, PERMISSIONS_REQUEST_READ_MEDIA);
            return false;
        }
        Log.d(TAG,permission + " => permission granted");
        return true ;
    }
}
