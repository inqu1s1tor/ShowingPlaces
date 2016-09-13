package com.aleksnik.showingplaces.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.aleksnik.showingplaces.R;
import com.aleksnik.showingplaces.storage.SharedDataManager;


public class SettingsFragment extends GenericFragment {

    private SharedDataManager mSharedDataManager;

    private TextInputLayout textInputLayoutCoverArea;
    private EditText inputCoverAreaEditText;
    private Button okButton;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.settings_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mSharedDataManager = mActivityBridge.getSPApplication().getSharedDataManager();

        textInputLayoutCoverArea = (TextInputLayout) view.findViewById(R.id.text_input_layout);
        inputCoverAreaEditText = (EditText) view.findViewById(R.id.input_cover_area_edit_text);

        okButton = (Button) view.findViewById(R.id.ok_settings_button);
        okButton.setOnClickListener(new Clicker());
    }

    private void buttonOkPressed() {

        String inputCoverArea = String.valueOf(inputCoverAreaEditText.getText());
        Log.d("TAG", "String s area " + inputCoverArea);

        if (TextUtils.isEmpty(inputCoverArea)) {
            textInputLayoutCoverArea.setError("Input cover area");
            return;
        }

        int coverArea = Integer.parseInt(inputCoverArea) * 1000;
        Log.d("TAG", "cover area " + coverArea);

        mSharedDataManager.setCoverArea(coverArea);
    }

    private final class Clicker implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            switch(view.getId()) {
                case R.id.ok_settings_button:
                    buttonOkPressed();
                    getActivity().onBackPressed();
                    break;


            }

        }
    }
}
