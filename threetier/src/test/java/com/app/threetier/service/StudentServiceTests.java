package com.app.threetier.service;

import com.app.threetier.domain.vo.StudentVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class StudentServiceTests {

    @Autowired
    private StudentService studentService;

    @Test
    public void registerStudentTest(){
        StudentVO studentVO = new StudentVO();
        studentVO.setStudentName("장보고");
        studentVO.setEng(30);
        studentVO.setKor(50);
        studentVO.setMath(77);
        studentService.registerStudent(studentVO);
        log.info("student: {}", studentService.getStudent(studentVO.getId()));
    }

    @Test
    public void getStudentListTest(){
        log.info("studentList: {}", studentService.getStudentList());
    }

    @Test
    public void updateStudentTest(){
        log.info("수정전, student: {}", studentService.getStudent(1L));

        StudentVO studentVO = new StudentVO();
        studentVO.setId(1L);
        studentVO.setStudentName("홍길동");
        studentVO.setEng(55);
        studentVO.setKor(50);
        studentVO.setMath(77);
        studentService.updateStudent(studentVO);

        log.info("수정후 student: {}", studentService.getStudent(1L));
    }

    @Test
    public void deleteStudentTest(){
        studentService.deleteStudent(21L);
    }
}
