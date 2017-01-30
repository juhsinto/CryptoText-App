package com.jacintomendes.cryptotext.fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.espian.showcaseview.ShowcaseView;
import com.espian.showcaseview.targets.ViewTarget;
import com.jacintomendes.cryptotext.R;
import com.jacintomendes.cryptotext.crypto.CryptoCoreLogic;

/**
 * Created by Jm on 19-Sep-14.
 */
public class EncryptFragment extends Fragment {

    ShowcaseView sv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_encrypt, container, false);
        Button button = (Button) rootView.findViewById(R.id.btn_encrypt);

        final EditText ptEditText = (EditText) rootView.findViewById(R.id.plainText);
        final EditText keyEditText = (EditText) rootView.findViewById(R.id.keyText);

        Button button_del_pt = (Button) rootView.findViewById(R.id.btn_del_pt_text);
        Button button_del_pt_key = (Button) rootView.findViewById(R.id.btn_del_pt_key);

        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
//                Log.v("crypto","encryupt button was clicked!");

                String encryptedString = CryptoCoreLogic.encryptString(ptEditText.getText().toString(), keyEditText.getText().toString());
                ptEditText.setText(encryptedString);

                Toast.makeText(getActivity().getApplicationContext(), "Encrypted !!", Toast.LENGTH_SHORT).show();
            }
        });

        button_del_pt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ptEditText.setText("");
            }
        });

        button_del_pt_key.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                keyEditText.setText("");
            }
        });

        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        SharedPreferences wmbPreference = PreferenceManager.getDefaultSharedPreferences(getActivity());
        boolean isFirstRun = wmbPreference.getBoolean("FIRSTRUN", true);
        if (isFirstRun)
        {

            ShowcaseView.ConfigOptions co = new ShowcaseView.ConfigOptions();
            ViewTarget target = new ViewTarget(getView());
            co.hideOnClickOutside = true;

            sv = ShowcaseView.insertShowcaseView(target, getActivity(),
                    R.string.showcase_main_title, R.string.showcase_main_message,co);

            sv.setScaleMultiplier(0.0f);
            sv.animateGesture(250, 200, -200, 200);

            SharedPreferences.Editor editor = wmbPreference.edit();
            editor.putBoolean("FIRSTRUN", false);
            editor.commit();
        }



    }
}
