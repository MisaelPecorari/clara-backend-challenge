package clara.backend.challenge.infrastructure.rest.controller;

import clara.backend.challenge.domain.dto.ReleaseDto;
import clara.backend.challenge.domain.port.repository.ReleaseRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ArtistControllerIntegrationTest {

    @Autowired
    private ReleaseRepository releaseRepository;

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    @Test
    void whenGetDiscography_thenFetchApi_andSave() {
        var artistId = 125249L;
        assertEquals(List.of(), releaseRepository.findAllByArtistIdOrderByReleaseYearAsc(artistId));
        var url = String.format("http://localhost:%s/artists/%s/releases", port, artistId);
        var response = restTemplate.exchange(url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<ReleaseDto>>(){});
        var releases = response.getBody();

        var responseFromRepository = releaseRepository.findAllByArtistIdOrderByReleaseYearAsc(artistId);
        assertEquals(responseFromRepository.size(), releases.size());
        for (int i=0; i < releases.size(); i++) {
            assertEquals(responseFromRepository.get(i).getId(), releases.get(i).id());
        }
    }

}