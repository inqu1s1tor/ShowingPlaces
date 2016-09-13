package com.aleksnik.showingplaces.core.bridge;

import com.aleksnik.showingplaces.core.SPApplication;
import com.aleksnik.showingplaces.ui.FragmentLauncher;



public interface ActivityBridge {

    SPApplication getSPApplication();

    FragmentLauncher getFragmentLauncher();

}
