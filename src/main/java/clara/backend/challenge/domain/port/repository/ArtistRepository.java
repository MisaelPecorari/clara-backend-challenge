package clara.backend.challenge.domain.port.repository;

import clara.backend.challenge.domain.model.Artist;

import java.util.List;

public interface ArtistRepository {

    List<Artist> findAllById(Iterable<Long> artistIds);

    Artist findById(long id);

    Artist save(Artist artist);
}
