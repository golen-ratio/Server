package com.umc.goldenratio.api.domain.repository;

import com.umc.goldenratio.api.domain.entity.Balance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BalanceRepository extends JpaRepository<Balance, Long> {
}
