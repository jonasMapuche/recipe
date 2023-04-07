package br.com.stomach.recipe;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;

import br.com.stomach.recipe.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private AppBarConfiguration appBarConfiguration;

    private boolean bolBot = false;
    private boolean bolFloating = false;

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

        setListeners();

    }

    public boolean onSupportNavigateUp() {
        if (bolBot == true && bolFloating == false) {
            binding.includeMain.floatingPlus.show();
            bolFloating = false;
            bolBot = false;
        } else if (bolFloating == true && bolBot == false) {
            binding.includeMain.floatingPlus.show();
            bolFloating = false;
            bolBot = false;
        }

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
        } catch (Exception err) {
            try {
                NavHostFragment.findNavController(navHostFragment).navigate(R.id.action_fragment_bot_to_fragment_setting);
            } catch (Exception err2) {
                System.out.println("Something went wrong in myself.");
            }
        }
        binding.includeMain.floatingPlus.hide();
        bolBot = true;
    }

    public void setListeners() {
        binding.includeMain.floatingPlus.setOnClickListener(v -> clickFloatingPlus());
    }

    public void clickFloatingPlus() {
        NavHostFragment navHostFragment =
                (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_navigation);
        NavHostFragment.findNavController(navHostFragment).navigate(R.id.action_fragment_home_to_fragment_bot);
        binding.includeMain.floatingPlus.hide();
        bolFloating = true;
    }

}