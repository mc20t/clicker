package ru.lesha.clicker;

import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

public class Storage {
    public static int balance = 0;

    public static HashMap<String, Integer> items = new HashMap<String, Integer>() {{
        put("increment", 1);
        put("timer", 0);
        put("multiplier", 1);
        put("timerdelaydivider", 1);
    }};

    public static boolean buy(String name, int cost){
        if(balance >= cost){
            items.put(name, items.get(name)+1);
            balance-=cost;
            updateTimer();
            return true;
        }else {
            return false;
        }
    }

    public static int getFeature(String name){
        return items.get(name);
    }

    public static int getBalance(){
        return balance;
    }

    public static void changeBalance(int delta){
        balance+=delta;
    }

    public static Timer timer = new Timer();

    public static void updateTimer(){
        try {
            timer.cancel();
        }
        catch (Exception ignored) {
        }
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                balance+=getFeature("timer")*getFeature("multiplier");

            }
        },0,1000/Storage.getFeature("timerdelaydivider"));
    }
}
