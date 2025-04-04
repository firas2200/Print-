package org.example.kach.Service.PartID;

import org.example.kach.Entity.PartId.PartIDData;
import org.example.kach.Repository.PartID.PartIDDataRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PartIDDataService implements PartIDDataServiceInterface {

    private final PartIDDataRepository repository;

    public PartIDDataService(PartIDDataRepository repository) {
        this.repository = repository;
    }

    public List<PartIDData> getAllParts() {
        return repository.findAll();
    }

    public Optional<PartIDData> getPartById(String id) {
        return repository.findById(id);
    }

    public PartIDData savePart(PartIDData part) {
        return repository.save(part);
    }

    public void deletePart(String id) {
        repository.deleteById(id);
    }
}
