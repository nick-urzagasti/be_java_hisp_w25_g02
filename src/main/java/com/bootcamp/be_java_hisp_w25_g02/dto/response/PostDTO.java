package com.bootcamp.be_java_hisp_w25_g02.dto.response;

import java.time.LocalDate;

public record PostDTO(
        Integer user_id,
        LocalDate date,
        ProductDTO product,
        Integer category,
        Double price
) { }
