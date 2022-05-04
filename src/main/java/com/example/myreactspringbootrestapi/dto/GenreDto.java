package com.example.myreactspringbootrestapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
@AllArgsConstructor
public class GenreDto {
    @NotBlank
    private String genre;
}
