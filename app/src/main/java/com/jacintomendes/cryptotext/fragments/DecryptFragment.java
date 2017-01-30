package com.jacintomendes.cryptotext.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.jacintomendes.cryptotext.R;
import com.jacintomendes.cryptotext.crypto.CryptoCoreLogic;

/**
 * Created by Jm on 19-Sep-14.
 */
public class DecryptFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View rootView = inflater.inflate(R.layout.fragment_decrypt, container, false);
        Button button = (Button) rootView.findViewById(R.id.btn_decrypt);

        final EditText ctEditText = (EditText) rootView.findViewById(R.id.cryptText);
        final EditText keyEditText = (EditText) rootView.findViewById(R.id.keyText);

        Button button_del_crypt = (Button) rootView.findViewById(R.id.btn_del_crypt_text);
        Button button_del_crypt_key = (Button) rootView.findViewById(R.id.btn_del_crypt_key);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String decryptMessage = "Decrypted!!";
                String decryptedString = CryptoCoreLogic.decryptString(ctEditText.getText().toString(), keyEditText.getText().toString());
                if (decryptedString.equalsIgnoreCase("Bad Key!")) {
                    decryptMessage = "Failed - Bad Key";
                }
                ctEditText.setText(decryptedString);
                Toast.makeText(getActivity().getApplicationContext(), decryptMessage, Toast.LENGTH_SHORT).show();
            }
        });

        button_del_crypt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ctEditText.setText("");
            }
        });

        button_del_crypt_key.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                keyEditText.setText("");
            }
        });

        return rootView;
    }

    private void clearEncryptEditText() {
        final EditText ctEditText = (EditText) getView().findViewById(R.id.cryptText);
        ctEditText.clearComposingText();
    }

    private void clearEncryptKeyEditText() {
        final EditText keyEditText = (EditText) getView().findViewById(R.id.keyText);
        keyEditText.clearComposingText();
    }

}
