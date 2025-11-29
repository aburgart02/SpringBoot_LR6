package com.lr6.lr6.controller;

import com.lr6.lr6.entity.Discipline;
import com.lr6.lr6.service.DisciplineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class DisciplineController {

    @Autowired
    private DisciplineService disciplineService;

    @GetMapping("/disciplines")
    public ResponseEntity<List<Discipline>> allDisciplines() {
        List<Discipline> list = disciplineService.getAllDisciplines();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/disciplines/{id}")
    public ResponseEntity<?> getDiscipline(@PathVariable("id") int id) {
        Discipline discipline = disciplineService.getDiscipline(id);
        if (discipline == null) {
            return new ResponseEntity<>("Дисциплина не найдена", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(discipline, HttpStatus.OK);
    }

    @PostMapping("/disciplines")
    public ResponseEntity<Discipline> saveDiscipline(@RequestBody Discipline discipline) {
        Discipline newDiscipline = disciplineService.saveDiscipline(discipline);
        return new ResponseEntity<>(newDiscipline, HttpStatus.CREATED);
    }

    @PutMapping("/disciplines")
    public ResponseEntity<Discipline> updateDiscipline(@RequestBody Discipline discipline) {
        disciplineService.saveDiscipline(discipline);
        return new ResponseEntity<>(discipline, HttpStatus.OK);
    }

    @DeleteMapping("/disciplines/{id}")
    public ResponseEntity<String> deleteDiscipline(@PathVariable("id") int id) {
        Discipline discipline = disciplineService.getDiscipline(id);
        if (discipline == null) {
            return new ResponseEntity<>("Дисциплина не найдена", HttpStatus.NOT_FOUND);
        }
        disciplineService.deleteDiscipline(id);
        return new ResponseEntity<>("Дисциплина удалена", HttpStatus.OK);
    }
}