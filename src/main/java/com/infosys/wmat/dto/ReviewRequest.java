package com.infosys.wmat.dto;

public class ReviewRequest {

    private Long productId;
    private Integer rating;
    private String comment;

    // --- CONSTRUCTORS ---
    public ReviewRequest() {}

    public ReviewRequest(Long productId, Integer rating, String comment) {
        this.productId = productId;
        this.rating = rating;
        this.comment = comment;
    }

    // --- GETTERS AND SETTERS ---
    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}