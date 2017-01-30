package com.jacintomendes.cryptotext;


import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.jacintomendes.cryptotext.adapter.SectionsPagerAdapter;


public class MainActivity extends ActionBarActivity {


    ActionBar actionBar;
    ViewPager mViewPager;
    SectionsPagerAdapter mSectionsPagerAdapter;
    public static final int MAX_TABS = 2;
    private String[] tabs = {"Encrypter", "Decrypter"};

    // String for LogCat documentation
    private final static String TAG = "App-CryptoText-Debug";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setOnPageChangeListener(
                new ViewPager.SimpleOnPageChangeListener() {
                    @Override
                    public void onPageSelected(int position) {
                        // When swiping between pages, select the corresponding tab.
                        getSupportActionBar().setSelectedNavigationItem(position);
                    }
                });
        mViewPager.setAdapter(mSectionsPagerAdapter);

        actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        ActionBar.TabListener tabListener = new ActionBar.TabListener() {
            @Override
            public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
                mViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

            }

            @Override
            public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

            }
        };

        // Add 3 tabs, specifying the tab's text and TabListener
        for (int i = 0; i < MAX_TABS; i++) {

            actionBar.addTab(
                    actionBar.newTab()
                            .setText(tabs[i])
                            .setTabListener(tabListener));
        }



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        final EditText ptEditText = (EditText) findViewById(R.id.plainText);
        final EditText keyEditText = (EditText) findViewById(R.id.keyText);

        String ptString = ptEditText.getText().toString();
        String keyString = keyEditText.getText().toString();


        switch (item.getItemId()) {
            // action with ID copy_encr_str was selected
            case R.id.copy_encr_str:
                // copy only if the plainText field is NOT empty
                if( !ptString.equals("") ) {

                    // put text on clipboard
                    putText(ptString);

                    Toast.makeText(this, "Encrypted string has been copied \n\t\t\t  to clipboard !", Toast.LENGTH_SHORT)
                            .show();
                    Log.i(TAG, "Encr str copied to Clipboard. String is "+ptString);
                } else {
                    Toast.makeText(this, "There was no encrypted text to be copied", Toast.LENGTH_SHORT)
                            .show();
                    Log.i(TAG, "PlainText field is empty; nothing copied.");
                }
                break;

            // action with ID copy_key_str was selected
            case R.id.copy_key_str:

                // put text on clipboard
                putText(keyString);

                Toast.makeText(this, "Keystring has been copied to clipboard !", Toast.LENGTH_SHORT)
                        .show();
                Log.i(TAG, "keystr copied to Clipboard. KeyString is "+keyString);
                break;

            // action with ID share_encr_str was selected
            case R.id.share_encr_str:
                // share only if the plainText field is NOT empty
                if( !ptString.equals("") ) {
                    shareIt(ptString);
                    Log.i(TAG, "Encrypted string has been shared ! String shared is "+ptString);
                } else {
                    Toast.makeText(this, "There was no encrypted text to be shared", Toast.LENGTH_SHORT)
                            .show();
                    Log.i(TAG, "PlainText field is empty; nothing copied.");
                }
                break;

            // action with ID share_key_str was selected
            case R.id.share_key_str:
                shareIt(keyString);
                Log.i(TAG, "keystr has been shared ! KeyString shared is " + keyString);
                break;

            default:
                break;
        }

        return true;
    }

    // Handle clipboard copy action
    @SuppressWarnings("deprecation")
    private void putText(String text){
        int sdk = android.os.Build.VERSION.SDK_INT;
        if(sdk < android.os.Build.VERSION_CODES. HONEYCOMB) {
            android.text.ClipboardManager clipboard = (android.text.ClipboardManager) this.getSystemService(Context.CLIPBOARD_SERVICE);
            clipboard.setText(text);
        } else {
            android.content.ClipboardManager clipboard = (android.content.ClipboardManager) this.getSystemService(Context.CLIPBOARD_SERVICE);
            android.content.ClipData clip = ClipData.newPlainText("simple text",text);
            clipboard.setPrimaryClip(clip);
        }
    }

    private void shareIt(String shareBody) {
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // This will open the "Complete action with" dialog if the user doesn't have a default app set.
            startActivity(sharingIntent);
        } else {
            startActivity(Intent.createChooser(sharingIntent, "Share Via"));
        }
    }
}