package br.com.stomach.recipe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import br.com.stomach.recipe.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private AppBarConfiguration appBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.includeMain.toolbarController);
        DrawerLayout drawer = binding.drawerMain;
        NavigationView navigationView = binding.navigatorMain;

        appBarConfiguration = new AppBarConfiguration.Builder(R.id.fragment_home, R.id.fragment_bot).setOpenableLayout(drawer).build();
        NavController navController = Navigation.findNavController(this,R.id.fragment_navigation);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

    }

    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.fragment_navigation);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_setting, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.item_setting:
                setSetting();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void setSetting() {
        NavHostFragment navHostFragment =
                (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_navigation);
        try {
            NavHostFragment.findNavController(navHostFragment).navigate(R.id.action_fragment_home_to_fragment_setting);
        } catch (Exception e) {
            try {
                NavHostFragment.findNavController(navHostFragment).navigate(R.id.action_fragment_bot_to_fragment_setting);
            } catch (Exception s) {
                System.out.println("Something went wrong in myself.");
            }
        }

    }

}