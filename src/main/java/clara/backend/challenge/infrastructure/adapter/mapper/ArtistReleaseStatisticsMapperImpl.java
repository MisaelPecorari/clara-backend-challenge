package clara.backend.challenge.infrastructure.adapter.mapper;

import clara.backend.challenge.domain.dto.ArtistReleaseStatisticsDto;
import clara.backend.challenge.domain.model.ArtistReleaseStatistics;
import clara.backend.challenge.domain.port.mapper.ArtistReleaseStatisticsMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ArtistReleaseStatisticsMapperImpl implements ArtistReleaseStatisticsMapper {

    @Override
    public ArtistReleaseStatisticsDto toDto(ArtistReleaseStatistics statistics) {
        return ArtistReleaseStatisticsDto.builder()
                .artistId(statistics.getArtistId())
                .numberOfReleases(statistics.getReleaseCount())
                .activeYears(Integer.sum(statistics.getMostRecentReleaseYear(), -statistics.getFirstReleaseYear()))
                .build();
    }

    @Override
    public List<ArtistReleaseStatisticsDto> toDto(List<ArtistReleaseStatistics> statistics) {
        return statistics.stream()
                .map(this::toDto)
                .toList();
    }
}
