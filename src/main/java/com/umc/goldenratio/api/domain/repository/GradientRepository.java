package com.umc.goldenratio.api.domain.repository;

import com.umc.goldenratio.api.domain.entity.Gradient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GradientRepository extends JpaRepository<Gradient, Long> {
    Optional<Gradient> findByGradientName(String name);
}
