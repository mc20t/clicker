package ru.lesha.clicker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class Shop extends AppCompatActivity {
    Timer timer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        ((Button) findViewById(R.id.incrementshop)).setOnClickListener((View v)->{
            buy("increment", 100);
        });

        ((Button) findViewById(R.id.timershop)).setOnClickListener((View v)->{
            buy("timer", 200);
        });

        ((Button) findViewById(R.id.multipliershop)).setOnClickListener((View v)->{
            buy("multiplier", 500);
        });

        ((Button) findViewById(R.id.timerdividershop)).setOnClickListener((View v)->{
            buy("timerdelaydivider", 150);
        });


        ((Button) findViewById(R.id.closeshopbtn)).setOnClickListener((View v)->{
            super.finish();
        });
        update();
    }

    public void update(){
        ((TextView) findViewById(R.id.incrementlbl)).setText(String.valueOf(Storage.getFeature("increment")));
        ((TextView) findViewById(R.id.timerlbl)).setText(String.valueOf(Storage.getFeature("timer")));
        ((TextView) findViewById(R.id.multiplierlbl)).setText(String.valueOf(Storage.getFeature("multiplier")));
        ((TextView) findViewById(R.id.balanceshoplbl)).setText(String.valueOf(Storage.balance));
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

    public void buy(String name, int cost){
        if(!Storage.buy(name, cost)){
            Toast.makeText(getApplicationContext(), "Balance is low", Toast.LENGTH_SHORT).show();
        }
        update();
    }

}