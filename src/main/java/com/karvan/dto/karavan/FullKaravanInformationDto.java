package com.karvan.dto.karavan;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class FullKaravanInformationDto {

    private KaravanDto karavanDto;
    private TexnikiXususiyyetlerDto texnikiXususiyyetlerDto;
}
