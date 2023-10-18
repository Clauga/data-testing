package com.wcs.datatesting.repository;

import com.wcs.datatesting.entity.Fire;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FireRepository extends JpaRepository<Fire, Long> {
}
