/*
    The Service layer is optional but still I recommend it to use to perform the additional logic.
This way your code becomes clean and easy to understand. The Service layer is also known as Business layer.
We are using Service here for connecting with the repository to perform CRUD operations.
Below is our Service StudentService.java inside the com.techblogstation.service package :

*/


package com.studentmanagement.crud.service;

import com.studentmanagement.crud.entity.StudentEntity;
import com.studentmanagement.crud.exception.RecordNotFoundException;
import com.studentmanagement.crud.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    StudentRepository repository;

    public List<StudentEntity> getAllStudents(){
        List<StudentEntity> studentList = repository.findAll();
        if (studentList.size()>0) {
            return studentList;
        }
        else {
            return new ArrayList<StudentEntity>();
        }
    }

    public StudentEntity getStudentById(Long id) throws RecordNotFoundException{
        Optional<StudentEntity> student = repository.findById(id);
        if(student.isPresent()){
            return student.get();
        }
        else {
            throw new RecordNotFoundException("No student record exist for the given id ", id);
        }
    }

    public StudentEntity createOrUpdateStudent(StudentEntity entity) throws RecordNotFoundException{
        if(entity.getId()!=null){
            Optional<StudentEntity> student = repository.findById(entity.getId());

            if (student.isPresent()){
                StudentEntity newEntity = student.get();
                newEntity.setEmailId(entity.getEmailId());
                newEntity.setFirstName(entity.getFirstName());
                newEntity.setLastName(entity.getLastName());
                newEntity.setBranch(entity.getBranch());

                newEntity = repository.save(newEntity);
                return newEntity;
            }
            else {
                entity = repository.save(entity);
                return entity;
            }

        }
        else {
            entity = repository.save(entity);
            return entity;
        }
    }

    public void deleteStudentById(Long id) throws RecordNotFoundException{
        Optional<StudentEntity> student = repository.findById(id);
        if (student.isPresent()){
            repository.deleteById(id);
        }
        else{
            throw new RecordNotFoundException("No student record exists for the given id ", id);
        }

    }

}


/*
    @Service annotation used to declare the class as a Service class which holds the business logic.

In our above Service class , we are injecting the instance of class StudentRepository using @Autowired annotation.

Spring Data JPA will automatically generate the proxy instance of the class StudentRepository and will inject it to
 the instance of StudentService class.

The above methods is StudentService class are calling the JpaRepository’s methods to retrieve the Students/
delete the Student / Create or Update the Student from the database.


*/