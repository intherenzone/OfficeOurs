package edu.umd.cs.officeours;

import android.app.Activity;
import android.support.v4.app.Fragment;

public class StatusActivity extends Activity {

    protected Fragment createFragment() {
        return StatusFragment.newInstance();
    }
}
