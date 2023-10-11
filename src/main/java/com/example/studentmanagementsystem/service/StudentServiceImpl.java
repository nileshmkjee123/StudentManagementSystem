package com.example.studentmanagementsystem.service;

import com.example.studentmanagementsystem.dto.StudentDto;
import com.example.studentmanagementsystem.entity.Student;
import com.example.studentmanagementsystem.mapper.StudentMapper;
import com.example.studentmanagementsystem.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
@AllArgsConstructor
public class StudentServiceImpl implements StudentService{
    private StudentRepository studentRepository;
    @Override
    public List<StudentDto> getAllStudents() {
        List<Student> list = studentRepository.findAll();
        List<StudentDto> students = new ArrayList<>();
        for(Student s : list)
        {
            students.add(StudentMapper.mapToStudentDto(s));
        }
        return students;
    }

    @Override
    public void createStudent(StudentDto studentDto) {
        Student student = StudentMapper.mapToStudent(studentDto);
        studentRepository.save(student);
    }

    @Override
    public StudentDto getStudentById(Long studentId) {
        Student student = studentRepository.findById(studentId).get();
        StudentDto studentDto = StudentMapper.mapToStudentDto(student);
        return studentDto;
    }

    @Override
    public void updateStudent(StudentDto studentDto) {
        studentRepository.save(StudentMapper.mapToStudent(studentDto));
    }

    @Override
    public void deleteStudent(Long studentId) {
        studentRepository.deleteById(studentId);
    }
}
