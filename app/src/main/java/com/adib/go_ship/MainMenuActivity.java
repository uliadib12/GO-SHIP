package com.adib.go_ship;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;

public class MainMenuActivity extends AppCompatActivity {
    private ArrayList<Fragment> allFragments;
    private MenuBarLiveData model;
    protected BottomNavigationView bottomNavigationView;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        // add all fragments to the list
        allFragments = new ArrayList<>();
        allFragments.add(new Home());
        allFragments.add(new HistoryMain());
        allFragments.add(new Options());

        // set all fragments to invisible
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        for (Fragment fragment : allFragments) {
            transaction.add(R.id.fragment_main_container, fragment);
            transaction.hide(fragment);
        }
        // show the first fragment
        transaction.show(allFragments.get(0));
        transaction.commit();

        // set model
        model = new ViewModelProvider(this).get(MenuBarLiveData.class);

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            CharSequence title = item.getTitle();

            FragmentTransaction transaction1 = getSupportFragmentManager().beginTransaction();

            Integer current = model.getCurrentLocation().getValue();

            switch (title.toString()){
                case "HOME":
                    if(current != 0){
                        model.getCurrentLocation().setValue(0);
                        setAllFragmentsHide(transaction1);
                        setAnimationTransitionFragment(transaction1, current, 0);
                        transaction1.show(allFragments.get(0));
                        transaction1.commit();
                        return true;
                    }
                    break;
                case "HISTORY":
                    if(current != 1){
                        model.getCurrentLocation().setValue(1);
                        setAllFragmentsHide(transaction1);
                        setAnimationTransitionFragment(transaction1, current, 1);
                        transaction1.show(allFragments.get(1));
                        transaction1.commit();
                        return true;
                    }
                    break;
                case "OPTIONS":
                    if(current != 2){
                        model.getCurrentLocation().setValue(2);
                        setAllFragmentsHide(transaction1);
                        setAnimationTransitionFragment(transaction1, current, 2);
                        transaction1.show(allFragments.get(2));
                        transaction1.commit();
                        return true;
                    }
                    break;
                default:
                    break;
            }
            return false;
        });
    }

    void setAllFragmentsHide(FragmentTransaction transaction){
        for (Fragment fragment : allFragments) {
            if (fragment.isVisible()){
                transaction.hide(fragment);
            }
        }
    }

    void setAnimationTransitionFragment(FragmentTransaction transaction, int current, int next){
        if(current < next){
            transaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left);
        }else{
            transaction.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right);
        }
    }
}