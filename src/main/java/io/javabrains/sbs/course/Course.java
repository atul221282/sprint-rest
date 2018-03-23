package io.javabrains.sbs.course;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import io.javabrains.sbs.topic.Topic;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@Entity
@Table(name = "Course")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = Course.class)
public class Course {
	public Course() {
	}

	public Course(String description, String name) {
		this.description = description;
		this.name = name;
	}

	public Course(String description, String name, CourseDetail cd) {
		this(description, name);
		this.detail = cd;
	}

	@NonNull
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id")
	private Long id;

	@Version
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "Version", nullable = false)
	private Date version;

	@NonNull
	@Column(name = "Name")
	private String name;

	@NonNull
	@Column(name = "Description")
	private String description;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "TopicId")
	private Topic topic;

	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "CourseDetailId", nullable = true)
	private CourseDetail detail;
}