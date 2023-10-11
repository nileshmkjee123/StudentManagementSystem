package com.example.studentmanagementsystem.controller;

import com.example.studentmanagementsystem.dto.StudentDto;
import com.example.studentmanagementsystem.service.StudentService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@AllArgsConstructor
@Controller
public class StudentController {
    private StudentService studentService;
   @GetMapping("/students")
    public String listStudents(Model model)
   {
       List<StudentDto> studentDtos = studentService.getAllStudents();
       model.addAttribute("students",studentDtos);
       return "students";
   }
   @GetMapping("/students/new")
    public String newStudent(Model model)
   {
       StudentDto studentDto = new StudentDto();
       model.addAttribute("student",studentDto);
       return "create_student";
   }

   @PostMapping("/students")
    public String saveStudent(@Valid @ModelAttribute("student")StudentDto studentDto, BindingResult bindingResult,Model model){
       if (bindingResult.hasErrors())
       {
           model.addAttribute("student",studentDto);
           return "create_student";
       }
       studentService.createStudent(studentDto);
       return "redirect:/students";
   }
   @GetMapping("/students/{studentId}/edit")
    public String editStudent(@PathVariable("studentId") Long studentId,Model model)
   {
        StudentDto studentDto = studentService.getStudentById(studentId);
        model.addAttribute("student",studentDto);
        return "edit_student";
   }

   @PostMapping("/students/{studentId}")
    public String updateStudent(@PathVariable("studentId")Long studentId,
                              @Valid  @ModelAttribute("student")StudentDto studentDto,
                                BindingResult bindingResult,Model model)
   {
        if(bindingResult.hasErrors())
        {
            model.addAttribute("student",studentDto);
            return "edit_student";
        }
        studentDto.setId(studentId);
        studentService.updateStudent(studentDto);
        return "redirect:/students";
   }

    @GetMapping("/students/{studentId}/delete")
    public String deleteStudent(@PathVariable("studentId") Long studentId) {
        studentService.deleteStudent(studentId);
        return "redirect:/students";
    }

    @GetMapping("/students/{studentId}/view")
    public String viewStudent(@PathVariable("studentId")Long studentId,Model model)
    {
       StudentDto studentDto =  studentService.getStudentById(studentId);
        model.addAttribute("student",studentDto);
        return "view_student";
    }
}
