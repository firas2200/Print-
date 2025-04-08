package org.example.print.Service;
import org.example.print.Entity.InspectionForm;
import java.util.Optional;

public interface InspectionFormServiceInterface {
    Optional<InspectionForm> findByVehicleId(short VehicleId);
}
