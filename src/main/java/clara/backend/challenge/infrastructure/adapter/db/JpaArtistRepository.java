package clara.backend.challenge.infrastructure.adapter.db;

import clara.backend.challenge.domain.model.Artist;
import clara.backend.challenge.domain.port.repository.ArtistRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaArtistRepository extends ArtistRepository, JpaRepository<Artist, Long> {
}
