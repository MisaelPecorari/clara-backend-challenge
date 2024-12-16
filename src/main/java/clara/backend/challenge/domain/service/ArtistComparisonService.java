package clara.backend.challenge.domain.service;

import clara.backend.challenge.domain.dto.ArtistReleaseStatisticsDto;
import clara.backend.challenge.domain.exception.ArtistNotFoundException;
import clara.backend.challenge.domain.port.mapper.ArtistReleaseStatisticsMapper;
import clara.backend.challenge.domain.port.repository.ArtistRepository;
import clara.backend.challenge.domain.port.repository.ArtistReleaseStatisticsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ArtistComparisonService {

    private final ArtistRepository artistRepository;
    private final ArtistReleaseStatisticsRepository artistReleaseStatisticsRepository;
    private final ArtistReleaseStatisticsMapper mapper;

    public List<ArtistReleaseStatisticsDto> compare(List<Long> artistIds) throws ArtistNotFoundException {
        var artists = this.artistRepository.findAllById(artistIds);
        if (artists.size() != artistIds.size()) {
            throw new ArtistNotFoundException("At least one of the artists does not exist");
        }

        var statistics = this.artistReleaseStatisticsRepository.calculate(artistIds);
        return this.mapper.toDto(statistics);
    }
}
