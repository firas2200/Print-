package org.example.kach.controller.ParID;

import org.example.kach.Entity.PartId.PartIDListing;
import org.example.kach.Service.PartID.PartIDListingServiceInterface;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/partidlisting")
public class PartIDListingController {

    private final PartIDListingServiceInterface service;

    public PartIDListingController(PartIDListingServiceInterface service) {
        this.service = service;
    }

    @GetMapping
    public List<PartIDListing> getAllListings() {
        return service.getAllListings();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PartIDListing> getListingById(@PathVariable String id) {
        Optional<PartIDListing> listing = service.getListingById(id);
        return listing.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public PartIDListing createListing(@RequestBody PartIDListing listing) {
        return service.saveListing(listing);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteListing(@PathVariable String id) {
        service.deleteListing(id);
        return ResponseEntity.noContent().build();
    }


}
