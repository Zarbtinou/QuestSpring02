package com.wildcodeschool.myProjectWithDB.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.wildcodeschool.myProjectWithDB.entities.School;
import com.wildcodeschool.myProjectWithDB.repositories.SchoolRepository;

@Controller
@ResponseBody
public class SchoolController {

    private final static String DB_URL = "jdbc:mysql://localhost:3306/wild_db_quest?serverTimezone=GMT";
    private final static String DB_USER = "___";//Replace ___ by ur user
    private final static String DB_PASSWORD = "___";//Replace ___ by ur password

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
}


