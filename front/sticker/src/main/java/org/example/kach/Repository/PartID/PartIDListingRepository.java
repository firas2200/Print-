package org.example.kach.Repository.PartID;

import org.example.kach.Entity.PartId.PartIDListing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartIDListingRepository extends JpaRepository<PartIDListing, String> {
}
