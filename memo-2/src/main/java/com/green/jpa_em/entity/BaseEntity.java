package com.green.jpa_em.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.ToString;

@EntityListeners(value = {AuditingEntityListener.class})
@MappedSuperclass
@Getter
@ToString
public abstract class BaseEntity {

	@CreatedDate
	@Column(name = "regdate", updatable = false)
	private LocalDateTime createdAt;
	@LastModifiedDate
	@Column(name = "moddate")
	private LocalDateTime modifiedAt;
}
