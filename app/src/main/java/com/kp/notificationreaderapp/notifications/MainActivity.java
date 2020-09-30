package com.kp.notificationreaderapp.notifications;

import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.kp.notificationreaderapp.R;

import androidx.core.app.NotificationCompat;

import android.text.format.DateUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RemoteViews;

public class MainActivity extends Activity implements View.OnClickListener {

    private Intent dndIntent, notifyIntent;
    private Button btnDnd, btnNotify;

    private String NOTIFICATION_TITLE = "Notification Sample App";
    private String CONTENT_TEXT = "Expand me to see a detailed message!";

    EditText mEditText;
    Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnDnd = findViewById(R.id.btnDnd);
        btnNotify = findViewById(R.id.btnNotify);
        btnDnd.setOnClickListener(this);
        btnNotify.setOnClickListener(this);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /*// Get the layouts to use in the custom notification
                RemoteViews notificationLayout = new RemoteViews(getPackageName(), R.layout.notification_small);
                RemoteViews notificationLayoutExpanded = new RemoteViews(getPackageName(), R.layout.notification_large);

                // Apply the layouts to the notification
                Notification customNotification = new NotificationCompat.Builder(this, CHANNEL_ID)
                        .setSmallIcon(R.drawable.ic_launcher_foreground)
                        .setStyle(new NotificationCompat.DecoratedCustomViewStyle())
                        .setCustomContentView(notificationLayout)
                        .setCustomBigContentView(notificationLayoutExpanded)
                        .build();*/

            }
        });

        mEditText = findViewById(R.id.edit_text);
        mButton = findViewById(R.id.button);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendNotification();
                mEditText.setText("");
            }
        });

    }

    private void sendNotification() {

        RemoteViews expandedView = new RemoteViews(getPackageName(), R.layout.view_expanded_notification);
        expandedView.setTextViewText(R.id.timestamp, DateUtils.formatDateTime(this, System.currentTimeMillis(),
                DateUtils.FORMAT_SHOW_TIME));
        expandedView.setTextViewText(R.id.notification_message, mEditText.getText());

        // adding action to left button
        Intent leftIntent = new Intent(this, NotificationIntentService.class);
        leftIntent.setAction("left");
        expandedView.setOnClickPendingIntent(R.id.left_button,
                PendingIntent.getService(this, 0, leftIntent, PendingIntent.FLAG_UPDATE_CURRENT));

        // adding action to right button
        Intent rightIntent = new Intent(this, NotificationIntentService.class);
        rightIntent.setAction("right");
        expandedView.setOnClickPendingIntent(R.id.right_button,
                PendingIntent.getService(this, 1, rightIntent, PendingIntent.FLAG_UPDATE_CURRENT));

        RemoteViews collapsedView = new RemoteViews(getPackageName(),
                R.layout.view_collapsed_notification);

        collapsedView.setTextViewText(R.id.timestamp,
                DateUtils.formatDateTime(this, System.currentTimeMillis(),
                        DateUtils.FORMAT_SHOW_TIME));

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)

                // these are the three things a NotificationCompat.Builder object requires at a minimum
                .setSmallIcon(R.drawable.ic_android_black_24dp)
                .setContentTitle(NOTIFICATION_TITLE)
                .setContentText(CONTENT_TEXT)
                // notification will be dismissed when tapped
                .setAutoCancel(true)
                // tapping notification will open MainActivity
                .setContentIntent(PendingIntent.getActivity(this, 0,
                        new Intent(this, MainActivity.class), 0))
                // setting the custom collapsed and expanded views
                .setCustomContentView(collapsedView)
                .setCustomBigContentView(expandedView)
                // setting style to DecoratedCustomViewStyle() is necessary for custom views to display
                .setStyle(new NotificationCompat.DecoratedCustomViewStyle());

        // retrieves android.app.NotificationManager
        NotificationManager notificationManager = (android.app.NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(0, builder.build());

    }

    @Override
    public void onClick(View v) {
        dndIntent = new Intent(this, DNDService.class);
        notifyIntent = new Intent(this, NotifyService.class);
        switch(v.getId()){
            case R.id.btnDnd:
                //this.startService(dndIntent);
                //this.stopService(notifyIntent);
                startActivity(new Intent(this, NotificationActivity.class));
                break;
            case R.id.btnNotify:
                this.startService(notifyIntent);
                this.stopService(dndIntent);
                break;
            default:
                break;
        }
    }

}