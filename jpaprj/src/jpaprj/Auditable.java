package jpaprj;

import java.time.LocalDateTime;

public interface Auditable {
	void setCreatedDate(LocalDateTime date);
    void setCreatedBy(String user);
    void setModifiedDate(LocalDateTime date);
    void setModifiedBy(String user);
}
