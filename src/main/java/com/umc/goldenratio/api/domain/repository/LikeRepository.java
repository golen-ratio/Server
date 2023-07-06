package com.umc.goldenratio.api.domain.repository;

import com.umc.goldenratio.api.domain.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Like, Long> {
}
