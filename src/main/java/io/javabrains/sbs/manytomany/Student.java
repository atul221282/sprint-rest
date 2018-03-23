package io.javabrains.sbs.manytomany;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
public class Student {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NonNull
	@Column(name = "Id")
	private Long id;

	@NonNull
	@Column(name = "Name")
	private String name;

	@ManyToMany()
	@JoinTable(name = "StudentTutorial", joinColumns = @JoinColumn(name = "StudentId"), inverseJoinColumns = @JoinColumn(name = "TutorialId"))
	private List<Tutorial> tutorials;
}
