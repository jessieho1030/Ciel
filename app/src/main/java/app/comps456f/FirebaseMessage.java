package app.comps456f;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class FirebaseMessage extends FirebaseMessagingService {
    public static final String PUSH_NOT = "pushNotification";
    public static final String TAG = "FirebaseMessage";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        handleMessage(remoteMessage.getNotification().getBody());
    }

    private void handleMessage(String body) {
        Intent notificationPush = new Intent(PUSH_NOT);
        //add string in intent()
        notificationPush.putExtra("message",body);
        Log.d(TAG, "Message :" + body);
        LocalBroadcastManager.getInstance(this).sendBroadcast(notificationPush);
    }

}
