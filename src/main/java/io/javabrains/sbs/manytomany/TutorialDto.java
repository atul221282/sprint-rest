package io.javabrains.sbs.manytomany;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class TutorialDto {

	@NonNull
	private Long id;

	@NonNull
	private String name;

	@NonNull
	private Long studentId;

	@NonNull
	private String studentName;
}
