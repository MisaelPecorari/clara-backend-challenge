package clara.backend.challenge.domain.port.mapper;

import clara.backend.challenge.domain.dto.ArtistReleaseStatisticsDto;
import clara.backend.challenge.domain.model.ArtistReleaseStatistics;

import java.util.List;

public interface ArtistReleaseStatisticsMapper {

    ArtistReleaseStatisticsDto toDto(ArtistReleaseStatistics statistics);

    List<ArtistReleaseStatisticsDto> toDto(List<ArtistReleaseStatistics> statistics);

}
