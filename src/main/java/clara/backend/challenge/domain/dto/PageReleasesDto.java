package clara.backend.challenge.domain.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record PageReleasesDto(PaginationDto pagination, List<ReleaseDto> releases) {}
