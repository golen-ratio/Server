package com.umc.goldenratio.api.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BalanceRequestDto {
    private String balanceName;
    private int balanceNum;
}
