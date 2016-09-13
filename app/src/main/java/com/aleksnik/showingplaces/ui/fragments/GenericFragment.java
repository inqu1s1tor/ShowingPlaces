package com.aleksnik.showingplaces.ui.fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.widget.Toast;

import com.aleksnik.showingplaces.core.bridge.ActivityBridge;


public abstract class GenericFragment extends Fragment {

    protected ActivityBridge mActivityBridge;
    private ProgressDialog progressDialog;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        mActivityBridge = (ActivityBridge) getActivity();
    }

    protected void showShortToast(String contentString) {
        Toast.makeText(getActivity(), contentString, Toast.LENGTH_SHORT).show();
    }

    protected void showProgressDialog() {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(getActivity());
            progressDialog.setMessage("Loading....");
        }

        progressDialog.show();
    }

    protected void hideProgressDialog() {

        if (progressDialog != null) {
            progressDialog.dismiss();
        }
        progressDialog = null;
    }


    protected void showLongToast(String content) {
        Toast.makeText(getActivity(), content, Toast.LENGTH_LONG).show();
    }


}
