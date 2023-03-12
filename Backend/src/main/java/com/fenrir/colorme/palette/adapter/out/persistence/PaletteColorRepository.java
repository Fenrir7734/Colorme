package com.fenrir.colorme.palette.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

interface PaletteColorRepository extends JpaRepository<PaletteColorEntity, Long> {
}
