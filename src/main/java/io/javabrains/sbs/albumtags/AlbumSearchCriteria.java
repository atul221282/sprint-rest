package io.javabrains.sbs.albumtags;

import java.util.Date;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class AlbumSearchCriteria {

	@NonNull
	private String title;
	@NonNull
	private Date createdOn;
	@NonNull
	private String description;
}
