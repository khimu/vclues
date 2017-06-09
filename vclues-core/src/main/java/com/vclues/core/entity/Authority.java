package com.vclues.core.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

@Entity
@Table(name="authority")
public class Authority extends AbstractEntity {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String name;
	
	public Authority() {}
	
	public Authority(String name) {
		this.name = name;
	}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Authority authority = (Authority) o;

        if (!name.equals(authority.name)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}

}
