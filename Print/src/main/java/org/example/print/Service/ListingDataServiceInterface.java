package org.example.print.Service;

import org.example.print.Entity.ListingData;
import java.util.Optional;

public interface ListingDataServiceInterface {
    Optional<ListingData> findByPartId(String partId);


}
