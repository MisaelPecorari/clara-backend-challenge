package clara.backend.challenge.domain.service;

import clara.backend.challenge.domain.exception.ArtistNotFoundException;
import clara.backend.challenge.domain.model.Artist;
import clara.backend.challenge.domain.model.ArtistReleaseStatistics;
import clara.backend.challenge.domain.port.mapper.ArtistReleaseStatisticsMapper;
import clara.backend.challenge.domain.port.repository.ArtistReleaseStatisticsRepository;
import clara.backend.challenge.domain.port.repository.ArtistRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ArtistComparisonServiceTest {

    @InjectMocks
    private ArtistComparisonService artistComparisonService;

    @Mock
    private ArtistRepository artistRepository;

    @Mock
    private ArtistReleaseStatisticsRepository artistReleaseStatisticsRepository;

    @Mock
    private ArtistReleaseStatisticsMapper mapper;

    @Test
    void given2ArtistId_whenCompare_andOneDoesNotExist_thenException() {
        var artistIds = List.of(1L, 2L);
        when(this.artistRepository.findAllById(artistIds)).thenReturn(List.of(mockArtist(1)));

        assertThrows(ArtistNotFoundException.class, () -> this.artistComparisonService.compare(artistIds),
                "At least one of the artists does not exist");
    }

    @Test
    void given2ArtistId_whenCompare_thenCalculate_andMap() throws ArtistNotFoundException{
        var artistIds = List.of(1L, 2L);
        when(this.artistRepository.findAllById(artistIds)).thenReturn(List.of(mockArtist(1), mockArtist(2)));
        List<ArtistReleaseStatistics> statistics = List.of();
        when(this.artistReleaseStatisticsRepository.calculate(artistIds)).thenReturn(statistics);
        when(this.mapper.toDto(statistics)).thenReturn(List.of());

        this.artistComparisonService.compare(artistIds);

        verify(this.artistReleaseStatisticsRepository, times(1)).calculate(anyList());
    }

    private Artist mockArtist(long artistId) {
        return Artist.builder().id(artistId).build();
    }



}