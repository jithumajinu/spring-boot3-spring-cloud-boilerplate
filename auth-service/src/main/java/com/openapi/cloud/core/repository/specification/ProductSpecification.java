package com.openapi.cloud.core.repository.specification;

import com.google.common.collect.Lists;
//import io.crm.app.core.constant.DeleteFlag;
//import io.crm.app.entity.customer.CustomerEntity;
//import io.crm.app.entity.customer.CustomerEntity_;
//import io.crm.app.model.customer.CustomerPageCondition;
//import io.crm.app.model.customer.CustomerSortItem;
//import io.crm.app.repository.specification.AbstractSpecifications;
import com.openapi.cloud.core.model.dto.request.ProductPageCondition;
import com.openapi.cloud.core.model.dto.request.ProductSortBy;
import com.openapi.cloud.core.model.entities.Product;

import com.openapi.cloud.core.model.entities.Product_;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import jakarta.persistence.criteria.*;
//import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ProductSpecification extends AbstractSpecifications {

    public static Specification<Product> getPageByCondition(ProductPageCondition condition) {
        return (root, query, cb) -> {

//            if (StringUtils.isBlank(condition.getAccountId())) {
//                throw new IllegalArgumentException("account ID is null.");
//            }

            List<Predicate> predicates = new ArrayList<>();

            if (StringUtils.isNotBlank(condition.getKeyword())) {
                String keyword = String.format("%%%s%%", condition.getKeyword());

                System.out.println("----" + keyword);
                //  Predicate phonePredicate = cb.like(root.get(CustomerEntity_.phone), keyword);
                Predicate namePredicate = cb.like(root.get(Product_.name), keyword);
                // Predicate lastnamePredicate = cb.like(root.get(CustomerEntity_.lastName), keyword);
                //  Predicate companyPredicate = cb.like(root.get(CustomerEntity_.company), keyword);
                // Predicate suscribedPredicate = cb.equal(root.get(CustomerEntity_.mailUnsubscribed), true);

                Predicate combinedPredicate = cb.or(namePredicate);

                //  Predicate combinedPredicate = cb.or(phonePredicate, firstnamePredicate, lastnamePredicate, companyPredicate);

                //predicates.add(cb.or(cb.like(root.get(CustomerEntity_.phone), keyword)));
                //predicates.add(cb.or(cb.like(root.get(CustomerEntity_.phone), keyword)));

//                Expression<String> keywordSearchExpression = cb.concat(
//                        cb.concat(root.get(CustomerEntity_.firstName) , " "),
//                        root.get(CustomerEntity_.lastName)
//                        );
//                predicates.add(cb.like(keywordSearchExpression, keyword));
                predicates.add(combinedPredicate);
            }

            predicates.add(cb.equal(root.get(Product_.deleteFlag), deleteFlag));
            query.distinct(true);
            orderByCustomerSortItem(root, query, cb, condition.getSortItem());
            if (Objects.isNull(predicates)) {  // (predicates == null || predicates.size() == 0
                return null;
            }

//          return cb.and(predicates.toArray(new Predicate[predicates.size()]));
            return cb.and(predicates.toArray(Predicate[]::new));
        };
    }


    private static void orderByCustomerSortItem(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder cb,
                                                ProductSortBy sortItem) {
        if (Objects.isNull(sortItem)) {
            query.orderBy(cb.asc(root.get(Product_.id)));
        } else {
            switch (sortItem) {
                case CUSTOMER_ID_ASC:
                    query.orderBy(cb.asc(root.get(Product_.id)));
                    break;
                case CUSTOMER_ID_DESC:
                    query.orderBy(cb.desc(root.get(Product_.id)));
                    break;
                case CUSTOMER_NAME_ASC:
                    query.orderBy(cb.asc(root.get(Product_.name)));
                    break;
                case CUSTOMER_NAME_DESC:
                    query.orderBy(cb.desc(root.get(Product_.name)));
                    break;
//                case CREATED_TIMESTAMP_ASC:
//                    query.orderBy(cb.asc(root.get(CustomerEntity_.createTimestamp)));
//                    break;
//                case UPDATED_TIMESTAMP_DESC:
//                    query.orderBy(cb.desc(root.get(CustomerEntity_.updateTimestamp)));
//                    break;
                default:
                    query.orderBy(cb.asc(root.get(Product_.id)));
                    break;
            }
        }
    }

}
