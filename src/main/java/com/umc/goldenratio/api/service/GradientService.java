package com.umc.goldenratio.api.service;

import com.umc.goldenratio.api.domain.entity.Gradient;
import com.umc.goldenratio.api.domain.repository.GradientRepository;
import com.umc.goldenratio.api.dto.request.GradientRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GradientService {

    private final GradientRepository gradientRepository;

    public Optional<Gradient> findByGradient(String gradientName) {
        return gradientRepository.findByGradientName(gradientName);
    }

    public Gradient save(GradientRequestDto gradient) {
        return gradientRepository.save(Gradient.toEntity(gradient.getGradientName(), gradient.getGradientImageUrl()));
    }
}
