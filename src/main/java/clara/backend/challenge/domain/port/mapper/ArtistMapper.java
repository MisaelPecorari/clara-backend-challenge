package clara.backend.challenge.domain.port.mapper;

import clara.backend.challenge.domain.dto.ArtistDto;
import clara.backend.challenge.domain.model.Artist;

public interface ArtistMapper {

    ArtistDto toDto(Artist artist);

    Artist toModel(ArtistDto artistDto);
}
