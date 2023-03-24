package com.example.mediumbatis.mapper;

import com.example.mediumbatis.entity.Student;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.MappedTypes;

import java.util.List;

@Mapper
@MappedTypes({Student.class})

public interface StudentMapper {
    @Select("select * from student")
    @ResultMap("studentResultMap")
    List<Student> findAllStudents();
    @Select("select * from student where id_student=#{id}")
    @ResultMap("studentResultMap")
    Student findStudentById(Integer id);

    @Insert("insert into student(name,age,sex) values(#{name},#{age},#{sex})")
    void insertStudent(Student student);

    @Update("update student set name=#{student.name},age=#{student.age},sex=#{student.sex} where id_student=#{id}")
    void updateStudent(Student student,int id);

    @Delete("delete from student where id_student=#{id}")
    void deleteStudent(Integer id);

    @Results(id = "studentResultMap", value = {
            @Result(property = "idStudent", column = "id_student"),
            @Result(property = "name", column = "name"),
            @Result(property = "age", column = "age"),
            @Result(property = "sex", column = "sex")
    })
    @Select("select * from student")
    List<Student> findAllStudentsWithAnnotations();
}
