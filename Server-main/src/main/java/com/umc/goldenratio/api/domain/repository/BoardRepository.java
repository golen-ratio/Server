package com.umc.goldenratio.api.domain.repository;

import com.umc.goldenratio.api.domain.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {
    //카테고리를 기반으로 게시판을 검색할 때 필요함
    //List<Board> findByCategoriesContaining(String category);
}
