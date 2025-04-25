package com.grepp.team07.app.model.product.dto;

import com.grepp.team07.infra.util.file.FileDto;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductImgDto {

    private Integer imageId;
    private Integer productId;
    private String originalFileName;
    private String renameFileName;
    private String savePath;
    private LocalDateTime createdAt;
    private Boolean activated;

    public ProductImgDto(Integer productId, FileDto fileDto) {
        this.productId = productId;
        this.originalFileName = fileDto.originFileName();
        this.renameFileName = fileDto.renameFileName();
        this.savePath = fileDto.savePath();
    }

    public String getUrl() {
        return "/download/" + savePath + renameFileName;
    }
}
