package com.seek.test.candidates.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.UUID;


@Entity
@Table(name = "candidates")

@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class Candidate {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private UUID id;
	@Column(name = "NAME")
	private String name;
	@Column(name = "EMAIL", unique = true)
	private String email;
	@Column(name = "GENDER")
	private String gender;
	@Column(name = "EXPECTED_SALARY")
	private double expectedSalary;
	@Column(name = "YEARS_EXPERIENCE")
	private int yearsExperience;
	@CreationTimestamp
	private LocalDateTime created;
	@UpdateTimestamp
	private LocalDateTime modified;

	@PrePersist
	protected void onCreate() {
		created = LocalDateTime.now();
		modified = created;
	}

	@PreUpdate
	protected void onUpdate() {
		modified = LocalDateTime.now();
	}


}
