package com.labprog.labprog.DTO;

import com.labprog.labprog.model.entities.Categories;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SubCategoryDTO {

    private Categories category;
    private String subCategoryName;
    private String subCategoryDescription;

}
