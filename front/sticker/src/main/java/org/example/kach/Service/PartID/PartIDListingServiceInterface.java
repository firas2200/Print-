package org.example.kach.Service.PartID;

import org.example.kach.Entity.PartId.PartIDListing;
import java.util.List;
import java.util.Optional;

public interface PartIDListingServiceInterface {

    List<PartIDListing> getAllListings();

    Optional<PartIDListing> getListingById(String id);

    PartIDListing saveListing(PartIDListing listing);

    void deleteListing(String id);
}
