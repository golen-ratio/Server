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
@AllArgsConstructor
@NoArgsConstructor
public class Gradient {
    @Id
    @GeneratedValue
    @Column(name = "gradient_id")
    private Long id;

    @Column(name = "gradient_name")
    private String gradientName;

    @Column(name = "gradient_image_url")
    private String gradientImageUrl;

    @OneToMany(mappedBy = "gradient", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Mapping> mappings = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    @Builder
    public Gradient(String gradientName, String gradientImageUrl) {
        this.gradientName = gradientName;
        this.gradientImageUrl = gradientImageUrl;
    }

    public static Gradient toEntity(String gradientName, String gradientImageUrl) {
        return Gradient.builder()
                .gradientName(gradientName)
                .gradientImageUrl(gradientImageUrl)
                .build();
    }

    public void add(Gradient gradient) {
    }
}
