package org.example.print.Service;

import org.example.print.Entity.InspectionForm;
import org.example.print.Repository.InspectionFormRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class InspectionFormService implements InspectionFormServiceInterface {

    private final InspectionFormRepository inspectionFormRepository;

    public InspectionFormService(InspectionFormRepository inspectionFormRepository) {
        this.inspectionFormRepository = inspectionFormRepository;
    }

    @Override
    public Optional<InspectionForm> findByVehicleId(short VehicleId) {
        return inspectionFormRepository.findByVehicleId(VehicleId);
    }
}
