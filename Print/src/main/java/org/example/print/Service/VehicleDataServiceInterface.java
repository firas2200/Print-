package org.example.print.Service;

import org.example.print.Entity.VehicleData;
import java.util.Optional;

public interface VehicleDataServiceInterface {
    Optional<VehicleData> findByVehicleId(Short vehicleId);
}
