package edu.kust.mylms.pojo.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageBean<T> {
    // 总条数
    private Integer total;
    // 当前页数据集合
    private List<T> items;
}
