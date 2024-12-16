package clara.backend.challenge.domain.port.repository;

import clara.backend.challenge.domain.model.Release;

import java.util.List;

public interface ReleaseRepository {

    List<Release> persistAll(Iterable<Release> releases);

    List<Release> findAllByArtistIdOrderByReleaseYearAsc(long artistId);
}
