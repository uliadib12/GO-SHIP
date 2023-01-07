package com.adib.go_ship;

import androidx.annotation.MenuRes;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.textfield.TextInputLayout;

public class MainMenuActivity extends AppCompatActivity {

    private MenuBarLiveData model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        model = new ViewModelProvider(this).get(MenuBarLiveData.class);
        setContentView(R.layout.activity_main_menu);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                CharSequence title = item.getTitle();

                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

                switch (title.toString()){
                    case "HOME":
                        if(model.getCurrentLocation().getValue() != 0){
                            model.getCurrentLocation().setValue(0);
                            transaction.replace(R.id.fragment_main_container, Home.class, null);
                            transaction.commit();
                            return true;
                        }
                        break;
                    case "HISTORY":
                        if(model.getCurrentLocation().getValue() != 1){
                            model.getCurrentLocation().setValue(1);
                            transaction.replace(R.id.fragment_main_container, History.class, null);
                            transaction.commit();
                            return true;
                        }
                        break;
                    case "OPTIONS":
                        if(model.getCurrentLocation().getValue() != 2){
                            model.getCurrentLocation().setValue(2);
                            transaction.replace(R.id.fragment_main_container, Options.class, null);
                            transaction.commit();
                            return true;
                        }
                        break;
                    default:
                        break;
                }
                return false;
            }
        });
    }
}