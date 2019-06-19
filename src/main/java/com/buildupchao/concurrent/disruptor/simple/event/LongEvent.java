package com.buildupchao.concurrent.disruptor.simple.event;

import lombok.Data;

/**
 * 声明一个Event用来传递数据
 * @author buildupchao
 * @date 2019/1/9
 */
@Data
public class LongEvent {
    private Long value;
}
