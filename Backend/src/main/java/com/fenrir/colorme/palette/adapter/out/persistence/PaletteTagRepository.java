package com.fenrir.colorme.palette.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

interface PaletteTagRepository extends JpaRepository<PaletteTagEntity, PaletteTagEntity.Id> {
    void deleteAllByPaletteCode(String code);
}
