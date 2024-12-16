package clara.backend.challenge.domain.dto;

import java.util.List;

public record PageDto<T>(PaginationDto pagination, List<T> results) {}
