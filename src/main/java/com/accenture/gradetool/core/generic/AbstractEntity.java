package com.accenture.gradetool.core.generic;

import com.accenture.gradetool.domain.subject.user.User;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Entity
@EntityListeners({AuditingEntityListener.class})
public abstract class AbstractEntity {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @Column(name = "id")
    protected String id;

    protected boolean deleted;

    @CreatedDate
    @Column(name = "created_at")
    protected LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "created_by")
    @CreatedBy
    protected User createdBy;

    @LastModifiedDate
    @Column(name = "modified_at")
    protected LocalDateTime modifiedAt;

    @ManyToOne
    @JoinColumn(name = "last_modified_by")
    @LastModifiedBy
    protected User lastModifiedBy;

    public String getId() {
        return id;
    }

    public AbstractEntity setId(String id) {
        this.id = id;
        return this;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public AbstractEntity setDeleted(boolean deleted) {
        this.deleted = deleted;
        return this;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public AbstractEntity setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public AbstractEntity setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public LocalDateTime getModifiedAt() {
        return modifiedAt;
    }

    public AbstractEntity setModifiedAt(LocalDateTime modifiedAt) {
        this.modifiedAt = modifiedAt;
        return this;
    }

    public User getLastModifiedBy() {
        return lastModifiedBy;
    }

    public AbstractEntity setLastModifiedBy(User lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
        return this;
    }
}
