package com.cs.redis.bean.bo;

import lombok.*;

/**
 * @author 陈帅
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@ToString
public class ShopNumBO {
    private Long count;
    private Long shopId;
}
