package com.umc.goldenratio.api.service;

import com.umc.goldenratio.api.domain.entity.Board;
import com.umc.goldenratio.api.domain.entity.Gradient;
import com.umc.goldenratio.api.domain.entity.Mapping;
import com.umc.goldenratio.api.domain.repository.MappingRepository;
import com.umc.goldenratio.api.dto.request.GradientRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class MappingService {
    private final GradientService gradientService;
    private final MappingRepository mappingRepository;

    public void save(List<GradientRequestDto> gradientList, Board board) {
        if(gradientList.size() == 0) return;
        gradientList.stream()
                .map(gradient -> gradientService.findByGradient(gradient.getGradientName())
                        .orElseGet(() -> gradientService.save(gradient)))
                .forEach(gradient -> mapGradientToBoard(gradient, board));
    }

    public void update(List<GradientRequestDto> gradientList, Board board) {
        mappingRepository.deleteByBoard(board);
        save(gradientList, board);
    }

    private Long mapGradientToBoard(Gradient gradient, Board board) {
        Mapping mapping = new Mapping(board, gradient);
        return mappingRepository.save(mapping).getId();
    }
}
