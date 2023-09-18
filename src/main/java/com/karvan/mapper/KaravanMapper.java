package com.karvan.mapper;

import com.karvan.dto.karavan.KaravanDto;
import com.karvan.entity.karavan.Karavan;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE
)
public interface KaravanMapper {

    Karavan karavanDtoToKaravan(KaravanDto dto);

    KaravanDto karavanToKaravanDto(Karavan karavan);
}
