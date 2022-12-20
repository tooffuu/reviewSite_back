package com.project.reviewSite_backend.detail.dto;

import com.project.reviewSite_backend.detail.domain.Detail;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Getter
@NoArgsConstructor
public class DetailDto {

    @Column(unique = true)
    private String detail_id;
    private String detail_name;

    public DetailDto (Detail detail) {
        this.detail_id = detail.getDetail_id();
        this.detail_name = detail.getDetail_name();
    }
}
