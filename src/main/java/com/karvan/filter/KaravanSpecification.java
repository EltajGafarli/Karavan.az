package com.karvan.filter;

import com.karvan.entity.karavan.Karavan;
import com.karvan.entity.user.User;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;


public class KaravanSpecification {

    public static Specification<Karavan> filterByKaravanNovu(String karavanNovu) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("karavanNovu"), karavanNovu);
    }

    public static Specification<Karavan> filterBySernisinSayi(int min, int max) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.between(root.get("sernisinSayi"), min, max);
    }

    public static Specification<Karavan> filterByLocation(String location) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("location"), location);
    }

    public static Specification<Karavan>  filterByMinAndMaxPrice(BigDecimal min, BigDecimal max) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.between(root.get("BigDecimal"), min, max);
    }

}
