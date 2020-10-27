/* Finally we are here to create the Controller. This will be used to expose all the operations
through the REST API endpoints / urls. Client Application needs to be connect with these endpoints to
perform the create / update / retrieve / delete operations on Students Records in Database.

Below is our controller class StudentController.java inside the package com.techblogstation.controller :

*/

package com.studentmanagement.crud.controller;

import com.studentmanagement.crud.entity.StudentEntity;
import com.studentmanagement.crud.exception.RecordNotFoundException;
import com.studentmanagement.crud.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
// Let's save this project to Github
public class StudentController {

    @Autowired
    StudentService service;

    @GetMapping
    /* In this method , we are calling the getAllStudents() method of the StudentService class,
     which is returning the List containing the Student Data.

    @GetMapping defines that the annotated method will get called with Http Get method .

    The above method will get called with the path /students (which we have defined at class level).
    */
    public ResponseEntity<List<StudentEntity>> getAllStudents(){
        List<StudentEntity> list = service.getAllStudents();

        return new ResponseEntity<List<StudentEntity>>(list, new HttpHeaders(), HttpStatus.OK);

    }

    @GetMapping ("/{id}")
    /* In this method , we are calling the getStudentById() method of the StudentService class,
    which is returning the Student against the provided id.

    The above method will get called with the path /students/{id} .

    The annotation @PathVariable is used to bind the path variable and the method parameter.
    */
    public ResponseEntity<StudentEntity> getStudentById(@PathVariable("id") Long id) throws RecordNotFoundException {
        StudentEntity entity = service.getStudentById(id);

        return new ResponseEntity<StudentEntity>(entity, new HttpHeaders(), HttpStatus.OK);

    }

    @PostMapping
    /*
    @PostMapping defines that the annotated method will get called with Http Post method .

    In the method shown below , we are calling the createOrUpdateStudent() method of the StudentService class.
    The above method will get called with the HTTP post request with the path /students
    (which we have defined at class level).
    */
    public ResponseEntity<StudentEntity> createOrUpdateStudent(@Validated @RequestBody StudentEntity student)
            throws RecordNotFoundException {
        StudentEntity updated = service.createOrUpdateStudent(student);

        return new ResponseEntity<StudentEntity>(updated, new HttpHeaders(), HttpStatus.OK);

    }

    @DeleteMapping ("/{id}")
    // Here, we are calling the deleteStudentById() method of the StudentService class.
    public HttpStatus deleteStudentById(@PathVariable("id") Long id) throws RecordNotFoundException {
        service.deleteStudentById(id);
        return HttpStatus.FORBIDDEN;

    }


}
