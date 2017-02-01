package com.voting.repository;

import com.voting.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

    /**
     * Returns all of the voting options Theme
     * @param id
     * @return
     */
    @Query(name = "select tc from Question tc join theme t where t.id = :id")
    List<Question> findByThemeId(@Param("id") Long id);

}


