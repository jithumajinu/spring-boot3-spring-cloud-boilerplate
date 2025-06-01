package com.openapi.cloud.core.model.entities.audit;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.openapi.cloud.core.constants.DeleteFlag;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.LastModifiedDate;

import java.io.Serializable;
import java.time.LocalDateTime;

@NoArgsConstructor
@SuperBuilder
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@MappedSuperclass
public abstract class DateAudit implements Serializable {

    @Builder.Default
    @Column(name = "delete_flag", nullable = false)
    @Enumerated(EnumType.ORDINAL)
    @JsonIgnore
    private DeleteFlag deleteFlag = DeleteFlag.ACTIVE;

    @JsonIgnore
    @LastModifiedDate
    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    @JsonIgnore
    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @JsonIgnore
    @LastModifiedDate
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();

        if (deleteFlag == DeleteFlag.DELETED && deletedAt == null) {
            deletedAt = LocalDateTime.now();
        }
    }

}
