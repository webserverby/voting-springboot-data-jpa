package com.voting.repository;

import com.voting.model.Theme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ThemeRepository extends JpaRepository<Theme, Long> {

    /**
     * Add closing date Theme
     * @param id
     * @return
     */
    @Modifying
    @Query("update Theme set closeDate = now() where id = :id")
    void closeDateTheme(@Param("id") Long id);

}
