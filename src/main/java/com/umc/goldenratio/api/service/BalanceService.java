package com.umc.goldenratio.api.service;

import com.umc.goldenratio.api.domain.entity.Balance;
import com.umc.goldenratio.api.domain.entity.Board;
import com.umc.goldenratio.api.domain.repository.BalanceRepository;
import com.umc.goldenratio.api.dto.request.BalanceRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class BalanceService {

    private final BalanceRepository balanceRepository;


    public void save(List<BalanceRequestDto> balances, Board board){
        for(BalanceRequestDto balance : balances){
            balanceRepository.save(Balance.toEntity(balance.getBalanceName(), balance.getBalanceNum(), board));
        }
    }

    public void update(List<BalanceRequestDto> balanceList, Board board) {
        balanceRepository.deleteByBoard(board);
        save(balanceList, board);
    }
}
