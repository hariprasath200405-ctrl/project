package com.realestate.service;

import com.realestate.entity.Property;
import com.realestate.repository.PropertyRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PropertyService {

    private final PropertyRepository repository;

    public PropertyService(PropertyRepository repository) {
        this.repository = repository;
    }

    public List<Property> getAllProperties() {
        return repository.findAll();
    }

    public Optional<Property> getPropertyById(Long id) {
        return repository.findById(id);
    }

    public Property addProperty(Property property) {
        return repository.save(property);
    }

    public Property updateProperty(Long id, Property property) {

        Property existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Property not found"));

        existing.setTitle(property.getTitle());
        existing.setLocation(property.getLocation());
        existing.setPropertyType(property.getPropertyType());
        existing.setListingType(property.getListingType());
        existing.setPrice(property.getPrice());
        existing.setBedrooms(property.getBedrooms());
        existing.setBathrooms(property.getBathrooms());
        existing.setArea(property.getArea());
        existing.setDescription(property.getDescription());

        return repository.save(existing);
    }

    public void deleteProperty(Long id) {
        repository.deleteById(id);
    }

    public List<Property> searchByLocation(String location) {
        return repository.findByLocationContainingIgnoreCase(location);
    }
}
