package com.green.memo.entity;

import org.hibernate.annotations.DynamicInsert;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity // JPA에서 관리되는 엔티티 객체임을 의미함.
@Table(name = "tbl_memo")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder // @AllArgs + @NoArgs 함께 처리해야 함.
@DynamicInsert
public class Memo extends BaseEntity{
	
	@Id //반드시 지정해야 함.
	@GeneratedValue(strategy = GenerationType.IDENTITY) //PK(key) 생성전략
	private Integer mno;
	
	@Column(length = 200, nullable = false)
	private String memoText;
	
	@Column(columnDefinition = "varchar(50) default 'NOMAL'")
	private String level = "NORMAL";
	
	@ToString.Exclude
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id") //필수 연관관계 @ManyToOne(optional = false) 와 같음.
	private Member member;
	
	@Transient //테이블 컬럼으로 생성하지 않기. 
	private String etc;
}

/*
 * 키 생성전략
 * - AUTO(default) - JPA 구현체(스프링부트에서는 Hibernate)가 생성 방식을 결정
 * - IDENTITY - 사용하는 데이터베이스가 키 생성을 결정. MySQL이나 MariaDB의 경우 auto_increment 방식을 이용
 * - SEQUENCE - 데이터베이스의 sequence를 이용해서 키를 생성. @SequenceGenerator와 같이 사용
 * - TABLE - 키 생성 전용 테이블을 생성해서 키 생성. @TableGenerator와 함께 사용
 */
