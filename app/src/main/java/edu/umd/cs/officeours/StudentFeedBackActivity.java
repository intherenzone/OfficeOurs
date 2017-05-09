package edu.umd.cs.officeours;

import android.support.v4.app.Fragment;


public class StudentFeedBackActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return StudentFeedBackFragment.newInstance();
    }

}
