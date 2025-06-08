package jpaprj;

import java.time.LocalDateTime;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

public class AuditListener {
	@PrePersist
    public void setCreatedOn(Object entity) {
        if (entity instanceof Auditable) {
            Auditable auditable = (Auditable) entity;
            auditable.setCreatedDate(LocalDateTime.now());
            auditable.setCreatedBy("SYSTEM");
        }
    }
    
    @PreUpdate
    public void setUpdatedOn(Object entity) {
        if (entity instanceof Auditable) {
            Auditable auditable = (Auditable) entity;
            auditable.setModifiedDate(LocalDateTime.now());
            auditable.setModifiedBy("SYSTEM");
        }
    }
}
