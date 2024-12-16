package clara.backend.challenge.infrastructure.adapter.db;

import clara.backend.challenge.domain.model.ArtistReleaseStatistics;
import clara.backend.challenge.domain.model.Release;
import clara.backend.challenge.domain.port.repository.ArtistReleaseStatisticsRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JpaArtistReleaseStatisticsRepository extends ArtistReleaseStatisticsRepository, JpaRepository<Release, Long> {

    @Override
    @Query(value = "SELECT artist_id AS artistId, " +
            "       COUNT(id) AS releaseCount, " +
            "       MIN(release_year) AS firstReleaseYear, " +
            "       MAX(release_year) AS mostRecentReleaseYear " +
            "FROM artist_releases " +
            "WHERE artist_id IN :artistIds " +
            "GROUP BY artist_id",
            nativeQuery = true)
    List<ArtistReleaseStatistics> calculate(@Param("artistIds") List<Long> artistsIds);

}
