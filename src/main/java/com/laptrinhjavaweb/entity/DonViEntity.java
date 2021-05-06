package com.laptrinhjavaweb.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "dmdonvi")
public class DonViEntity {
	@Id
	private Long iddv;
	
	public Long getIddv() {
		return iddv;
	}
	public void setIddv(Long iddv) {
		this.iddv = iddv;
	}
	public String getTendv() {
		return tendv;
	}
	public void setTendv(String tendv) {
		this.tendv = tendv;
	}
	public Long getCapdv() {
		return capdv;
	}
	public void setCapdv(Long capdv) {
		this.capdv = capdv;
	}
	public String getMaldv() {
		return maldv;
	}
	public void setMaldv(String maldv) {
		this.maldv = maldv;
	}
	public Long getIddvcha() {
		return iddvcha;
	}
	public void setIddvcha(Long iddvcha) {
		this.iddvcha = iddvcha;
	}
	public String getDiachi() {
		return diachi;
	}
	public void setDiachi(String diachi) {
		this.diachi = diachi;
	}
	public String getDienthoai() {
		return dienthoai;
	}
	public void setDienthoai(String dienthoai) {
		this.dienthoai = dienthoai;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public String getGhichu() {
		return ghichu;
	}
	public void setGhichu(String ghichu) {
		this.ghichu = ghichu;
	}
	public String getMadv() {
		return madv;
	}
	public void setMadv(String madv) {
		this.madv = madv;
	}
	public String getMadv_old() {
		return madv_old;
	}
	public void setMadv_old(String madv_old) {
		this.madv_old = madv_old;
	}
	public Boolean getLatochuckhcn() {
		return latochuckhcn;
	}
	public void setLatochuckhcn(Boolean latochuckhcn) {
		this.latochuckhcn = latochuckhcn;
	}
	public String getGioithieutochuc() {
		return gioithieutochuc;
	}
	public void setGioithieutochuc(String gioithieutochuc) {
		this.gioithieutochuc = gioithieutochuc;
	}
	public String getFileanh() {
		return fileanh;
	}
	public void setFileanh(String fileanh) {
		this.fileanh = fileanh;
	}
	public String getWebsitekhcn() {
		return websitekhcn;
	}
	public void setWebsitekhcn(String websitekhcn) {
		this.websitekhcn = websitekhcn;
	}
	public Date getNgay_tao_st() {
		return ngay_tao_st;
	}
	public void setNgay_tao_st(Date ngay_tao_st) {
		this.ngay_tao_st = ngay_tao_st;
	}
	public Date getNgay_cap_nhat_st() {
		return ngay_cap_nhat_st;
	}
	public void setNgay_cap_nhat_st(Date ngay_cap_nhat_st) {
		this.ngay_cap_nhat_st = ngay_cap_nhat_st;
	}
	public Long getTrang_thai_st() {
		return trang_thai_st;
	}
	public void setTrang_thai_st(Long trang_thai_st) {
		this.trang_thai_st = trang_thai_st;
	}
	@Column(name = "tendv")
	private String tendv;
	@Column(name = "capdv")
	private Long capdv;
	
	@Column(name = "maldv")
	private String maldv;
	@Column(name = "iddvcha")
	private Long iddvcha;
	
	@Column(name = "diachi")
	private String diachi;
	@Column(name = "dienthoai")
	private String dienthoai;
	
	@Column(name = "email")
	private String email;
	@Column(name = "website")
	private String website;
	
	@Column(name = "ghichu")
	private String ghichu;
	@Column(name = "madv")
	private String madv;
	
	@Column(name = "madv_old")
	private String madv_old;
	@Column(name = "latochuckhcn")
	private Boolean latochuckhcn;
	
	@Column(name = "gioithieutochuc")
	private String gioithieutochuc;
	@Column(name = "fileanh")
	private String fileanh;
	
	@Column(name = "websitekhcn")
	private String websitekhcn;
	@Column(name = "ngay_tao_st")
	private Date ngay_tao_st;
	
	@Column(name = "ngay_cap_nhat_st")
	private Date ngay_cap_nhat_st;
	@Column(name = "trang_thai_st")
	private Long trang_thai_st;
	

}