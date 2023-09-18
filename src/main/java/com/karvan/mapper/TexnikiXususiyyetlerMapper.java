package com.karvan.mapper;

import com.karvan.dto.karavan.TexnikiXususiyyetlerDto;
import com.karvan.entity.karavan.TexnikiXususiyyetler;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE
)
public interface TexnikiXususiyyetlerMapper {

    TexnikiXususiyyetlerDto texnikiToTexnikiDto(TexnikiXususiyyetler texnikiXususiyyetler);
}
