package org.example.print.Service;

import org.example.print.Entity.PartIDData;
import org.example.print.Repository.PartIDDataRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PartIDDataService implements PartIDDataServiceInterface {

    private final PartIDDataRepository repository;

    public PartIDDataService(PartIDDataRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<PartIDData> getPartById(String id) {
        return repository.findById(id);
    }


    public List<PartIDData> findPartIdByTitle(String title) {
        return repository.findByTitleContainingIgnoreCase(title);
    }


}

