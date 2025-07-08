package com.example.ai_pgv.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "items")
@Setter
@Getter
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // pgvector는 float[]로 매핑 가능 (JPA는 기본 지원 안하지만 SQL 사용 가능)
    @Column(columnDefinition = "vector(3)")
    private float[] embedding;
}