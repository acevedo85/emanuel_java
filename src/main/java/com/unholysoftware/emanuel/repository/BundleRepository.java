package com.unholysoftware.emanuel.repository;

import com.unholysoftware.emanuel.model.Bundle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BundleRepository extends JpaRepository<Bundle, Long> {
}
