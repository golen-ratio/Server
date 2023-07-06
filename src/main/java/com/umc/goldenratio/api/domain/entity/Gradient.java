package com.umc.goldenratio.api.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Gradient {
    @Id
    @GeneratedValue
    @Column(name = "gradient_id")
    private Long id;

    @Column(name = "gradient_name")
    private String gradient_name;

    @Column(name = "gradient_image_url")
    private String gradientImageUrl;

    @OneToMany(mappedBy = "gradient", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Mapping> mappings = new ArrayList<>();
}
