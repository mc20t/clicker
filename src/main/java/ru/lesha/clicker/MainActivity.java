package ru.lesha.clicker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    Timer timer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ((Button) findViewById(R.id.button)).setOnClickListener((View v)->{
            Storage.balance+=Storage.getFeature("increment")*Storage.getFeature("multiplier");
            update();
        });

        ((Button) findViewById(R.id.buttonshop)).setOnClickListener((View v)->{
            Intent intent = new Intent(this, Shop.class);
            startActivity(intent);
        });
        update();
    }

    public void update(){
        ((TextView) findViewById(R.id.balancelbl)).setText(String.valueOf(Storage.balance));
        runUpdateTimer(this);
    }

    public void runUpdateTimer(AppCompatActivity activity){
        try {
            timer.cancel();
        }
        catch (Exception ignored) {
        }
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                activity.runOnUiThread(()->{update();});
            }
        },0,1000/Storage.getFeature("timerdelaydivider"));

    }
}