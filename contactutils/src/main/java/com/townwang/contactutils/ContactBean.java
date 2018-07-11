/*
 * Copyright © 文科中的技术宅
 * blog:https://www.townwang.com
 */
package com.townwang.contactutils;

import java.util.List;

/**
 * @author Town
 * @created at 2016/12/16 16:25
 * @Last Modified by: Town
 * @Last Modified time: 2016/12/16 16:25
 * @Remarks 联系人bean
 */

public class ContactBean {
    private String name;
    //支持多个电话号码
    private List<String> numbers;

    public List<String> getNumbers() {
        return numbers;
    }

    public void setNumbers(List<String> numbers) {
        this.numbers = numbers;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
