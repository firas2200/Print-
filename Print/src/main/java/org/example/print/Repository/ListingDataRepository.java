package org.example.print.Repository;
import org.example.print.Entity.ListingData;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
public interface ListingDataRepository extends JpaRepository<ListingData, String> {


    Optional<ListingData> findByPartId(String PartId);




}
