package com.umc.goldenratio.api.domain.repository;

import com.umc.goldenratio.api.domain.entity.Mapping;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MappingRepository extends JpaRepository<Mapping, Long> {
}
