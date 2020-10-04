package com.anki.offlinelisting.view.activity;

import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentTransaction;

import dagger.android.DaggerActivity;
import dagger.android.support.DaggerAppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.anki.offlinelisting.R;
import com.anki.offlinelisting.databinding.ActivityMainBinding;
import com.anki.offlinelisting.view.fragment.MemberFragment;

public class MainActivity extends DaggerAppCompatActivity {
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        binding.toolbar.setNavigationIcon(ContextCompat.getDrawable(this , R.drawable.ic_back));
        binding.toolbar.setTitle("Member list");
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.fragContainer, MemberFragment.newInstance(), MemberFragment.class.getSimpleName())
                .commit();

        Toast.makeText(this , "Pull to refresh to get new API data" , Toast.LENGTH_LONG).show();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }

        return super.onOptionsItemSelected(item);
    }
}