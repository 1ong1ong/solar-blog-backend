package com.cxlsky.pojo.entity;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author cxl
 * @since 2020-02-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class BConfig implements Serializable {

private static final long serialVersionUID=1L;

    /**
     * 关于我，markdown正文
     */
    private String aboutMdContent;


}
