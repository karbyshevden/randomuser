package com.karbyshev.randomuser.view.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.karbyshev.randomuser.R;
import com.karbyshev.randomuser.view.main.MainFragment;

public class MainActivity extends AppCompatActivity {

    NavController mNavController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        mNavController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, mNavController);
    }

    @Override
    public boolean onSupportNavigateUp() {
        return NavigationUI.navigateUp(mNavController, (DrawerLayout) null);
    }

    public void dialPhoneNumber(String cell){
        Intent cellIntent = new Intent(Intent.ACTION_DIAL);
        cellIntent.setData(Uri.parse("tel:" + cell));
        if (cellIntent.resolveActivity(getPackageManager()) != null){
            startActivity(cellIntent);
        }
    }
}
