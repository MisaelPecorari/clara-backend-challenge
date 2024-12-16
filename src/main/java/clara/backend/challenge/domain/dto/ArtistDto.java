package clara.backend.challenge.domain.dto;

import lombok.Builder;

@Builder
public record ArtistDto(long id, String name, String profile) {}
