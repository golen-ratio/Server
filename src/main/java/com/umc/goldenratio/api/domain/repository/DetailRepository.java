package com.umc.goldenratio.api.domain.repository;

import com.umc.goldenratio.api.domain.entity.Detail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DetailRepository extends JpaRepository<Detail,Long> {
}
