package com.example.beegoeproject;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import io.netpie.microgear.Microgear;
import io.netpie.microgear.MicrogearEventListener;

public class Display extends AppCompatActivity {

    SwitchCompat switchCompat;
    ImageView trackIcon, dogIcon, scheduleIcon,userIcon;
    Button btn;

    TextView textView;

    //Microgear sent ON/OFF to Netpie
  public Microgear microgear = new Microgear(this);
    private String appid = "Beego"; //APP_ID
    private String key = "Oa154niSjYKxfK3"; //KEY
    private String secret = "xdCCgOf7uKx9Cl70oy4u0aPnU"; //SECRET
    private String alias = "Beego";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dispaly);

        MicrogearCallBack callback = new MicrogearCallBack();

        microgear.setCallback(callback);
        microgear.subscribe("/app");
        microgear.connect(appid,key,secret,alias);

        microgear.publish("/status", "off");

        switchCompat = findViewById(R.id.switchButton);
        getSupportActionBar().hide();
//        getSupportActionBar().setTitle("Home");
//        // calling the action bar
//        ActionBar actionBar = getSupportActionBar();
//
//        // showing the back button in action bar
//        actionBar.setDisplayHomeAsUpEnabled(true);

        trackIcon = findViewById(R.id.trackIcon);
        dogIcon = findViewById(R.id.dogIcon);
        scheduleIcon = findViewById(R.id.schedulelcon);
        userIcon = findViewById(R.id.userIcon);


        textView = findViewById(R.id.result);

        textView.setText(Information.sentDogNameIn);


        btn = findViewById(R.id.test);

        //Microgear for netpie
        switchCompat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(switchCompat.isChecked()){
                    microgear.publish("/status", "on");
                }
                else{
                    microgear.publish("/status", "off");
                }

//                (new Thread(new Runnable()
//                {
//                    // int count = 1;
//                    @Override
//                    public void run()
//                    {
//                        while (!Thread.interrupted())
//                            try
//                            {
//                                runOnUiThread(new Runnable() // start actions in UI thread
//                                {
//                                    @Override
//                                    public void run(){
//
//
//                                        if(switchCompat.isChecked()){
//                                            microgear.publish("/status", "on");
//                                        }
//                                        else{
//                                            microgear.publish("/status", "off");
//                                        }
//
//
//
//                                        //count++;
//                                    }
//                                });
//                                Thread.sleep(2000);
//                            }
//                            catch (InterruptedException e)
//                            {
//                                // ooops
//                            }
//                    }
//                })).start();

            }
        });



        //Menu to next page

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                microgear.publish("/status", "Test");
            }
        });

        dogIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Display .this, Information.class);
                startActivity(intent);
            }
        });

//        trackIcon.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(Display .this, MapsActivity.class);
//                startActivity(intent);
//            }
//        });

        scheduleIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Display .this, Information.class);
                startActivity(intent);
            }
        });

        userIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Display .this, FirsPage.class);
                startActivity(intent);
            }
        });

    }

//    protected void onDestroy() {
//        super.onDestroy();
//        microgear.disconnect();
//    }

//    protected void onResume() {
//        super.onResume();
//        microgear.bindServiceResume();
//    }

    class MicrogearCallBack implements MicrogearEventListener{
        @Override
        public void onConnect() {
//            Message msg = handler.obtainMessage();
//            Bundle bundle = new Bundle();
//            bundle.putString("myKey", "Now I'm connected with netpie");
//            msg.setData(bundle);
//            handler.sendMessage(msg);
            Log.i("Connected","Now I'm connected with netpie");
        }

        @Override
        public void onMessage(String topic, String message) {
//            Message msg = handler.obtainMessage();
//            Bundle bundle = new Bundle();
//            bundle.putString("myKey", topic+" : "+message);
//            msg.setData(bundle);
//            handler.sendMessage(msg);
            Log.i("Message",topic+" : "+message);
        }

        @Override
        public void onPresent(String token) {
//            Message msg = handler.obtainMessage();
//            Bundle bundle = new Bundle();
//            bundle.putString("myKey", "New friend Connect :"+token);
//            msg.setData(bundle);
//            handler.sendMessage(msg);
            Log.i("present","New friend Connect :"+token);
        }

        @Override
        public void onAbsent(String token) {
//            Message msg = handler.obtainMessage();
//            Bundle bundle = new Bundle();
//            bundle.putString("myKey", "Friend lost :"+token);
//            msg.setData(bundle);
//            handler.sendMessage(msg);
            Log.i("absent","Friend lost :"+token);
        }

        @Override
        public void onDisconnect() {
//            Message msg = handler.obtainMessage();
//            Bundle bundle = new Bundle();
//            bundle.putString("myKey", "Disconnected");
//            msg.setData(bundle);
//            handler.sendMessage(msg);
            Log.i("disconnect","Disconnected");
        }

        @Override
        public void onError(String error) {
//            Message msg = handler.obtainMessage();
//            Bundle bundle = new Bundle();
//            bundle.putString("myKey", "Exception : "+error);
//            msg.setData(bundle);
//            handler.sendMessage(msg);
            Log.i("exception","Exception : "+error);
        }

        @Override
        public void onInfo(String info) {
//            Message msg = handler.obtainMessage();
//            Bundle bundle = new Bundle();
//            bundle.putString("myKey", "Exception : "+info);
//            msg.setData(bundle);
//            handler.sendMessage(msg);
            Log.i("info","Info : "+info);
        }
    }

//     this event will enable the back
//     function to the button on press
//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        switch (item.getItemId()) {
//            case android.R.id.home:
//                this.finish();
//                return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }
}
