/* After the Model class, we need the repository to access the data from the database.
 The Spring JPA comes up with a JpaRepository interface . This interface defines all the
 methods used for CRUD operations on the entity.

Spring JPA also has one default implementation of the JpaRepository interface which is SimpleJpaRepository.

Now, let us create our repository inside the com/techblogstation/repository folder or package.

*/

package com.studentmanagement.crud.repository;

import com.studentmanagement.crud.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
// Let's save this project to Github
public interface StudentRepository extends JpaRepository<StudentEntity, Long> {

}

/*
    We have annotated the above interface with @Repository annotation.
@Repository annotation specifies that this needs to be considered during component scan for the application.

Our Repository layer is now ready.

JpaRepository has several methods like save, delete, count , findOne etc....
These methods are implemented by the SimpleJpaRepository class so we do not need to implement these methods.

*/