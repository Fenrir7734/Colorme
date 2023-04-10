package com.fenrir.colorme.palette.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

interface PaletteColorRepository extends JpaRepository<PaletteColorEntity, Long> {
    List<PaletteColorEntity> findAllByPaletteCode(String code);
    void deleteAllByPaletteCode(String code);
}
