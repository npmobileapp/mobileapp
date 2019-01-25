package com.dtcc.hackathon;

import android.app.NotificationManager;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.Calendar;
import java.util.Date;

public class NotificationsFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_notifications, container, false);

        Button button = (Button) view.findViewById(R.id.button1);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(getContext());
                mBuilder.setSmallIcon(R.drawable.ic_notifications_black_24dp);
                mBuilder.setContentTitle("DTCC Downtime Alert");
                mBuilder.setContentText(getNotification());

                NotificationManager mNotificationManager = (NotificationManager) getContext().getSystemService(Context.NOTIFICATION_SERVICE);

                mNotificationManager.notify(123, mBuilder.build());

            }
        });

        return view;
    }

    public String getNotification() {
        String notificationText = null;
        Date theDate = new Date() ;
        Calendar cal = Calendar.getInstance();
        cal.setTime(theDate);
        int dayVal = cal.get(Calendar.DAY_OF_WEEK) ;

        switch(dayVal) {
            case Calendar.SUNDAY : // value = 1
                if (cal.get(cal.HOUR_OF_DAY) <= 6) {
                    notificationText = "GTR system is under maintenance" ;
                } else {
                    notificationText = "GTR maintenance complete" ;
                }
            case Calendar.MONDAY : //value = 2
                cal.add(Calendar.DATE, 5);
                notificationText = "GTR maintenance starts from " + cal.getTime() + " 10 PM";
                break ;
            case Calendar.TUESDAY : //value = 3
                cal.add(Calendar.DATE, 4);
                notificationText = "GTR maintenance starts from " + cal.getTime() + " 10 PM";

                break ;
            case Calendar.WEDNESDAY : //value = 4
                cal.add(Calendar.DATE, 3);
                notificationText = "GTR maintenance starts from " + cal.getTime() + " 10 PM";
                break ;
            case Calendar.THURSDAY : //value = 5
                cal.add(Calendar.DATE, 2);
                notificationText = "GTR maintenance starts from " + cal.getTime() + " 10 PM";
                break ;

            case Calendar.FRIDAY : //value = 6
                cal.add(Calendar.DATE, 1);
                notificationText = "GTR maintenance starts from " + cal.getTime() + " 10 PM";
                break ;
            case Calendar.SATURDAY : //value = 7
                if (cal.get(cal.HOUR_OF_DAY) >= 22) {
                    notificationText = "GTR system is under maintenance" ;
                } else {
                    notificationText = "GTR maintenance complete" ;
                }


                break ;

        }

        return notificationText;

    }

}
