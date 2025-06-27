package com.nilesh.SpringSecEx.controller;


import com.nilesh.SpringSecEx.model.Student;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class StudentController {


    private List<Student> student=new ArrayList<>(List.of(
            new Student(2,"nilesh",56),
            new Student(4,"ram",43),
            new Student(3,"sham",54)
    ));

    @GetMapping("/")
    public String greet()
    {
        return "Welcome Nilesh";
    }


    @GetMapping("/students")
    public List<Student> getStudents()
    {
        return student;
    }

    @GetMapping("/csrf-token")
    public CsrfToken getCsrfToken(HttpServletRequest request)
    {
        return (CsrfToken) request.getAttribute("_csrf");
    }

    @PostMapping("/students")
    public Student addStudent(@RequestBody Student student1)
    {
        student.add(student1);
        return student1;
    }



}
