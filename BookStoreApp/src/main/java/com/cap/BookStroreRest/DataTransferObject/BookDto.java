package com.cap.BookStroreRest.DataTransferObject;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class BookDto {
    @NotBlank(message = "at least give the title")
    private String title;

    @NotBlank(message = "at least on author")
    private String author;


    @Min(value = 100,message = "the price should be greater than 100")
    private double price;

}
