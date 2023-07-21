package com.umc.goldenratio.api.domain.repository;

import com.umc.goldenratio.api.domain.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {
    //카테고리를 기반으로 게시판을 검색할 때 필요함
    //List<Board> findByCategoriesContaining(String category);

    // 도수순서대로 정렬해서 게시판을 가져옴
    @Query("SELECT b FROM Board b JOIN b.details d ORDER BY d.alcohol DESC")
    List<Board> findAllByOrderByAlcoholDesc();

    // 단맛순서대로 정렬해서 게시판을 가져옴
    @Query("SELECT b FROM Board b JOIN b.details d ORDER BY d.sweet ASC")
    List<Board> findAllByOrderBySweetlASC();

    // 칵테일 게시판 중 별점순서로 정렬해서 게시판을 가져옴
    @Query("SELECT b FROM Board b WHERE b.category = '칵테일' ORDER BY b.averageScore DESC")
    List<Board> findAllByCocktailOrderByAverageScoreDesc();
}
