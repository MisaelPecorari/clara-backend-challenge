package clara.backend.challenge.domain.model;

public interface ArtistReleaseStatistics {
    Long getArtistId();
    Long getReleaseCount();
    Integer getFirstReleaseYear();
    Integer getMostRecentReleaseYear();
}
