package com.prowal.infrastructure.config.db.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.prowal.infrastructure.config.db.schema.category.CategorySchema;

@Repository
public interface CategoryRepository extends JpaRepository<CategorySchema, Long> {
	
	@Query("SELECT c FROM categories c INNER JOIN c.user u WHERE u.id = :userId")
	List<CategorySchema> findByUserId(@Param("userId") Long userId);
}
