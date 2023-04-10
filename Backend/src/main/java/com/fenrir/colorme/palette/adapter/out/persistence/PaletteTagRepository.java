package com.fenrir.colorme.palette.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

interface PaletteTagRepository extends JpaRepository<PaletteTagEntity, PaletteTagEntity.Id> {
    List<PaletteTagEntity> findAllByPaletteCode(String code);
    void deleteAllByPaletteCode(String code);
}
