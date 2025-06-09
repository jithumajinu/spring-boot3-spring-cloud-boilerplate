package com.example.acid.web.model.audit;

import com.example.acid.web.constants.DeleteFlag;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(DateAudit.class)
public class DateAudit_ {

    public static SingularAttribute<DateAudit, DeleteFlag> deleteFlag;

}
