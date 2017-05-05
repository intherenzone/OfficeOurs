package edu.umd.cs.officeours;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


/**
 * The Status page will show the current status of the professor
 * Students will be abled to see the if the professor is available or away
 *
 * This Fragment will call the layout named fragment_status
 *
 * Something special about fragment_status is the
 */
public class StatusFragment extends Fragment {

    private Button homeButton;
    private Button switchButton;
    private Button prevButton;
    private Button nxnButton;

    private TextView officeHour;

    public static StatusFragment newInstance() {
        return new StatusFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_status, container, false);

        homeButton = (Button)view.findViewById(R.id.home_button);
        homeButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //TODO: back to home page
            }
        });

        switchButton = (Button)view.findViewById(R.id.switch_professor);
        switchButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //TODO: switch to another professor
            }
        });

        prevButton = (Button)view.findViewById(R.id.previous_button);
        prevButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //TODO: previous picture ?
            }
        });

        nxnButton = (Button)view.findViewById(R.id.next_button);
        nxnButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //TODO: next picture ?
            }
        });

        return view;
    }

}
