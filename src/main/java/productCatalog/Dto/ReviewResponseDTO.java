package productCatalog.Dto;

import lombok.Data;

@Data
public class ReviewResponseDTO {
    private Long review_Id;
    private Long productId;
    private String comment;
    private Integer rating;
} 