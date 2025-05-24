package com.openapi.cloud.core.model.entities;

import com.openapi.cloud.core.model.entities.audit.DateAudit_;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Product.class)
public class Product_ extends DateAudit_ {

    public static volatile SingularAttribute<Product, Long> id;

    public static volatile SingularAttribute<Product, String> name;

}
