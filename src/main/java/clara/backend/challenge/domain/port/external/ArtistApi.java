package clara.backend.challenge.domain.port.external;

import clara.backend.challenge.domain.dto.ArtistDto;
import clara.backend.challenge.domain.dto.PageReleasesDto;
import clara.backend.challenge.domain.dto.SearchArtistDto;
import clara.backend.challenge.domain.dto.PageDto;

public interface ArtistApi {

    ArtistDto fetch(long artistId);

    PageDto<SearchArtistDto> search(String name, String type, int perPage, int page);

    PageReleasesDto getDiscography(long artistId, int perPage, int page);
}
