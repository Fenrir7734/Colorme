package com.fenrir.colorme.palette.adapter.out.persistence;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "palettes")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
class PaletteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "BPCHAR",
            insertable = false,
            updatable = false)
    private String code;

    @CreationTimestamp
    private LocalDateTime createdAt;

    private Long ownerId;

    @OneToMany(mappedBy = "palette", fetch = FetchType.LAZY)
    private List<PaletteColorEntity> colors = new ArrayList<>();

    @OneToMany(mappedBy = "palette", fetch = FetchType.LAZY)
    private List<PaletteLikeEntity> likes = new ArrayList<>();

    @OneToMany(mappedBy = "palette", fetch = FetchType.LAZY)
    private List<PaletteTagEntity> tags = new ArrayList<>();
}
