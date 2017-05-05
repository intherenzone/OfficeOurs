package edu.umd.cs.officeours;


import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import edu.umd.cs.officeours.model.Professor;
import edu.umd.cs.officeours.services.ProfService;



public class SelectProfFragment extends Fragment {
    private ProfService profService;
    private RecyclerView professorRecyclerView;
    private ProfessorAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        profService = DependencyFactory.getProfService(getActivity().getApplicationContext());

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_prof_select, container, false);

        //THIS IS FOR DEMO SETUP. NEED TO ADD COURSES TO THESE GUYS TOO
        Professor nelson = new Professor("Nelson Padua Perez");
        Professor kruskal = new Professor("Clyde Kruskal");
        nelson.setPicBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.default_profile_pic));
        kruskal.setPicBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.default_profile_pic));
        profService.addProfessorToList(nelson);
        profService.addProfessorToList(kruskal);


        professorRecyclerView = (RecyclerView)view.findViewById(R.id.prof_recycler_view);
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

            profNameTextView = (TextView)itemView.findViewById(R.id.list_item_professor_name);
            profPicImageView = (ImageView)itemView.findViewById(R.id.list_item_professor_pic);

        }

        void bindProfessor(Professor professor) {
            this.professor = professor;

            profNameTextView.setText(professor.getName());
            profPicImageView.setImageBitmap(professor.getPicBitmap());

        }

        @Override
        public void onClick(View view) {
//            Intent intent = StoryActivity.newIntent(getActivity(), story.getId());
//            startActivityForResult(intent, REQUEST_CODE_CREATE_STORY);
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