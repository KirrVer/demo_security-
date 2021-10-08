package com.example.demo.demo.student;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

import static java.lang.String.format;


@RestController
@RequestMapping("management/api/v1/students")
public class StudentManagementController {

    private final static List<Student> STUDENTS = Arrays.asList(
            new Student(1, "James"),
            new Student(2, "Maria"),
            new Student(3, "Anna")
    );

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ADMINISTRATOR')")
    public List<Student> getAllStudents(){
        System.out.println("getAllStudents");
        return STUDENTS;
    }
    @PostMapping
    @PreAuthorize("hasAuthority('student:write')")
    public void registerStudent(@RequestBody Student student){
        System.out.println("registerStudent");
        System.out.println(student);
    }
    @DeleteMapping(path = "{studentId}")
    @PreAuthorize("hasAuthority('student:write')")
    public void deleteStudent(@PathVariable("studentId") Integer studentId){
        System.out.println("deleteStudent");
        System.out.println(studentId);
    }
    @PutMapping(path = "{studentId}")
    @PreAuthorize("hasAuthority('student:write')")
    public void updateStudent(@PathVariable("studentId") Integer studentId, @RequestBody Student student){
        System.out.println("updateStudent");
        System.out.println(format("%s %s",studentId, student));
    }
}
