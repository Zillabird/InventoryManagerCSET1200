package com.example.cset1200finalproject;

import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.cset1200finalproject.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.text.SimpleDateFormat;

public class MainActivity extends Inventory {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Email function to come", Snackbar.LENGTH_LONG)
                        .setAnchorView(R.id.fab)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }

}

class Inventory extends AppCompatActivity {
    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;
    private String name;
    private double price;
    private String description;
    private String seller;
    private int quantity;
    private int id;
    private java.util.Date date;
    ArrayList<Inventory> inventory = new ArrayList<com.example.cset1200finalproject.Inventory>();
    ArrayList<String> log = new ArrayList<String>();
    public Inventory(){

    }

    public Inventory(String name,double price, String description,String seller, int quantity, int id) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.seller = seller;
        this.quantity = quantity;
        this.id = id;
        date = new java.util.Date();
    }

    public void addInventory(String name, double price, String description,String seller, int quantity){
        int idnum;
        if (inventory.size() == 0) {
            idnum = 1;
        }
        else{
            idnum = inventory.get(inventory.size()-1).id + 1;
        }
        Inventory item = new Inventory(name,price,description,seller,quantity,idnum);
        inventory.add(item);
        java.util.Date date;
        date = new java.util.Date();
        createLog("Added Inventory Item: " + item.name,date);
    }
    public void makePurchase(int id, String buyer){
        for (com.example.cset1200finalproject.Inventory inv : inventory){
            if (inv.id == id) {
                inv.quantity = inv.quantity - 1;
                java.util.Date purchDate = new java.util.Date();
                createLog("Purchased Item: " + inv.name,"Purchased By: " + buyer, purchDate);
                if (inv.quantity == 0) {
                    delInventory(inv.id);
                }
                break;
            }
            else {
                continue;
            }
        }
    }
    public void delInventory(int id){
        for (com.example.cset1200finalproject.Inventory inv: inventory){
            if (inv.id == id){
                java.util.Date delDate = new java.util.Date();
                createLog("Deleted Inventory Item: " + inv.name,delDate);
                inventory.remove(inv);
                break;
            }
            else{
                continue;
            }
        }
    }
    public String printInventory(){
        String fullInventory = "";
        if (inventory.size() > 0) {
            for (Inventory inv: inventory) {
            SimpleDateFormat formatter = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
            String strDate = formatter.format(inv.date);
            String item = String.join("\n"
            , "ID: " + inv.id + " Name: " + inv.name
            , "Description: " + inv.description
            , "Quantity: " + Integer.toString(inv.quantity) + " Price: $" + Double.toString(inv.price)
            , "Seller: " + inv.seller
            , "Date Added: " + strDate
            , "\n");
            fullInventory = fullInventory + item;
            }
            return fullInventory;
        }
        else{
        fullInventory = "Inventory is empty";
        return  fullInventory;
        }
    }

    public void createLog(String event, java.util.Date date){
        SimpleDateFormat formatter = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
        String strDate = formatter.format(date);
        log.add(event + " " + strDate + "\n");
    }
    public void createLog(String event, String buyer, java.util.Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
        String strDate = formatter.format(date);
        log.add(event + " " + buyer + " " +  strDate + "\n");
    }
    public String printLog(){
        String fullLog = "";
        for (String l: log){
            fullLog = fullLog + l;
        }
        return fullLog;
    }
}