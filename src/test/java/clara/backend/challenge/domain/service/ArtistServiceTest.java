package clara.backend.challenge.domain.service;

import clara.backend.challenge.domain.dto.ArtistDto;
import clara.backend.challenge.domain.dto.PageDto;
import clara.backend.challenge.domain.model.Artist;
import clara.backend.challenge.domain.port.external.ArtistApi;
import clara.backend.challenge.domain.port.mapper.ArtistMapper;
import clara.backend.challenge.domain.port.repository.ArtistRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ArtistServiceTest {

    @InjectMocks
    private ArtistService artistService;

    @Mock
    private ArtistApi artistApi;

    @Mock
    private ArtistRepository artistRepository;

    @Mock
    private ArtistMapper artistMapper;


    @Test
    void whenSearch_thenInvokeArtistApi() {
        var searchParam = "nirvana";
        var pageSize = 10;
        var page = 1;
        var mockedResponse = Mockito.mock(PageDto.class);
        when(this.artistApi.search(searchParam, "artist", pageSize, page))
                .thenReturn(mockedResponse);

        var result = this.artistService.search(searchParam, pageSize, page);

        assertEquals(mockedResponse, result);
        verify(this.artistApi, Mockito.times(1)).search(searchParam, "artist", pageSize, page);
    }

    @Test
    void givenArtistNotInRepository_whenGet_thenFetchFromApiAndSave() {
        var artistId = 1;
        var artistDto = mockArtistDto(artistId);
        var artist = mockArtist(artistId);
        when(this.artistRepository.findById(artistId)).thenReturn(null);
        when(this.artistApi.fetch(artistId)).thenReturn(artistDto);
        when(this.artistMapper.toModel(artistDto)).thenReturn(artist);
        when(this.artistRepository.save(artist)).thenReturn(artist);

        var result = this.artistService.getArtist(artistId);

        assertEquals(artist, result);
        verify(this.artistApi, times(1)).fetch(artistId);
        verify(this.artistRepository, times(1)).save(artist);
    }

    @Test
    void givenArtistInRepository_whenGet_thenEarlyReturn() {
        var artistId = 1;
        var artist = mockArtist(artistId);
        when(this.artistRepository.findById(artistId)).thenReturn(artist);

        var result = this.artistService.getArtist(artistId);

        assertEquals(artist, result);
        verify(this.artistApi, times(0)).fetch(artistId);
        verify(this.artistRepository, times(0)).save(artist);
    }

    private ArtistDto mockArtistDto(long artistId) {
        return ArtistDto.builder()
                .id(artistId).build();
    }

    private Artist mockArtist(long artistId) {
        return Artist.builder().id(artistId).build();
    }


}