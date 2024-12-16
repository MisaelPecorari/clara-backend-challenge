package clara.backend.challenge.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "artists")
@Builder
public class Artist {
    @Id
    private Long id;
    private String name;
    @Column(columnDefinition = "TEXT")
    private String profile;
}
