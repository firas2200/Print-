package org.example.kach.Service.PartID;

import org.example.kach.Entity.PartId.PartIDListing;
import org.example.kach.Repository.PartID.PartIDListingRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PartIDListingService implements PartIDListingServiceInterface {

    private final PartIDListingRepository repository;

    public PartIDListingService(PartIDListingRepository repository) {
        this.repository = repository;
    }

    public List<PartIDListing> getAllListings() {
        return repository.findAll();
    }

    public Optional<PartIDListing> getListingById(String id) {
        return repository.findById(id);
    }

    public PartIDListing saveListing(PartIDListing listing) {
        return repository.save(listing);
    }

    public void deleteListing(String id) {
        repository.deleteById(id);
    }
}
