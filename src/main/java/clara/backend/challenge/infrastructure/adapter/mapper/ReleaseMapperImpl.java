package clara.backend.challenge.infrastructure.adapter.mapper;

import clara.backend.challenge.domain.dto.ReleaseDto;
import clara.backend.challenge.domain.model.Artist;
import clara.backend.challenge.domain.model.Release;
import clara.backend.challenge.domain.port.mapper.ReleaseMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ReleaseMapperImpl implements ReleaseMapper {

    @Override
    public Release toModel(ReleaseDto releaseDto, Artist artist) {
        return Release.builder()
                .id(releaseDto.id())
                .releaseYear(releaseDto.year())
                .releaseType(releaseDto.type())
                .title(releaseDto.title())
                .artist(artist)
                .build();
    }

    @Override
    public ReleaseDto toDto(Release release) {
        return ReleaseDto.builder()
                .artist(release.getArtist().getName())
                .id(release.getId())
                .title(release.getTitle())
                .type(release.getReleaseType())
                .year(release.getReleaseYear())
                .build();
    }

    @Override
    public List<Release> toModel(List<ReleaseDto> releaseDtoList, Artist artist) {
        return releaseDtoList.stream()
                        .map(releaseDto -> this.toModel(releaseDto, artist))
                                .toList();
    }

    @Override
    public List<ReleaseDto> toDto(List<Release> releases) {
        return releases.stream()
                .map(this::toDto)
                .toList();
    }
}
