package ru.akhmat.SpringRestProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.akhmat.SpringRestProject.model.Measurments;

public interface MeasurmentsRepository extends JpaRepository<Measurments, Integer> {
}
