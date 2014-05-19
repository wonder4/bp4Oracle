package com.ebiz.bp_oracle.domain;

import java.io.Serializable;
import java.util.List;

import com.ebiz.ssi.domain.BaseDomain;

public class ModPopedom extends BaseDomain implements Serializable {

	private static final long serialVersionUID = -1L;

	private Long id;

	private Long mod_id;

	private Long user_id;

	private Long role_id;

	private String ppdm_code;

	List<ModPopedom> modPopedomList;

	public ModPopedom() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getMod_id() {
		return mod_id;
	}

	public void setMod_id(Long mod_id) {
		this.mod_id = mod_id;
	}

	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}

	public Long getRole_id() {
		return role_id;
	}

	public void setRole_id(Long role_id) {
		this.role_id = role_id;
	}

	public String getPpdm_code() {
		return ppdm_code;
	}

	public void setPpdm_code(String ppdmCode) {
		ppdm_code = ppdmCode;
	}

	public List<ModPopedom> getModPopedomList() {
		return modPopedomList;
	}

	public void setModPopedomList(List<ModPopedom> modPopedomList) {
		this.modPopedomList = modPopedomList;
	}

}