package clara.backend.challenge.infrastructure.adapter.mapper;

import clara.backend.challenge.domain.dto.ArtistDto;
import clara.backend.challenge.domain.model.Artist;
import clara.backend.challenge.domain.port.mapper.ArtistMapper;
import org.springframework.stereotype.Component;

@Component
public class ArtistMapperImpl implements ArtistMapper {

    @Override
    public ArtistDto toDto(Artist artist) {
        return ArtistDto.builder()
                .id(artist.getId())
                .name(artist.getName())
                .profile(artist.getProfile())
                .build();
    }

    @Override
    public Artist toModel(ArtistDto artistDto) {
        return Artist.builder()
                .id(artistDto.id())
                .name(artistDto.name())
                .profile(artistDto.profile())
                .build();
    }
}
