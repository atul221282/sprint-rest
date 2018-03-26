package io.javabrains.sbs.albumtags;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class AlbumSummaryDto {

	@NonNull
	private Long id;

	@NonNull
	private String title;

	@NonNull
	private String description;
}
