package CapstoneDesign.EvacuationGuide.repository;

import CapstoneDesign.EvacuationGuide.domain.Shelter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShelterRepository extends JpaRepository<Shelter,Long> {

}