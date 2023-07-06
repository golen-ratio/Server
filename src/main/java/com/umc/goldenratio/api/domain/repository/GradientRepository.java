package com.umc.goldenratio.api.domain.repository;

import com.umc.goldenratio.api.domain.entity.Gradient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GradientRepository extends JpaRepository<Gradient, Long> {
}
