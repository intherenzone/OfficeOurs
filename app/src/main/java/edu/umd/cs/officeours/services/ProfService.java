package edu.umd.cs.officeours.services;

import java.util.List;

import edu.umd.cs.officeours.model.Professor;


public interface ProfService {
    void addProfessorToList(Professor professor);
    List<Professor> getAllProfessors();
    Professor getProfessorByLastName(String profName);
    void deleteProfessorFromList(Professor professor);
}
