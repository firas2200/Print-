package org.example.print.Service;

import org.example.print.Entity.VehicleData;
import org.example.print.Repository.VehicleDataRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VehicleDataService implements VehicleDataServiceInterface {

    private final VehicleDataRepository vehicleDataRepository;

    public VehicleDataService(VehicleDataRepository vehicleDataRepository) {
        this.vehicleDataRepository = vehicleDataRepository;
    }

    @Override
    public Optional<VehicleData> findByVehicleId(Short vehicleId) {
        return vehicleDataRepository.findByVehicleId(vehicleId);
    }
}
