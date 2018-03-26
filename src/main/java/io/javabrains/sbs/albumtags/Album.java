package io.javabrains.sbs.albumtags;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

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
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = Album.class)
public class Album {

	public Album(String title, String description, Date createdOn, List<AlbumTag> albumTags) {
		this.title = title;
		this.description = description;
		this.createdOn = createdOn;
		this.albumTags = albumTags;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NonNull
	@Column(name = "Id")
	private Long id;

	@Version
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "Version", nullable = false)
	private Date version;

	@NonNull
	@Column(name = "Title")
	private String title;

	@NonNull
	@Column(name = "Description")
	private String description;

	@NonNull
	@Column(name = "CreatedOn")
	private Date createdOn;

	@OneToMany(mappedBy = "tag", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<AlbumTag> albumTags;
}
