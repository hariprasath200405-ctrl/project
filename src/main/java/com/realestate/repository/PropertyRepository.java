package com.realestate.repository;

import com.realestate.entity.Property;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PropertyRepository extends JpaRepository<Property, Long> {

    List<Property> findByLocationContainingIgnoreCase(String location);

    List<Property> findByPropertyTypeIgnoreCase(String propertyType);

    List<Property> findByListingTypeIgnoreCase(String listingType);
}
