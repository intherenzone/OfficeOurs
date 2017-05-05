package edu.umd.cs.officeours;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import edu.umd.cs.officeours.model.Professor;


public class SelectProfActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return SelectProfFragment.newInstance();
    }

}
