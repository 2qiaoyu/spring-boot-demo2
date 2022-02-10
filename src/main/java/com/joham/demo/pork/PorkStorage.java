package com.joham.demo.pork;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 猪肉库存的数据库实体类
 *
 * @author joham
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PorkStorage {

    private Long id;

    private Long cnt;
}
