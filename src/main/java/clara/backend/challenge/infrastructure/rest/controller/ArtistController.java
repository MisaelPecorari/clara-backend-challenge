package clara.backend.challenge.infrastructure.rest.controller;

import clara.backend.challenge.domain.dto.ReleaseDto;
import clara.backend.challenge.domain.dto.SearchArtistDto;
import clara.backend.challenge.domain.dto.PageDto;
import clara.backend.challenge.domain.service.ArtistService;
import clara.backend.challenge.domain.service.DiscographyService;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class ArtistController {

    private final ArtistService artistService;
    private final DiscographyService discographyService;

    @GetMapping(path = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
    public PageDto<SearchArtistDto> search(@RequestParam(name = "name") @NotEmpty(message = "Name cannot be empty") String name,
                                           @RequestParam(name = "page_size", required = false, defaultValue = "100") @Min(value = 1, message = "Page size must be at least 1") @Max(value = 100, message = "Page size must be at most 100") int pageSize,
                                           @RequestParam(name = "page", required = false, defaultValue = "1") @Min(value = 1, message = "Page number must be at least 1") int page) {
        return this.artistService.search(name, pageSize, page);
    }

    @GetMapping(path = "/artists/{artistId}/releases", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ReleaseDto> getReleases(@PathVariable("artistId") @Min(value = 0, message = "Artist id cannot be negative") long artistId) {
        return this.discographyService.getDiscography(artistId);
    }

}
