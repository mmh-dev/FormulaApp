package com.example.formulaapp.Fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.formulaapp.BuildConfig;
import com.example.formulaapp.R;


public class AboutAppFragment extends Fragment {

    TextView app_version, os_version, device_model, dev_email;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        getView().setFocusableInTouchMode(true);
        getView().requestFocus();

        getView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    if (keyCode == KeyEvent.KEYCODE_BACK) {
                        getFragmentManager().beginTransaction()
                                .setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_right, R.anim.exit_to_left)
                                .replace(R.id.fragment_container, new MainMenuFragment()).commit();
                        return true;
                    }
                }
                return false;
            }
        });
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_about_app, container, false);

        getActivity().setTitle(getString(R.string.about_app));
        app_version = view.findViewById(R.id.app_version);
        os_version = view.findViewById(R.id.os_version);
        device_model = view.findViewById(R.id.device_model);
        dev_email = view.findViewById(R.id.dev_email);

        app_version.setText(BuildConfig.VERSION_NAME);
        os_version.setText(String.valueOf(android.os.Build.VERSION.RELEASE));
        device_model.setText(android.os.Build.MODEL);

        dev_email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:"));
                intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"murod.hodjaev@gmail.com"});
                if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
                    startActivity(intent);
                }
            }
        });
        return view;
    }
}