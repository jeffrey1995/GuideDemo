package com.jeffrey.demo.guidedemo;

import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity {
    private GuidePage guidePage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initGuideView();
    }

    private void initGuideView() {
        guidePage = findViewById(R.id.guide);
        guidePage.init(null);
    }

}
