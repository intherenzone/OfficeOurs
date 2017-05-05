package edu.umd.cs.officeours;

import android.support.v4.app.Fragment;

public class StatusActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return StatusFragment.newInstance();
    }
}
