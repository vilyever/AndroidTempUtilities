package com.vilyever.androidtemputilities;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.vilyever.logger.LoggerDisplay;
import com.vilyever.temputilities.UI.EditTextLayout.EditTextLayout;
import com.vilyever.temputilities.UI.LoadingView.LoadingAngularVariationDrawable;
import com.vilyever.temputilities.UI.LoadingView.LoadingColorBarDrawable;
import com.vilyever.temputilities.UI.LoadingView.LoadingView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        LoggerDisplay.initialize(getApplication());

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();



                getEditTextLayout().getEditText().setHint("haha");
                getEditTextLayout().setFloatingTip("heihei是的窘境扫荡干净哦睡觉哦大概送到即佛isgsdfg撒旦发给阿瑟反攻三芙蛋糕");

                getLoadingView().setLoadingDrawable(new LoadingColorBarDrawable());
            }
        });

        getEditTextLayout().setFloatingTip("test sdfghjkqwertyuiozxcvbnm,sdfsdfasdfasdfasdfsdfsdf");

        LoadingAngularVariationDrawable drawable = getLoadingView().getLoadingDrawable();
        drawable.setTorusWidth(30);
        drawable.setLeapAngular(800);
        drawable.setRadius(88);
        drawable.setColor(Color.BLACK);
        drawable.setMinAngular(20);
        drawable.setMaxAngular(300);
        drawable.setAnimationDuration(2000);

    }
    
    private EditTextLayout editTextLayout;
    protected EditTextLayout getEditTextLayout() { if (this.editTextLayout == null) {this.editTextLayout = (EditTextLayout) this.findViewById(R.id.editTextLayout); } return this.editTextLayout; }

    private LoadingView loadingView;
    protected LoadingView getLoadingView() { if (this.loadingView == null) {this.loadingView = (LoadingView) this.findViewById(R.id.loadingView); } return this.loadingView; }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
