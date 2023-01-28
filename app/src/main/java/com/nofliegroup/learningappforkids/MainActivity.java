package com.nofliegroup.learningappforkids;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MainActivity extends AppCompatActivity {


    String[] tabTitle = {"Alphabet", "Animals", "Fruits", "Locations"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TabLayout ttl = findViewById(R.id.tabLayout);
        ViewPager2 pager = findViewById(R.id.viewPager2);
        pager.setOffscreenPageLimit(3);
        //Binding the view pager and the tab layout using the adapter class
        pager.setAdapter(new MyAdapter(getSupportFragmentManager(), getLifecycle()));

        TabLayoutMediator tm = new TabLayoutMediator(ttl, pager, true, (tab, position) -> tab.setText(tabTitle[position]));
        tm.attach();
    }
}