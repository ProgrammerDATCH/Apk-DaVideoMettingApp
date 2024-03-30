package com.davideomettingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.zegocloud.uikit.prebuilt.videoconference.ZegoUIKitPrebuiltVideoConferenceConfig;
import com.zegocloud.uikit.prebuilt.videoconference.ZegoUIKitPrebuiltVideoConferenceFragment;

public class ConferenceActivity extends AppCompatActivity {

    TextView meetingIDText;
    ImageView shareBtn;
    String meetingID,userID,name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conference);

        meetingIDText = findViewById(R.id.meeting_id_textview);
        shareBtn = findViewById(R.id.share_btn);

        meetingID = getIntent().getStringExtra("meeting_ID");
        userID = getIntent().getStringExtra("user_id");
        name = getIntent().getStringExtra("name");

        meetingIDText.setText("Meeting ID: "+meetingID);

        addFragment();

        shareBtn.setOnClickListener((v)->
        {
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_TEXT,"Join meeting in DaVideo Meeting App\n Meeting ID: "+meetingID+"\n\nby Programmer DATCH");
            startActivity(Intent.createChooser(intent, "Share via"));
        });

    }

    public void addFragment() {
        long appID = AppConstants.appId;
        String appSign = AppConstants.appSign;

        ZegoUIKitPrebuiltVideoConferenceConfig config = new ZegoUIKitPrebuiltVideoConferenceConfig();
        ZegoUIKitPrebuiltVideoConferenceFragment fragment = ZegoUIKitPrebuiltVideoConferenceFragment.newInstance(appID, appSign, userID, name,meetingID,config);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commitNow();
    }
}