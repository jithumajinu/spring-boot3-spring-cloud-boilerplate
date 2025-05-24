package com.openapi.cloud.core.model.entities.audit;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.MappedSuperclass;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.*;


@NoArgsConstructor
@SuperBuilder
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@MappedSuperclass
public abstract class UserDateAudit extends DateAudit {

    @CreatedBy
    private Long createdBy;

    @LastModifiedBy
    private Long updatedBy;

    @LastModifiedBy
    private Long deletedBy;

}