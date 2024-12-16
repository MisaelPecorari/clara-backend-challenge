package clara.backend.challenge.domain.service;

import clara.backend.challenge.domain.dto.PageReleasesDto;
import clara.backend.challenge.domain.dto.ReleaseDto;
import clara.backend.challenge.domain.model.Artist;
import clara.backend.challenge.domain.model.Release;
import clara.backend.challenge.domain.port.external.ArtistApi;
import clara.backend.challenge.domain.port.mapper.ReleaseMapper;
import clara.backend.challenge.domain.port.repository.ReleaseRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DiscographyServiceTest {

    @InjectMocks
    private DiscographyService discographyService;

    @Mock
    private ArtistApi artistApi;

    @Mock
    private ArtistService artistService;

    @Mock
    private ReleaseRepository releaseRepository;


    @Mock
    private ReleaseMapper releaseMapper;

    @Test
    void givenDiscographyInRepository_whenGet_thenFetchFromRepository() {
        var artistId = 1;
        var releases = List.of(mockRelease(1), mockRelease(2));
        var releasesDto = List.of(mockReleaseDto(1), mockReleaseDto(2));
        when(this.releaseRepository.findAllByArtistIdOrderByReleaseYearAsc(artistId)).thenReturn(releases);
        when(this.releaseMapper.toDto(releases)).thenReturn(releasesDto);

        var discography = this.discographyService.getDiscography(artistId);

        assertEquals(releasesDto, discography);
        verifyNoInteractions(this.artistApi);
    }

    @Test
    void givenDiscographyNotInRepository_andArtistNotPresent_whenGet_thenGetArtist_andDiscographyFromApi_andSortByReleaseDate() {
        var artistId = 1;
        var artist = mockArtist(artistId);
        when(this.releaseRepository.findAllByArtistIdOrderByReleaseYearAsc(artistId)).thenReturn(List.of());
        when(this.artistService.getArtist(artistId)).thenReturn(artist);

        var releases = List.of(mockRelease(2000), mockRelease(1999));
        var releasesDto = List.of(mockReleaseDto(2000), mockReleaseDto(1999));
        var pageReleaseDto = mockPageReleasesDto(releasesDto);
        when(this.artistApi.getDiscography(artistId, 500, 1)).thenReturn(pageReleaseDto);
        when(this.releaseMapper.toModel(releasesDto, artist)).thenReturn(releases);

        when(this.releaseMapper.toDto(any(Release.class))).thenAnswer(answer -> {
            var model = (Release) answer.getArgument(0);
            return mockReleaseDto(model.getReleaseYear());
        });

        var discography = this.discographyService.getDiscography(artistId);

        assertEquals(2, discography.size());
        assertEquals(1999, discography.get(0).year());
        assertEquals(2000, discography.get(1).year());
        verify(this.releaseRepository, times(1)).persistAll(releases);
    }

    private Release mockRelease(int releaseYear) {
        return Release.builder().releaseYear(releaseYear).build();
    }

    private ReleaseDto mockReleaseDto(int year) {
        return ReleaseDto.builder().year(year).build();
    }

    private Artist mockArtist(long artistId) {
        return Artist.builder().id(artistId).build();
    }

    private PageReleasesDto mockPageReleasesDto(List<ReleaseDto> releaseDtoList) {
        return PageReleasesDto.builder().releases(releaseDtoList).build();
    }

}