package com.buildupchao.concurrent.discover.research.domain;

import java.io.Serializable;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @author buildupchao
 * @date 2018/05/20
 * @since JDK1.8
 */
@Getter
@Setter
@AllArgsConstructor
public class User implements Serializable {

	private static final long serialVersionUID = 8067788212279433558L;

	private String userName;

	private String passWord;

	@Override
	public int hashCode() {
		return Objects.hash(userName, passWord);
	}

	public static User of(String userName, String passWord) {
		return new User(userName, passWord);
	}

	public Boolean validate() {
		return StringUtils.isNoneBlank(userName) && StringUtils.isNoneBlank(passWord);
	}
}
