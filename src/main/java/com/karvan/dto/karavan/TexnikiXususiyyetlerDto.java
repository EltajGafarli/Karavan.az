package com.karvan.dto.karavan;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class TexnikiXususiyyetlerDto {

    private String yanacaqNovu;
    private String Istehlak;
    private Double yanacaqCeninHecmi;
    private Double avtomobilHundurluk;
    private Double avtomobilUzunlugu;
    private Double avtomobilEni;
    private Double temizSuCenininHecmi;
    private Double tullantiSUAnbarininHecmi;

}
