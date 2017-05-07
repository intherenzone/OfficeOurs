package edu.umd.cs.officeours;

import android.app.Activity;
import android.support.v4.app.Fragment;

public class MapActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return MapFragment.newInstance();
    }
//    testing changes
}
