package clara.backend.challenge.domain.port.mapper;

import clara.backend.challenge.domain.dto.ReleaseDto;
import clara.backend.challenge.domain.model.Artist;
import clara.backend.challenge.domain.model.Release;

import java.util.List;

public interface ReleaseMapper {

    Release toModel(ReleaseDto releaseDto, Artist artist);

    ReleaseDto toDto(Release release);

    List<Release> toModel(List<ReleaseDto> releaseDtoList, Artist artist);

    List<ReleaseDto> toDto(List<Release> releases);

}
