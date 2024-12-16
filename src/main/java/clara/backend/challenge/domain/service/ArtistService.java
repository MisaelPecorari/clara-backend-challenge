package clara.backend.challenge.domain.service;

import clara.backend.challenge.domain.dto.PageDto;
import clara.backend.challenge.domain.dto.SearchArtistDto;
import clara.backend.challenge.domain.port.mapper.ArtistMapper;
import clara.backend.challenge.domain.model.Artist;
import clara.backend.challenge.domain.port.external.ArtistApi;
import clara.backend.challenge.domain.port.repository.ArtistRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ArtistService {

    private static final String ARTIST_TYPE = "artist";

    private final ArtistApi artistApi;
    private final ArtistRepository artistRepository;
    private final ArtistMapper artistMapper;

    public PageDto<SearchArtistDto> search(String name, int perPage, int page) {
        return this.artistApi.search(name, ARTIST_TYPE, perPage, page);
    }

    public Artist getArtist(long artistId) {
        var artist = this.artistRepository.findById(artistId);
        if (artist == null) {
            var artistDto = this.artistApi.fetch(artistId);
            artist = this.artistMapper.toModel(artistDto);
            artist = this.artistRepository.save(artist);
        }
        return artist;
    }

}
