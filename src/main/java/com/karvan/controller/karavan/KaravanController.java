package com.karvan.controller.karavan;

import com.karvan.dto.karavan.FullKaravanInformationDto;
import com.karvan.dto.karavan.KaravanDto;
import com.karvan.entity.karavan.Karavan;
import com.karvan.filter.KaravanSpecification;
import com.karvan.service.karavan.KaravanService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping(path = "/karavan")
@RequiredArgsConstructor
public class KaravanController {

    private final KaravanService karavanService;

    @GetMapping
    public List<KaravanDto> getKaravans() {
        return karavanService.getKaravans();
    }

    @PostMapping
    public void addKaravan(@RequestBody KaravanDto karavanDto) {
        karavanService.addKaravan(karavanDto);
    }

    @GetMapping(path = "/{id}")
    public FullKaravanInformationDto getFullKaravanInfo(@PathVariable long id) {
        return this.karavanService.getFullKaravanDtoInformation(id);
    }

    public List<KaravanDto> filterKaravans(
            @RequestParam("karavanNovu") String karavanNovu,
            @RequestParam("serSayiMin") int minSerSayi,
            @RequestParam("serSayMax") int maxSerSayi,
            @RequestParam("location") String location,
            @RequestParam("minPrice") BigDecimal minPrice,
            @RequestParam("maxPrice") BigDecimal maxPrice
            ) {


        return this.karavanService
                .filterKaravans(
                        karavanNovu,
                        minSerSayi,
                        maxSerSayi,
                        location,
                        minPrice,
                        maxPrice);
    }


    @GetMapping("/latest")
    public List<KaravanDto> getLatestKaravans() {
        return this.karavanService.getNewAddedKaravans();
    }


}
