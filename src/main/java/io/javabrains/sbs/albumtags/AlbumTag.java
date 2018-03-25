package io.javabrains.sbs.albumtags;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = AlbumTag.class)
public class AlbumTag {
	@NonNull
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id")
	private Long Id;

	@ManyToOne
	@JoinColumn(name = "AlbumId")
	private Album album;

	@ManyToOne
	@JoinColumn(name = "TagId")
	private Tag tag;

	@NonNull
	@Column(nullable = false, name = "CreatedDate")
	private Date createdDate;
}
