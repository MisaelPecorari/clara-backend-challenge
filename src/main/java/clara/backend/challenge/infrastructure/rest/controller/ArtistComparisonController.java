package clara.backend.challenge.infrastructure.rest.controller;

import clara.backend.challenge.domain.dto.ArtistReleaseStatisticsDto;
import clara.backend.challenge.domain.exception.ArtistNotFoundException;
import clara.backend.challenge.domain.service.ArtistComparisonService;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class ArtistComparisonController {

    private final ArtistComparisonService artistComparisonService;

    @GetMapping(path = "/comparison", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ArtistReleaseStatisticsDto> compare(@RequestParam(name = "ids") @Size(min = 1, max = 10, message = "The comparator must take between 1 and 10 artists") List<Long> artistIds) throws ArtistNotFoundException {
        return this.artistComparisonService.compare(artistIds);
    }

}
