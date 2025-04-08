package org.example.print.Repository;
import org.example.print.Entity.PartIDData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface PartIDDataRepository extends JpaRepository<PartIDData, String> {
    List<PartIDData> findByTitleContainingIgnoreCase(String title);
}
