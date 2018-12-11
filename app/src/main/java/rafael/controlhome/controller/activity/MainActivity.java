package rafael.controlhome.controller.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import rafael.controlhome.R;
import rafael.controlhome.controller.fragment.FragmentAbout;
import rafael.controlhome.controller.fragment.FragmentControlHome;
import rafael.controlhome.controller.fragment.FragmentRecord;
import rafael.controlhome.connection.db.DataBase;
import rafael.controlhome.connection.mqtt.ConnectionMQTT;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView navigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listenerNavigationItem();

        DataBase.getInstance(this.getApplicationContext());

        ConnectionMQTT.connection(getApplicationContext());

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.layout_main, FragmentControlHome.newInstance());
        transaction.commit();

    }

    private void listenerNavigationItem(){

        BottomNavigationView.OnNavigationItemSelectedListener itemSelectedListener
                = new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                Fragment selectedFragment = null;

                switch (item.getItemId()) {
                    case R.id.navigation_controle:
                        selectedFragment = FragmentControlHome.newInstance();
                       break;
                    case R.id.navigation_registros:
                        selectedFragment = FragmentRecord.newInstance();
                        break;
                    case R.id.navigation_sobre:
                        selectedFragment = FragmentAbout.newInstance();
                        break;
                }

                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.layout_main, selectedFragment);
                transaction.setCustomAnimations( android.R.anim.fade_in, android.R.anim.fade_out);
                transaction.commit();
                transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                return true;
            }

        };
        navigation =  findViewById(R.id.menu_navigation);
        navigation.setOnNavigationItemSelectedListener(itemSelectedListener);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_config, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem){
        int id = menuItem.getItemId();
        if (id == R.id.menu){
            Intent intent = new Intent(MainActivity.this, ActivityConfig.class);
            startActivity(intent);
        }
        return true;
    }

}
