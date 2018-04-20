package com.deznorth.fridgekeeper;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class ProfileActivity extends Fragment {

    private TextView profileName;
    private ImageView profileImgView;

    Context context = getActivity();
    SharedPreferences sharedPrefs = context.getSharedPreferences(
            getString(R.string.Shared_Prefs_Key), Context.MODE_PRIVATE);

    SharedPreferences.Editor prefsEditor = sharedPrefs.edit();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_profile, container, false);
        return rootView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        profileName = profileName.findViewById(R.id.profile_name);
        profileImgView = profileImgView.findViewById(R.id.ProfileImg);

        profileName.setText(sharedPrefs.getString(getString(R.string.profile_name_Key)
                ,getString(R.string.profile_default_name)));

        profileName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    changeName(profileName.getText().toString());
                }
            }
        });
    }

    public void changeName(String name){
        prefsEditor.putString(getString(R.string.profile_name_Key),name);
        prefsEditor.apply();
        Toast.makeText(getActivity(), "Name Changed!", Toast.LENGTH_SHORT).show();
    }

}
