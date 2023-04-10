package com.fenrir.colorme.palette.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

interface PaletteLikeRepository extends JpaRepository<PaletteLikeEntity, Long> {
    List<PaletteLikeEntity> findAllByPaletteCode(String code);
    void deleteAllByPaletteCode(String code);
}
