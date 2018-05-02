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
        if (remoteMessage.getData().size() > 0 && remoteMessage.getNotification() != null) {
          //  String dataTitle = remoteMessage.getData().get("title"), dataMessage = remoteMessage.getData().get("message");

            String click_action = remoteMessage.getNotification().getClickAction();
            String title = remoteMessage.getNotification().getTitle();
            String message = remoteMessage.getNotification().getBody();
            handleMessage(title, message, click_action);
        }
    }

    private void handleMessage(String title, String body, String action) {
        Intent notificationPush = new Intent(action);
        //add string in intent()
        notificationPush.putExtra("message",body);
        notificationPush.putExtra("title",title);

        notificationPush.putExtra(PUSH_NOT, action);
        Log.d(TAG, "Message :" + body);
        LocalBroadcastManager.getInstance(this).sendBroadcast(notificationPush);
    }

}
