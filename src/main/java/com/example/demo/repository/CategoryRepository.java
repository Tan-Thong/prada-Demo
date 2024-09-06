package com.example.demo.repository;

import com.example.demo.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    @Query("select c from Category c where c.name like %?1%")
    public List<Category> searchCategory(String keyWord);

    @Query("select c from Category c where c.parent is null")
    public List<Category> getCategoryNoParent();

    @Query("select c from Category c where c.parent is not null")
    public List<Category> getCategoryHaveParent();

    @Query("select c from Category c where c.parent.name = ?1 and c.gender != ?2")
    public List<Category> searchCategoryByParentNameAndGenderNot(String ParentName, int gender);

    @Query("select c from Category c where c.id != ?1 and c.parent is null")
    List<Category> findByIdNot(Integer id);
}
