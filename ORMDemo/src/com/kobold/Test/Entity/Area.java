package com.kobold.Test.Entity;

public class Area {
	private int Id;
	private String Name;
	private String Code;
	private int ParentId;
	private String Path;
	private String CodeId;

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getCode() {
		return Code;
	}

	public void setCode(String code) {
		Code = code;
	}

	public int getParentId() {
		return ParentId;
	}

	public void setParentId(int parentId) {
		ParentId = parentId;
	}

	public String getPath() {
		return Path;
	}

	public void setPath(String path) {
		Path = path;
	}

	public String getCodeId() {
		return CodeId;
	}

	public void setCodeId(String codeId) {
		CodeId = codeId;
	}
}
