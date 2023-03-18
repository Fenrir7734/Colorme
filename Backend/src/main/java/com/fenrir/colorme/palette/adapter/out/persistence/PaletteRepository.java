package com.fenrir.colorme.palette.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

interface PaletteRepository extends JpaRepository<PaletteEntity, Long> {
    Optional<PaletteEntity> findByCode(String code);
    boolean existsByCode(String code);
    void deleteByCode(String code);
}
