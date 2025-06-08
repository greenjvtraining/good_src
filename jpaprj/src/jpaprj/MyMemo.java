package jpaprj;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Entity
@Data
//@EntityListeners(AuditListener.class)
public class MyMemo {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer mno;
	private String memo;

	@CreationTimestamp // Hibernate가 자동 처리
	@Column(updatable = false)
	private LocalDateTime createdAt;

	@UpdateTimestamp // Hibernate가 자동 처리
	private LocalDateTime updatedAt;

}
