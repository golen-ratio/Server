package com.umc.goldenratio.api.service;

import com.umc.goldenratio.api.domain.entity.Board;
import com.umc.goldenratio.api.domain.entity.Detail;
import com.umc.goldenratio.api.domain.repository.DetailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class DetailService {

    private final DetailRepository detailRepository;

    public void save(int sweet, int alcohol, Board board) {
        detailRepository.save(Detail.toEntity(sweet, alcohol, board));
    }

    public void update(int sweet, int alcohol, Board board) {
        Detail detail = detailRepository.findByBoard(board);
        detail.update(sweet, alcohol, board);
        detailRepository.save(detail);
    }
}
