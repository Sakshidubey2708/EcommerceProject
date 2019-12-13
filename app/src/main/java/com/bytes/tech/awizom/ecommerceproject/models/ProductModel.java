package com.bytes.tech.awizom.ecommerceproject.models;

public class ProductModel {

    public long ProductId ;
    public String ModelName ;
    public double MRP;
    public double AssuredPrice ;
    public double TotalDiscounts ;
    public long BrandId;
    public long CategoryId;
    public String highlights ;
    public String Img1;




    public String ProductName;
    public String TypeWeight ;

    public long MainCatId ;
    public long SubCatId ;
    public long TypeSubCatId ;
    public String HighlightsDesign ;
    public String Descriptions ;

    public long getProductId() {
        return ProductId;
    }

    public void setProductId(long productId) {
        ProductId = productId;
    }

    public String getModelName() {
        return ModelName;
    }

    public void setModelName(String modelName) {
        ModelName = modelName;
    }

    public double getMRP() {
        return MRP;
    }

    public void setMRP(double MRP) {
        this.MRP = MRP;
    }

    public double getAssuredPrice() {
        return AssuredPrice;
    }

    public void setAssuredPrice(double assuredPrice) {
        AssuredPrice = assuredPrice;
    }

    public double getTotalDiscounts() {
        return TotalDiscounts;
    }

    public void setTotalDiscounts(double totalDiscounts) {
        TotalDiscounts = totalDiscounts;
    }

    public long getBrandId() {
        return BrandId;
    }

    public void setBrandId(long brandId) {
        BrandId = brandId;
    }

    public long getCategoryId() {
        return CategoryId;
    }

    public void setCategoryId(long categoryId) {
        CategoryId = categoryId;
    }

    public String getHighlights() {
        return highlights;
    }

    public void setHighlights(String highlights) {
        this.highlights = highlights;
    }

    public String getImg1() {
        return Img1;
    }

    public void setImg1(String img1) {
        Img1 = img1;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public String getTypeWeight() {
        return TypeWeight;
    }

    public void setTypeWeight(String typeWeight) {
        TypeWeight = typeWeight;
    }

    public long getMainCatId() {
        return MainCatId;
    }

    public void setMainCatId(long mainCatId) {
        MainCatId = mainCatId;
    }

    public long getSubCatId() {
        return SubCatId;
    }

    public void setSubCatId(long subCatId) {
        SubCatId = subCatId;
    }

    public long getTypeSubCatId() {
        return TypeSubCatId;
    }

    public void setTypeSubCatId(long typeSubCatId) {
        TypeSubCatId = typeSubCatId;
    }

    public String getHighlightsDesign() {
        return HighlightsDesign;
    }

    public void setHighlightsDesign(String highlightsDesign) {
        HighlightsDesign = highlightsDesign;
    }

    public String getDescriptions() {
        return Descriptions;
    }

    public void setDescriptions(String descriptions) {
        Descriptions = descriptions;
    }
}
