package com.example.sergio.tabsmaster.Activities;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.example.sergio.tabsmaster.Adapters.ViewPagerAdapter;
import com.example.sergio.tabsmaster.Fragments.PersonListFragment;
import com.example.sergio.tabsmaster.Interfaces.OnPersonCreated;
import com.example.sergio.tabsmaster.Models.Person;
import com.example.sergio.tabsmaster.R;

public class MainActivity extends AppCompatActivity implements OnPersonCreated {

    /*
    * Para poder conectarse a internet hay que añadir la siguiente linea en el manifest:
    *   <uses-permission android:name="android.permission.INTERNET" />
    * */
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPagerAdapter adapter;

    // Índice de posición de los fragments. Para que sea mas legible en vez de con "0" "1" siempre
    public static final int PERSON_FORM_FRAGMENT = 0;
    public static final int PERSON_LIST_FRAGMENT = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Se crean metodos para poder tener el codigo mas limpio y ver como se crea mejor
        setToolbar();
        setTabLayout();
        setViewPager();
        setListenerTabLayout(viewPager);
    }

    private void setToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    private void setTabLayout() {
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.tab_title_first)));
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.tab_title_second)));
    }

    private void setViewPager() {
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        adapter = new ViewPagerAdapter(getSupportFragmentManager(), this, tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
    }

    private void setListenerTabLayout(final ViewPager viewPager) {
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                viewPager.setCurrentItem(position);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

    }

    @Override
    public void createPerson(Person person) {
        // Obtenemos el fragment deseado, ListFragment, a través de
        // getSupportFragmentManager(), y posteriormente pasamos el índice de posición
        // de dicho fragment
        PersonListFragment fragment = (PersonListFragment) getSupportFragmentManager().getFragments().get(PERSON_LIST_FRAGMENT);
        // Llamamos al método de nuestro fragment
        fragment.addPerson(person);
        // Movemos el viewpager hacia el ListFragment para ver la persona añadida en el listView
        viewPager.setCurrentItem(PERSON_LIST_FRAGMENT);
    }
}
