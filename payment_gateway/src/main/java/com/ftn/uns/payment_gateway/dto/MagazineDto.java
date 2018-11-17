package com.ftn.uns.payment_gateway.dto;

import java.util.HashSet;
import java.util.Set;

public class MagazineDto {
    private String issn;
    private String title;
    private Set<Long> details = new HashSet<>();

    public MagazineDto() {
    }

    public MagazineDto(String issn, String title, Set<Long> details) {
        this.issn = issn;
        this.title = title;
        this.details = details;
    }

    public String getIssn() {
        return issn;
    }

    public void setIssn(String issn) {
        this.issn = issn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<Long> getDetails() {
        return details;
    }

    public void setDetails(Set<Long> details) {
        this.details = details;
    }
}
