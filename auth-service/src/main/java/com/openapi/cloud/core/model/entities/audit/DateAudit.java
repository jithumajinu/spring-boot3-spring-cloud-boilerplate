package com.openapi.cloud.core.model.entities.audit;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.openapi.cloud.core.constants.DeleteFlag;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@NoArgsConstructor
@SuperBuilder
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@MappedSuperclass
public abstract class DateAudit {

//    @Builder.Default
//    @Column(name = "delete_flag", nullable = false)
//    @JsonIgnore
//    private boolean deleteFlag = false;
//    // private DeleteFlag deleteFlag = DeleteFlag.VALID;


    @Builder.Default
    @Column(name = "delete_flag", nullable = false)
    @Enumerated(EnumType.ORDINAL)
    @JsonIgnore
    private DeleteFlag deleteFlag = DeleteFlag.VALID;

    @JsonIgnore
    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    @JsonIgnore
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @JsonIgnore
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
    }

}

/*

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.time.Instant;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(
        value = {"createdAt", "updatedAt"},
        allowGetters = true
)
public abstract class DateAudit implements Serializable {

    @CreatedDate
    private Instant createdAt;

    @LastModifiedDate
    private Instant updatedAt;

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

}


 */