package CapstoneDesign.EvacuationGuide.repository;

import CapstoneDesign.EvacuationGuide.domain.DisasterNews;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DisasterNewsRepository extends JpaRepository<DisasterNews,Long> {


}