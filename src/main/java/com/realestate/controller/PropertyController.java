package com.realestate.controller;

import com.realestate.entity.Property;
import com.realestate.service.PropertyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/properties")
@CrossOrigin
public class PropertyController {

    private final PropertyService service;

    public PropertyController(PropertyService service) {
        this.service = service;
    }

    @GetMapping
    public List<Property> getAllProperties() {
        return service.getAllProperties();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Property> getProperty(@PathVariable Long id) {
        return service.getPropertyById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Property addProperty(@RequestBody Property property) {
        return service.addProperty(property);
    }

    @PutMapping("/{id}")
    public Property updateProperty(
            @PathVariable Long id,
            @RequestBody Property property) {

        return service.updateProperty(id, property);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProperty(@PathVariable Long id) {

        service.deleteProperty(id);

        return ResponseEntity.ok("Property deleted successfully");
    }

    @GetMapping("/search")
    public List<Property> search(
            @RequestParam String location) {

        return service.searchByLocation(location);
    }
}
