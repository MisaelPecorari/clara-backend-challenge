package clara.backend.challenge.domain.service;

import clara.backend.challenge.domain.dto.ReleaseDto;
import clara.backend.challenge.domain.model.Artist;
import clara.backend.challenge.domain.model.Release;
import clara.backend.challenge.domain.port.external.ArtistApi;
import clara.backend.challenge.domain.port.mapper.ReleaseMapper;
import clara.backend.challenge.domain.port.repository.ReleaseRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@AllArgsConstructor
@Service
public class DiscographyService {

    private final ArtistApi artistApi;
    private final ReleaseRepository releaseRepository;
    private final ArtistService artistService;
    private final ReleaseMapper releaseMapper;

    public List<ReleaseDto> getDiscography(long artistId) {
        var discography = fetchDiscographySortedByReleaseYear(artistId);
        if (!discography.isEmpty()) {
            return discography;
        }
        var artist = this.artistService.getArtist(artistId);
        return fetchAndSaveDiscographyFromApi(artist);
    }

    private List<ReleaseDto> fetchDiscographySortedByReleaseYear(long artistId) {
        var discography = this.releaseRepository.findAllByArtistIdOrderByReleaseYearAsc(artistId);
        if (discography != null && !discography.isEmpty()) {
            return this.releaseMapper.toDto(discography);
        }
        return List.of();
    }

    private List<ReleaseDto> fetchAndSaveDiscographyFromApi(Artist artist) {
        var releaseDtoPage = this.artistApi.getDiscography(artist.getId(), 500, 1);
        var releases = this.releaseMapper.toModel(releaseDtoPage.releases(), artist);
        this.releaseRepository.persistAll(releases);
        return releases.stream()
                .sorted(Comparator.comparingInt(Release::getReleaseYear))
                .map(this.releaseMapper::toDto)
                .toList();
    }

}
