package com.hardwareInfo.hardwareInfo.repositories;

import com.hardwareInfo.hardwareInfo.entities.GraphicsCardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

@Repository
public interface GraphicsCardRepository extends JpaRepository<GraphicsCardEntity, Long> {
    @Query("SELECT gc FROM GraphicsCardEntity gc WHERE LOWER(gc.modelName) LIKE LOWER(CONCAT('%', :modelName, '%'))")
    Page<GraphicsCardEntity> searchGraphicsCardsByModelName(@Param("modelName") String modelName, Pageable pageable);

    @Query("SELECT gc FROM GraphicsCardEntity gc " +
            "JOIN FETCH gc.gpuModel gm " +
            "JOIN FETCH gc.vramType vt " +
            "JOIN FETCH gc.vramCapacity vc " +
            "JOIN FETCH gc.subVendor sv " +
            "JOIN FETCH gc.tdp tdp " +
            "WHERE (:gpuModelId IS NULL OR gm.id = :gpuModelId) " +
            "AND (:vramTypeId IS NULL OR vt.id = :vramTypeId) " +
            "AND (:vramCapacityId IS NULL OR vc.vramCapacity = :vramCapacityValue) " +
            "AND (:vramFrequency IS NULL OR gc.vramFrequency = :vramFrequency) " +
            "AND (:gpuFrequency IS NULL OR gc.gpuFrequency = :gpuFrequency) " +
            "AND (:tdpValue IS NULL OR tdp.tdpValue = :tdpValue)")
    Page<GraphicsCardEntity> filterGraphicsCards(@Param("gpuModelId") Long gpuModelId,
                                                 @Param("vramTypeId") Long vramTypeId,
                                                 @Param("vramCapacityId") Integer vramCapacityId,
                                                 @Param("vramFrequency") Double vramFrequency,
                                                 @Param("gpuFrequency") Double gpuFrequency,
                                                 @Param("tdpValue") Integer tdpValue,
                                                 Pageable pageable);

    @Query("SELECT gc FROM GraphicsCardEntity gc " +
            "JOIN FETCH gc.gpuModel gm " +
            "JOIN FETCH gc.vramType vt " +
            "JOIN FETCH gc.vramCapacity vc " +
            "JOIN FETCH gc.subVendor sv " +
            "JOIN FETCH gc.tdp tdp " +
            "ORDER BY " +
            "CASE WHEN :orderBy = 'vramCapacity' THEN vc.vramCapacity END DESC, " +
            "CASE WHEN :orderBy = 'vramFrequency' THEN gc.vramFrequency END DESC, " +
            "CASE WHEN :orderBy = 'gpuFrequency' THEN gc.gpuFrequency END DESC, " +
            "CASE WHEN :orderBy = 'tdp' THEN tdp.tdpValue END DESC, " +
            "CASE WHEN :orderBy = 'releaseDate' THEN gc.releaseDate END DESC, " +
            "CASE WHEN :orderBy = 'vramType' THEN vt.vramType END ASC, " +
            "CASE WHEN :orderBy = 'subVendor' THEN sv.gpuVendor END ASC, " +
            "CASE WHEN :orderBy = 'gpuModel' THEN gm.gpuName END ASC")
    Page<GraphicsCardEntity> sortGraphicsCards(@Param("orderBy") String orderBy, Pageable pageable);
}