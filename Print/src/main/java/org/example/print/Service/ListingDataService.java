package org.example.print.Service;

import org.example.print.Entity.ListingData;
import org.example.print.Repository.ListingDataRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ListingDataService implements ListingDataServiceInterface {

    private final ListingDataRepository listingDataRepository;

    public ListingDataService(ListingDataRepository listingDataRepository) {
        this.listingDataRepository = listingDataRepository;
    }

    @Override
    public Optional<ListingData> findByPartId(String partId) {
        return listingDataRepository.findByPartId(partId) ;
    }

}
