package edu.umd.cs.officeours;


import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import edu.umd.cs.officeours.model.Course;
import edu.umd.cs.officeours.model.DayEnum;
import edu.umd.cs.officeours.model.Professor;
import edu.umd.cs.officeours.services.ProfService;


public class SelectProfFragment extends Fragment {
    private ProfService profService;
    private RecyclerView professorRecyclerView;
    private ProfessorAdapter adapter;
    private static final int REQUEST_CODE_CREATE_PROFESSOR = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        profService = DependencyFactory.getProfService(getActivity().getApplicationContext());

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_prof_select, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_create_professor:
                Intent createStoryIntent = new Intent(getActivity(), logIn.class);
                startActivity(createStoryIntent);
                return true;
            case R.id.menu_item_map:
                Toast.makeText(getContext(), "test this", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_prof_select, container, false);
        //set up for add professor button

        //THIS IS FOR DEMO SETUP.
        Professor nelson = new Professor("Nelson", "Padua Perez");
        Professor kruskal = new Professor("Clyde", "Kruskal");
        Professor song = new Professor("Charles", "Song");
        Professor mamat = new Professor("Anwar", "Mamat");
        Professor davis = new Professor("Larry", "Davis");
        Professor gasarch = new Professor("Billy", "Gesarch");
        Professor herman = new Professor("Larry", "Herman");
        nelson.setPicBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.default_profile_pic));
        kruskal.setPicBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.default_profile_pic));
        song.setPicBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.default_profile_pic));
        mamat.setPicBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.default_profile_pic));
        davis.setPicBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.default_profile_pic));
        gasarch.setPicBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.default_profile_pic));
        herman.setPicBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.default_profile_pic));
        Course course1 = new Course("CMSC216-0101");
        Course course2 = new Course("CMSC351-0101");
        Course course3 = new Course("CMSC132-0101");
        Course course4 = new Course("CMSC451-0101");
        course1.setTAOfficeHours(BitmapFactory.decodeResource(getResources(), android.R.drawable.ic_menu_my_calendar));
        course2.setTAOfficeHours(BitmapFactory.decodeResource(getResources(), android.R.drawable.ic_menu_my_calendar));
        course3.setTAOfficeHours(BitmapFactory.decodeResource(getResources(), android.R.drawable.ic_menu_my_calendar));
        course4.setTAOfficeHours(BitmapFactory.decodeResource(getResources(), android.R.drawable.ic_menu_my_calendar));
        nelson.setCourse(course1);
        nelson.setCourse(course3);
        kruskal.setCourse(course4);
        kruskal.setCourse(course2);
        nelson.setScheduleForDay(DayEnum.MONDAY, 1300, 1500);
        nelson.setScheduleForDay(DayEnum.SATURDAY, 1000, 1200);
        nelson.setScheduleForDay(DayEnum.SATURDAY, 30, 100);
        nelson.setScheduleForDay(DayEnum.SUNDAY, 30, 100);
        profService.addProfessorToList(nelson);
        profService.addProfessorToList(kruskal);
        profService.addProfessorToList(gasarch);
        profService.addProfessorToList(song);
        profService.addProfessorToList(mamat);
        profService.addProfessorToList(davis);
        profService.addProfessorToList(herman);
        //END DEMO SETUP


        professorRecyclerView = (RecyclerView) view.findViewById(R.id.prof_recycler_view);
        professorRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();

        return view;
    }

    public static SelectProfFragment newInstance() {
        return new SelectProfFragment();
    }


    private void updateUI() {


        List<Professor> professors = profService.getAllProfessors();

        if (adapter == null) {
            adapter = new ProfessorAdapter(professors);
            professorRecyclerView.setAdapter(adapter);
        } else {
            adapter.setProfessors(professors);
            adapter.notifyDataSetChanged();
        }
    }

//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        if (resultCode != Activity.RESULT_OK) {
//            return;
//        }
//
//        if (requestCode == REQUEST_CODE_CREATE_STORY) {
//            if (data == null) {
//                return;
//            }
//
//            Story storyCreated = StoryActivity.getStoryCreated(data);
//            storyService.addStoryToBacklog(storyCreated);
//            updateUI();
//        }
//    }

    private class ProfessorHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView profNameTextView;
        private ImageView profPicImageView;


        private Professor professor;

        ProfessorHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            profNameTextView = (TextView) itemView.findViewById(R.id.list_item_professor_name);
            profPicImageView = (ImageView) itemView.findViewById(R.id.list_item_professor_pic);

        }

        void bindProfessor(Professor professor) {
            this.professor = professor;

            profNameTextView.setText(professor.getFName() + " " + professor.getLName());
            profPicImageView.setImageBitmap(professor.getPicBitmap());

        }

        @Override
        public void onClick(View view) {
            Intent intent = ProfActivity.newIntent(getActivity(), professor.getProfID());
            startActivity(intent);
        }
    }

    private class ProfessorAdapter extends RecyclerView.Adapter<ProfessorHolder> {
        private List<Professor> professors;

        ProfessorAdapter(List<Professor> professors) {
            this.professors = professors;
        }

        void setProfessors(List<Professor> professors) {
            this.professors = professors;
        }

        @Override
        public ProfessorHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.list_item_professor, parent, false);
            return new ProfessorHolder(view);
        }

        @Override
        public void onBindViewHolder(ProfessorHolder holder, int position) {
            Professor professor = professors.get(position);
            holder.bindProfessor(professor);
        }

        @Override
        public int getItemCount() {
            return professors.size();
        }
    }
}