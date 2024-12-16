package clara.backend.challenge.infrastructure.adapter.db;

import clara.backend.challenge.domain.model.Release;
import clara.backend.challenge.domain.port.repository.ReleaseRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JpaReleaseRepository extends ReleaseRepository, JpaRepository<Release, Long> {

    @Override
    default List<Release> persistAll(Iterable<Release> releases) {
        return this.saveAll(releases);
    }

}
