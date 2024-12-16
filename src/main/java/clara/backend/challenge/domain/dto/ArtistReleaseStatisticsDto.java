package clara.backend.challenge.domain.dto;

import lombok.Builder;

@Builder
public record ArtistReleaseStatisticsDto(long artistId, long numberOfReleases, int activeYears) {}
