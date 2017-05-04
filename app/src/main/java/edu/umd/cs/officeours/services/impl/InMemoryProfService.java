package edu.umd.cs.officeours.services.impl;

import android.content.Context;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import edu.umd.cs.officeours.model.Professor;
import edu.umd.cs.officeours.services.ProfService;



public class InMemoryProfService implements ProfService{
    private Context context;
    private List<Professor> professorList;


    public InMemoryProfService(Context context) {
        this.context = context;
        this.professorList = new ArrayList<>();
    }

    public void addProfessorToList(Professor professor) {
        Professor currProfessor = getProfessorByName(professor.getName());
        if (currProfessor == null) {
            professorList.add(professor);
        } else {
            professorList.remove(currProfessor);
            professorList.add(professor);
        }
    }

    public Professor getProfessorByName(String profName){
        if(profName == null){return null;}
        for(Professor prof : professorList){
            if(prof.getName().compareTo(profName) == 0){
                return prof;
            }
        }
        return null;
    }

    public List<Professor> getAllProfessors(){
        Collections.sort(professorList);
        return this.professorList;
    }

}
