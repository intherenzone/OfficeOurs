package edu.umd.cs.officeours;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import edu.umd.cs.officeours.model.Professor;
import edu.umd.cs.officeours.services.ProfService;


public class StudentFeedBackFragment extends Fragment {
    Button backButton, submitButton;
    EditText inputBox;
    ProfService listOfProfessors;
    TextView mainBox;
    Professor currentProfessor;
    List<String> list;

    public static StudentFeedBackFragment newInstance() {
        return new StudentFeedBackFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        listOfProfessors = DependencyFactory.getProfService(getActivity());
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.student_feed_back, container, false);
        backButton = (Button) view.findViewById(R.id.feedBackPageBackButton);
        submitButton = (Button) view.findViewById(R.id.feed_back_sumbit_Button);
        mainBox = (TextView) view.findViewById(R.id.feed_back_textView_Box);
        inputBox = (EditText) view.findViewById(R.id.feedback_Edit_textBox);

        String proLname = getActivity().getIntent().getStringExtra("proLname");
        currentProfessor = listOfProfessors.getProfessorByLastName(proLname);
        list = currentProfessor.getFeedBackList();
        for (int i = list.size(); 0 < i; i--) {
            mainBox.append(list.get(i - 1) + "\n");
        }
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (inputBox.getText().toString().isEmpty()) {
                    Toast.makeText(getActivity(), "you need to enter something.",
                            Toast.LENGTH_SHORT).show();
                } else {
                    Calendar calendar = Calendar.getInstance();
                    Date date = calendar.getTime();
                    String prevText = mainBox.getText().toString();
                    String temp = "#" + (list.size() + 1) + ":";
                    temp = temp + " " + inputBox.getText().toString() + "\n" + "(Submitted on: "
                            + date.toString() + ")" + "\r\n";
                    currentProfessor.setFeedBacks(temp);
                    inputBox.setText("");
                    mainBox.setText(temp + "\r\n" + prevText);
                }
            }
        });
        return view;
    }
}
