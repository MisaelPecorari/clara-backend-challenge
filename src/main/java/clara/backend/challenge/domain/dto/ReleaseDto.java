package clara.backend.challenge.domain.dto;

import lombok.Builder;

@Builder
public record ReleaseDto(long id, String artist, String title, String type, int year) {}