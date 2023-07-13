package com.umc.goldenratio.api.service;

import com.umc.goldenratio.api.domain.entity.Board;
import com.umc.goldenratio.api.domain.entity.Users;
import com.umc.goldenratio.api.domain.repository.BalanceRepository;
import com.umc.goldenratio.api.domain.repository.BoardRepository;
import com.umc.goldenratio.api.domain.repository.UsersRepository;
import com.umc.goldenratio.api.dto.request.CocktailRequestDto;
import com.umc.goldenratio.exception.CustomException;
import com.umc.goldenratio.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final UsersRepository usersRepository;
    private final MappingService mappingService;
    private final BalanceService balanceService;
    private final DetailService detailService;

    @Transactional
    public void createCocktail(Authentication authentication, CocktailRequestDto cocktailRequestDto) {
        Users users = usersRepository.findByUserId(authentication.getName()).orElseThrow(() -> new CustomException(ErrorCode.USERID_NOT_FOUND));
        Board board = Board.toEntity(cocktailRequestDto.getTitle(),
                cocktailRequestDto.getContent(),
                cocktailRequestDto.getCocktailMainImageUrl(),
                cocktailRequestDto.getCategory(), users);

        boardRepository.save(board);
        mappingService.save(cocktailRequestDto.getGradientList(), board);
        balanceService.save(cocktailRequestDto.getBalanceList(), board);
        detailService.save(cocktailRequestDto.getSweet(),cocktailRequestDto.getAlcohol(), board);
    }
}
