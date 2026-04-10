package com.app.threetier.service;

import com.app.threetier.domain.dto.StudentDTO;
import com.app.threetier.domain.vo.StudentVO;
import com.app.threetier.repository.StudentDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(rollbackFor = Exception.class)
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentDAO studentDAO;

    // 학생 등록
    @Override
    public void registerStudent(StudentVO studentVO) {
        studentDAO.save(studentVO);
    }

    // 서비스단에서 옵셔널 처리
    // 학생 단일 조회
    @Override
    public Optional<StudentDTO> getStudent(Long id) {
        return Optional.ofNullable(studentDAO.findById(id));
    }

    @Override
    public List<StudentDTO> getStudentList() {
        return studentDAO.findAll();
    }

    @Override
    public void updateStudent(StudentVO studentVO) {
        studentDAO.update(studentVO);
    }

    @Override
    public void deleteStudent(Long id) {
        studentDAO.delete(id);
    }
}
