package org.example.kach.Repository.PartID;

import org.example.kach.Entity.PartId.PartIDData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartIDDataRepository extends JpaRepository<PartIDData, String> {
    // Custom queries (if needed)
}
