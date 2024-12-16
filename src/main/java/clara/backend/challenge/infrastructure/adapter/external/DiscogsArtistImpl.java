package clara.backend.challenge.infrastructure.adapter.external;

import clara.backend.challenge.domain.dto.ArtistDto;
import clara.backend.challenge.domain.dto.PageReleasesDto;
import clara.backend.challenge.domain.dto.SearchArtistDto;
import clara.backend.challenge.domain.dto.PageDto;
import clara.backend.challenge.domain.port.external.ArtistApi;
import clara.backend.challenge.infrastructure.configuration.DiscogsFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "discogs-artist-api", url = "${api.external.discogs.url}", configuration = DiscogsFeignConfig.class)
public interface DiscogsArtistImpl extends ArtistApi {

    @Override
    @GetMapping(path = "/database/search")
    PageDto<SearchArtistDto> search(@RequestParam(name = "q") String name,
                                    @RequestParam(name = "type") String type,
                                    @RequestParam(name = "per_page") int perPage,
                                    @RequestParam(name = "page") int page);

    @Override
    @GetMapping(path = "/artists/{artistId}")
    ArtistDto fetch(@PathVariable("artistId") long artistId);

    @Override
    @GetMapping(path = "/artists/{artistId}/releases")
    PageReleasesDto getDiscography(@PathVariable("artistId") long artistId,
                                   @RequestParam(name = "per_page") int perPage,
                                   @RequestParam(name = "page") int page);
}
