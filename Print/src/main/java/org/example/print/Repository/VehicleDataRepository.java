package org.example.print.Repository;

import org.example.print.Entity.VehicleData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface VehicleDataRepository extends JpaRepository<VehicleData, Short> {
    // Assuming Vehicle_ID is the primary key for VehicleData.
    Optional<VehicleData> findByVehicleId(Short vehicleId);
}
