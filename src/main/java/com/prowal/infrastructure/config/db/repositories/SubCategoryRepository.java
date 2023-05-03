package com.prowal.infrastructure.config.db.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.prowal.infrastructure.config.db.schema.subcategory.SubCategorySchema;

@Repository
public interface SubCategoryRepository extends JpaRepository<SubCategorySchema, Long>{

	@Query("SELECT s FROM SubCategorySchema s INNER JOIN s.category c WHERE c.id = :categoryId")
	List<SubCategorySchema> findByCategoryId(@Param("categoryId") Long categoryId);
}
