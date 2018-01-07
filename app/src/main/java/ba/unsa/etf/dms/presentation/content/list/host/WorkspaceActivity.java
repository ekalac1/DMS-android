package ba.unsa.etf.dms.presentation.content.list.host;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import ba.unsa.etf.dms.R;
import ba.unsa.etf.dms.presentation.BaseLoggedActivity;
import ba.unsa.etf.dms.presentation.content.ListType;
import ba.unsa.etf.dms.presentation.content.list.ContentListInterface;
import ba.unsa.etf.dms.presentation.content.list.ListFragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WorkspaceActivity extends BaseLoggedActivity
        implements ContentListInterface.View,
        NavigationView.OnNavigationItemSelectedListener {

    private static final int FILE_SELECT_CODE = 1;
    @BindView(R.id.drawer_layout) DrawerLayout drawer;
    @BindView(R.id.fab) FloatingActionButton fab;
    @BindView(R.id.nav_view) NavigationView navigationView;
    @BindView(R.id.toolbar) Toolbar toolbar;

    public interface ListFragmentInterface {
        void uploadFile(Uri file);
    }


    ListFragmentInterface fragmentInterface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_list);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        loadProfileFragment();
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @OnClick(R.id.fab)
    void onFabClicked() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);

        try {
            startActivityForResult(Intent.createChooser(intent, "Select a File to Upload"), FILE_SELECT_CODE);
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(this, "Please install a File Manager.",
                    Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.workspace) {
            loadWorkSpaceFragment();
        } else if (id == R.id.logout) {
            logout();
        } else if (id == R.id.profile) {
            loadProfileFragment();
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case FILE_SELECT_CODE:
                if (resultCode == RESULT_OK) {
                    // Get the Uri of the selected file
                    Uri uri = data.getData();
                    fragmentInterface.uploadFile(uri);
                }
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void logout() {
        SharedPreferences settings = getSharedPreferences(BaseLoggedActivity.USERNAME_CONST, MODE_PRIVATE);

        SharedPreferences.Editor editor = settings.edit();
        editor.putString(BaseLoggedActivity.USERNAME_CONST, "");
        editor.apply();

        onResume();
    }

    void loadProfileFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        ListFragment fragment = ListFragment.newInstance(ListType.PROFILE);
        fragmentTransaction.add(R.id.frame_layout, fragment);
        fragmentTransaction.commit();

        fragmentInterface = fragment;

        getSupportActionBar().setTitle("Vaši dokumenti");

    }

    void loadWorkSpaceFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        ListFragment fragment = ListFragment.newInstance(ListType.WORKSPACE);
        fragmentTransaction.add(R.id.frame_layout, fragment);
        fragmentTransaction.commit();

        fragmentInterface = fragment;

        getSupportActionBar().setTitle("Podjeljeno sa Vama");

    }

}
