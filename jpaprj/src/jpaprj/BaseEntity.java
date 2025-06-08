package jpaprj;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@MappedSuperclass
public abstract class BaseEntity {
	//@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    //private Long id;
	
	@CreationTimestamp
	@Column(updatable = false)
	private LocalDateTime createdTime;
	@UpdateTimestamp
	private LocalDateTime updatedTime;
}
