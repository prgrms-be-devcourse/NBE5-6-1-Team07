package com.grepp.team07.infra.payload;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class PageParam{
    @Min(1)
    private int page = 1;
    @Min(1)
    private int size = 3;
}