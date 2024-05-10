package com.skypro.FirstTeamPetShelter.repository;

import com.skypro.FirstTeamPetShelter.model.Info;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InfoRepository extends JpaRepository<Info, Long> {
    Optional<Info> findByKeyWord(String keyWord);
    void deleteByKeyWord(String keyWord);
}
