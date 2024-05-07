package CapstoneDesign.EvacuationGuide.repository;

import CapstoneDesign.EvacuationGuide.domain.Disaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DisasterRepository extends JpaRepository<Disaster, Long> {
    Optional<Disaster> findBySEQ(int SEQ);
}
