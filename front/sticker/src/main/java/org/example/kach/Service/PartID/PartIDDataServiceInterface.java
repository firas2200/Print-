package org.example.kach.Service.PartID;

import org.example.kach.Entity.PartId.PartIDData;
import java.util.List;
import java.util.Optional;

public interface PartIDDataServiceInterface {

    List<PartIDData> getAllParts();

    Optional<PartIDData> getPartById(String id);

    PartIDData savePart(PartIDData part);

    void deletePart(String id);
}
