package com.umc.goldenratio.api.domain.repository;

import com.umc.goldenratio.api.domain.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
