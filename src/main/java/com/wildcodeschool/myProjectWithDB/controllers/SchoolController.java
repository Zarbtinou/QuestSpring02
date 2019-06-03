package com.wildcodeschool.myProjectWithDB.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.wildcodeschool.myProjectWithDB.entities.School;
import com.wildcodeschool.myProjectWithDB.repositories.SchoolRepository;

@Controller
@ResponseBody
public class SchoolController {

    private final static String DB_URL = "jdbc:mysql://localhost:3306/wild_db_quest?serverTimezone=GMT";
    private final static String DB_USER = "root";//Replace ___ by ur user
    private final static String DB_PASSWORD = "Alexia2018";//Replace ___ by ur password

    @PostMapping("/api/schools")
    @ResponseStatus
    public School store(
            @RequestParam String name,
            @RequestParam int capacity,
            @RequestParam String country

    ) {
        int idGeneratedByInsertion = SchoolRepository.insert(
                name,
                capacity,
                country

        );
        return SchoolRepository.selectById(
                idGeneratedByInsertion
        );
    }

    @PutMapping("/api/schools/{id}")
    public School update(
            @PathVariable int id,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Integer capacity,
            @RequestParam(required = false) String country
    ) {
        School school = SchoolRepository.selectById(id);
        SchoolRepository.update(
                id,
                name != null ? name : school.getName(),
                capacity != null ? capacity : school.getCapacity(),
                country != null ? country : school.getCountry()
        );
        return SchoolRepository.selectById(id);
    }

    @DeleteMapping("/api/schools/{id}")
    public void delete(@PathVariable int id) {

        SchoolRepository.delete(id);
    }
}


