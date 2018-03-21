package io.javabrains.sbs.course;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import io.javabrains.sbs.topic.Topic;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@Entity
@Table(name = "Course")
public class Course {
	public Course() {
	}

	@NonNull
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="Id")
	private Long id;
	
	@NonNull
	@Column(name="Name")
	private String name;
	@NonNull
	@Column(name="Description")
	private String description;

	@ManyToOne
	@JoinColumn(name = "TopicId")
	@JsonBackReference
	private Topic topic;
}
