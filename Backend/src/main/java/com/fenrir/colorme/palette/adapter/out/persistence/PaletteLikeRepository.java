package com.fenrir.colorme.palette.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

interface PaletteLikeRepository extends JpaRepository<PaletteLikeEntity, Long> {
    List<PaletteLikeEntity> findAllByPaletteCode(String code);
    PaletteLikeEntity findByPaletteCodeAndIdUserId(String paletteCode, Long userId);
    void deleteAllByPaletteCode(String code);
    boolean existsByPaletteCodeAndIdUserId(String paletteCode, Long userId);

    @Query("select ple.palette.code from PaletteLikeEntity as ple where ple.id.userId = :userId")
    List<String> findAllUserLikedPalettesCodes(Long userId);
}
