package com.karvan.service.karavan;

import com.karvan.dto.karavan.FullKaravanInformationDto;
import com.karvan.dto.karavan.KaravanDto;
import com.karvan.dto.karavan.TexnikiXususiyyetlerDto;
import com.karvan.entity.karavan.Karavan;
import com.karvan.filter.KaravanSpecification;
import com.karvan.mapper.KaravanMapper;
import com.karvan.mapper.TexnikiXususiyyetlerMapper;
import com.karvan.repository.karavan.KaravanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class KaravanService {

    private final KaravanRepository karavanRepository;
    private final KaravanMapper karavanMapper;
    private final TexnikiXususiyyetlerMapper texnikiXususiyyetlerMapper;

    public List<KaravanDto> getKaravans() {
        return karavanRepository.findAll().stream()
                .map(karavanMapper::karavanToKaravanDto)
                .toList();
    }

    @Transactional
    public void addKaravan(KaravanDto dto) {
        Karavan karavan = karavanMapper.karavanDtoToKaravan(dto);
        karavanRepository.save(karavan);
    }


    public KaravanDto getKaravanById(long id) {
        return karavanRepository
                .findById(id)
                .map(karavanMapper::karavanToKaravanDto)
                .orElseThrow();
    }

    public List<KaravanDto> filterKaravans(String karavanNovu, int minSerSayi, int maxSerSayi, String location, BigDecimal minPrice, BigDecimal maxPrice) {
        Specification<Karavan> filterByKaravanNovu = KaravanSpecification.filterByKaravanNovu(karavanNovu);
        Specification<Karavan> filterByLocation = KaravanSpecification.filterByLocation(location);
        Specification<Karavan> filterByMinAndMaxPrice = KaravanSpecification.filterByMinAndMaxPrice(minPrice, maxPrice);
        Specification<Karavan> filterBySernisinSayi = KaravanSpecification.filterBySernisinSayi(minSerSayi, maxSerSayi);
        Specification<Karavan> specification =
                Specification.where(filterByKaravanNovu)
                        .and(filterByLocation)
                        .and(filterByMinAndMaxPrice)
                        .and(filterBySernisinSayi);

        List<Karavan> allKaravans = this.karavanRepository.findAll(specification);

        return allKaravans
                .stream()
                .map(karavanMapper::karavanToKaravanDto)
                .toList();

    }


    public List<KaravanDto> getNewAddedKaravans() {

        List<Karavan> allOrderByCreatedAtDesc = this.karavanRepository.findAllOrderByCreatedAtDesc();


        return allOrderByCreatedAtDesc
                .stream()
                .map(karavanMapper::karavanToKaravanDto)
                .toList();
    }



    public FullKaravanInformationDto getFullKaravanDtoInformation(long id) {
        Karavan karavan = this.karavanRepository.findById(id).orElseThrow();
        KaravanDto karavanDto = karavanMapper.karavanToKaravanDto(karavan);
        TexnikiXususiyyetlerDto texnikiXususiyyetlerDto = this.texnikiXususiyyetlerMapper.texnikiToTexnikiDto(karavan.getTexnikiXususiyyetler());

        return FullKaravanInformationDto
                .builder()
                .karavanDto(karavanDto)
                .texnikiXususiyyetlerDto(texnikiXususiyyetlerDto)
                .build();
    }
}
