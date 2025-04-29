package com.openapi.cloud.core.model.entities;

// import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@NoArgsConstructor
@SuperBuilder
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@MappedSuperclass
public abstract class AuditableEntity {

    @Builder.Default
    @Column(name = "delete_flag", nullable = false)
    @JsonIgnore
    private boolean deleteFlag = false;
    //  private DeleteFlag deleteFlag = DeleteFlag.VALID;

    @JsonIgnore
    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    @JsonIgnore
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @JsonIgnore
    @Column(name = "created_at")
    private LocalDateTime createdAt;

}