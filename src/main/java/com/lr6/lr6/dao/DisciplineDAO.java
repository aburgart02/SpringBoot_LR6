package com.lr6.lr6.dao;

import com.lr6.lr6.entity.Discipline;
import java.util.List;

public interface DisciplineDAO {
    List<Discipline> getAllDisciplines();
    Discipline saveDiscipline(Discipline discipline);
    Discipline getDiscipline(int id);
    void deleteDiscipline(int id);
}