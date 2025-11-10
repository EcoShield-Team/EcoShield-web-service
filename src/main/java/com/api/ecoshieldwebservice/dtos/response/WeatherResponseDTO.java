package com.api.ecoshieldwebservice.dtos.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WeatherResponseDTO {
    private String location;
    private String description;
    private Double tempMin;
    private Double tempMax;
    private String rainProb;
    private String weatherIcon;
    private String precipIcon;
}
