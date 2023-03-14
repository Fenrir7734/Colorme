package com.fenrir.colorme.palette.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PaletteLikeRepository extends JpaRepository<PaletteLikeEntity, Long> {
    void deleteAllByPaletteId(Long id);
}
