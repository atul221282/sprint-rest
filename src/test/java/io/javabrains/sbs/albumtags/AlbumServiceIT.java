package io.javabrains.sbs.albumtags;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class AlbumServiceIT {

	@Autowired
	private AlbumService service;

	@Test
	public void findBySearchCriteria_withValidCriteria_returnAlbums() {
		assertNotNull(service);

		Album album = service.save(new Album("title", "desc", new Date(), null));
		assertNotNull(album.getId());

		List<Album> albums = service.findBySearchCriteria(new AlbumSearchCriteria("title", new Date(), "desc"));

		assertTrue(albums.size() > 0);
		assertTrue(albums.get(0).getTitle() == "title");

		albums = service.findBySearchCriteria(new AlbumSearchCriteria("title", new Date(), "desc2"));

		assertTrue(albums.size() <= 0);
	}

	@Test
	public void findBySearchCriteria_withInvalidCriteria_returnNoAlbums() {
		List<Album> albums = service.findBySearchCriteria(new AlbumSearchCriteria("asas", new Date(), ""));

		assertTrue(albums.size() <= 0);
	}

	@Test
	public void findByAlbumSearchCriteria_withValidCriteria_returnAlbumSummary() {
		List<AlbumSummaryDto> albums = service
				.findByAlbumSearchCriteria(new AlbumSearchCriteria("title", new Date(), "desc"));

		assertTrue(albums.size() > 0);
	}
	
	@Test
	public void findByAlbumSearchCriteria_withInvalidCriteria_returnNoAlbumSummary() {
		List<AlbumSummaryDto> albums = service
				.findByAlbumSearchCriteria(new AlbumSearchCriteria("asasas", new Date(), "qwqwq"));

		assertTrue(albums.size() <= 0);
	}

}
