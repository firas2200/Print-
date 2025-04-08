package org.example.print.Repository;

import org.example.print.Entity.InspectionForm;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface InspectionFormRepository extends JpaRepository<InspectionForm, Long> {

    Optional<InspectionForm> findByVehicleId(short VehicleId);
}
