package clara.backend.challenge.domain.port.repository;

import clara.backend.challenge.domain.model.ArtistReleaseStatistics;

import java.util.List;

public interface ArtistReleaseStatisticsRepository {

    List<ArtistReleaseStatistics> calculate(List<Long> artistsIds);
}
