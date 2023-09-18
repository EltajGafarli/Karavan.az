package com.karvan.dto.karavan;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class KaravanDto {
    private String karavanName;
    private String karavanNovu;
    private String location;
    private String neqliyyatIli;
    private String pricePerDay;
    private String sernisinSayi;

}
