package com.fenrir.colorme.palette.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

interface PaletteLikeRepository extends JpaRepository<PaletteLikeEntity, Long> {
    void deleteAllByPaletteCode(String code);
}
