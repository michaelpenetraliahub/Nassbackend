package com.jtb.taxpayerws.repository.specs;

import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CustomSpecification<T> implements Specification<T> {

    private List<SearchCriteria> criteriaList;

    public CustomSpecification() {
        this.criteriaList = new ArrayList<>();
    }

    public void add(SearchCriteria criteria) {
        criteriaList.add(criteria);
    }

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        List<Predicate> predicates = new ArrayList<>();

        criteriaList.forEach(x -> {
            switch (x.getOperation()){
                case GREATER_THAN: predicates.add(cb.greaterThan(root.get(x.getKey()), (LocalDate) x.getValue())); break;
                case LESS_THAN: predicates.add(cb.lessThan(root.get(x.getKey()), (LocalDate) x.getValue())); break;
                case GREATER_THAN_EQUAL: predicates.add(cb.greaterThanOrEqualTo(root.get(x.getKey()), (LocalDate) x.getValue())); break;
                case LESS_THAN_EQUAL: predicates.add(cb.lessThanOrEqualTo(root.get(x.getKey()),(LocalDate) x.getValue())); break;
                case EQUALS: predicates.add(cb.equal(root.get(x.getKey()), x.getValue().toString()));
            }
        });

        return cb.and(predicates.toArray(new Predicate[0]));
    }
}
