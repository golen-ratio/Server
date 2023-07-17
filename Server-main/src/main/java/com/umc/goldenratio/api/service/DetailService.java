package com.umc.goldenratio.api.service;

import com.umc.goldenratio.api.domain.entity.Board;
import com.umc.goldenratio.api.domain.entity.Detail;
import com.umc.goldenratio.api.domain.repository.DetailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DetailService {

    private final DetailRepository detailRepository;

    public void save(int sweet, int alcohol, Board board) {
        detailRepository.save(Detail.toEntity(sweet, alcohol, board));
    }
}
