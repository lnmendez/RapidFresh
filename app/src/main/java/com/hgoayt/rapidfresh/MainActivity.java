package com.hgoayt.rapidfresh;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import de.hdodenhof.circleimageview.CircleImageView;


public class MainActivity extends AppCompatActivity {

    private android.support.v7.widget.Toolbar toolbar;

    private TabLayout tabs;
    private ViewPager pager;

    private AdapterPager adapterPager;

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private NavigationView navigationView;

    TextView textView_nombre;
    TextView textView_correo;
    private CircleImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        tabs = (TabLayout) findViewById(R.id.tabs);
        pager = (ViewPager) findViewById(R.id.pager);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        adapterPager = new AdapterPager(getSupportFragmentManager());
        tabs.setupWithViewPager(pager);
        setSupportActionBar(toolbar);
        pager.setAdapter(adapterPager);

        tabs.getTabAt(0).setIcon(R.drawable.tab1);
        tabs.getTabAt(1).setIcon(R.drawable.tab2);

        navigationView = (NavigationView) findViewById(R.id.navigation);
        View header = navigationView.getHeaderView(0);
        imageView = (CircleImageView) header.findViewById(R.id.draw_perfil);
        textView_nombre = (TextView) header.findViewById(R.id.draw_nombre);
        textView_correo = (TextView) header.findViewById(R.id.draw_email);

        textView_nombre.setText(getIntent().getStringExtra("NOMBRE") + " " + getIntent().getStringExtra("APELLIDO"));
        textView_correo.setText(getIntent().getStringExtra("MAIL"));
        Bitmap imagen = StringToBitMap(getIntent().getStringExtra("FOTO"));
        imageView.setImageBitmap(imagen);

        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.openDrawer, R.string.closeDrawer);
        drawerLayout.setDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                menuSelect(item);
                return true;
            }
        });

    }

    private void menuSelect(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_1:
                Intent intent = new Intent(getApplicationContext(), configuracion.class);
                intent.putExtra("RUT", getIntent().getStringExtra("RUT"));
                intent.putExtra("TELEFONO", getIntent().getStringExtra("TELEFONO"));
                intent.putExtra("NOMBRE", getIntent().getStringExtra("NOMBRE"));
                intent.putExtra("APELLIDO", getIntent().getStringExtra("APELLIDO"));
                intent.putExtra("MAIL", getIntent().getStringExtra("MAIL"));
                intent.putExtra("CLAVE", getIntent().getStringExtra("CLAVE"));
                intent.putExtra("FOTO", getIntent().getStringExtra("FOTO"));
                startActivity(intent);
                finish();
                break;
            case R.id.item_2:
                finish();
                break;
        }
    }

    public class AdapterPager extends FragmentPagerAdapter {

        public AdapterPager(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "";
                case 1:
                    return "";
            }
            return null;
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    tab1 t1 = new tab1();
                    return t1;
                case 1:
                    tab2 t2 = new tab2();
                    return t2;
            }

            return null;
        }
    }

    public Bitmap StringToBitMap(String encodedString) {
        try {
            byte[] encodeByte = Base64.decode(encodedString, Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        finish();
        super.onDestroy();
    }
}
