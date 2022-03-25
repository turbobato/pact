package com.pact.pactag;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.os.Bundle;

import com.pact.pactag.databinding.ActivityMainBinding;
import com.pact.pactag.fragments.BoxesFragment;
import com.pact.pactag.fragments.ForumFragment;
import com.pact.pactag.fragments.HomeFragment;
import com.pact.pactag.fragments.PlantsFragment;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new HomeFragment(this));

        binding.navigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.home_page:
                    replaceFragment(new HomeFragment(this));
                    break;
                case R.id.plants_page:
                    replaceFragment(new PlantsFragment());
                    break;
                case R.id.boxes_page:
                    replaceFragment(new BoxesFragment());
                    break;
                case R.id.forum_page:
                    replaceFragment(new ForumFragment());
                    break;
            }
            return true ;
        });

        }

    private void replaceFragment(Fragment fragment){


        FragmentTransaction transaction = this.getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_layout, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

}

