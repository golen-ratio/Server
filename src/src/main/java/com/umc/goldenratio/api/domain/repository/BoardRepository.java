package com.umc.goldenratio.api.domain.repository;

import com.umc.goldenratio.api.domain.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {
}
