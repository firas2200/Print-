package org.example.print.Service;

import org.example.print.Entity.PartIDData;
import java.util.List;
import java.util.Optional;

public interface PartIDDataServiceInterface {

    Optional<PartIDData> getPartById(String id);


}
