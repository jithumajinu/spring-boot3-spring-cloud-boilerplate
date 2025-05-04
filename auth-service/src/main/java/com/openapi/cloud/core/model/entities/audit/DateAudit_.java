package com.openapi.cloud.core.model.entities.audit;

import com.openapi.cloud.core.constants.DeleteFlag;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(DateAudit.class)
public class DateAudit_ {

    public static volatile SingularAttribute<DateAudit, DeleteFlag> deleteFlag;


}
